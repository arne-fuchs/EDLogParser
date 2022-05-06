package de.paesserver;

import com.formdev.flatlaf.FlatDarkLaf;
import de.paesserver.frames.MainFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args){
        //Creating Frame
        try {
            UIManager.setLookAndFeel( new FlatDarkLaf());
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }
        MainFrame mainFrame = new MainFrame("EDLogParser");
        mainFrame.setSize(1420,900);
        mainFrame.setVisible(true);
    }
}
