import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

public class JournalLogParser implements ActionListener {

    private JTextArea logOutput;
    public JournalLogParser(JTextArea logOutput){
        this.logOutput = logOutput;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //TODO Read latest journalLog
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyddMMHHmmss");
            //TODO only for development this path
            File directory = new File("./ressources");
            System.out.println(Arrays.toString(Objects.requireNonNull(directory.list())));

            //Do not touch dangerous zone
            Optional<Date> optionalDate = Arrays.stream(Objects.requireNonNull(directory.list((dir, name) -> name.contains("Journal")))).map(s -> {
                try {
                    String parsedNumber = s.split("\\.")[1];
                    return simpleDateFormat.parse(parsedNumber);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
                return null;
            }).sorted().findFirst();

            if(optionalDate.isEmpty())
                throw new Exception("Couldn't find any log file");

            BufferedReader bufferedReader = new BufferedReader(new FileReader("./ressources/Journal." + simpleDateFormat.format(optionalDate.get()) + ".01.log"));
            String line;
            while((line = bufferedReader.readLine()) != null){
                //TODO Extract information that is needed
                logOutput.append(line);
                logOutput.append("\n");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
