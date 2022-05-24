package de.paesserver.journalLog;

import de.paesserver.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.swing.*;
import java.sql.*;
import java.util.Arrays;
import java.util.Set;

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
                    "BodyID INT, " +
                    "BodyType TEXT, " +
                    "JumpDist REAL," +
                    "FuelUsed REAL, " +
                    "FuelLevel REAL, " +
                    "Factions TEXT, " +
                    "SystemFaction TEXT, " +
                    "Conflicts TEXT, " +
                    "Progress REAL, " +
                    "BodyCount Int, " +
                    "NonBodyCount Int" +
                    ");";

            statement.executeUpdate(sql);

            sql = "CREATE TABLE IF NOT EXISTS STAR(" +
                    "timestamp TEXT, " +
                    "event TEXT, " +
                    "ScanType TEXT, " +
                    "BodyName TEXT PRIMARY KEY, " +
                    "BodyID INT, " +
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
                    "StarSystem TEXT, " +
                    "SystemAddress TEXT, " +
                    "DistanceFromArrivalLS REAL, " +
                    "TidalLock INTEGER, " +
                    "TerraformState TEXT, " +
                    "PlanetClass TEXT, " +
                    "Atmosphere TEXT, " +
                    "AtmosphereType TEXT, " +
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

            sql = "CREATE TABLE IF NOT EXISTS ATMOSPHERECOMPOSITION(" +
                    "timestamp TEXT, " +
                    "BodyName TEXT, " +
                    "Name TEXT, " +
                    "Percent REAL, " +
                    "PRIMARY KEY (BodyName,Name)" +
                    ");";

            statement.executeUpdate(sql);

            sql = "CREATE TABLE IF NOT EXISTS COMPOSITION(" +
                    "timestamp TEXT, " +
                    "BodyName TEXT, " +
                    "Type TEXT, " +
                    "Percent REAL, " +
                    "PRIMARY KEY (BodyName,Type)" +
                    ");";

            statement.executeUpdate(sql);

            sql = "CREATE TABLE IF NOT EXISTS PARENT(" +
                    "timestamp TEXT, " +
                    "BodyName TEXT, " +
                    "ParentType TEXT, " +
                    "ParentBodyID INT, " +
                    "PRIMARY KEY (BodyName,ParentType,ParentBodyID)" +
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
                    "IsStation INTEGER, " +
                    "PRIMARY KEY (SystemAddress,SignalName)" +
                    ");";

            statement.executeUpdate(sql);

            sql = "CREATE TABLE IF NOT EXISTS BELTCLUSTER(" +
                    "timestamp TEXT, " +
                    "event TEXT, " +
                    "ScanType TEXT, " +
                    "BodyName TEXT PRIMARY KEY ," +
                    "BodyID INTEGER, " +
                    "Parents TEXT, " +
                    "StarSystem TEXT, " +
                    "SystemAddress TEXT, " +
                    "DistanceFromArrivalLS REAL, " +
                    "WasDiscovered INTEGER, " +
                    "WasMapped INTEGER" +
                    ");";

            statement.executeUpdate(sql);

            sql = "CREATE TABLE IF NOT EXISTS RING(" +
                    "timestamp TEXT, " +
                    "event TEXT, " +
                    "ScanType TEXT, " +
                    "BodyName TEXT PRIMARY KEY ," +
                    "BodyID INTEGER, " +
                    "Parents TEXT, " +
                    "StarSystem TEXT, " +
                    "SystemAddress TEXT, " +
                    "DistanceFromArrivalLS REAL, " +
                    "SemiMajorAxis REAL, " +
                    "OrbitalPeriod REAL, " +
                    "MeanAnomaly REAL, " +
                    "WasDiscovered INTEGER, " +
                    "WasMapped INTEGER, " +
                    "RingClass TEXT, " +
                    "MassMT REAL, " +
                    "InnerRad REAL, " +
                    "OuterRad REAL " +
                    ");";

            statement.executeUpdate(sql);

            statement.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
        }
    }

    public void insertStar(JSONObject jsonObject) {
        Logger.log(jsonObject.toJSONString().replace(",",",\n"));

        String query = "INSERT INTO STAR(" +
                "timestamp,event,ScanType,BodyName,BodyID,StarSystem,SystemAddress,StarType,StellarMass,Radius," +
                "SurfaceTemperature,SemiMajorAxis,Eccentricity,OrbitalInclination,Periapsis,OrbitalPeriod,AscendingNode,MeanAnomaly,RotationPeriod,AxialTilt," +
                "WasDiscovered,WasMapped,AbsoluteMagnitude,DistanceFromArrivalLS,Age_MY,Luminosity,Subclass) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) " +
                "ON CONFLICT DO NOTHING";

        try (PreparedStatement statement = databaseConnection.prepareStatement(query)) {

            statement.setString(1, jsonObject.get("timestamp").toString());
            statement.setString(2, jsonObject.get("event").toString());
            statement.setString(3, jsonObject.get("ScanType").toString());
            statement.setString(4, jsonObject.get("BodyName").toString());
            statement.setLong(5, (long) jsonObject.get("BodyID"));
            statement.setString(6, jsonObject.get("StarSystem").toString());
            statement.setString(7, jsonObject.get("SystemAddress").toString());
            statement.setString(8, jsonObject.get("StarType").toString());
            statement.setString(9, jsonObject.get("StellarMass").toString());
            statement.setDouble(10, (double) jsonObject.get("Radius"));
            statement.setDouble(11, (double) jsonObject.get("SurfaceTemperature"));
            statement.setDouble(12, jsonObject.containsKey("SemiMajorAxis") ? (double) jsonObject.get("SemiMajorAxis") : -1);
            statement.setDouble(13, jsonObject.containsKey("Eccentricity") ? (double) jsonObject.get("Eccentricity") : -1);
            statement.setDouble(14, jsonObject.containsKey("OrbitalInclination") ? (double) jsonObject.get("OrbitalInclination") : -1);
            statement.setDouble(15, jsonObject.containsKey("Periapsis") ? (double) jsonObject.get("Periapsis") : -1);
            statement.setDouble(16, jsonObject.containsKey("OrbitalPeriod") ? (double) jsonObject.get("OrbitalPeriod") : -1);
            statement.setDouble(17, jsonObject.containsKey("AscendingNode") ? (double) jsonObject.get("AscendingNode") : -1);
            statement.setDouble(18, jsonObject.containsKey("MeanAnomaly") ? (double) jsonObject.get("MeanAnomaly") : -1);
            statement.setDouble(19, jsonObject.containsKey("RotationPeriod") ? (double) jsonObject.get("RotationPeriod") : -1);
            statement.setDouble(20, (double) jsonObject.get("AxialTilt"));
            statement.setInt(21, jsonObject.get("WasDiscovered").toString().equals("true") ? 1 : 0);
            statement.setInt(22, jsonObject.get("WasMapped").toString().equals("true") ? 1 : 0);
            statement.setDouble(23, (double) jsonObject.get("AbsoluteMagnitude"));
            statement.setDouble(24, (double) jsonObject.get("DistanceFromArrivalLS"));
            statement.setLong(25, (long) jsonObject.get("Age_MY"));
            statement.setString(26, jsonObject.get("Luminosity").toString());
            statement.setLong(27, (long) jsonObject.get("Subclass"));

            Logger.log(statement.toString());

            statement.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
        }
        addParents(jsonObject);
    }

    public void insertBody(JSONObject jsonObject) {
        Logger.log(jsonObject.toJSONString().replace(",",",\n"));

        if(jsonObject.get("ScanType").toString().equals("Basic")){
            String query = "INSERT INTO PLANET(timestamp,event,ScanType,BodyName,BodyID,StarSystem,SystemAddress,DistanceFromArrivalLS," +
                    "PlanetClass,MassEM,Radius,SurfaceGravity,SemiMajorAxis,Eccentricity,OrbitalInclination," +
                    "Periapsis,OrbitalPeriod,AscendingNode,MeanAnomaly,RotationPeriod,AxialTilt,WasDiscovered,WasMapped) " +
                    "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) " +
                    "ON CONFLICT DO NOTHING";

            try (PreparedStatement statement = databaseConnection.prepareStatement(query)) {

                statement.setString(1, jsonObject.get("timestamp").toString());
                statement.setString(2, jsonObject.get("event").toString());
                statement.setString(3, jsonObject.get("ScanType").toString());
                statement.setString(4, jsonObject.get("BodyName").toString());
                statement.setLong(5, (long) jsonObject.get("BodyID"));
                statement.setString(6, jsonObject.get("StarSystem").toString());
                statement.setString(7, jsonObject.get("SystemAddress").toString());
                statement.setDouble(8, (double) jsonObject.get("DistanceFromArrivalLS"));
                statement.setString(9, jsonObject.get("PlanetClass").toString());
                statement.setDouble(10, (double) jsonObject.get("MassEM"));
                statement.setDouble(11, (double) jsonObject.get("Radius"));
                statement.setDouble(12, (double) jsonObject.get("SurfaceGravity"));
                statement.setDouble(13, (double) jsonObject.get("Eccentricity"));
                statement.setDouble(14, (double) jsonObject.get("OrbitalInclination"));
                statement.setDouble(15, (double) jsonObject.get("Periapsis"));
                statement.setDouble(16, (double) jsonObject.get("OrbitalPeriod"));
                statement.setDouble(17, (double) jsonObject.get("AscendingNode"));
                statement.setDouble(18, (double) jsonObject.get("MeanAnomaly"));
                statement.setDouble(19, (double) jsonObject.get("RotationPeriod"));
                statement.setDouble(20, (double) jsonObject.get("AxialTilt"));
                statement.setInt(21, jsonObject.get("WasDiscovered").toString().equals("true") ? 1 : 0);
                statement.setInt(22, jsonObject.get("WasMapped").toString().equals("true") ? 1 : 0);

                Logger.log(statement.toString());

                statement.executeUpdate();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
            }
        }else {
            String query = "INSERT INTO PLANET(timestamp,event,ScanType,BodyName,BodyID,StarSystem,SystemAddress,DistanceFromArrivalLS," +
                    "TidalLock,TerraformState,PlanetClass,Atmosphere,AtmosphereType,Volcanism,MassEM,Radius,SurfaceGravity," +
                    "SurfaceTemperature,SurfacePressure,Landable,SemiMajorAxis,Eccentricity,OrbitalInclination," +
                    "Periapsis,OrbitalPeriod,AscendingNode,MeanAnomaly,RotationPeriod,AxialTilt,ReserveLevel,WasDiscovered,WasMapped) " +
                    "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) " +
                    "ON CONFLICT DO NOTHING";


            try (PreparedStatement statement = databaseConnection.prepareStatement(query)) {

                statement.setString(1, jsonObject.get("timestamp").toString());
                statement.setString(2, jsonObject.get("event").toString());
                statement.setString(3, jsonObject.get("ScanType").toString());
                statement.setString(4, jsonObject.get("BodyName").toString());
                statement.setLong(5, (long) jsonObject.get("BodyID"));
                statement.setString(6, jsonObject.get("StarSystem").toString());
                statement.setString(7, jsonObject.get("SystemAddress").toString());
                statement.setDouble(8, (double) jsonObject.get("DistanceFromArrivalLS"));
                statement.setInt(9, jsonObject.containsKey("TidalLock") ? jsonObject.get("TidalLock").toString().equals("true") ? 1 : 0 : 0);
                statement.setString(10, jsonObject.containsKey("TerraformState") ? jsonObject.get("TerraformState").toString() : null);
                statement.setString(11, jsonObject.get("PlanetClass").toString());
                statement.setString(12, jsonObject.containsKey("Atmosphere") ? jsonObject.get("Atmosphere").toString() : "");
                statement.setString(13, jsonObject.containsKey("Atmosphere") ? jsonObject.get("Atmosphere").toString().equals("") ? null : jsonObject.get("AtmosphereType").toString() : null);
                statement.setString(14, jsonObject.containsKey("Volcanism") ? jsonObject.get("Volcanism").toString() : "None");
                statement.setDouble(15, (double) jsonObject.get("MassEM"));
                statement.setDouble(16, (double) jsonObject.get("Radius"));
                statement.setDouble(17, (double) jsonObject.get("SurfaceGravity"));
                statement.setDouble(18, (double) jsonObject.get("SurfaceTemperature"));
                statement.setDouble(19, (double) jsonObject.get("SurfacePressure"));
                statement.setInt(20, jsonObject.get("Landable").equals("true") ? 1 : 0);
                statement.setDouble(21, (double) jsonObject.get("SemiMajorAxis"));
                statement.setDouble(22, (double) jsonObject.get("Eccentricity"));
                statement.setDouble(23, (double) jsonObject.get("OrbitalInclination"));
                statement.setDouble(24, (double) jsonObject.get("Periapsis"));
                statement.setDouble(25, (double) jsonObject.get("OrbitalPeriod"));
                statement.setDouble(26, (double) jsonObject.get("AscendingNode"));
                statement.setDouble(27, (double) jsonObject.get("MeanAnomaly"));
                statement.setDouble(28, (double) jsonObject.get("RotationPeriod"));
                statement.setDouble(29, (double) jsonObject.get("AxialTilt"));
                statement.setString(30, jsonObject.containsKey("ReserveLevel") ? jsonObject.get("ReserveLevel").toString() : null);
                statement.setInt(31, jsonObject.get("WasDiscovered").toString().equals("true") ? 1 : 0);
                statement.setInt(32, jsonObject.get("WasMapped").toString().equals("true") ? 1 : 0);

                Logger.log(statement.toString());

                statement.executeUpdate();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
            }

        }
        addParents(jsonObject);
        addAtmosphereComposition(jsonObject);
        addRings(jsonObject);
        addComposition(jsonObject);
    }

    public void insertSystemSignal(JSONObject jsonObject) {
        Logger.log(jsonObject.toJSONString().replace(",",",\n"));

        String query = "INSERT INTO SYSTEMSIGNAL(" +
                "timestamp,event,SystemAddress,SignalName,SignalName_Localised,USSType,USSType_Localised,SpawningState,SpawningState_Localised," +
                "SpawningFaction,ThreatLevel,TimeRemaining,IsStation) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?) ON CONFLICT DO NOTHING;";

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
            statement.setLong(11, jsonObject.containsKey("ThreatLevel") ? (long) jsonObject.get("ThreatLevel") : 0);
            statement.setDouble(12, jsonObject.containsKey("TimeRemaining") ? (double) jsonObject.get("TimeRemaining") : -1);
            statement.setLong(13, jsonObject.containsKey("IsStation") ? jsonObject.get("IsStation").equals("true") ? 1 : 0 : 0);

            Logger.log(statement.executeUpdate());

            statement.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
        }
    }

    public void insertPlanetSignals(JSONObject jsonObject) {
        Logger.log(jsonObject.toJSONString().replace(",",",\n"));

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
            JOptionPane.showMessageDialog(null,e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
        }
    }

    public void insertBeltCluster(JSONObject jsonObject) {
        Logger.log(jsonObject.toJSONString().replace(",",",\n"));

        String query = "INSERT INTO BELTCLUSTER(timestamp,event,ScanType,BodyName,BodyID,StarSystem,SystemAddress,DistanceFromArrivalLS,WasDiscovered,WasMapped)" +
                " VALUES (?,?,?,?,?,?,?,?,?,?) " +
                "ON CONFLICT DO NOTHING";
        try (PreparedStatement statement = databaseConnection.prepareStatement(query)) {

            statement.setString(1, jsonObject.get("timestamp").toString());
            statement.setString(2, jsonObject.get("event").toString());
            statement.setString(3, jsonObject.get("ScanType").toString());
            statement.setString(4, jsonObject.get("BodyName").toString());
            statement.setLong(5, (long) jsonObject.get("BodyID"));
            statement.setString(6, jsonObject.get("StarSystem").toString());
            statement.setString(7, jsonObject.get("SystemAddress").toString());
            statement.setDouble(8, (double) jsonObject.get("DistanceFromArrivalLS"));
            statement.setInt(9, jsonObject.get("WasDiscovered").toString().equals("true") ? 1 : 0);
            statement.setInt(10, jsonObject.get("WasMapped").toString().equals("true") ? 1 : 0);

            Logger.log(statement.toString());

            statement.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
        }
        addParents(jsonObject);
    }

    public void insertBodyRing(JSONObject jsonObject) {
        Logger.log(jsonObject.toJSONString().replace(",",",\n"));

        String query = "UPDATE RING SET timestamp = ?,event = ?,ScanType = ?,BodyName = ?,BodyID = ?,StarSystem = ?,SystemAddress = ?,DistanceFromArrivalLS = ?," +
                "SemiMajorAxis = ?,OrbitalPeriod = ?,MeanAnomaly = ?," +
                "WasDiscovered = ?,WasMapped = ? "+
                " where BodyName = ?";

        try (PreparedStatement statement = databaseConnection.prepareStatement(query)) {

            statement.setString(1, jsonObject.get("timestamp").toString());
            statement.setString(2, jsonObject.get("event").toString());
            statement.setString(3, jsonObject.get("ScanType").toString());
            statement.setString(4, jsonObject.get("BodyName").toString());
            statement.setLong(5, (long) jsonObject.get("BodyID"));
            statement.setString(6, jsonObject.get("StarSystem").toString());
            statement.setString(7, jsonObject.get("SystemAddress").toString());
            statement.setDouble(8, (double) jsonObject.get("DistanceFromArrivalLS"));

            statement.setDouble(9, (double) jsonObject.get("SemiMajorAxis"));
            statement.setDouble(10, (double) jsonObject.get("OrbitalPeriod"));
            statement.setDouble(11, (double) jsonObject.get("MeanAnomaly"));


            statement.setInt(12, jsonObject.get("WasDiscovered").toString().equals("true") ? 1 : 0);
            statement.setInt(13, jsonObject.get("WasMapped").toString().equals("true") ? 1 : 0);

            statement.setString(14, jsonObject.get("BodyName").toString());

            Logger.log(statement.toString());
            if(statement.executeUpdate() == 0){
                statement.close();

                query = "INSERT INTO RING(timestamp,event,ScanType,BodyName,BodyID,StarSystem,SystemAddress,DistanceFromArrivalLS," +
                        "SemiMajorAxis,OrbitalPeriod,MeanAnomaly," +
                        "WasDiscovered,WasMapped)" +
                        " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?) " +
                        "ON CONFLICT DO NOTHING ";
                try (PreparedStatement insertStatement = databaseConnection.prepareStatement(query)) {

                    insertStatement.setString(1, jsonObject.get("timestamp").toString());
                    insertStatement.setString(2, jsonObject.get("event").toString());
                    insertStatement.setString(3, jsonObject.get("ScanType").toString());
                    insertStatement.setString(4, jsonObject.get("BodyName").toString());
                    insertStatement.setLong(5, (long) jsonObject.get("BodyID"));
                    insertStatement.setString(6, jsonObject.get("StarSystem").toString());
                    insertStatement.setString(7, jsonObject.get("SystemAddress").toString());
                    insertStatement.setDouble(8, (double) jsonObject.get("DistanceFromArrivalLS"));

                    insertStatement.setDouble(9, (double) jsonObject.get("SemiMajorAxis"));
                    insertStatement.setDouble(10, (double) jsonObject.get("OrbitalPeriod"));
                    insertStatement.setDouble(11, (double) jsonObject.get("MeanAnomaly"));


                    insertStatement.setInt(12, jsonObject.get("WasDiscovered").toString().equals("true") ? 1 : 0);
                    insertStatement.setInt(13, jsonObject.get("WasMapped").toString().equals("true") ? 1 : 0);

                    Logger.log(insertStatement.toString());

                    insertStatement.executeUpdate();
                } catch (SQLException ex) {
                    System.out.println(jsonObject.toJSONString().replace(",",",\n"));
                    ex.printStackTrace();
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
        }

        addParents(jsonObject);
    }

    public void insertSystem(JSONObject jsonObject) {
        Logger.log(jsonObject.toJSONString().replace(",",",\n"));

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
            JOptionPane.showMessageDialog(null,e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
        }
    }

    private void addParents(JSONObject jsonObject){
        if(jsonObject.containsKey("Parents")){
            JSONArray jsonArray = (JSONArray) jsonObject.get("Parents");

            for(Object object : jsonArray){
                JSONObject parentObject = (JSONObject) object;

                String query = "INSERT INTO PARENT(" +
                        "timestamp,BodyName,ParentType,ParentBodyID) " +
                        "VALUES (?,?,?,?) " +
                        "ON CONFLICT DO NOTHING";

                try (PreparedStatement statement = databaseConnection.prepareStatement(query)) {

                    statement.setString(1, jsonObject.get("timestamp").toString());
                    statement.setString(2, jsonObject.get("BodyName").toString());

                    Set<String> keySet = parentObject.keySet();

                    for(String key : keySet){
                        statement.setString(3, key);
                        statement.setLong(4, (long) parentObject.get(key));
                    }

                    Logger.log(statement.toString());

                    statement.executeUpdate();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null,e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
                }
            }
        }
    }

    private void addAtmosphereComposition(JSONObject jsonObject){
        if(jsonObject.containsKey("AtmosphereComposition")){
            String query = "INSERT INTO ATMOSPHERECOMPOSITION(timestamp,BodyName,Name,Percent)" +
                    "VALUES (?,?,?,?);";

            JSONArray jsonArray = (JSONArray) jsonObject.get("AtmosphereComposition");
            for(Object object : jsonArray){
                JSONObject compositionObject = (JSONObject) object;
                try(PreparedStatement statement = databaseConnection.prepareStatement(query)) {
                    statement.setString(1,jsonObject.get("timestamp").toString());
                    statement.setString(2,jsonObject.get("BodyName").toString());
                    statement.setString(3,compositionObject.get("Name").toString());
                    statement.setDouble(4,(Double) compositionObject.get("Percent"));

                    Logger.log(statement.toString());

                    statement.executeUpdate();
                }catch (SQLException e){
                    if(e.getErrorCode() != 19){
                       JOptionPane.showMessageDialog(null,e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
                    }
                }
            }
        }
    }

    private void addRings(JSONObject jsonObject){
        if(jsonObject.containsKey("Rings")){
            //System.out.println(jsonObject.toJSONString().replace(",",",\n"));
            Logger.log(jsonObject.toJSONString().replace(",",",\n"));

            JSONArray jsonArray = (JSONArray) jsonObject.get("Rings");

            for(Object object : jsonArray){
                JSONObject ringObject = (JSONObject) object;
                String query = "UPDATE RING SET timestamp = ?,event = ?,ScanType = ?, RingClass = ?, MassMT = ?, InnerRad = ?, OuterRad = ? " +
                        " where BodyName = ?;";

                try (PreparedStatement statement = databaseConnection.prepareStatement(query)) {

                    statement.setString(1, jsonObject.get("timestamp").toString());
                    statement.setString(2, jsonObject.get("event").toString());
                    statement.setString(3, jsonObject.get("ScanType").toString());

                    statement.setString(4, ringObject.get("RingClass").toString());
                    statement.setDouble(5, Double.parseDouble(ringObject.get("MassMT").toString()));
                    statement.setDouble(6, (double) ringObject.get("InnerRad"));
                    statement.setDouble(7, (double) ringObject.get("OuterRad"));


                    statement.setString(8, ringObject.get("Name").toString());

                    Logger.log(statement.toString());
                    if(statement.executeUpdate() == 0){
                        statement.close();


                        query = "INSERT INTO RING ( timestamp, event, ScanType, BodyName, StarSystem, SystemAddress, RingClass, MassMT, InnerRad, OuterRad)" +
                                "VALUES (?,?,?,?,?,?,?,?,?,?);";
                        try (PreparedStatement insertStatement = databaseConnection.prepareStatement(query)) {


                            insertStatement.setString(1, jsonObject.get("timestamp").toString());
                            insertStatement.setString(2, jsonObject.get("event").toString());
                            insertStatement.setString(3, jsonObject.get("ScanType").toString());

                            insertStatement.setString(4, ringObject.get("Name").toString());

                            insertStatement.setString(5, jsonObject.get("StarSystem").toString());
                            insertStatement.setLong(6, (long) jsonObject.get("SystemAddress"));

                            insertStatement.setString(7, ringObject.get("RingClass").toString());
                            insertStatement.setDouble(8, Double.parseDouble(ringObject.get("MassMT").toString()));
                            insertStatement.setDouble(9, (double) ringObject.get("InnerRad"));
                            insertStatement.setDouble(10, (double) ringObject.get("OuterRad"));

                            Logger.log(insertStatement.toString());

                            insertStatement.executeUpdate();


                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null,ex.getMessage() + "\n" + Arrays.toString(ex.getStackTrace()));
                        }
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null,e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
                }
            }
        }
    }

    private void addComposition(JSONObject jsonObject){
        if(jsonObject.containsKey("Composition")){
            String query = "INSERT INTO COMPOSITION(timestamp,BodyName,Type,Percent)" +
                    "VALUES (?,?,?,?);";

            JSONObject composition = (JSONObject) jsonObject.get("Composition");

            Set<String> keySet = composition.keySet();

            keySet.forEach(key -> {
                try(PreparedStatement statement = databaseConnection.prepareStatement(query)) {
                    statement.setString(1,jsonObject.get("timestamp").toString());
                    statement.setString(2,jsonObject.get("BodyName").toString());


                    statement.setString(3,key);
                    statement.setDouble(4,(Double) composition.get(key));

                    Logger.log(statement.toString());

                    statement.executeUpdate();
                }catch (SQLException e){
                    if(e.getErrorCode() != 19){
                        JOptionPane.showMessageDialog(null,e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
                    }
                }
            });
        }
    }

    public void addBodyCounts(JSONObject jsonObject){
        boolean updateable = false;

        String query = "SELECT Progress FROM SYSTEM WHERE SystemAddress = ?";
        try(PreparedStatement statement = databaseConnection.prepareStatement(query)) {
            statement.setLong(1,(long) jsonObject.get("SystemAddress"));

            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                double progress = resultSet.getLong("Progress");
                if(progress < (double) jsonObject.get("Progress"))
                    updateable = true;
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
        }

        if(updateable){
            query = "UPDATE SYSTEM SET Progress = ?, BodyCount = ?, NonBodyCount = ? WHERE SystemAddress = ?;";
            try(PreparedStatement statement = databaseConnection.prepareStatement(query)) {
                statement.setDouble(1,(double) jsonObject.get("Progress"));
                statement.setLong(2,(long) jsonObject.get("BodyCount"));
                statement.setLong(3,(long) jsonObject.get("NonBodyCount"));

                statement.setLong(4,(long) jsonObject.get("SystemAddress"));

                statement.executeUpdate();
            }catch (SQLException e){
                JOptionPane.showMessageDialog(null,e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
            }
        }


    }
}