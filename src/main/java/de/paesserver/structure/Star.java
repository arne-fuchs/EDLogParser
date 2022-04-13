package de.paesserver.structure;

import org.json.simple.JSONObject;

public class Star extends Body{

    //{ "timestamp":"2022-03-18T11:03:19Z", "event":"Scan", "ScanType":"AutoScan", "BodyName":"Asura 1", "BodyID":19, "Parents":[ {"Null":18}, {"Star":0} ], "StarSystem":"Asura", "SystemAddress":12274907287851, "DistanceFromArrivalLS":3429.862972,
    // "StarType":"Y", "Subclass":4, "StellarMass":0.011719, "Radius":44763468.000000, "AbsoluteMagnitude":23.094269, "Age_MY":2196, "SurfaceTemperature":382.000000, "Luminosity":"V", "SemiMajorAxis":187018096.446991, "Eccentricity":0.177403, "OrbitalInclination":-9.462458, "Periapsis":11.611861, "OrbitalPeriod":30123112.201691, "AscendingNode":-99.327490, "MeanAnomaly":51.740628, "RotationPeriod":136713.382037, "AxialTilt":0.377794,
    // "Rings":[ { "Name":"Asura 1 A Ring", "RingClass":"eRingClass_Rocky", "MassMT":1.9939e+12, "InnerRad":1.1623e+08, "OuterRad":5.7778e+08 } ],
    // "WasDiscovered":true, "WasMapped":false }

    final String starType;
    final int subclass;
    final double stellarMass;
    final int age_MY;
    //radiated electromagnetic power
    final String luminosity;

    public Star(JSONObject jsonObject){
        super(jsonObject);
        starType = (String) jsonObject.get("StarType");
        subclass = (int) jsonObject.get("Subclass");
        stellarMass = (double) jsonObject.get("StellarMass");
        age_MY = (int) jsonObject.get("Age_MY");
        luminosity = (String) jsonObject.get("Luminosity");
    }
}
