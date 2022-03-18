package de.paesserver.journalLog;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.swing.*;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class JournalLogParser implements Runnable {

    private boolean stop = false;
    private boolean halt = false;
    final SimpleDateFormat journalDateFormat = new SimpleDateFormat("yyMMddHHmmss");

    private final HashMap<String,JTextArea> textAreaHashMap;
    public JournalLogParser(HashMap<String,JTextArea> textAreaHashMap){
        this.textAreaHashMap = textAreaHashMap;
    }

    @Override
    public void run() {
        //TODO Read latest journalLog
        try {
            //TODO only for development this path
            File directory = new File("./");

            //Do not touch dangerous stream
            Optional<Date> optionalDate =
                    Arrays.stream(Objects.requireNonNull(directory.list((dir, name) -> name.contains("Journal"))))
                            .map(this::extractDate)
                            .max(Date::compareTo);
            if(optionalDate.isEmpty())
                throw new Exception("Couldn't find any log file");

            //setup reader to read latest log
            BufferedReader bufferedReader = new BufferedReader(new FileReader("./Journal." + journalDateFormat.format(optionalDate.get()) + ".01.log"));

            textAreaHashMap.get("logOutput").setText("---LOG---\t\t\t\t\t\t\n");
            textAreaHashMap.get("bodiesOutput").setText("---BODIES---\t\t\t\t\t\t\n");
            textAreaHashMap.get("nonBodiesOutput").setText("---SIGNALS---\t\t\t\t\t\t\n");

            SystemInfo systemInfo = new SystemInfo(textAreaHashMap.get("systemInfo"));
            systemInfo.updateText();

            String jsonLine;
            while(!stop){
                try {
                    if((jsonLine = bufferedReader.readLine()) != null && !halt){
                        //TODO Extract information that is needed
                        JSONParser parser = new JSONParser();
                        JSONObject jsonObject = (JSONObject) parser.parse(jsonLine);
                        String event = (String) jsonObject.get("event");
                        switch (event){
                            //Will be called by loading the game
                            case "Location":
                            case "FSDJump":
                                //TODO Implement location
                                textAreaHashMap.get("logOutput").setText("---LOG---\t\t\t\t\t\t\n");
                                textAreaHashMap.get("bodiesOutput").setText("---BODIES---\t\t\t\t\t\t\n");
                                textAreaHashMap.get("nonBodiesOutput").setText("---SIGNALS---\t\t\t\t\t\t\n");
                                systemInfo.resetSuffix();

                                systemInfo.systemName.suffix = (String) jsonObject.get("StarSystem");
                                systemInfo.allegiance.suffix = (String) jsonObject.get("SystemAllegiance");
                                systemInfo.economy.suffix = (String) jsonObject.get("SystemEconomy_Localised");
                                systemInfo.secondEconomy.suffix = (String) jsonObject.get("SystemSecondEconomy_Localised");
                                systemInfo.government.suffix = (String) jsonObject.get("SystemGovernment_Localised");
                                systemInfo.security.suffix = (String) jsonObject.get("SystemSecurity_Localised");
                                systemInfo.population.suffix = jsonObject.get("Population").toString();
                                systemInfo.updateText();
                                break;
                            case "StartJump":
                                //TODO Implement StartJump
                                textAreaHashMap.get("logOutput").append("Jump has been initialised"+ "\n");
                                break;

                            //SCAN ACTIVITIES
                            case "FSSDiscoveryScan":
                                //TODO Implement FSSDiscoveryScan
                                textAreaHashMap.get("logOutput").append("Discovery-Scan has been initialised\n");
                                systemInfo.bodiesCount.suffix = jsonObject.get("BodyCount").toString();
                                systemInfo.nonBodiesCount.suffix = jsonObject.get("NonBodyCount").toString();
                                systemInfo.updateText();
                                break;
                            case "FSSAllBodiesFound":
                                //TODO Implement FSSAllBodiesFound
                                textAreaHashMap.get("logOutput").append("All bodies discovered"+ "\n");
                                break;
                            case "Scan":
                                //TODO Implement Scan
                                textAreaHashMap.get("bodiesOutput").append(jsonObject.get("BodyName") + "\n");
                                break;
                            case "FSSSignalDiscovered":
                                //TODO Implement FSSSignalDiscovered
                                if(jsonObject.containsKey("SignalName_Localised"))
                                    textAreaHashMap.get("nonBodiesOutput").append(jsonObject.get("SignalName_Localised")+"\n");
                                else
                                    textAreaHashMap.get("nonBodiesOutput").append(jsonObject.get("SignalName")+"\n");
                                break;
                            default:
                        }
                    }
                }catch (Exception e){System.out.println(e.getMessage());}
                synchronized (this) {
                    this.wait(100);
                }
            }
            bufferedReader.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Date extractDate(String dateString){
        try {
            return journalDateFormat.parse(dateString.split("\\.")[1]);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void stop() {
        this.stop = true;
    }

    public void setHalt(Boolean b){
        halt = b;
    }
}