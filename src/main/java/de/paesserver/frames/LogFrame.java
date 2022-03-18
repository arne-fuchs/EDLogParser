package de.paesserver.frames;

import de.paesserver.journalLog.JournalLogParser;
import de.paesserver.journalLog.JSONInterpreter;
import de.paesserver.journalLog.JournalLogRunner;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;

public class LogFrame implements MenuListener {
    Container container;
    JournalLogRunner journalLogRunner;
    public LogFrame(Container container){
        this.container = container;
    }

    @Override
    public void menuSelected(MenuEvent e) {
        //Create terminal for output
        HashMap<String,JTextArea> textAreaHashMap = new HashMap<>();
        GridBagConstraints constraints;

        //System info
        JTextArea systemInfo =  new JTextArea();
        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.ipady = 40;
        constraints.gridx = 0;
        constraints.gridy = 1;
        container.add(systemInfo,constraints);
        textAreaHashMap.put("systemInfo",systemInfo);

        //Log
        JTextArea logOutput =  new JTextArea();
        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.ipady = 40;
        constraints.gridx = 0;
        constraints.gridy = 2;
        container.add(logOutput,constraints);
        textAreaHashMap.put("logOutput",logOutput);

        //Planets
        JTextArea bodiesOutput =  new JTextArea();
        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.ipady = 40;
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridheight = 2;
        container.add(bodiesOutput,constraints);
        textAreaHashMap.put("bodiesOutput",bodiesOutput);

        //Signals
        JTextArea nonBodiesOutput =  new JTextArea();
        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.ipady = 40;
        constraints.gridx = 2;
        constraints.gridy = 1;
        constraints.gridheight = 2;
        container.add(nonBodiesOutput,constraints);
        textAreaHashMap.put("nonBodiesOutput",nonBodiesOutput);

        //Creating LogParser and giving it the textArea, where it can write into
        if(journalLogRunner == null){
            journalLogRunner = new JournalLogRunner(textAreaHashMap);
            Thread thread = new Thread(journalLogRunner);
            thread.start();
        }else{
            journalLogRunner.setHalt(false);
        }

        //Reset Button
        JButton resetButton =  new JButton("Reset Reader");
        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 3;

        resetButton.addActionListener(a -> {
            try {
                File log = journalLogRunner.parser.getLatestLogInWorkingDirectory();
                journalLogRunner.setHalt(true);
                journalLogRunner.parser.closeReader();
                if(journalLogRunner.parser.setBufferedReaderForFile(log))
                    journalLogRunner.setHalt(false);
                else
                    throw new Exception("There was an error starting the buffered reader");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        container.add(resetButton,constraints);

    }

    public void stopThread(){
        journalLogRunner.stop();
    }

    public void haltThread(boolean b){
        journalLogRunner.setHalt(b);
    }


    @Override
    public void menuDeselected(MenuEvent e) {

    }

    @Override
    public void menuCanceled(MenuEvent e) {

    }
}
