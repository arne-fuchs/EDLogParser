import com.formdev.flatlaf.FlatDarkLaf;
import de.paesserver.GlobalRegister;
import de.paesserver.frames.logframe.LogFrame;
import de.paesserver.frames.settingframe.Settings;

import javax.swing.*;
import java.awt.*;

public class EDLogParser {

    public static LogFrame logFrame;
    public static JFrame mainFrame;
    public static void main(String[] args){
        //Creating Frame
        Settings.initializeGlobalSettings();
        try {
            UIManager.setLookAndFeel(GlobalRegister.properties.getProperty("theme"));
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }
        GlobalRegister.getMainFrame();
    }
}
