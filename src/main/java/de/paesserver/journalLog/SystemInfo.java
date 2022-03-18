package de.paesserver.journalLog;

import javax.swing.*;
import java.util.ArrayList;

public class SystemInfo {
    private final JTextArea textArea;

    public StringPair systemName = new StringPair("System");
    public StringPair bodiesCount = new StringPair("Bodies");
    public StringPair nonBodiesCount = new StringPair("Non-Bodies");
    public StringPair allegiance = new StringPair("Allegiance");
    public StringPair government = new StringPair("Government");
    public StringPair security = new StringPair("Security");
    public StringPair population = new StringPair("Population");
    public StringPair economy = new StringPair("Economy");
    public StringPair secondEconomy = new StringPair("Sec. Economy");

    private final ArrayList<StringPair> systemDataList;

    public SystemInfo(JTextArea textArea){
        this.textArea = textArea;

        systemDataList = new ArrayList<>();
        systemDataList.add(systemName);
        systemDataList.add(allegiance);
        systemDataList.add(government);
        systemDataList.add(security);
        systemDataList.add(population);
        systemDataList.add(economy);
        systemDataList.add(secondEconomy);
        systemDataList.add(bodiesCount);
        systemDataList.add(nonBodiesCount);
    }
    public void updateText(){
        StringBuilder builder = new StringBuilder();
        builder.append("---SystemInfo---");
        builder.append("\n");
        systemDataList.forEach(stringPair -> builder.append(stringPair.toString()).append("\n"));
        textArea.setText(builder.toString());
    }

    public void resetSuffix(){
        systemDataList.forEach(stringPair -> stringPair.suffix = "n/a");
    }


}
class StringPair{
    final String prefix;
    String suffix;

    public StringPair(String prefix,String suffix){
        this.prefix = prefix;
        this.suffix = suffix;
    }

    public StringPair(String prefix){
        this.prefix = prefix;
        this.suffix = "n/a";
    }

    @Override
    public String toString() {
        return prefix + ":\n" + suffix + "\n";
    }
}