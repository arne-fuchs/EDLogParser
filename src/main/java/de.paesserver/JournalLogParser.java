package de.paesserver;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

public class JournalLogParser implements ActionListener {

    private final JTextArea logOutput,bodiesOutput,nonBodiesOutput;
    public JournalLogParser(JTextArea logOutput,JTextArea bodiesOutput,JTextArea nonBodiesOutput){
        this.logOutput = logOutput;
        this.bodiesOutput = bodiesOutput;
        this.nonBodiesOutput = nonBodiesOutput;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //TODO Read latest journalLog
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyddMMHHmmss");
            //TODO only for development this path
            File directory = new File("./");
            System.out.println(Arrays.toString(directory.list()));

            //Do not touch dangerous stream
            Optional<Date> optionalDate = Arrays.stream(Objects.requireNonNull(directory.list((dir, name) -> name.contains("Journal")))).map(this::extractDate).sorted().findFirst();

            if(optionalDate.isEmpty())
                throw new Exception("Couldn't find any log file");

            BufferedReader bufferedReader = new BufferedReader(new FileReader("./Journal." + simpleDateFormat.format(optionalDate.get()) + ".01.log"));
            logOutput.setText("---LOG---\n");
            bodiesOutput.setText("---BODIES---\n");
            nonBodiesOutput.setText("---SIGNALS---\n");
            String jsonLine;
            while((jsonLine = bufferedReader.readLine()) != null){
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
                        logOutput.append("FSDJump has been initialised"+ "\n");
                        break;

                        //SCAN ACTIVITIES
                    case "FSSDiscoveryScan":
                        //TODO Implement FSSDiscoveryScan
                        logOutput.append("Discovery-Scan has been initialised\n");
                        bodiesOutput.append("Bodies: " + (String) jsonObject.get("BodyCount")+ "\n");
                        nonBodiesOutput.append("Non-bodies: " + (String) jsonObject.get("NonBodyCount")+ "\n");
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
                    default: continue;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Date extractDate(String dateString){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyddMMHHmmss");
        try {
            return simpleDateFormat.parse(dateString.split("\\.")[1]);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
