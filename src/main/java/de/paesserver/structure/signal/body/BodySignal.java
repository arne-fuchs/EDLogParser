package de.paesserver.structure.signal.body;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;

import static de.paesserver.structure.signal.body.BodySignalType.*;

public class BodySignal {
    //{ "timestamp":"2022-03-18T11:20:59Z", "event":"FSSBodySignals", "BodyName":"Randgnid 3", "BodyID":4, "SystemAddress":2688540283179,
    // "Signals":[ { "Type":"$SAA_SignalType_Geological;", "Type_Localised":"Geologisch", "Count":2 } ] }

    //{ "timestamp":"2022-04-09T17:38:14Z", "event":"FSSBodySignals", "BodyName":"Eephaik GN-A c1-14 C 6", "BodyID":45, "SystemAddress":3910600630282,
    // "Signals":[ { "Type":"$SAA_SignalType_Biological;", "Type_Localised":"Biological", "Count":1 } ] }

    public String bodyName,type_Localised;
    public long bodyID,systemAddress,count;
    public BodySignalType bodySignalType;

    public BodySignal(JSONObject jsonObject,int index) {
        bodyName = (String) jsonObject.get("BodyName");
        bodyID = (long) jsonObject.get("BodyID");
        systemAddress = (long) jsonObject.get("SystemAddress");

        JSONArray jsonArray =  (JSONArray) jsonObject.get("Signals");

        bodySignalType = extractBodySignalType((String)((JSONObject)jsonArray.get(index)).get("Type"));
        type_Localised = (String)((JSONObject)jsonArray.get(index)).get("Type_Localised");
        count = (long) ((JSONObject)jsonArray.get(index)).get("Count");
    }



    public static BodySignal[] getBodySignals(JSONObject jsonObject){

        JSONArray jsonArray =  (JSONArray) jsonObject.get("Signals");
        BodySignal[] bodySignals = new BodySignal[jsonArray.size()];

        for(int i = 0;i < bodySignals.length;i++){
            bodySignals[i] = new BodySignal(jsonObject,i);
        }
        return bodySignals;
    }
}
