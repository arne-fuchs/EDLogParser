package de.paesserver.frames;

import de.paesserver.JournalLogParser;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogFrame extends GeneralFrame implements MenuListener {
    Container container;
    JournalLogParser journalLogParser;
    public LogFrame(Container container){
        this.container = container;
    }
    @Override
    public void menuSelected(MenuEvent e) {
        //Create terminal for output
        GridBagConstraints constraints;
        JTextArea logOutput =  setJTextAreaStandards(new JTextArea());
        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.ipady = 40;
        constraints.gridx = 0;
        constraints.gridy = 1;
        container.add(logOutput,constraints);

        JTextArea bodiesOutput =  setJTextAreaStandards(new JTextArea());
        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.ipady = 40;
        constraints.gridx = 1;
        constraints.gridy = 1;
        container.add(bodiesOutput,constraints);

        JTextArea nonBodiesOutput =  setJTextAreaStandards(new JTextArea());
        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.ipady = 40;
        constraints.gridx = 2;
        constraints.gridy = 1;
        container.add(nonBodiesOutput,constraints);
        //Creating LogParser and giving it the textArea, where it can write into
        if(journalLogParser == null){
            journalLogParser = new JournalLogParser(logOutput,bodiesOutput,nonBodiesOutput);
            Thread thread = new Thread(journalLogParser);
            thread.start();
        }else{
            journalLogParser.setHalt(false);
        }
    }

    public void stopThread(){
        journalLogParser.stop();
    }

    public void haltThread(boolean b){
        journalLogParser.setHalt(b);
    }


    @Override
    public void menuDeselected(MenuEvent e) {

    }

    @Override
    public void menuCanceled(MenuEvent e) {

    }
}
