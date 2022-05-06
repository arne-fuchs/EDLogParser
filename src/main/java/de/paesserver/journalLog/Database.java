package de.paesserver.journalLog;

import de.paesserver.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.*;

public class Database {

    public Connection databaseConnection = null;

    public Database() {
        try {
            Class.forName("org.sqlite.JDBC");
            databaseConnection = DriverManager.getConnection("jdbc:sqlite:log.db");

            Statement statement = databaseConnection.createStatement();

            String sql = "CREATE TABLE IF NOT EXISTS SYSTEM(" +
                    "timestamp TEXT, " +
                    "event TEXT, " +
                    "Taxi INTEGER, " +
                    "Multicrew INTEGER, " +
                    "StarSystem TEXT PRIMARY KEY, " +
                    "SystemAddress TEXT, " +
                    "StarPos TEXT, " +
                    "SystemAllegiance TEXT, " +
                    "SystemEconomy TEXT, " +
                    "SystemEconomy_Localised TEXT, " +
                    "SystemSecondEconomy TEXT, " +
                    "SystemSecondEconomy_Localised TEXT, " +
                    "SystemGovernment TEXT, " +
                    "SystemGovernment_Localised TEXT, " +
                    "SystemSecurity TEXT, " +
                    "SystemSecurity_Localised TEXT, " +
                    "Population INTEGER, " +
                    "Body TEXT, " +
                    "BodyID TEXT, " +
                    "BodyType TEXT, " +
                    "JumpDist REAL," +
                    "FuelUsed REAL, " +
                    "FuelLevel REAL, " +
                    "Factions TEXT, " +
                    "SystemFaction TEXT, " +
                    "Conflicts TEXT" +
                    ");";

            statement.executeUpdate(sql);

            sql = "CREATE TABLE IF NOT EXISTS STAR(" +
                    "timestamp TEXT, " +
                    "event TEXT, " +
                    "ScanType TEXT, " +
                    "BodyName TEXT PRIMARY KEY, " +
                    "BodyID INT, " +
                    "Parents TEXT, " +
                    "StarSystem TEXT, " +
                    "SystemAddress TEXT, " +
                    "StarType TEXT, " +
                    "StellarMass REAL, " +
                    "Radius REAL, " +
                    "SurfaceTemperature REAL, " +
                    "SemiMajorAxis REAL, " +
                    "Eccentricity REAL, " +
                    "OrbitalInclination REAL, " +
                    "Periapsis REAL, " +
                    "OrbitalPeriod REAL, " +
                    "AscendingNode REAL, " +
                    "MeanAnomaly REAL, " +
                    "RotationPeriod REAL, " +
                    "AxialTilt REAL, " +
                    "WasDiscovered INTEGER, " +
                    "WasMapped INTEGER, " +
                    "AbsoluteMagnitude REAl, " +
                    "DistanceFromArrivalLS REAL, " +
                    "Age_MY INTEGER, " +
                    "Luminosity TEXT, " +
                    "Subclass INTEGER" +
                    ");";

            statement.executeUpdate(sql);

            sql = "CREATE TABLE IF NOT EXISTS PLANET(" +
                    "timestamp TEXT, " +
                    "event TEXT, " +
                    "ScanType TEXT, " +
                    "BodyName TEXT PRIMARY KEY, " +
                    "BodyID INT, " +
                    "Parents TEXT, " +
                    "StarSystem TEXT, " +
                    "SystemAddress TEXT, " +
                    "DistanceFromArrivalLS REAL, " +
                    "TidalLock INTEGER, " +
                    "TerraformState TEXT, " +
                    "PlanetClass TEXT, " +
                    "Atmosphere TEXT, " +
                    "AtmosphereType TEXT, " +
                    "AtmosphereComposition TEXT, " +
                    "Volcanism TEXT, " +
                    "MassEM REAL, " +
                    "Radius REAL, " +
                    "SurfaceGravity REAL, " +
                    "SurfaceTemperature REAL, " +
                    "SurfacePressure REAL, " +
                    "Landable INT, " +
                    "Composition TEXT, " +
                    "SemiMajorAxis REAL, " +
                    "Eccentricity REAL, " +
                    "OrbitalInclination REAL, " +
                    "Periapsis REAL, " +
                    "OrbitalPeriod REAL, " +
                    "AscendingNode REAL, " +
                    "MeanAnomaly REAL, " +
                    "RotationPeriod REAL, " +
                    "AxialTilt REAL, " +
                    "ReserveLevel TEXT, " +
                    "WasDiscovered INTEGER, " +
                    "WasMapped INTEGER" +
                    ");";

            statement.executeUpdate(sql);

            sql = "CREATE TABLE IF NOT EXISTS BODYSIGNAL(" +
                    "timestamp TEXT, " +
                    "event TEXT, " +
                    "BodyName TEXT, " +
                    "BodyID INTEGER, " +
                    "SystemAddress TEXT, " +
                    "Type TEXT, " +
                    "Type_Localised TEXT, " +
                    "Count INTEGER, " +
                    "PRIMARY KEY (BodyName,Type)" +
                    //"FOREIGN KEY (Type) REFERENCES PLANET(BodyName)" +
                    ");";

            statement.executeUpdate(sql);

            sql = "CREATE TABLE IF NOT EXISTS SYSTEMSIGNAL(" +
                    "timestamp TEXT, " +
                    "event TEXT, " +
                    "SystemAddress TEXT, " +
                    "SignalName TEXT, " +
                    "SignalName_Localised TEXT, " +
                    "USSType TEXT, " +
                    "USSType_Localised TEXT, " +
                    "SpawningState TEXT, " +
                    "SpawningState_Localised TEXT, " +
                    "SpawningFaction TEXT, " +
                    "ThreatLevel INTEGER, " +
                    "TimeRemaining REAL, " +
                    "IsStation INTEGER" +
                    ");";

            statement.executeUpdate(sql);

            sql = "CREATE TABLE IF NOT EXISTS NONBODY(" +
                    "timestamp TEXT, " +
                    "event TEXT, " +
                    "ScanType TEXT, " +
                    "BodyName TEXT," +
                    "BodyID INTEGER, " +
                    "Parents TEXT, " +
                    "StarSystem TEXT, " +
                    "SystemAddress TEXT, " +
                    "DistanceFromArrivalLS REAL, " +
                    "WasDiscovered INTEGER, " +
                    "WasMapped INTEGER" +
                    ");";

            statement.executeUpdate(sql);

            statement.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

    }

    public void insertStar(JSONObject jsonObject) {
        Logger.log(jsonObject.toJSONString());

        String query = "INSERT INTO STAR(" +
                "timestamp,event,ScanType,BodyName,BodyID,Parents,StarSystem,SystemAddress,StarType,StellarMass,Radius," +
                "SurfaceTemperature,SemiMajorAxis,Eccentricity,OrbitalInclination,Periapsis,OrbitalPeriod,AscendingNode,MeanAnomaly,RotationPeriod,AxialTilt," +
                "WasDiscovered,WasMapped,AbsoluteMagnitude,DistanceFromArrivalLS,Age_MY,Luminosity,Subclass) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) " +
                "ON CONFLICT DO NOTHING";

        try (PreparedStatement statement = databaseConnection.prepareStatement(query)) {

            statement.setString(1, jsonObject.get("timestamp").toString());
            statement.setString(2, jsonObject.get("event").toString());
            statement.setString(3, jsonObject.get("ScanType").toString());
            statement.setString(4, jsonObject.get("BodyName").toString());
            statement.setLong(5, (long) jsonObject.get("BodyID"));
            statement.setString(6, jsonObject.containsKey("Parents") ? jsonObject.get("Parents").toString() : null); //If System has only one Star
            statement.setString(7, jsonObject.get("StarSystem").toString());
            statement.setString(8, jsonObject.get("SystemAddress").toString());
            statement.setString(9, jsonObject.get("StarType").toString());
            statement.setString(10, jsonObject.get("StellarMass").toString());
            statement.setDouble(11, (double) jsonObject.get("Radius"));
            statement.setDouble(12, (double) jsonObject.get("SurfaceTemperature"));
            statement.setDouble(13, jsonObject.containsKey("SemiMajorAxis") ? (double) jsonObject.get("SemiMajorAxis") : -1);
            statement.setDouble(14, jsonObject.containsKey("Eccentricity") ? (double) jsonObject.get("Eccentricity") : -1);
            statement.setDouble(15, jsonObject.containsKey("OrbitalInclination") ? (double) jsonObject.get("OrbitalInclination") : -1);
            statement.setDouble(16, jsonObject.containsKey("Periapsis") ? (double) jsonObject.get("Periapsis") : -1);
            statement.setDouble(17, jsonObject.containsKey("OrbitalPeriod") ? (double) jsonObject.get("OrbitalPeriod") : -1);
            statement.setDouble(18, jsonObject.containsKey("AscendingNode") ? (double) jsonObject.get("AscendingNode") : -1);
            statement.setDouble(19, jsonObject.containsKey("MeanAnomaly") ? (double) jsonObject.get("MeanAnomaly") : -1);
            statement.setDouble(20, jsonObject.containsKey("RotationPeriod") ? (double) jsonObject.get("RotationPeriod") : -1);
            statement.setDouble(21, (double) jsonObject.get("AxialTilt"));
            statement.setInt(22, jsonObject.get("WasDiscovered").toString().equals("true") ? 1 : 0);
            statement.setInt(22, jsonObject.get("WasMapped").toString().equals("true") ? 1 : 0);
            statement.setDouble(24, (double) jsonObject.get("AbsoluteMagnitude"));
            statement.setDouble(25, (double) jsonObject.get("DistanceFromArrivalLS"));
            statement.setLong(26, (long) jsonObject.get("Age_MY"));
            statement.setString(27, jsonObject.get("Luminosity").toString());
            statement.setLong(28, (long) jsonObject.get("Subclass"));

            Logger.log(statement.toString());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertBody(JSONObject jsonObject) {
        Logger.log(jsonObject.toJSONString());

        String query = "INSERT INTO PLANET(timestamp,event,ScanType,BodyName,BodyID,Parents,StarSystem,SystemAddress,DistanceFromArrivalLS," +
                "TidalLock,TerraformState,PlanetClass,Atmosphere,AtmosphereType,AtmosphereComposition,Volcanism,MassEM,Radius,SurfaceGravity," +
                "SurfaceTemperature,SurfacePressure,Landable,Composition,SemiMajorAxis,Eccentricity,OrbitalInclination," +
                "Periapsis,OrbitalPeriod,AscendingNode,MeanAnomaly,RotationPeriod,AxialTilt,ReserveLevel,WasDiscovered,WasMapped) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) " +
                "ON CONFLICT DO NOTHING";


        try (PreparedStatement statement = databaseConnection.prepareStatement(query)) {

            statement.setString(1, jsonObject.get("timestamp").toString());
            statement.setString(2, jsonObject.get("event").toString());
            statement.setString(3, jsonObject.get("ScanType").toString());
            statement.setString(4, jsonObject.get("BodyName").toString());
            statement.setLong(5, (long) jsonObject.get("BodyID"));
            statement.setString(6, jsonObject.get("Parents").toString());
            statement.setString(7, jsonObject.get("StarSystem").toString());
            statement.setString(8, jsonObject.get("SystemAddress").toString());
            statement.setDouble(9, (double) jsonObject.get("DistanceFromArrivalLS"));
            statement.setInt(10, jsonObject.get("TidalLock").toString().equals("true") ? 1 : 0);
            statement.setString(11, jsonObject.get("TerraformState").toString());
            statement.setString(12, jsonObject.get("PlanetClass").toString());
            statement.setString(13, jsonObject.get("Atmosphere").toString());
            statement.setString(14, jsonObject.get("Atmosphere").toString().equals("") ? null : jsonObject.get("AtmosphereType").toString());
            statement.setString(15, jsonObject.get("Atmosphere").toString().equals("") ? null : jsonObject.get("AtmosphereComposition").toString());
            statement.setString(16, jsonObject.get("Volcanism").toString());
            statement.setDouble(17, (double) jsonObject.get("MassEM"));
            statement.setDouble(18, (double) jsonObject.get("Radius"));
            statement.setDouble(19, (double) jsonObject.get("SurfaceGravity"));
            statement.setDouble(20, (double) jsonObject.get("SurfaceTemperature"));
            statement.setDouble(21, (double) jsonObject.get("SurfacePressure"));
            statement.setInt(22, jsonObject.get("Landable").equals("true") ? 1 : 0);
            statement.setString(23, jsonObject.containsKey("Composition") ? jsonObject.get("Composition").toString() : null);
            statement.setDouble(24, (double) jsonObject.get("SemiMajorAxis"));
            statement.setDouble(25, (double) jsonObject.get("Eccentricity"));
            statement.setDouble(26, (double) jsonObject.get("OrbitalInclination"));
            statement.setDouble(27, (double) jsonObject.get("Periapsis"));
            statement.setDouble(28, (double) jsonObject.get("OrbitalPeriod"));
            statement.setDouble(29, (double) jsonObject.get("AscendingNode"));
            statement.setDouble(30, (double) jsonObject.get("MeanAnomaly"));
            statement.setDouble(31, (double) jsonObject.get("RotationPeriod"));
            statement.setDouble(32, (double) jsonObject.get("AxialTilt"));
            statement.setString(33, jsonObject.containsKey("ReserveLevel") ? jsonObject.get("ReserveLevel").toString() : null);
            statement.setInt(34, jsonObject.get("WasDiscovered").toString().equals("true") ? 1 : 0);
            statement.setInt(35, jsonObject.get("WasMapped").toString().equals("true") ? 1 : 0);

            Logger.log(statement.toString());

            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(jsonObject.toJSONString());
            throw new RuntimeException(e);
        }

    }

    public void insertSystemSignal(JSONObject jsonObject) {
        Logger.log(jsonObject.toJSONString());

        String query = "INSERT INTO SYSTEMSIGNAL(" +
                "timestamp,event,SystemAddress,SignalName,SignalName_Localised,USSType,USSType_Localised,SpawningState,SpawningState_Localised," +
                "SpawningFaction,ThreatLevel,TimeRemaining,IsStation) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?) " +
                "ON CONFLICT DO NOTHING";

        try (PreparedStatement statement = databaseConnection.prepareStatement(query)) {

            statement.setString(1, jsonObject.get("timestamp").toString());
            statement.setString(2, jsonObject.get("event").toString());
            statement.setString(3, jsonObject.get("SystemAddress").toString());
            statement.setString(4, jsonObject.get("SignalName").toString());
            statement.setString(5, jsonObject.containsKey("SignalName_Localised") ? jsonObject.get("SignalName_Localised").toString() : null);
            statement.setString(6, jsonObject.containsKey("USSType") ? jsonObject.get("USSType").toString() : null);
            statement.setString(7, jsonObject.containsKey("USSType_Localised") ? jsonObject.get("USSType_Localised").toString() : null);
            statement.setString(8, jsonObject.containsKey("SpawningState") ? jsonObject.get("SpawningState").toString() : null);
            statement.setString(9, jsonObject.containsKey("SpawningState_Localised") ? jsonObject.get("SpawningState_Localised").toString() : null);
            statement.setString(10, jsonObject.containsKey("SpawningFaction") ? jsonObject.get("SpawningFaction").toString() : null);
            statement.setDouble(11, jsonObject.containsKey("TimeRemaining") ? (double) jsonObject.get("TimeRemaining") : -1);
            statement.setString(12, jsonObject.containsKey("USSType") ? jsonObject.get("USSType").toString() : null);
            statement.setLong(13, jsonObject.containsKey("IsStation") ? jsonObject.get("IsStation").equals("true") ? 1 : 0 : 0);

            Logger.log(statement.executeUpdate());

            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(jsonObject.toJSONString());
            throw new RuntimeException(e);
        }
    }

    public void insertPlanetSignals(JSONObject jsonObject) {
        Logger.log(jsonObject.toJSONString());

        String query = "INSERT INTO BODYSIGNAL (timestamp,event,BodyName,BodyID,SystemAddress,Type,Type_Localised,Count) " +
                "VALUES (?,?,?,?,?,?,?,?) " +
                "ON CONFLICT DO NOTHING";
        try (PreparedStatement statement = databaseConnection.prepareStatement(query)) {

            JSONArray jsonArray = (JSONArray) jsonObject.get("Signals");

            statement.setString(1, jsonObject.get("timestamp").toString());
            statement.setString(2, jsonObject.get("event").toString());
            statement.setString(3, jsonObject.get("BodyName").toString());
            statement.setLong(4, (long) jsonObject.get("BodyID"));
            statement.setString(5, jsonObject.get("SystemAddress").toString());

            for (Object object : jsonArray) {
                JSONObject signal = (JSONObject) object;
                statement.setString(6, signal.get("Type").toString());
                statement.setString(7, signal.containsKey("Type_Localised") ? signal.get("Type_Localised").toString() : null);
                statement.setLong(8, (long) signal.get("Count"));

                statement.executeUpdate();
            }

        } catch (SQLException e) {
            System.out.println(jsonObject.toJSONString());
            throw new RuntimeException(e);
        }
    }

    public void insertBeltCluster(JSONObject jsonObject) {
        Logger.log(jsonObject.toJSONString());

        String query = "INSERT INTO NONBODY(timestamp,event,ScanType,BodyName,BodyID,Parents,StarSystem,SystemAddress,DistanceFromArrivalLS,WasDiscovered,WasMapped)" +
                " VALUES (?,?,?,?,?,?,?,?,?,?,?) " +
                "ON CONFLICT DO NOTHING";
        try (PreparedStatement statement = databaseConnection.prepareStatement(query)) {

            statement.setString(1, jsonObject.get("timestamp").toString());
            statement.setString(2, jsonObject.get("event").toString());
            statement.setString(3, jsonObject.get("ScanType").toString());
            statement.setString(4, jsonObject.get("BodyName").toString());
            statement.setLong(5, (long) jsonObject.get("BodyID"));
            statement.setString(6, jsonObject.get("Parents").toString());
            statement.setString(7, jsonObject.get("StarSystem").toString());
            statement.setString(8, jsonObject.get("SystemAddress").toString());
            statement.setDouble(9, (double) jsonObject.get("DistanceFromArrivalLS"));
            statement.setInt(10, jsonObject.get("WasDiscovered").toString().equals("true") ? 1 : 0);
            statement.setInt(11, jsonObject.get("WasMapped").toString().equals("true") ? 1 : 0);

            Logger.log(statement.toString());

            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(jsonObject.toJSONString());
            throw new RuntimeException(e);
        }
    }

    public void insertSystem(JSONObject jsonObject) {
        Logger.log(jsonObject.toJSONString());

        String query = "INSERT INTO SYSTEM (" +
                "timestamp,event,Taxi,Multicrew,StarSystem,SystemAddress,StarPos," +
                "SystemAllegiance,SystemEconomy,SystemEconomy_Localised,SystemSecondEconomy,SystemSecondEconomy_Localised,SystemGovernment,SystemGovernment_Localised," +
                "SystemSecurity,SystemSecurity_Localised,Population,Body,BodyID,BodyType,JumpDist,FuelUsed,FuelLevel,Factions,SystemFaction,Conflicts) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) " +
                "ON CONFLICT DO NOTHING";

        try (PreparedStatement statement = databaseConnection.prepareStatement(query)) {

            statement.setString(1, jsonObject.get("timestamp").toString());
            statement.setString(2, jsonObject.get("event").toString());
            statement.setLong(3, jsonObject.containsKey("Taxi") ? (jsonObject.get("Taxi").toString().equals("false") ? 0 : 1) : 0);
            statement.setLong(4, jsonObject.containsKey("Multicrew") ? (jsonObject.get("Multicrew").toString().equals("false") ? 0 : 1) : 0);
            statement.setString(5, jsonObject.get("StarSystem").toString());
            statement.setString(6, jsonObject.get("SystemAddress").toString());
            statement.setString(7, jsonObject.get("StarPos").toString());

            statement.setString(8, jsonObject.get("SystemAllegiance").toString());
            statement.setString(9, jsonObject.get("SystemEconomy").toString().equals("") ? null : jsonObject.get("SystemEconomy").toString());
            statement.setString(10, jsonObject.get("SystemEconomy_Localised").toString());
            statement.setString(11, jsonObject.get("SystemSecondEconomy").toString());
            statement.setString(12, jsonObject.get("SystemSecondEconomy_Localised").toString());

            statement.setString(13, jsonObject.get("SystemGovernment").toString());
            statement.setString(14, jsonObject.get("SystemGovernment_Localised").toString());
            statement.setString(15, jsonObject.get("SystemSecurity").toString());
            statement.setString(16, jsonObject.get("SystemSecurity_Localised").toString());
            statement.setLong(17, (long) jsonObject.get("Population"));

            statement.setString(18, jsonObject.get("Body").toString());
            statement.setLong(19, (long) jsonObject.get("BodyID"));
            statement.setString(20, jsonObject.get("BodyType").toString());
            statement.setDouble(21, jsonObject.containsKey("JumpDist") ? (double) jsonObject.get("JumpDist") : -1);
            statement.setDouble(22, jsonObject.containsKey("FuelUsed") ? (double) jsonObject.get("FuelUsed") : -1);
            statement.setDouble(23, jsonObject.containsKey("FuelLevel") ? (double) jsonObject.get("FuelLevel") : -1);
            statement.setString(24, jsonObject.containsKey("Factions") ? jsonObject.get("Factions").toString() : null);
            statement.setString(25, jsonObject.containsKey("SystemFaction") ? jsonObject.get("SystemFaction").toString() : null);
            statement.setString(26, jsonObject.containsKey("Conflicts") ? jsonObject.get("Conflicts").toString() : null);

            Logger.log(statement.toString());

            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(jsonObject.toJSONString());
            throw new RuntimeException(e);
        }
    }
}
