package de.paesserver.structure.body;

import de.paesserver.structure.signal.body.BodySignal;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public abstract class Body {
    //{ "timestamp":"2022-03-18T11:20:56Z", "event":"Scan", "ScanType":"Detailed", "BodyName":"Randgnid 2", "BodyID":3, "Parents":[ {"Null":2}, {"Star":0} ], "StarSystem":"Randgnid", "SystemAddress":2688540283179, "DistanceFromArrivalLS":65.490883,
    // "TidalLock":true, "TerraformState":"", "PlanetClass":"Metal rich body", "Atmosphere":"", "AtmosphereType":"None", "Volcanism":"silicate vapour geysers volcanism", "MassEM":1.564247, "Radius":5515080.500000, "SurfaceGravity":20.497974,

    //{ "timestamp":"2022-03-18T11:06:03Z", "event":"Scan", "ScanType":"AutoScan", "BodyName":"Asura 1 b", "BodyID":22, "Parents":[ {"Star":19}, {"Null":18}, {"Star":0} ], "StarSystem":"Asura", "SystemAddress":12274907287851, "DistanceFromArrivalLS":3427.302463,
    // "TidalLock":true, "TerraformState":"", "PlanetClass":"Rocky body", "Atmosphere":"", "AtmosphereType":"None", "Volcanism":"minor metallic magma volcanism", "MassEM":0.009847, "Radius":1493835.125000, "SurfaceGravity":1.758709, "SurfaceTemperature":118.273735, "SurfacePressure":0.000000, "Landable":true, "Materials":[ { "Name":"iron", "Name_Localised":"Eisen", "Percent":19.737341 }, { "Name":"sulphur", "Name_Localised":"Schwefel", "Percent":18.908337 }, { "Name":"carbon", "Name_Localised":"Kohlenstoff", "Percent":15.899953 }, { "Name":"nickel", "Percent":14.928497 }, { "Name":"phosphorus", "Name_Localised":"Phosphor", "Percent":10.179422 }, { "Name":"chromium", "Name_Localised":"Chrom", "Percent":8.876536 }, { "Name":"vanadium", "Percent":4.846804 }, { "Name":"selenium", "Name_Localised":"Selen", "Percent":2.959315 }, { "Name":"tellurium", "Name_Localised":"Tellur", "Percent":1.452948 }, { "Name":"niobium", "Name_Localised":"Niob", "Percent":1.348942 }, { "Name":"mercury", "Name_Localised":"Quecksilber", "Percent":0.861897 } ], "Composition":{ "Ice":0.000000, "Rock":0.904999, "Metal":0.095001 }, "SemiMajorAxis":791421014.070511, "Eccentricity":0.000055, "OrbitalInclination":0.171712, "Periapsis":359.297190, "OrbitalPeriod":103634.119034, "AscendingNode":110.688441, "MeanAnomaly":359.893629, "RotationPeriod":103635.016380, "AxialTilt":0.615009, "WasDiscovered":true, "WasMapped":true }

    //{ "timestamp":"2022-03-18T11:03:19Z", "event":"Scan", "ScanType":"AutoScan", "BodyName":"Asura 1", "BodyID":19, "Parents":[ {"Null":18}, {"Star":0} ], "StarSystem":"Asura", "SystemAddress":12274907287851, "DistanceFromArrivalLS":3429.862972,
    // "StarType":"Y", "Subclass":4, "StellarMass":0.011719, "Radius":44763468.000000, "AbsoluteMagnitude":23.094269, "Age_MY":2196, "SurfaceTemperature":382.000000, "Luminosity":"V", "SemiMajorAxis":187018096.446991, "Eccentricity":0.177403, "OrbitalInclination":-9.462458, "Periapsis":11.611861, "OrbitalPeriod":30123112.201691, "AscendingNode":-99.327490, "MeanAnomaly":51.740628, "RotationPeriod":136713.382037, "AxialTilt":0.377794,
    // "Rings":[ { "Name":"Asura 1 A Ring", "RingClass":"eRingClass_Rocky", "MassMT":1.9939e+12, "InnerRad":1.1623e+08, "OuterRad":5.7778e+08 } ],
    // "WasDiscovered":true, "WasMapped":false }

    final public String bodyName;
    final public long bodyID;
    //TODO -> parents
    final public String starSystem;
    final public long systemAddress;
    final public double distanceFromArrivalLS;

    //TODO Rings
    final public boolean wasDiscovered;
    final public boolean wasMapped;

    public BodySignal[] bodySignals;

    public Body(JSONObject jsonObject){
        //System.out.println("\nInitializing Body");
        //System.out.println(jsonObject.toJSONString());

        bodyName = (String) jsonObject.get("BodyName");
        bodyID = (long) jsonObject.get("BodyID");
        //TODO -> parents
        starSystem = (String) jsonObject.get("StarSystem");
        systemAddress = (long) jsonObject.get("SystemAddress");
        distanceFromArrivalLS = (double) jsonObject.get("DistanceFromArrivalLS");
        wasDiscovered = (boolean) jsonObject.get("WasDiscovered");
        wasMapped = (boolean) jsonObject.get("WasMapped");
        bodySignals = new BodySignal[0];
    }
    @Override
    public String toString(){
        return bodyName;
    }
}
