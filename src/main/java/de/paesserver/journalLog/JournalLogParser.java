package de.paesserver.journalLog;


import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class JournalLogParser{

    final SimpleDateFormat journalDateFormat = new SimpleDateFormat("yyMMddHHmmss");
    private BufferedReader bufferedReader;
    String directoryPath = "./";

    /**
     * Reads the latest log in the current working directory
     */
    public JournalLogParser() {
        try {
            bufferedReader = new BufferedReader(new FileReader(getLatestLogInWorkingDirectory()));
        }catch (Exception e){
            e.printStackTrace();
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
                Arrays.stream(Objects.requireNonNull(directory.list((dir, name) -> name.contains("Journal"))))
                        .map(this::extractDateFromJournalName)
                        .max(Date::compareTo);

        if(optionalDate.isEmpty())
            throw new FileNotFoundException("Couldn't find any log file");

        //setup reader to read latest log
        return new File("./Journal." + journalDateFormat.format(optionalDate.get()) + ".01.log");
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
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Reads next line in file, if available. Null if no line can be read.
     * @return next line in file
     */
    public String getNextLine(){
        try {
            return bufferedReader.readLine();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean closeReader(){
        try {
            bufferedReader.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * Returns date which is present in the name of the journal log name
     * The date format is as followed: yyMMddHHmmss
     * The journal log name is build as followed: Journal.[date].[part].log
     * @param dateString Name from the journal log file
     * @return Date
     */
    public Date extractDateFromJournalName(String dateString){
        try {
            return journalDateFormat.parse(dateString.split("\\.")[1]);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
