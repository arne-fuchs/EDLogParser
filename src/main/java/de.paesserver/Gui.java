package de.paesserver;

import javax.swing.*;
import java.awt.*;

public class Gui {
    public Gui(){
        //Creating Frame
        JFrame frame = new JFrame("EDLogParser");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,600);
        frame.setBackground(Color.BLACK);
        frame.setForeground(Color.WHITE);


        //Creating the MenuBar and adding components
        JMenuBar mb = new JMenuBar();
        JMenu logMenu = new JMenu("Logs");
        JMenu aboutMenu = new JMenu("About");
        JMenu helpMenu = new JMenu("Help");
        mb.add(logMenu);
        mb.add(aboutMenu);
        mb.add(helpMenu);
        mb.setBackground(Color.GRAY);
        mb.setForeground(Color.WHITE);

        //Create terminal for output
        JTextArea logOutput =  setJTextAreaStandards(new JTextArea());
        JTextArea bodiesOutput =  setJTextAreaStandards(new JTextArea());
        JTextArea nonBodiesOutput =  setJTextAreaStandards(new JTextArea());

        //Creating LogParser and giving it the textArea, where it can write into
        JournalLogParser journalLogParser = new JournalLogParser(logOutput,bodiesOutput,nonBodiesOutput);
        //Creating Button to read file
        JButton readFileButton = new JButton("Read Log");
        readFileButton.addActionListener(journalLogParser);
        readFileButton.setBackground(Color.GRAY);
        readFileButton.setForeground(Color.WHITE);
        //Adding components to the frame
        frame.getContentPane().add(BorderLayout.NORTH, mb);
        frame.getContentPane().add(BorderLayout.SOUTH,readFileButton);
        frame.getContentPane().add(BorderLayout.WEST,logOutput);
        frame.getContentPane().add(BorderLayout.CENTER,bodiesOutput);
        frame.getContentPane().add(BorderLayout.EAST,nonBodiesOutput);

        frame.setVisible(true);
    }

    private JTextArea setJTextAreaStandards(JTextArea jTextArea){
        jTextArea.setBackground(Color.BLACK);
        jTextArea.setEditable(false);
        jTextArea.setAutoscrolls(true);
        jTextArea.setForeground(Color.WHITE);
        return jTextArea;
    }
}
