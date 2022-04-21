package de.paesserver.frames;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.util.ArrayList;

public class SystemPane {
    private final JTextArea textArea;

    public StringPair systemName = new StringPair("System Name");
    public StringPair bodiesCount = new StringPair("Bodies");
    public StringPair nonBodiesCount = new StringPair("Non-Bodies");
    public StringPair allegiance = new StringPair("Allegiance");
    public StringPair government = new StringPair("Government");
    public StringPair security = new StringPair("Security");
    public StringPair population = new StringPair("Population");
    public StringPair economy = new StringPair("Economy");
    public StringPair secondEconomy = new StringPair("Second Economy");

    public DefaultMutableTreeNode systemTreeNode;

    private final ArrayList<StringPair> systemDataList;

    public SystemPane(JTextArea textArea,DefaultMutableTreeNode systemTreeNode){
        this.textArea = textArea;
        this.systemTreeNode = systemTreeNode;

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

    public void setSuffix(String key,String suffix){
        switch (key){
            case "systemName": systemName.suffix = suffix; break;
            case "bodiesCount": bodiesCount.suffix = suffix; break;
            case "nonBodiesCount": nonBodiesCount.suffix = suffix; break;
            case "allegiance": allegiance.suffix = suffix; break;
            case "government": government.suffix = suffix; break;
            case "security": security.suffix = suffix; break;
            case "population": population.suffix = suffix; break;
            case "economy": economy.suffix = suffix; break;
            case "secondEconomy": secondEconomy.suffix = suffix; break;
            default: java.lang.System.out.println("Couldn't find stringPair: " + key);
        }
    }
}
