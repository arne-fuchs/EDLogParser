package de.paesserver.journalLog;

import de.paesserver.frames.logframe.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class JSONInterpreter {

    private final LogFrame logFrame;

    public JSONInterpreter(LogFrame logFrame){
        this.logFrame = logFrame;
    }


    public void computeJSONObject(JSONObject jsonObject){

        String event = (String) jsonObject.get("event");
        Database database = DatabaseSingleton.getInstance();

        switch (event){
            //Will be called by loading the game
            case "Location":
            case "FSDJump":
                //TODO Implement location

                database.insertSystem(jsonObject);
                logFrame.setSystemData(new SystemInfo((long)jsonObject.get("SystemAddress")));
                logFrame.replaceSystemTree(jsonObject.get("StarSystem").toString());
                logFrame.updateSystemTree(jsonObject.get("StarSystem").toString());
                logFrame.updateBodySingalsTable((long) jsonObject.get("SystemAddress"));
                logFrame.wipeBodyData();
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
                database.addBodyCounts(jsonObject);
                logFrame.systemInfoData.setTextForBodyCounts();
                logFrame.updateSystemData();
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
                logFrame.updateBodySingalsTable((long) jsonObject.get("SystemAddress"));
                logFrame.updateBodyData();
                break;

            case "FSSSignalDiscovered":
                //TODO Implement FSSSignalDiscovered
                database.insertSystemSignal(jsonObject);
                logFrame.updateSystemSignalsTable((long) jsonObject.get("SystemAddress"));
                logFrame.updateSystemData();
                break;

            case "Scan":
                //TODO Implement Scan
                if(jsonObject.containsKey("StarType")){
                    database.insertStar(jsonObject);
                    BodyInfo bodyInfo = new BodyInfo();
                    bodyInfo.setTextforStar(jsonObject.get("BodyName").toString());
                    logFrame.setBodyData(bodyInfo);
                }
                else
                    if(((String)jsonObject.get("BodyName")).contains("Belt Cluster")){
                        database.insertBeltCluster(jsonObject);
                    }
                    else if(((String)jsonObject.get("BodyName")).contains("Ring")){
                            database.insertBodyRing(jsonObject);
                        }
                        else{
                            database.insertBody(jsonObject);
                            BodyInfo bodyInfo = new BodyInfo();
                            bodyInfo.setTextForPlanet(jsonObject.get("BodyName").toString());
                            logFrame.setBodyData(bodyInfo);
                        }

                logFrame.replaceSystemTree(jsonObject.get("StarSystem").toString());
                logFrame.updateSystemTree(jsonObject.get("StarSystem").toString());
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
