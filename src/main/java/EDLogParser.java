import com.formdev.flatlaf.FlatDarkLaf;
import de.paesserver.GlobalRegister;
import de.paesserver.frames.logframe.LogFrame;
import de.paesserver.frames.settingframe.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.io.File;
import java.util.Hashtable;
import java.util.Properties;

public class EDLogParser {

    public static LogFrame logFrame;
    public static JFrame mainFrame;
    public static void main(String[] args){
        //Creating Frame
        Settings.initializeGlobalSettings();
        try {
            UIManager.setLookAndFeel(GlobalRegister.properties.getProperty("theme"));

            Hashtable<TextAttribute, Object> m = new Hashtable<>();
            m.put(TextAttribute.FAMILY, GlobalRegister.properties.getProperty("font"));
            m.put(TextAttribute.SIZE, Integer.parseInt(GlobalRegister.properties.getProperty("fontSize")));
            m.put(TextAttribute.KERNING, TextAttribute.KERNING_ON);
            Font font = Font.getFont(m);
            UIManager.getLookAndFeelDefaults().put("defaultFont", font);
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }
        GlobalRegister.getMainFrame();
    }
}
