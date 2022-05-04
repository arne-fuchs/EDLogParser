package de.paesserver.frames;

import de.paesserver.journalLog.JournalLogRunner;
import de.paesserver.structure.SystemMutableTreeNode;
import de.paesserver.structure.body.BeltCluster;
import de.paesserver.structure.body.BodyMutableTreeNode;
import de.paesserver.structure.body.Planet;
import de.paesserver.structure.body.Star;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
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
        HashMap<String,Component> textAreaHashMap = new HashMap<>();
        GridBagConstraints constraints;

        //System info
        JTextArea systemInfo =  new JTextArea();
        systemInfo.setEditable(false);
        systemInfo.setPreferredSize(new Dimension(100,600));
        systemInfo.setMaximumSize(new Dimension(100,600));
        systemInfo.setMinimumSize(new Dimension(100,600));
        systemInfo.setFont(Font.getFont("Liberation Mono"));

        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 0;
        constraints.gridy = 1;
        container.add(systemInfo,constraints);
        textAreaHashMap.put("systemInfo",systemInfo);



        //Planet Info
        JTextArea bodyInfo =  new JTextArea();
        bodyInfo.setAutoscrolls(true);
        bodyInfo.setEditable(false);

        bodyInfo.setPreferredSize(new Dimension(100,1));
        bodyInfo.setMaximumSize(new Dimension(100,1));
        bodyInfo.setMinimumSize(new Dimension(100,1));
        bodyInfo.setSize(new Dimension(100,1));
        bodyInfo.setFont(Font.getFont("Liberation Mono"));

        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.ipady = 40;
        constraints.gridx = 2;
        constraints.gridy = 1;
        container.add(bodyInfo,constraints);
        textAreaHashMap.put("bodyInfo",bodyInfo);

        //Planets
        DefaultMutableTreeNode system = new DefaultMutableTreeNode("Milky Way");
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
        tree.setPreferredSize(new Dimension(250,1));
        tree.setMinimumSize(new Dimension(250,1));
        tree.setMaximumSize(new Dimension(250,1));
        tree.setFont(Font.getFont("Liberation Mono"));

        //event listener for clicking on a planet
        tree.addTreeSelectionListener(event -> {

            TreePath treePath = event.getNewLeadSelectionPath();
            if(!(treePath.getLastPathComponent() instanceof BodyMutableTreeNode))
                return;
            BodyMutableTreeNode bodyMutableTreeNode = (BodyMutableTreeNode) treePath.getLastPathComponent();
            if(bodyMutableTreeNode.body instanceof Planet)
                journalLogRunner.bodyPane.setTextForPlanet((Planet) bodyMutableTreeNode.body);
            else if (bodyMutableTreeNode.body instanceof Star)
                journalLogRunner.bodyPane.setTextforStar((Star) bodyMutableTreeNode.body);
                else if (bodyMutableTreeNode.body instanceof BeltCluster)
                        journalLogRunner.bodyPane.setTextForBeltCluster((BeltCluster) bodyMutableTreeNode.body);
                    else if (bodyMutableTreeNode.imaginaryBody != null)
                        journalLogRunner.bodyPane.setTextForImaginary(bodyMutableTreeNode.imaginaryBody);
        });

        //Set icons in tree
        tree.setCellRenderer(new DefaultTreeCellRenderer(){
            final private ImageIcon galaxyIcon = new ImageIcon(new ImageIcon("org.edassets/galaxy-map/Realistic-galaxy-map.png").getImage().getScaledInstance(15,15,java.awt.Image.SCALE_SMOOTH));
            final private ImageIcon systemIcon = new ImageIcon(new ImageIcon("de.paesserver/galaxy-map/orrery_map.png").getImage().getScaledInstance(15,15,java.awt.Image.SCALE_SMOOTH));
            final private ImageIcon planetIcon = new ImageIcon(new ImageIcon("org.edassets/galaxy-map/planet.png").getImage().getScaledInstance(15,15,java.awt.Image.SCALE_SMOOTH));
            final private ImageIcon markerIcon = new ImageIcon(new ImageIcon("org.edassets/galaxy-map/Marker-galaxy-map.png").getImage().getScaledInstance(15,15,java.awt.Image.SCALE_SMOOTH));

            final private ImageIcon starIcon = new ImageIcon(new ImageIcon("de.paesserver/galaxy-map/star.png").getImage().getScaledInstance(15,15,java.awt.Image.SCALE_SMOOTH));

            final private ImageIcon asteroidIcon = new ImageIcon(new ImageIcon("de.paesserver/galaxy-map/asteroids.png").getImage().getScaledInstance(15,15,java.awt.Image.SCALE_SMOOTH));

            @Override
            public Component getTreeCellRendererComponent(JTree tree,
                                                          Object value, boolean selected, boolean expanded,
                                                          boolean leaf, int row, boolean hasFocus) {
                super.getTreeCellRendererComponent(tree, value, selected,expanded, leaf, row, hasFocus);
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
                ImageIcon icon = null;
                if (node.equals(tree.getModel().getRoot())) {
                    icon = galaxyIcon;
                } else if (node instanceof SystemMutableTreeNode) {
                    icon = systemIcon;
                } else if(node instanceof BodyMutableTreeNode){
                    BodyMutableTreeNode bodyMutableTreeNode = (BodyMutableTreeNode) node;
                    if(bodyMutableTreeNode.body instanceof Star)
                        icon = starIcon;
                    else if (bodyMutableTreeNode.body instanceof Planet)
                        icon = planetIcon;
                    else if (bodyMutableTreeNode.body instanceof BeltCluster)
                        icon = asteroidIcon;
                }
                else {
                    icon = markerIcon;
                }
                setIcon(icon);
                return this;
            }

        });
        container.add(tree,constraints);
        textAreaHashMap.put("bodiesOutput",tree);

        //Signals
        JTextArea nonBodiesOutput =  new JTextArea();
        nonBodiesOutput.setAutoscrolls(true);
        nonBodiesOutput.setEditable(false);

        nonBodiesOutput.setPreferredSize(new Dimension(100,1));
        nonBodiesOutput.setMaximumSize(new Dimension(100,1));
        nonBodiesOutput.setMinimumSize(new Dimension(100,1));
        nonBodiesOutput.setSize(new Dimension(100,1));
        nonBodiesOutput.setFont(Font.getFont("Liberation Mono"));


        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.ipady = 40;
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridheight = 1;
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
        resetButton.setFont(Font.getFont("Liberation Mono"));

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
