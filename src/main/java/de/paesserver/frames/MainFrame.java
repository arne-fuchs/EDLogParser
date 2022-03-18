package de.paesserver.frames;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public MainFrame(String name){
        super(name);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addComponents(getContentPane());
        setVisible(true);
    }

    private void addComponents(Container container){
        container.setLayout(new GridBagLayout());

        //Creating the MenuBar and adding components
        JMenuBar menuBar = new JMenuBar();
        JMenu logMenu = new JMenu("Logs");
        logMenu.addMenuListener(new LogFrame(container));
        JMenu aboutMenu = new JMenu("About");
        aboutMenu.addActionListener(null);
        JMenu helpMenu = new JMenu("Help");
        helpMenu.addActionListener(null);
        menuBar.add(logMenu);
        menuBar.add(aboutMenu);
        menuBar.add(helpMenu);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1.0;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 3;
        container.add(menuBar,constraints);
    }
}