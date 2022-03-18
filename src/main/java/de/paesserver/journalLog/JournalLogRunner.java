package de.paesserver.journalLog;

import org.json.simple.JSONObject;

import javax.swing.*;
import java.util.*;

public class JournalLogRunner implements Runnable{

    private boolean stop = false;
    private boolean halt = false;

    private final HashMap<String, JTextArea> textAreaHashMap;
    public final JournalLogParser parser;
    public final JSONInterpreter interpreter;

    private SystemInfo systemInfo;

    public JournalLogRunner(HashMap<String, JTextArea> textAreaHashMap) {
        this.textAreaHashMap = textAreaHashMap;
        parser = new JournalLogParser();
        interpreter = new JSONInterpreter(textAreaHashMap);
        initializeTextAreas();
    }
    public JournalLogRunner(HashMap<String, JTextArea> textAreaHashMap, String directoryPath) {
        this.textAreaHashMap = textAreaHashMap;
        parser = new JournalLogParser(directoryPath);
        interpreter = new JSONInterpreter(textAreaHashMap);
        initializeTextAreas();
    }

    private void initializeTextAreas(){
        textAreaHashMap.get("logOutput").setText("---LOG---\t\t\t\t\t\t\n");
        textAreaHashMap.get("bodiesOutput").setText("---BODIES---\t\t\t\t\t\t\n");
        textAreaHashMap.get("nonBodiesOutput").setText("---SIGNALS---\t\t\t\t\t\t\n");

        systemInfo = new SystemInfo(textAreaHashMap.get("systemInfo"));
        systemInfo.updateText();
    }

    @Override
    public void run() {
        try {
            while(!stop){
                if(!halt){
                    String line = parser.getNextLine();
                    //Check if new line is available
                    if(line != null) {
                        JSONObject jsonObject = JSONInterpreter.extractJSONObjectFromString(line);
                        //check if line is valid (it should be since in journal logs, there only is json data)
                        if(jsonObject != null)
                            interpreter.computeJSONObject(jsonObject, systemInfo);
                        else
                            System.out.println("Invalid JSON line found: " + line);
                    }
                }
                synchronized (this) {
                    this.wait(100);
                }
            }
            parser.closeReader();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void stop() {
        this.stop = true;
    }

    public void setHalt(Boolean b){
        halt = b;
    }
}
