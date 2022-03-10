package de.paesserver.frames;

import javax.swing.*;
import java.awt.*;

public abstract class GeneralFrame {
    public static JTextArea setJTextAreaStandards(JTextArea jTextArea){
        jTextArea.setBackground(Color.BLACK);
        jTextArea.setEditable(false);
        jTextArea.setAutoscrolls(true);
        jTextArea.setForeground(Color.WHITE);
        return jTextArea;
    }
}
