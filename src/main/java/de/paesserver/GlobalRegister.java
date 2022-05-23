package de.paesserver;

import de.paesserver.frames.logframe.LogFrame;
import de.paesserver.frames.settingframe.SettingFrame;

import javax.swing.*;
import java.awt.*;
import java.util.Properties;

public class GlobalRegister {
    private static JFrame mainFrame;

    public static Properties properties;
    public static JFrame getMainFrame(){
        if(mainFrame == null){
            mainFrame = new JFrame("EDLogParser");
            SettingFrame settingFrame = new SettingFrame();
            mainFrame.setContentPane(settingFrame.mainPane);
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.setIconImage(new ImageIcon("org.edassets/materials/Basic.png").getImage());
            mainFrame.setSize(1600,800);
            mainFrame.setVisible(true);
        }
        return mainFrame;
    }

    public static void initializeMenuBar(JButton logButton, JButton settingButton){
        logButton.addActionListener(e -> {
            LogFrame logFrame = new LogFrame();
            mainFrame.setContentPane(logFrame.mainPane);
            mainFrame.setVisible(true);
        });
        settingButton.addActionListener(e -> {
            SettingFrame settingFrame = new SettingFrame();
            mainFrame.setContentPane(settingFrame.mainPane);
            mainFrame.setVisible(true);
        });
    }
}
