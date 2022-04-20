package de.paesserver.structure.body;

import de.paesserver.structure.body.Body;
import org.json.simple.JSONObject;

public class Star extends Body {

    //{ "timestamp":"2022-03-18T11:03:19Z", "event":"Scan", "ScanType":"AutoScan", "BodyName":"Asura 1", "BodyID":19, "Parents":[ {"Null":18}, {"Star":0} ], "StarSystem":"Asura", "SystemAddress":12274907287851, "DistanceFromArrivalLS":3429.862972,
    // "StarType":"Y", "Subclass":4, "StellarMass":0.011719, "Radius":44763468.000000, "AbsoluteMagnitude":23.094269, "Age_MY":2196, "SurfaceTemperature":382.000000, "Luminosity":"V", "SemiMajorAxis":187018096.446991, "Eccentricity":0.177403, "OrbitalInclination":-9.462458, "Periapsis":11.611861, "OrbitalPeriod":30123112.201691, "AscendingNode":-99.327490, "MeanAnomaly":51.740628, "RotationPeriod":136713.382037, "AxialTilt":0.377794,
    // "Rings":[ { "Name":"Asura 1 A Ring", "RingClass":"eRingClass_Rocky", "MassMT":1.9939e+12, "InnerRad":1.1623e+08, "OuterRad":5.7778e+08 } ],
    // "WasDiscovered":true, "WasMapped":false }

    final public String starType;
    final public long subclass;
    final public double stellarMass;
    final public long age_MY;
    //radiated electromagnetic power
    final public String luminosity;
    final public double radius;
    final public double surfaceTemperature;

    final public double semiMajorAxis;
    //How much the orbital line differents from a perfect circle
    final public double eccentricity;
    //tilt of the orbital line
    final public double orbitalInclination;
    final public double periapsis;
    final public double orbitalPeriod;
    final public double ascendingNode;
    final public double meanAnomaly;
    final public double rotationPeriod;
    final public double axialTilt;

    public Star(JSONObject jsonObject){
        super(jsonObject);
        starType = (String) jsonObject.get("StarType");
        subclass = (long) jsonObject.get("Subclass");
        stellarMass = (double) jsonObject.get("StellarMass");
        age_MY = (long) jsonObject.get("Age_MY");
        luminosity = (String) jsonObject.get("Luminosity");
        radius = (double) jsonObject.get("Radius");
        surfaceTemperature = (double) jsonObject.get("SurfaceTemperature");

        //TODO Check for right values or why values are missing in json
        if(jsonObject.containsKey("Eccentricity")){
            eccentricity = (double) jsonObject.get("Eccentricity");
            semiMajorAxis = (double) jsonObject.get("SemiMajorAxis");
            orbitalInclination = (double)  jsonObject.get("OrbitalInclination");
            periapsis = (double) jsonObject.get("Periapsis");
            orbitalPeriod = (double) jsonObject.get("OrbitalPeriod");
            ascendingNode = (double)jsonObject.get("AscendingNode");
            meanAnomaly = (double) jsonObject.get("MeanAnomaly");
            rotationPeriod = (double) jsonObject.get("RotationPeriod");
            axialTilt = (double) jsonObject.get("AxialTilt");
        }else{
            semiMajorAxis = 0;
            eccentricity = 0;
            orbitalInclination = 0;
            periapsis = 0;
            orbitalPeriod = 0;
            ascendingNode = 0;
            meanAnomaly = 0;
            rotationPeriod = 0;
            axialTilt = 0;
        }
    }
}
