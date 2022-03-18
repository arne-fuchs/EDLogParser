package de.paesserver.journalLog;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.swing.*;
import java.util.HashMap;

public class JSONInterpreter {

    private final HashMap<String,JTextArea> textAreaHashMap;

    public JSONInterpreter(HashMap<String, JTextArea> textAreaHashMap){
        this.textAreaHashMap = textAreaHashMap;
    }

    public void computeJSONObject(JSONObject jsonObject,SystemInfo systemInfo){

        String event = (String) jsonObject.get("event");

        switch (event){
            //Will be called by loading the game
            case "Location":
            case "FSDJump":
                //TODO Implement location
                textAreaHashMap.get("logOutput").setText("---LOG---\t\t\t\t\t\t\n");
                textAreaHashMap.get("bodiesOutput").setText("---BODIES---\t\t\t\t\t\t\n");
                textAreaHashMap.get("nonBodiesOutput").setText("---SIGNALS---\t\t\t\t\t\t\n");
                systemInfo.resetSuffix();

                systemInfo.systemName.suffix = (String) jsonObject.get("StarSystem");
                systemInfo.allegiance.suffix = (String) jsonObject.get("SystemAllegiance");
                systemInfo.economy.suffix = (String) jsonObject.get("SystemEconomy_Localised");
                systemInfo.secondEconomy.suffix = (String) jsonObject.get("SystemSecondEconomy_Localised");
                systemInfo.government.suffix = (String) jsonObject.get("SystemGovernment_Localised");
                systemInfo.security.suffix = (String) jsonObject.get("SystemSecurity_Localised");
                systemInfo.population.suffix = jsonObject.get("Population").toString();
                systemInfo.updateText();
                break;
            case "StartJump":
                //TODO Implement StartJump
                textAreaHashMap.get("logOutput").append("Jump has been initialised"+ "\n");
                break;

            //SCAN ACTIVITIES
            case "FSSDiscoveryScan":
                //TODO Implement FSSDiscoveryScan
                textAreaHashMap.get("logOutput").append("Discovery-Scan has been initialised\n");
                systemInfo.bodiesCount.suffix = jsonObject.get("BodyCount").toString();
                systemInfo.nonBodiesCount.suffix = jsonObject.get("NonBodyCount").toString();
                systemInfo.updateText();
                break;
            case "FSSAllBodiesFound":
                //TODO Implement FSSAllBodiesFound
                textAreaHashMap.get("logOutput").append("All bodies discovered"+ "\n");
                break;
            case "Scan":
                //TODO Implement Scan
                textAreaHashMap.get("bodiesOutput").append(jsonObject.get("BodyName") + "\n");
                break;
            case "FSSSignalDiscovered":
                //TODO Implement FSSSignalDiscovered
                if(jsonObject.containsKey("SignalName_Localised"))
                    textAreaHashMap.get("nonBodiesOutput").append(jsonObject.get("SignalName_Localised")+"\n");
                else
                    textAreaHashMap.get("nonBodiesOutput").append(jsonObject.get("SignalName")+"\n");
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
