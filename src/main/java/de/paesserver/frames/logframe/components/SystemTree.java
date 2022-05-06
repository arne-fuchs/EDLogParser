package de.paesserver.frames.logframe.components;

import de.paesserver.structure.SystemMutableTreeNode;
import de.paesserver.structure.body.BeltCluster;
import de.paesserver.structure.body.BodyMutableTreeNode;
import de.paesserver.structure.body.Planet;
import de.paesserver.structure.body.Star;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;
import java.awt.*;

public class SystemTree extends JTree {
    public static final DefaultMutableTreeNode systemTreeRootNode = new DefaultMutableTreeNode("Milky Way");;
    public SystemTree(){
        super(systemTreeRootNode);

        systemTreeRootNode.setAllowsChildren(true);

        //Click event on tree
        addTreeSelectionListener(event -> {
            TreePath treePath = event.getNewLeadSelectionPath();
            if(!(treePath.getLastPathComponent() instanceof BodyMutableTreeNode))
                return;
            BodyMutableTreeNode bodyMutableTreeNode = (BodyMutableTreeNode) treePath.getLastPathComponent();
            if(bodyMutableTreeNode.body instanceof Planet)
                BodyInfo.setTextForPlanet((Planet) bodyMutableTreeNode.body);
            else if (bodyMutableTreeNode.body instanceof Star)
                BodyInfo.setTextforStar((Star) bodyMutableTreeNode.body);
            else if (bodyMutableTreeNode.body instanceof BeltCluster)
                BodyInfo.setTextForBeltCluster((BeltCluster) bodyMutableTreeNode.body);
            else if (bodyMutableTreeNode.imaginaryBody != null)
                BodyInfo.setTextForImaginary(bodyMutableTreeNode.imaginaryBody);
        });

        //Set icons for tree
        setCellRenderer(new DefaultTreeCellRenderer(){
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
    }
}
