package de.paesserver.frames;

import de.paesserver.journalLog.JournalLogParser;
import de.paesserver.journalLog.JournelLogInterpreter;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.util.HashMap;

public class LogFrame implements MenuListener {
    Container container;
    JournelLogInterpreter journalLogInterpreter;
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
        if(journalLogInterpreter == null){
            journalLogInterpreter = new JournelLogInterpreter(textAreaHashMap);
            Thread thread = new Thread(journalLogInterpreter);
            thread.start();
        }else{
            journalLogInterpreter.setHalt(false);
        }

        //Reset Button
        JButton resetButton =  new JButton("Reset Reader");
        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 3;

        resetButton.addActionListener(a -> {
            journalLogInterpreter.getJournelLogParser().stop();
            journalLogInterpreter = new JournalLogParser(textAreaHashMap);
            Thread thread = new Thread(journalLogInterpreter);
            thread.start();
        });

        container.add(resetButton,constraints);

    }

    public void stopThread(){
        journalLogInterpreter.stop();
    }

    public void haltThread(boolean b){
        journalLogInterpreter.setHalt(b);
    }


    @Override
    public void menuDeselected(MenuEvent e) {

    }

    @Override
    public void menuCanceled(MenuEvent e) {

    }
}
