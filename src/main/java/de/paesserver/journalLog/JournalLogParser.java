package de.paesserver.journalLog;


import de.paesserver.GlobalRegister;
import org.json.simple.JSONObject;

import javax.swing.*;
import java.io.*;
import java.rmi.UnexpectedException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JournalLogParser{

    final SimpleDateFormat journalDateFormat = new SimpleDateFormat("yyyy-MM-dd-HHmmss");
    final SimpleDateFormat prefixJournalDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    final SimpleDateFormat suffixJournalDateFormat = new SimpleDateFormat("HHmmss");
    private BufferedReader bufferedReader;
    String directoryPath = GlobalRegister.properties.getProperty("journalLogPath");

    /**
     * Reads the latest log in the current working directory
     */
    public JournalLogParser() {
        try {
            bufferedReader = new BufferedReader(new FileReader(getLatestLogInWorkingDirectory()));
        }catch (Exception e){
            e.printStackTrace();
        }

        Database database = DatabaseSingleton.getInstance();
        ExecutorService exec = Executors.newFixedThreadPool(Integer.parseInt(GlobalRegister.properties.getProperty("journalLogCoreCount")));
        long timeout = Long.parseLong(GlobalRegister.properties.getProperty("journalLogUpdaterInterval"));
        try {
            File directory = new File(directoryPath);

            String[] filenames = directory.list((dir, name) -> name.startsWith("Journal") && name.contains("T"));
            assert filenames != null;
            for(String filename : filenames){
                exec.submit(() -> {
                    File log = new File(directoryPath+"/"+filename);
                    try {
                        BufferedReader reader = new BufferedReader(new FileReader(log));
                        String line;
                        while((line = reader.readLine()) != null && line.length() > 0){
                            JSONObject jsonObject = JSONInterpreter.extractJSONObjectFromString(line);
                            assert jsonObject != null;
                            switch (jsonObject.get("event").toString()){
                                case "Scan":
                                    if(jsonObject.containsKey("StarType"))
                                        database.insertStar(jsonObject);
                                    else
                                    if(((String)jsonObject.get("BodyName")).contains("Belt Cluster"))
                                        database.insertBeltCluster(jsonObject);
                                    else if(((String)jsonObject.get("BodyName")).contains("Ring")){
                                        database.insertBodyRing(jsonObject);
                                    }
                                    else
                                        database.insertBody(jsonObject);
                                    break;
                                case "FSSSignalDiscovered":
                                    database.insertSystemSignal(jsonObject);
                                    break;
                                case "FSSBodySignals":
                                    database.insertPlanetSignals(jsonObject);
                                case "FSDJump":
                                    database.insertSystem(jsonObject);
                            }
                        }
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(null, e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
                    }
                    synchronized (this) {
                        try {
                            this.wait(timeout);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }finally {
            exec.shutdown();
        }
    }

    /**
     * Sets the path, in which the reader should work and starts a new buffered reader on the latest log in the given path
     * @param directoryPath Path to the directory with logs
     */
    public JournalLogParser(String directoryPath) {
        try {
            this.directoryPath = directoryPath;
            bufferedReader = new BufferedReader(new FileReader(getLatestLogInWorkingDirectory()));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Reads the current directory and returns the latest found log file
     * @return Journal with the latest date
     * @throws FileNotFoundException
     */
    public File getLatestLogInWorkingDirectory() throws FileNotFoundException {
        File directory = new File(directoryPath);

        //Do not touch dangerous stream
        Optional<Date> optionalDate =
                Arrays.stream(Objects.requireNonNull(directory.list((dir, name) -> name.startsWith("Journal") && name.contains("T"))))
                        .map(this::extractDateFromJournalName)
                        .max(Date::compareTo);

        if(optionalDate.isEmpty())
            throw new FileNotFoundException("Couldn't find any log file");

        System.out.println("Choosing journal file: Journal." + prefixJournalDateFormat.format(optionalDate.get()) + "T" + suffixJournalDateFormat.format(optionalDate.get()) + ".01.log");
        //setup reader to read latest log
        return new File(directoryPath+"/Journal." + prefixJournalDateFormat.format(optionalDate.get()) + "T" + suffixJournalDateFormat.format(optionalDate.get()) + ".01.log");
    }

    /**
     * Creates a buffered reader from given file and sets it internally
     * @param file File, from which the buffered reader should read
     * @return True if creating new buffered reader succeeded, else false;
     */
    public Boolean setBufferedReaderForFile(File file){
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            return true;
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
            return false;
        }
    }

    /**
     * Reads next line in file, if available. Null if no line can be read.
     * @return next line in file
     */
    public String getNextLine(){
        if(bufferedReader == null){
            JOptionPane.showMessageDialog(null,"Couldn't find any journal files. Make sure that the journal log path is correct.");
            return "-1";
        }
        try {
            return bufferedReader.readLine();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
            return null;
        }
    }

    public boolean closeReader(){
        try {
            bufferedReader.close();
            return true;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
            return false;
        }

    }

    /**
     * Returns date which is present in the name of the journal log name
     * Date format since Odyssey Update 11: yyyy-MM-ddTHHmmss
     * The date format was as followed: yyMMddHHmmss
     * The journal log name is build as followed: Journal.[date].[part].log
     * @param dateString Name from the journal log file
     * @return Date
     */
    public Date extractDateFromJournalName(String dateString){
        try {
            return journalDateFormat.parse(dateString.split("\\.")[1].split("T")[0] + "-" + dateString.split("\\.")[1].split("T")[1]);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
        }
        return null;
    }
}
