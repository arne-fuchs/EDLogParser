package de.paesserver.journalLog;

import de.paesserver.Logger;
import de.paesserver.frames.BodyPane;
import de.paesserver.frames.SystemPane;
import de.paesserver.structure.*;
import de.paesserver.structure.StarSystem;
import de.paesserver.structure.body.*;
import de.paesserver.structure.signal.body.BodySignal;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class JSONInterpreter {

    private final HashMap<String, Component> componentHashMap;
    ArrayList<BodySignal[]> bodySignalsList = new ArrayList<>();
    Body bodyToAdd;

    public JSONInterpreter(HashMap<String, Component> componentHashMap){
        this.componentHashMap = componentHashMap;
    }

    public void computeJSONObject(JSONObject jsonObject, SystemPane systemPane, BodyPane bodyPane){

        String event = (String) jsonObject.get("event");
        Database database = DatabaseSingleton.getInstance();

        switch (event){
            //Will be called by loading the game
            case "Location":
            case "FSDJump":
                //TODO Implement location

                database.insertSystem(jsonObject);

                ((DefaultMutableTreeNode)systemPane.systemTreeNode.getRoot()).removeAllChildren();
                //((DefaultMutableTreeNode)systemPane.systemTreeNode.getRoot()).setUserObject(jsonObject.get("StarSystem").toString());
                StarSystem starSystem = new StarSystem(jsonObject);
                ((DefaultMutableTreeNode)systemPane.systemTreeNode.getRoot()).add(new SystemMutableTreeNode(starSystem));

                JTree systemJTree = ((JTree)componentHashMap.get("bodiesOutput"));
                systemJTree.updateUI();

                systemPane.setSuffix("systemName", starSystem.starSystem);
                systemPane.setSuffix("allegiance", starSystem.systemAllegiance);
                systemPane.setSuffix("economy", starSystem.systemEconomy_Localised);
                systemPane.setSuffix("secondEconomy", starSystem.systemSecondEconomy_Localised);
                systemPane.setSuffix("government", starSystem.systemGovernment_Localised);
                systemPane.setSuffix("security", starSystem.systemSecurity_Localised);
                systemPane.setSuffix("population", String.valueOf(starSystem.population));
                systemPane.wipeSignals();
                systemPane.updateText();
                bodyPane.wipeText();

                break;
            case "StartJump":
                //if(jsonObject.get("JumpType").equals("Supercruise"))
                    //((JTextArea)componentHashMap.get("logOutput")).append("Entered Supercruise"+ "\n");
                /*
                if(jsonObject.get("JumpType").equals("Hyperspace")){
                    //((JTextArea)componentHashMap.get("logOutput")).setText("---LOG---\t\t\t\t\t\t\n");
                    ((JTree)componentHashMap.get("bodiesOutput")).removeAll();
                    ((JTextArea)componentHashMap.get("nonBodiesOutput")).setText("---SIGNALS---\t\t\t\t\t\t\n");
                    systemPane.resetSuffix();
                    systemPane.updateText();
                    //((JTextArea)componentHashMap.get("logOutput")).append("Jump has been initialised"+ "\n");
                }
                 */
                break;
            case "SupercruiseExit":
                //((JTextArea)componentHashMap.get("logOutput")).append("Exited Supercruise"+ "\n");
                break;
            case "MaterialCollected":
                //((JTextArea)componentHashMap.get("logOutput")).append("Material collected: " + jsonObject.get("Name_Localised")  + "\n");
                //((JTextArea)componentHashMap.get("logOutput")).append("Count: " + jsonObject.get("Count")  + "\n");
                break;
            case "Bounty":
                //TODO More details about bounty:
                //{ "timestamp":"2022-03-16T16:33:27Z", "event":"Bounty", "Rewards":[ { "Faction":"HOTCOL", "Reward":271012 } ], "Target":"empire_trader", "Target_Localised":"Imperial Clipper", "TotalReward":271012, "VictimFaction":"HOTCOL" }
                //{ "timestamp":"2022-03-16T16:36:11Z", "event":"Bounty", "Rewards":[ { "Faction":"HOTCOL", "Reward":174749 }, { "Faction":"Colonia Co-operative", "Reward":59843 } ], "Target":"diamondback", "Target_Localised":"Diamondback Scout", "TotalReward":234592, "VictimFaction":"Matlehi Silver Mob" }
                //((JTextArea)componentHashMap.get("logOutput")).append("Bounty collected: " + jsonObject.get("Target_Localised")  + "\n");
                //((JTextArea)componentHashMap.get("logOutput")).append("Total Reward: " + jsonObject.get("TotalReward")  + "\n");
                break;

            //SCAN ACTIVITIES
            case "FSSDiscoveryScan":
                //TODO Implement FSSDiscoveryScan
                //((JTextArea)componentHashMap.get("logOutput")).append("Discovery-Scan has been initialised\n");
                systemPane.setSuffix("bodiesCount", jsonObject.get("BodyCount").toString());
                systemPane.setSuffix("nonBodiesCount", jsonObject.get("NonBodyCount").toString());
                systemPane.updateText();
                break;
            case "FSSAllBodiesFound":
                //TODO Implement FSSAllBodiesFound
                //((JTextArea)componentHashMap.get("logOutput")).append("All bodies discovered"+ "\n");
                break;
            case "SAAScanComplete":
                //TODO Implement SAAScanComplete
                break;
            //{ "timestamp":"2022-02-13T23:07:21Z", "event":"SAAScanComplete", "BodyName":"Synuefe ZR-I b43-10 D 2", "SystemAddress":22671924274545, "BodyID":46, "ProbesUsed":4, "EfficiencyTarget":4 }

            case "SAASignalsFound":

                //{ "timestamp":"2022-02-13T23:07:21Z", "event":"SAASignalsFound", "BodyName":"Synuefe ZR-I b43-10 D 2", "SystemAddress":22671924274545, "BodyID":46,
                // "Signals":[ { "Type":"$SAA_SignalType_Guardian;", "Type_Localised":"Guardian", "Count":4 },
                //  { "Type":"$SAA_SignalType_Biological;", "Type_Localised":"Biologisch", "Count":1 },
                //  { "Type":"$SAA_SignalType_Geological;", "Type_Localised":"Geologisch", "Count":3 },
                //  { "Type":"$SAA_SignalType_Human;", "Type_Localised":"Menschlich", "Count":2 } ] }

            case "FSSBodySignals":
            //{ "timestamp":"2022-04-07T15:59:02Z", "event":"FSSBodySignals", "BodyName":"Eol Prou HG-X d1-519 4 a", "BodyID":25, "SystemAddress":17841151349011,
                // "Signals":[
                //  { "Type":"$SAA_SignalType_Biological;", "Type_Localised":"Biological", "Count":2 },
                //  { "Type":"$SAA_SignalType_Geological;", "Type_Localised":"Geological", "Count":3 }
                //  ] }

            //TODO Implement FSSBodySignals
                database.insertPlanetSignals(jsonObject);

                BodySignal[] potentialBodySignals = BodySignal.getBodySignals(jsonObject);
                if(potentialBodySignals.length != 0){
                    systemPane.addSignals(potentialBodySignals);
                    if(bodyToAdd != null && potentialBodySignals[0].bodyID == bodyToAdd.bodyID){
                        bodyToAdd.bodySignals = potentialBodySignals;
                        bodyPane.updateText();
                    }else
                        bodySignalsList.add(potentialBodySignals);
                }


                break;
            case "FSSSignalDiscovered":
                //TODO Implement FSSSignalDiscovered
                database.insertSystemSignal(jsonObject);
                if(jsonObject.containsKey("SignalName_Localised"))
                    ((JTextArea)componentHashMap.get("nonBodiesOutput")).append(jsonObject.get("SignalName_Localised")+"\n");
                else
                    ((JTextArea)componentHashMap.get("nonBodiesOutput")).append(jsonObject.get("SignalName")+"\n");
                break;
            case "Scan":
                //TODO Implement Scan

                if(jsonObject.containsKey("StarType"))
                    database.insertStar(jsonObject);
                else
                    if(((String)jsonObject.get("BodyName")).contains("Belt Cluster"))
                        database.insertBeltCluster(jsonObject);
                    else
                        database.insertBody(jsonObject);


                if(jsonObject.containsKey("StarType")){
                    //Star
                    Star star = new Star(jsonObject);
                    bodyToAdd = star;
                    bodyPane.setTextforStar(star);

                }else{
                    if(((String)jsonObject.get("BodyName")).contains("Belt Cluster")){
                        //Belt Cluster
                        bodyToAdd = new BeltCluster(jsonObject);
                    }else {
                        //Planet
                        Planet planet = new Planet(jsonObject);
                        bodyToAdd = planet;
                        bodyPane.setTextForPlanet(planet);
                    }
                }
                Logger.log("Found scan:" +bodyToAdd+"\n\n");
                BodyMutableTreeNode bodyMutableTreeNode = new BodyMutableTreeNode(bodyToAdd);
                ((DefaultMutableTreeNode)((DefaultMutableTreeNode)systemPane.systemTreeNode.getRoot()).getFirstChild()).add(bodyMutableTreeNode);
                JTree jtree = ((JTree)componentHashMap.get("bodiesOutput"));
                for(int i = 0; i < jtree.getRowCount();i++)
                    jtree.expandRow(i);
                jtree.updateUI();

                ArrayList<BodySignal[]> toRemove = new ArrayList<>();

                bodySignalsList.forEach(bArray -> {
                    if(bArray[0].bodyID == bodyToAdd.bodyID){
                        bodyToAdd.bodySignals = bArray;
                        toRemove.add(bArray);
                        bodyPane.updateText();
                    }
                });

                toRemove.forEach(bArray -> bodySignalsList.remove(bArray));
                break;
            default:
        }
    }

    public static JSONObject extractJSONObjectFromString(String line){
        try {
            JSONParser parser = new JSONParser();
            return  (JSONObject) parser.parse(line);
        }catch (org.json.simple.parser.ParseException e){
            e.printStackTrace();
            return null;
        }
    }
}
