package de.paesserver.journalLog;

import de.paesserver.frames.SystemPane;
import de.paesserver.structure.System;
import de.paesserver.structure.SystemMutableTreeNode;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.util.HashMap;

public class JSONInterpreter {

    private final HashMap<String, Component> componentHashMap;

    public JSONInterpreter(HashMap<String, Component> componentHashMap){
        this.componentHashMap = componentHashMap;
    }

    public void computeJSONObject(JSONObject jsonObject, SystemPane systemPane){

        String event = (String) jsonObject.get("event");

        switch (event){
            //Will be called by loading the game
            case "Location":
            case "FSDJump":
                //TODO Implement location
                ((DefaultMutableTreeNode)systemPane.systemTreeNode.getRoot()).setUserObject(jsonObject.get("StarSystem").toString());
                System system = new System(jsonObject);
                SystemMutableTreeNode systemMutableTreeNode = new SystemMutableTreeNode(system);

                systemPane.setSuffix("systemName", (String) jsonObject.get("StarSystem"));
                systemPane.setSuffix("allegiance", (String) jsonObject.get("SystemAllegiance"));
                systemPane.setSuffix("economy", (String) jsonObject.get("SystemEconomy_Localised"));
                systemPane.setSuffix("secondEconomy", (String) jsonObject.get("SystemSecondEconomy_Localised"));
                systemPane.setSuffix("government", (String) jsonObject.get("SystemGovernment_Localised"));
                systemPane.setSuffix("security", (String) jsonObject.get("SystemSecurity_Localised"));
                systemPane.setSuffix("population", jsonObject.get("Population").toString());
                systemPane.updateText();
                break;
            case "StartJump":
                if(jsonObject.get("JumpType").equals("Supercruise"))
                    ((JTextArea)componentHashMap.get("logOutput")).append("Entered Supercruise"+ "\n");
                if(jsonObject.get("JumpType").equals("Hyperspace")){
                    ((JTextArea)componentHashMap.get("logOutput")).setText("---LOG---\t\t\t\t\t\t\n");
                    ((JTree)componentHashMap.get("bodiesOutput")).removeAll();
                    ((JTextArea)componentHashMap.get("nonBodiesOutput")).setText("---SIGNALS---\t\t\t\t\t\t\n");
                    systemPane.resetSuffix();
                    systemPane.updateText();
                    ((JTextArea)componentHashMap.get("logOutput")).append("Jump has been initialised"+ "\n");
                }
                break;
            case "SupercruiseExit":
                ((JTextArea)componentHashMap.get("logOutput")).append("Exited Supercruise"+ "\n");
                break;
            case "MaterialCollected":
                ((JTextArea)componentHashMap.get("logOutput")).append("Material collected: " + jsonObject.get("Name_Localised")  + "\n");
                ((JTextArea)componentHashMap.get("logOutput")).append("Count: " + jsonObject.get("Count")  + "\n");
                break;
            case "Bounty":
                //TODO More details about bounty:
                //{ "timestamp":"2022-03-16T16:33:27Z", "event":"Bounty", "Rewards":[ { "Faction":"HOTCOL", "Reward":271012 } ], "Target":"empire_trader", "Target_Localised":"Imperial Clipper", "TotalReward":271012, "VictimFaction":"HOTCOL" }
                //{ "timestamp":"2022-03-16T16:36:11Z", "event":"Bounty", "Rewards":[ { "Faction":"HOTCOL", "Reward":174749 }, { "Faction":"Colonia Co-operative", "Reward":59843 } ], "Target":"diamondback", "Target_Localised":"Diamondback Scout", "TotalReward":234592, "VictimFaction":"Matlehi Silver Mob" }
                ((JTextArea)componentHashMap.get("logOutput")).append("Bounty collected: " + jsonObject.get("Target_Localised")  + "\n");
                ((JTextArea)componentHashMap.get("logOutput")).append("Total Reward: " + jsonObject.get("TotalReward")  + "\n");
                break;

            //SCAN ACTIVITIES
            case "FSSDiscoveryScan":
                //TODO Implement FSSDiscoveryScan
                ((JTextArea)componentHashMap.get("logOutput")).append("Discovery-Scan has been initialised\n");
                systemPane.setSuffix("bodiesCount", jsonObject.get("BodyCount").toString());
                systemPane.setSuffix("nonBodiesCount", jsonObject.get("NonBodyCount").toString());
                systemPane.updateText();
                break;
            case "FSSAllBodiesFound":
                //TODO Implement FSSAllBodiesFound
                ((JTextArea)componentHashMap.get("logOutput")).append("All bodies discovered"+ "\n");
                break;
            case "Scan":
                //TODO Implement Scan
                DefaultMutableTreeNode body = new DefaultMutableTreeNode(jsonObject.get("BodyName").toString());
                ((DefaultMutableTreeNode)systemPane.systemTreeNode.getRoot()).add(body);
                ((JTree)componentHashMap.get("bodiesOutput")).expandRow(0);
                break;
            case "FSSSignalDiscovered":
                //TODO Implement FSSSignalDiscovered
                if(jsonObject.containsKey("SignalName_Localised"))
                    ((JTextArea)componentHashMap.get("nonBodiesOutput")).append(jsonObject.get("SignalName_Localised")+"\n");
                else
                    ((JTextArea)componentHashMap.get("nonBodiesOutput")).append(jsonObject.get("SignalName")+"\n");
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
