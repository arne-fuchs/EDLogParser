package de.paesserver;

import de.paesserver.frames.aboutframe.AboutFrame;
import de.paesserver.frames.logframe.LogFrame;
import de.paesserver.frames.settingframe.SettingFrame;

import javax.swing.*;
import java.util.Properties;

public class GlobalRegister {
    private static JFrame mainFrame;

    public static Properties properties;
    public static JFrame getMainFrame(){
        if(mainFrame == null){
            mainFrame = new JFrame("EDLogParser");
            AboutFrame aboutFrame = new AboutFrame();
            mainFrame.setContentPane(aboutFrame.mainPane);
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.setIconImage(new ImageIcon("org.edassets/materials/Basic.png").getImage());
            mainFrame.setSize(1920,1080);
            mainFrame.setVisible(true);
        }
        return mainFrame;
    }

    public static void initializeMenuBar(JButton logButton, JButton settingButton, JButton aboutButton){
        logButton.addActionListener(e -> {
            mainFrame.setContentPane(getLogFrameInstance().mainPane);
            mainFrame.setVisible(true);
        });
        settingButton.addActionListener(e -> {
            mainFrame.setContentPane(getSettingFrameInstance().mainPane);
            mainFrame.setVisible(true);
        });
        aboutButton.addActionListener(e -> {
            mainFrame.setContentPane(getAboutFrameInstance().mainPane);
            mainFrame.setVisible(true);
        });
    }

    private static LogFrame logFrame;
    private static SettingFrame settingFrame;
    private static AboutFrame aboutFrame;

    public static LogFrame getLogFrameInstance(){
        if(logFrame == null)
            logFrame = new LogFrame();
        return logFrame;
    }
    public static SettingFrame getSettingFrameInstance(){
        if(settingFrame == null)
            settingFrame = new SettingFrame();
        return settingFrame;
    }
    public static AboutFrame getAboutFrameInstance(){
        if(aboutFrame == null)
            aboutFrame = new AboutFrame();
        return aboutFrame;
    }
}
