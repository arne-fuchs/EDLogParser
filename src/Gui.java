import javax.swing.*;
import java.awt.*;

public class Gui {
    public Gui(){
        //Creating Frame
        JFrame frame = new JFrame("EDLogParser");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,600);


        //Creating the MenuBar and adding components
        JMenuBar mb = new JMenuBar();
        JMenu logMenu = new JMenu("Logs");
        JMenu aboutMenu = new JMenu("About");
        JMenu helpMenu = new JMenu("Help");
        mb.add(logMenu);
        mb.add(aboutMenu);
        mb.add(helpMenu);

        //Create terminal for output
        JTextArea logOutput = new JTextArea();
        //Creating LogParser and giving it the textArea, where it can write into
        JournalLogParser journalLogParser = new JournalLogParser(logOutput);
        //Creating Button to read file
        JButton readFileButton = new JButton("Read Log");
        readFileButton.addActionListener(journalLogParser);
        //Adding components to the frame
        frame.getContentPane().add(BorderLayout.NORTH, mb);
        frame.getContentPane().add(BorderLayout.SOUTH,readFileButton);
        frame.getContentPane().add(BorderLayout.CENTER,logOutput);


        frame.setVisible(true);
    }
}
