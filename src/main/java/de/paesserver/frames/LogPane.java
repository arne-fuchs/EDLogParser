package de.paesserver.frames;

import de.paesserver.journalLog.JournalLogRunner;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.io.File;
import java.util.HashMap;

public class LogPane implements MenuListener {
    Container container;
    JournalLogRunner journalLogRunner;
    public LogPane(Container container){
        this.container = container;
    }

    @Override
    public void menuSelected(MenuEvent e) {
        //Create terminal for output
        HashMap<String,Component> textAreaHashMap = new HashMap<>();
        GridBagConstraints constraints;

        //System info
        JTextArea systemInfo =  new JTextArea();
        systemInfo.setEditable(false);
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
        logOutput.setAutoscrolls(true);
        logOutput.setEditable(false);
        logOutput.setPreferredSize(new Dimension(1,1));
        logOutput.setMaximumSize(new Dimension(1,1));
        logOutput.setMinimumSize(new Dimension(1,1));
        logOutput.setSize(new Dimension(1,1));
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
        DefaultMutableTreeNode system = new DefaultMutableTreeNode("System");
        system.setAllowsChildren(true);
        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.ipady = 40;
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridheight = 2;
        JTree tree = new JTree(system);
        container.add(tree,constraints);
        textAreaHashMap.put("bodiesOutput",tree);

        //Signals
        JTextArea nonBodiesOutput =  new JTextArea();
        nonBodiesOutput.setAutoscrolls(true);
        nonBodiesOutput.setEditable(false);
        nonBodiesOutput.setPreferredSize(new Dimension(1,1));
        nonBodiesOutput.setMaximumSize(new Dimension(1,1));
        nonBodiesOutput.setMinimumSize(new Dimension(1,1));
        nonBodiesOutput.setSize(new Dimension(1,1));
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
            journalLogRunner = new JournalLogRunner(textAreaHashMap,system);
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