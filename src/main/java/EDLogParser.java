import com.formdev.flatlaf.FlatDarkLaf;
import de.paesserver.frames.logframe.LogFrame;

import javax.swing.*;
import java.awt.*;

public class EDLogParser {

    public static LogFrame logFrame;
    public static void main(String[] args){
        //Creating Frame

        try {
            UIManager.setLookAndFeel( new FlatDarkLaf());
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }
        JFrame frame = new JFrame("EDLogParser");
        logFrame = new LogFrame();
        frame.setContentPane(logFrame.mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(new ImageIcon("org.edassets/materials/Basic.png").getImage());
        frame.setSize(1600,800);
        frame.setFont(Font.getFont("Liberation Mono"));
        frame.setVisible(true);

    }
}
