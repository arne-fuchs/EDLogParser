package de.paesserver.journalLog;

import de.paesserver.GlobalRegister;
import org.json.simple.JSONObject;

import javax.swing.*;
import java.util.Arrays;

public class JournalLogRunner implements Runnable{

    private boolean stop = false;
    private boolean halt = false;
    public final JournalLogParser parser;
    public final JSONInterpreter interpreter;
    long timeout = Long.parseLong(GlobalRegister.properties.getProperty("journalLogReaderInterval"));

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
                        //If -1 is thrown a wrong directory has been given
                        if(line.equals("-1"))
                            return;

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
                    this.wait(timeout);
                }
            }
            parser.closeReader();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage() + "\n" + Arrays.toString(ex.getStackTrace()));
        }
    }

    public void stop() {
        this.stop = true;
    }

    public void setHalt(Boolean b){
        halt = b;
    }
}
