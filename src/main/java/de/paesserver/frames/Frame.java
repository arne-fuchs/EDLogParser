package de.paesserver.frames;

import de.paesserver.JournalLogParser;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    public Frame(String name){
        super(name);
        setBackground(Color.BLACK);
        setForeground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addComponents(getContentPane());
        setVisible(true);
    }

    private void addComponents(Container container){
        container.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        //Creating the MenuBar and adding components
        JMenuBar mb = new JMenuBar();
        JMenu logMenu = new JMenu("Logs");
        logMenu.addMenuListener(new LogFrame(container));
        JMenu aboutMenu = new JMenu("About");
        aboutMenu.addActionListener(null);
        JMenu helpMenu = new JMenu("Help");
        helpMenu.addActionListener(null);
        mb.add(logMenu);
        mb.add(aboutMenu);
        mb.add(helpMenu);
        mb.setBackground(Color.GRAY);
        mb.setForeground(Color.WHITE);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1.0;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 3;
        container.add(mb,constraints);
    }
}
