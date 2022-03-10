package de.paesserver;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.swing.*;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

public class JournalLogParser implements Runnable {

    private boolean stop = false;
    private boolean halt = false;
    final SimpleDateFormat journalDateFormat = new SimpleDateFormat("yyMMddHHmmss");

    private final JTextArea logOutput,bodiesOutput,nonBodiesOutput;
    public JournalLogParser(JTextArea logOutput,JTextArea bodiesOutput,JTextArea nonBodiesOutput){
        this.logOutput = logOutput;
        this.bodiesOutput = bodiesOutput;
        this.nonBodiesOutput = nonBodiesOutput;
    }

    @Override
    public void run() {
        //TODO Read latest journalLog
        try {
            //TODO only for development this path
            File directory = new File("./");
            System.out.println(Arrays.toString(directory.list()));

            //Do not touch dangerous stream
            Optional<Date> optionalDate =
                    Arrays.stream(Objects.requireNonNull(directory.list((dir, name) -> name.contains("Journal"))))
                            .map(this::extractDate)
                            .max(Date::compareTo);
            if(optionalDate.isEmpty())
                throw new Exception("Couldn't find any log file");

            BufferedReader bufferedReader = new BufferedReader(new FileReader("./Journal." + journalDateFormat.format(optionalDate.get()) + ".01.log"));
            logOutput.setText("---LOG---\n");
            bodiesOutput.setText("---BODIES---\n");
            nonBodiesOutput.setText("---SIGNALS---\n");
            String jsonLine;
            while(!stop){
                try {
                    if((jsonLine = bufferedReader.readLine()) != null && !halt){
                        //TODO Extract information that is needed
                        JSONParser parser = new JSONParser();
                        JSONObject jsonObject = (JSONObject) parser.parse(jsonLine);
                        String event = (String) jsonObject.get("event");
                        switch (event){
                            case "Location":
                                //TODO Implement location
                                logOutput.append("StarSystem entered: " + jsonObject.get("StarSystem")+ "\n");
                                break;
                            case "StartJump":
                                //TODO Implement StartJump
                                logOutput.append("Jump has been initialised"+ "\n");
                                break;
                            case "FSDJump":
                                //TODO Implement FSDJump
                                logOutput.setText("---LOG---\n");
                                bodiesOutput.setText("---BODIES---\n");
                                nonBodiesOutput.setText("---SIGNALS---\n");
                                logOutput.append("FSDJump has been initialised"+ "\n");
                                break;

                            //SCAN ACTIVITIES
                            case "FSSDiscoveryScan":
                                //TODO Implement FSSDiscoveryScan
                                logOutput.append("Discovery-Scan has been initialised\n");
                                bodiesOutput.append("Bodies: " + jsonObject.get("BodyCount").toString() + "\n");
                                nonBodiesOutput.append("Non-bodies: " + jsonObject.get("NonBodyCount").toString() + "\n");
                                break;
                            case "FSSAllBodiesFound":
                                //TODO Implement FSSAllBodiesFound
                                logOutput.append("All bodies discovered"+ "\n");
                                break;
                            case "Scan":
                                //TODO Implement Scan
                                bodiesOutput.append(jsonObject.get("BodyName") + "\n");
                                break;
                            case "FSSSignalDiscovered":
                                //TODO Implement FSSSignalDiscovered
                                if(jsonObject.containsKey("SignalName_Localised"))
                                    nonBodiesOutput.append(jsonObject.get("SignalName_Localised")+"\n");
                                else
                                    nonBodiesOutput.append(jsonObject.get("SignalName")+"\n");
                                break;
                            default:
                        }
                    }
                }catch (Exception ignore){}
            }
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
