package de.paesserver.frames;

import de.paesserver.structure.signal.body.BodySignal;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    ArrayList<BodySignal> bodySignalsList = new ArrayList<>();

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
        builder.append("---System Info---");
        builder.append("\n");
        systemDataList.forEach(stringPair -> builder.append(stringPair.toString()).append("\n"));
        builder.append("\n---Body Signals---\n");

        if(!bodiesCount.suffix.equals("n/a")){
            bodySignalsList.stream().
                    collect(Collectors.groupingBy(
                            bodySignal -> bodySignal.type_Localised,Collectors.maxBy(Comparator.comparingInt(bodySignal -> (int) bodySignal.count)))
                    ).forEach((s,body) ->
                            builder.append("Max ").append(s).append(":\n").append(body.isPresent() ? body.get().bodyName : "Unknown Body").append(": \t").append(body.map(signal -> signal.count).orElse(0L)).append("\n")
                    );


            builder.append("\n");

            bodySignalsList.stream().
                    collect(Collectors.groupingBy(
                            bodySignal -> bodySignal.type_Localised,Collectors.summingLong(bodySignal -> bodySignal.count)
                    )).forEach((s,l) -> builder.append("Total ").append(s).append(":\t").append(l).append("\n"));


        }
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

    public void addSignals(BodySignal[] bodySignalsArray){
        bodySignalsList.addAll(Arrays.asList(bodySignalsArray));
        updateText();
    }

    public void wipeSignals(){
        bodySignalsList = new ArrayList<>();
    }
}
