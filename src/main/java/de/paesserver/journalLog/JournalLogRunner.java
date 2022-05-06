package de.paesserver.journalLog;

import de.paesserver.frames.logframe.LogFrameComponentsSingleton;
import de.paesserver.frames.logframe.components.BodyInfo;
import de.paesserver.frames.logframe.components.SystemInfo;
import org.json.simple.JSONObject;

public class JournalLogRunner implements Runnable{

    private boolean stop = false;
    private boolean halt = false;
    public final JournalLogParser parser;
    public final JSONInterpreter interpreter;

    public JournalLogRunner() {
        parser = new JournalLogParser();
        interpreter = new JSONInterpreter();
        LogFrameComponentsSingleton.getSignalTextArea().setText("---SIGNALS---\t\t\t\t\t\t\n");

        LogFrameComponentsSingleton.getSystemInfoTextArea().setText("---SYSTEM---\n");
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
                            interpreter.computeJSONObject(jsonObject);
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
