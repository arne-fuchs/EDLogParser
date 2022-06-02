package de.paesserver.frames.logframe;

import org.json.simple.JSONObject;

public enum BodyType {
    Galaxy,System, Planet, Star, Imaginary, BeltCluster, Ring;

    public static BodyType parseJson(JSONObject jsonObject){
        if(((String)jsonObject.get("BodyName")).contains("Belt Cluster"))
            return BeltCluster;
        if(((String)jsonObject.get("BodyName")).contains("Ring"))
            return Ring;
        if(jsonObject.containsKey("StarType"))
            return Star;
        if(jsonObject.containsKey("SystemGovernment"))
            return System;
        if(jsonObject.containsKey("Landable"))
            return Planet;
        return Imaginary;
    }

    public static BodyType parseString(String string){
        switch (string){
            case "Planet": return Planet;
            case "Star": return Star;
            case "Belt Cluster": return BeltCluster;
            default: return null;
        }
    }
}
