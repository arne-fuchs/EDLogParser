package de.paesserver.frames.logframe;

import de.paesserver.frames.logframe.components.SystemTree;

import javax.swing.*;
import java.awt.*;

/**
 * Contains all components, which are visible in the log window
 */
public class LogFrameComponentsSingleton {

    public static Font globalFont = Font.getFont("Liberation Mono");

    private static JTextArea systemInfoTextArea;
    public static JTextArea getSystemInfoTextArea(){
        if(systemInfoTextArea == null){
            systemInfoTextArea = new JTextArea();
            systemInfoTextArea.setEditable(false);

            systemInfoTextArea.setPreferredSize(new Dimension(100,600));
            systemInfoTextArea.setMaximumSize(new Dimension(100,600));
            systemInfoTextArea.setMinimumSize(new Dimension(100,600));
            systemInfoTextArea.setFont(globalFont);

        }
        return systemInfoTextArea;
    }

    private static JTextArea planetInfoTextArea;
    public static JTextArea getPlanetInfoTextArea(){
        if(planetInfoTextArea == null){
            planetInfoTextArea =  new JTextArea();
            planetInfoTextArea.setAutoscrolls(true);
            planetInfoTextArea.setEditable(false);

            planetInfoTextArea.setPreferredSize(new Dimension(100,1));
            planetInfoTextArea.setMaximumSize(new Dimension(100,1));
            planetInfoTextArea.setMinimumSize(new Dimension(100,1));
            planetInfoTextArea.setSize(new Dimension(100,1));
            planetInfoTextArea.setFont(globalFont);
        }
        return planetInfoTextArea;
    }

    private static SystemTree systemTree;
    public static SystemTree getSystemTree(){
        if(systemTree == null){
            systemTree = new SystemTree();

            systemTree.setPreferredSize(new Dimension(250,1));
            systemTree.setMinimumSize(new Dimension(250,1));
            systemTree.setMaximumSize(new Dimension(250,1));
            systemTree.setFont(globalFont);
        }
        return systemTree;
    }

    private static JTextArea signalTextArea;
    public static JTextArea getSignalTextArea(){
        if(signalTextArea == null){
            signalTextArea =  new JTextArea();
            signalTextArea.setAutoscrolls(true);
            signalTextArea.setEditable(false);

            signalTextArea.setPreferredSize(new Dimension(100,1));
            signalTextArea.setMaximumSize(new Dimension(100,1));
            signalTextArea.setMinimumSize(new Dimension(100,1));
            signalTextArea.setSize(new Dimension(100,1));
            signalTextArea.setFont(globalFont);
        }
        return signalTextArea;
    }
}
