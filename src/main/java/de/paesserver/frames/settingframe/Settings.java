package de.paesserver.frames.settingframe;

import de.paesserver.GlobalRegister;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

public class Settings {
    public static void initializeGlobalSettings(){
        try {
            GlobalRegister.properties = readPropertiesFile("settings.properties");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
        }
    }
    public static Properties readPropertiesFile(String fileName) throws IOException {
        Properties properties = null;
        try (FileInputStream fileInputStream = new FileInputStream(fileName)){
            properties = new java.util.Properties();
            properties.load(fileInputStream);
        }
        return properties;
    }

    public static void saveToPropertiesFile(Properties properties){
        try (FileOutputStream fileOutputStream = new FileOutputStream("settings.properties")){
            properties.store(fileOutputStream,null);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
        }
    }
}
