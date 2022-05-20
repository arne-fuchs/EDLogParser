package de.paesserver.journalLog;

import org.json.simple.JSONObject;

public class JournalLogRunner implements Runnable{

    private boolean stop = false;
    private boolean halt = false;
    public final JournalLogParser parser;
    public final JSONInterpreter interpreter;

    public JournalLogRunner(JournalLogParser parser, JSONInterpreter interpreter) {
        this.parser = parser;
        this.interpreter = interpreter;
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
                    this.wait(10);
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
