package de.paesserver.journalLog;

import de.paesserver.frames.BodyPane;
import de.paesserver.frames.SystemPane;
import org.json.simple.JSONObject;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.util.*;

public class JournalLogRunner implements Runnable{

    private boolean stop = false;
    private boolean halt = false;

    private final HashMap<String, Component> componentHashMap;
    public final JournalLogParser parser;
    public final JSONInterpreter interpreter;

    public SystemPane systemPane;
    public BodyPane bodyPane;

    public JournalLogRunner(HashMap<String, Component> componentHashMap,DefaultMutableTreeNode defaultMutableTreeNode) {
        this.componentHashMap = componentHashMap;
        parser = new JournalLogParser();
        interpreter = new JSONInterpreter(componentHashMap);
        //((JTextArea)componentHashMap.get("logOutput")).setText("---LOG---\t\t\t\t\t\t\n");
        ((JTextArea)componentHashMap.get("nonBodiesOutput")).setText("---SIGNALS---\t\t\t\t\t\t\n");

        systemPane = new SystemPane((JTextArea) componentHashMap.get("systemInfo"),defaultMutableTreeNode);
        systemPane.updateText();

        bodyPane = new BodyPane((JTextArea) componentHashMap.get("bodyInfo"));
    }
    public JournalLogRunner(HashMap<String, Component> componentHashMap, DefaultMutableTreeNode defaultMutableTreeNode, String directoryPath) {
        this.componentHashMap = componentHashMap;
        parser = new JournalLogParser(directoryPath);
        interpreter = new JSONInterpreter(componentHashMap);
        //((JTextArea)componentHashMap.get("logOutput")).setText("---LOG---\t\t\t\t\t\t\n");
        ((JTextArea)componentHashMap.get("nonBodiesOutput")).setText("---SIGNALS---\t\t\t\t\t\t\n");

        systemPane = new SystemPane((JTextArea) componentHashMap.get("systemInfo"),defaultMutableTreeNode);
        systemPane.updateText();

        bodyPane = new BodyPane((JTextArea) componentHashMap.get("bodyInfo"));
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
                        if(jsonObject != null){
                            interpreter.computeJSONObject(jsonObject, systemPane, bodyPane);
                        }
                        else
                            java.lang.System.out.println("Invalid JSON line found: " + line);
                    }
                }
                synchronized (this) {
                    this.wait(30);
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
