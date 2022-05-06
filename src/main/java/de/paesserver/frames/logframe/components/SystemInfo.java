package de.paesserver.frames.logframe.components;

import de.paesserver.frames.logframe.LogFrameComponentsSingleton;
import de.paesserver.structure.signal.body.BodySignal;
import org.json.simple.JSONObject;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

public class SystemInfo {

    private  ArrayList<BodySignal> bodySignalsList = new ArrayList<>();

    public SystemInfo(JSONObject jsonObject){
        setTextForSystem(jsonObject);
    }
    public void updateText(){
        String string = addBodyCounts();
        addBodySignalsAndPrint(string);
    }

    public  void addSignals(BodySignal[] bodySignalsArray){
        bodySignalsList.addAll(Arrays.asList(bodySignalsArray));
        updateText();
    }

    public void wipeSignals(){
        bodySignalsList = new ArrayList<>();
    }

    private String systemString;
    public long bodyCount = 0;
    public long nonBodyCount = 0;
    public void setTextForSystem(JSONObject jsonObject){

        systemString = "---"+ jsonObject.get("StarSystem") +"---\n" +
                "Allegiance:  \t" + jsonObject.get("SystemAllegiance") + "\n" +
                "Economy:     \t" + jsonObject.get("SystemEconomy_Localised") + "\n" +
                "Sec. Economy:\t" + jsonObject.get("SystemSecondEconomy_Localised") + "\n" +
                "Government:  \t" + jsonObject.get("SystemGovernment_Localised") + "\n\n" +

                "Security:    \t" + jsonObject.get("SystemSecurity_Localised") + "\n"+
                "Population:  \t" + jsonObject.get("Population") + "\n\n";


        String string = addBodyCounts();
        addBodySignalsAndPrint(string);
    }

    private String addBodyCounts(){
        return systemString +
                "Bodies:      \t" + bodyCount + "\n" +
                "Non-bodies:  \t" + nonBodyCount + "\n\n";
    }
    private void addBodySignalsAndPrint(String string){
        StringBuilder builder = new StringBuilder(string);

        bodySignalsList.stream().
                collect(Collectors.groupingBy(
                        bodySignal -> bodySignal.type_Localised,Collectors.summingLong(bodySignal -> bodySignal.count)
                )).forEach((s,l) -> builder.append("Total ").append(s).append(":\t").append(l).append("\n"));

        builder.append("\n");

        bodySignalsList.stream().
                collect(Collectors.groupingBy(
                        bodySignal -> bodySignal.type_Localised,Collectors.maxBy(Comparator.comparingInt(bodySignal -> (int) bodySignal.count)))
                ).forEach((s,body) ->
                        builder.append("Max ").append(s).append(":\n").append(body.isPresent() ? body.get().bodyName : "Unknown Body").append(": \t").append(body.map(signal -> signal.count).orElse(0L)).append("\n")
                );


        LogFrameComponentsSingleton.getSystemInfoTextArea().setText(builder.toString());
    }
}
