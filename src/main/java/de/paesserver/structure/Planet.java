package de.paesserver.structure;

import org.json.simple.JSONObject;

import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;
import java.util.Enumeration;

public class Planet extends Body {

    //{ "timestamp":"2022-03-18T11:20:56Z", "event":"Scan", "ScanType":"Detailed", "BodyName":"Randgnid 2", "BodyID":3, "Parents":[ {"Null":2}, {"Star":0} ], "StarSystem":"Randgnid", "SystemAddress":2688540283179, "DistanceFromArrivalLS":65.490883,
    // "TidalLock":true, "TerraformState":"", "PlanetClass":"Metal rich body", "Atmosphere":"", "AtmosphereType":"None", "Volcanism":"silicate vapour geysers volcanism",
    // "MassEM":1.564247, "Radius":5515080.500000, "SurfaceGravity":20.497974,

    //{ "timestamp":"2022-03-18T11:06:03Z", "event":"Scan", "ScanType":"AutoScan", "BodyName":"Asura 1 b", "BodyID":22, "Parents":[ {"Star":19}, {"Null":18}, {"Star":0} ], "StarSystem":"Asura", "SystemAddress":12274907287851, "DistanceFromArrivalLS":3427.302463,
    // "TidalLock":true, "TerraformState":"", "PlanetClass":"Rocky body", "Atmosphere":"", "AtmosphereType":"None", "Volcanism":"minor metallic magma volcanism",
    // "MassEM":0.009847, "Radius":1493835.125000, "SurfaceGravity":1.758709, "SurfaceTemperature":118.273735, "SurfacePressure":0.000000, "Landable":true,
    // "Materials":[ { "Name":"iron", "Name_Localised":"Eisen", "Percent":19.737341 }, { "Name":"sulphur", "Name_Localised":"Schwefel", "Percent":18.908337 }, { "Name":"carbon", "Name_Localised":"Kohlenstoff", "Percent":15.899953 }, { "Name":"nickel", "Percent":14.928497 }, { "Name":"phosphorus", "Name_Localised":"Phosphor", "Percent":10.179422 }, { "Name":"chromium", "Name_Localised":"Chrom", "Percent":8.876536 }, { "Name":"vanadium", "Percent":4.846804 }, { "Name":"selenium", "Name_Localised":"Selen", "Percent":2.959315 }, { "Name":"tellurium", "Name_Localised":"Tellur", "Percent":1.452948 }, { "Name":"niobium", "Name_Localised":"Niob", "Percent":1.348942 }, { "Name":"mercury", "Name_Localised":"Quecksilber", "Percent":0.861897 } ],
    // "Composition":{ "Ice":0.000000, "Rock":0.904999, "Metal":0.095001 },
    // "SemiMajorAxis":791421014.070511, "Eccentricity":0.000055, "OrbitalInclination":0.171712, "Periapsis":359.297190, "OrbitalPeriod":103634.119034, "AscendingNode":110.688441, "MeanAnomaly":359.893629, "RotationPeriod":103635.016380, "AxialTilt":0.615009, "WasDiscovered":true, "WasMapped":true }


    //From wikipedia:
    // Tidal locking between a pair of co-orbiting astronomical bodies occurs
    // when one of the objects reaches a state where there is no longer any net change in its rotation rate over the course of a complete orbit.
    final public boolean tidalLock;
    final public String terrafromState;
    final public String planetClass;
    final public String atmosphere;
    final public String atmosphereType;
    final public String volcanism;
    //Earth mass multiplied
    final public double massEM;
    final public double surfaceGravity;
    final public double surfacePressure;
    final public boolean landable;
    //TODO Materials
    //TODO Composition

    public Planet(JSONObject jsonObject) {
        super(jsonObject);
        tidalLock = (boolean) jsonObject.get("TidalLock");
        terrafromState = (String) jsonObject.get("TerraformState");
        planetClass = (String) jsonObject.get("PlanetClass");
        atmosphere = (String) jsonObject.get("Atmosphere");
        atmosphereType = (String) jsonObject.get("AtmosphereType");
        volcanism = (String) jsonObject.get("Volcanism");
        massEM = (double) jsonObject.get("MassEM");
        surfaceGravity = (double) jsonObject.get("SurfaceGravity");

        surfacePressure = jsonObject.containsKey("SurfacePressure") ? (double) jsonObject.get("SurfacePressure") : null;
        landable = jsonObject.containsKey("Landable") && (boolean) jsonObject.get("Landable");
        //TODO Materials
        //TODO Composition
    }
}
