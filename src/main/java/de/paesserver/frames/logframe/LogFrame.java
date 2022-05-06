package de.paesserver.frames.logframe;

import de.paesserver.journalLog.JournalLogRunner;
import de.paesserver.structure.SystemMutableTreeNode;
import de.paesserver.structure.body.BeltCluster;
import de.paesserver.structure.body.BodyMutableTreeNode;
import de.paesserver.structure.body.Planet;
import de.paesserver.structure.body.Star;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;

import java.awt.*;
import java.io.File;

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
        GridBagConstraints constraints;

        //System info
        JTextArea systemInfo =  LogFrameComponentsSingleton.getSystemInfoTextArea();

        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 0;
        constraints.gridy = 1;
        container.add(systemInfo,constraints);



        //Planet Info
        JTextArea bodyInfo = LogFrameComponentsSingleton.getPlanetInfoTextArea();

        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.ipady = 40;
        constraints.gridx = 2;
        constraints.gridy = 1;
        container.add(bodyInfo,constraints);

        //Planets
        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.ipady = 40;
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridheight = 2;
        JTree tree = LogFrameComponentsSingleton.getSystemTree();

        container.add(tree,constraints);

        //Signals
        JTextArea nonBodiesOutput = LogFrameComponentsSingleton.getSignalTextArea();

        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.ipady = 40;
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridheight = 1;
        container.add(nonBodiesOutput,constraints);

        //Creating LogParser and giving it the textArea, where it can write into
        if(journalLogRunner == null){
            journalLogRunner = new JournalLogRunner();
            Thread thread = new Thread(journalLogRunner);
            thread.start();
        }else{
            journalLogRunner.setHalt(false);
        }


        //Reset Button
        JButton resetButton =  new JButton("Reset Reader");
        resetButton.setFont(LogFrameComponentsSingleton.globalFont);

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
