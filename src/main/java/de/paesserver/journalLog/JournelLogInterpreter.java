package de.paesserver.journalLog;

import javax.swing.*;
import java.util.HashMap;
import java.util.HashSet;

public class JournelLogInterpreter {

    private final HashMap<String,JTextArea> textAreaHashMap;
    private JournalLogParser journalLogParser;

    public JournelLogInterpreter(HashMap<String, JTextArea> textAreaHashMap){
        this.textAreaHashMap = textAreaHashMap;
    }

    public JournalLogParser getJournelLogParser(){
        return journalLogParser;
    }

}
