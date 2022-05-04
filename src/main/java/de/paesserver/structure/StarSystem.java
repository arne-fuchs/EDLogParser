package de.paesserver.structure;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class StarSystem {
//{ "timestamp":"2022-04-12T18:58:02Z", "event":"FSDJump", "Taxi":false, "Multicrew":false,
// "StarSystem":"Floall QC-R b48-0", "SystemAddress":474725563809, "StarPos":[-14610.00000,-787.84375,15349.40625], "SystemAllegiance":"", "SystemEconomy":"$economy_None;", "SystemEconomy_Localised":"None", "SystemSecondEconomy":"$economy_None;", "SystemSecondEconomy_Localised":"None", "SystemGovernment":"$government_None;", "SystemGovernment_Localised":"None", "SystemSecurity":"$GAlAXY_MAP_INFO_state_anarchy;", "SystemSecurity_Localised":"Anarchy",
// "Population":0, "Body":"Floall QC-R b48-0 A", "BodyID":1, "BodyType":"Star", "JumpDist":32.691, "FuelUsed":11.844490, "FuelLevel":44.822250 }

    final public String starSystem;
    final public long systemAddress;
    final public double[] starPos;
    final public String systemAllegiance;
    final public String systemEconomy;
    final public String systemEconomy_Localised;
    final public String systemSecondEconomy;
    final public String systemSecondEconomy_Localised;
    final public String systemGovernment;
    final public String systemGovernment_Localised;
    final public String systemSecurity;
    final public String systemSecurity_Localised;
    final public long population;
    final public String body;
    final public long bodyID;
    final public String bodyType;
    final public double jumpDist;
    final public double fuelUsed;
    final public double fuelLevel;


    public StarSystem(JSONObject jsonObject){
        starSystem = (String) jsonObject.get("StarSystem");
        systemAddress = (long) jsonObject.get("SystemAddress");

        JSONArray jsonArray = ((JSONArray) jsonObject.get("StarPos"));
        starPos = new double[jsonArray.size()];
        for(int i = 0;i < jsonArray.size();i++){
            starPos[i] = (double) jsonArray.get(i);
        }

        systemAllegiance = (String) jsonObject.get("SystemAllegiance");
        systemEconomy = (String) jsonObject.get("SystemEconomy");
        systemEconomy_Localised = (String) jsonObject.get("SystemEconomy_Localised");
        systemSecondEconomy = (String) jsonObject.get("SystemSecondEconomy");
        systemSecondEconomy_Localised = (String) jsonObject.get("SystemSecondEconomy_Localised");
        systemGovernment = (String) jsonObject.get("SystemGovernment");
        systemGovernment_Localised = (String) jsonObject.get("SystemGovernment_Localised");
        systemSecurity = (String) jsonObject.get("SystemSecurity");
        systemSecurity_Localised = (String) jsonObject.get("SystemSecurity_Localised");
        population = (long) jsonObject.get("Population");
        body = (String) jsonObject.get("Body");
        bodyID = (long) jsonObject.get("BodyID");
        bodyType = (String) jsonObject.get("BodyType");
        if(jsonObject.containsKey("JumpDist")){
            jumpDist = (double) jsonObject.get("JumpDist");
            fuelUsed = (double) jsonObject.get("FuelUsed");
            fuelLevel = (double) jsonObject.get("FuelLevel");
        }else {
            jumpDist = -1;
            fuelUsed = -1;
            fuelLevel = -1;
        }
    }

    @Override
    public String toString() {
        return starSystem;
    }
}
