package de.paesserver.frames.logframe;

import de.paesserver.journalLog.DatabaseSingleton;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Objects;

public class BodyInfo {

    BodyType bodyType;
    String bodyName;
    private String generatedText = "";
    public String getText(){
        return generatedText;
    }

    public void setTextForPlanet(String bodyName) {
        bodyType = BodyType.Planet;
        this.bodyName = bodyName;
        StringBuilder stringBuilder = new StringBuilder();

        String query = "SELECT * FROM PLANET WHERE BodyName = ?";
        try (PreparedStatement statement = DatabaseSingleton.getInstance().databaseConnection.prepareStatement(query)){
            statement.setString(1,bodyName);

            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                long estEarn = 0;
                long eastMapEarn = 0;
                boolean wasDiscovered = resultSet.getLong("WasDiscovered") != 0;
                String terraformState = resultSet.getString("TerraformState");
                switch (Objects.requireNonNull(PlanetClass.getBodyClass(resultSet.getString("PlanetClass")))){
                    case AmmoniaWorld:
                        if(wasDiscovered){
                            //FSS || FSS+DSS
                            estEarn = 143463;
                            eastMapEarn = 1724965;
                        }else {
                            //FSS+FDD || FSS+FD+DSS
                            estEarn = 373004;
                            eastMapEarn = 597762;
                        }
                        break;
                    case EarthlikeWorld:
                        if(wasDiscovered){
                            //FSS || FSS+DSS
                            estEarn = 270290;
                            eastMapEarn = 1126206;
                        }else {
                            //FSS+FDD || FSS+FD+DSS
                            estEarn = 702753 ;
                            eastMapEarn = 3249900;
                        }
                        break;
                    case WaterWorld:
                        if("Terraformable".equals(terraformState)){
                            if(wasDiscovered){
                                //FSS || FSS+DSS
                                estEarn = 268616;
                                eastMapEarn = 1119231;
                            }else {
                                //FSS+FDD || FSS+FD+DSS
                                estEarn = 698400  ;
                                eastMapEarn = 3229773   ;
                            }
                        }else {
                            if(wasDiscovered){
                                //FSS || FSS+DSS
                                estEarn = 99747 ;
                                eastMapEarn = 415613  ;
                            }else {
                                //FSS+FDD || FSS+FD+DSS
                                estEarn = 259343 ;
                                eastMapEarn = 1199337  ;
                            }
                        }
                        break;
                    case HighMetalContentPlanet:
                        if("Terraformable".equals(terraformState)){
                            if(wasDiscovered){
                                //FSS || FSS+DSS
                                estEarn = 163948;
                                eastMapEarn = 683116 ;
                            }else {
                                //FSS+FDD || FSS+FD+DSS
                                estEarn = 426264 ;
                                eastMapEarn = 1971272;
                            }
                        }else {
                            if(wasDiscovered){
                                //FSS || FSS+DSS
                                estEarn = 14070 ;
                                eastMapEarn = 58624 ;
                            }else {
                                //FSS+FDD || FSS+FD+DSS
                                estEarn = 36581;
                                eastMapEarn = 169171;
                            }
                        }
                        break;
                    case IcyBody:
                        if(wasDiscovered){
                            //FSS || FSS+DSS
                            estEarn = 500;
                            eastMapEarn = 1569;
                        }else {
                            //FSS+FDD || FSS+FD+DSS
                            estEarn = 1300;
                            eastMapEarn = 4527;
                        }
                        break;
                    case MetalRichBody:
                        if(wasDiscovered){
                            //FSS || FSS+DSS
                            estEarn = 31632 ;
                            estEarn = 131802 ;
                        }else {
                            //FSS+FDD || FSS+FD+DSS
                            estEarn = 82244;
                            eastMapEarn = 380341 ;
                        }
                        break;
                    case RockyBody:
                        if("Terraformable".equals(terraformState)){
                            if(wasDiscovered){
                                //FSS || FSS+DSS
                                estEarn = 129504;
                                eastMapEarn = 539601;
                            }else {
                                //FSS+FDD || FSS+FD+DSS
                                estEarn = 336711;
                                eastMapEarn = 1557130;
                            }
                        }else {
                            if(wasDiscovered){
                                //FSS || FSS+DSS
                                estEarn = 500;
                                eastMapEarn = 1476;
                            }else {
                                //FSS+FDD || FSS+FD+DSS
                                estEarn = 1300;
                                eastMapEarn = 4260;
                            }
                        }
                        break;
                    case RockyIceBody:
                        if(wasDiscovered){
                            //FSS || FSS+DSS
                            estEarn = 500;
                            eastMapEarn = 1752 ;
                        }else {
                            //FSS+FDD || FSS+FD+DSS
                            estEarn = 1300 ;
                            eastMapEarn = 5057 ;
                        }
                        break;
                    case ClassIGasGiant:
                        if(wasDiscovered){
                            //FSS || FSS+DSS
                            estEarn = 3845 ;
                            eastMapEarn = 16021 ;
                        }else {
                            //FSS+FDD || FSS+FD+DSS
                            estEarn = 9997;
                            eastMapEarn = 46233 ;
                        }
                        break;
                    case ClassIIGasGiant:
                        if(wasDiscovered){
                            //FSS || FSS+DSS
                            estEarn = 28405 ;
                            eastMapEarn = 118354 ;
                        }else {
                            //FSS+FDD || FSS+FD+DSS
                            estEarn = 73853 ;
                            eastMapEarn = 341536 ;
                        }
                        break;
                    case ClassIIIGasGiant:
                        if(wasDiscovered){
                            //FSS || FSS+DSS
                            estEarn = 995;
                            eastMapEarn = 4145 ;
                        }else {
                            //FSS+FDD || FSS+FD+DSS
                            estEarn = 2587 ;
                            eastMapEarn = 11963 ;
                        }
                        break;
                    case ClassIVGasGiant:
                        if(wasDiscovered){
                            //FSS || FSS+DSS
                            estEarn = 1119;
                            eastMapEarn = 4663 ;
                        }else {
                            //FSS+FDD || FSS+FD+DSS
                            estEarn = 2910;
                            eastMapEarn = 13457 ;
                        }
                        break;
                    case ClassVGasGiant:
                        if(wasDiscovered){
                            //FSS || FSS+DSS
                            estEarn = 966;
                            eastMapEarn = 4023;
                        }else {
                            //FSS+FDD || FSS+FD+DSS
                            estEarn = 2510;
                            eastMapEarn =  	11609;
                        }
                        break;
                    case GasGiantwithAmmoniabasedLife:
                        if(wasDiscovered){
                            //FSS || FSS+DSS
                            estEarn = 774;
                            eastMapEarn = 3227;
                        }else {
                            //FSS+FDD || FSS+FD+DSS
                            estEarn = 2014;
                            eastMapEarn = 9312;
                        }
                        break;
                    case GasGiantwithWaterbasedLife:
                        if(wasDiscovered){
                            //FSS || FSS+DSS
                            estEarn = 883;
                            eastMapEarn = 3679;
                        }else {
                            //FSS+FDD || FSS+FD+DSS
                            estEarn = 2295;
                            eastMapEarn = 10616;
                        }
                        break;
                    case HeliumRichGasGiant:
                        if(wasDiscovered){
                            //FSS || FSS+DSS
                            estEarn = 900;
                            eastMapEarn = 3749;
                        }else {
                            //FSS+FDD || FSS+FD+DSS
                            estEarn = 2339;
                            eastMapEarn = 10818;
                        }
                        break;
                    case WaterGiant:
                        if(wasDiscovered){
                            //FSS || FSS+DSS
                            estEarn = 667;
                            eastMapEarn = 2779 ;
                        }else {
                            //FSS+FDD || FSS+FD+DSS
                            estEarn = 1734;
                            eastMapEarn = 8019;
                        }
                        break;
                }

                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

                 stringBuilder= new StringBuilder("---" + resultSet.getString("BodyName") + "---" + "\n" +
                        "Body Class:      " + resultSet.getString("PlanetClass") + "\n" +
                        "Terraform State: " + resultSet.getString("TerraformState") + "\n\n" +

                        "Discovered:      " + wasDiscovered + "\n" +
                        "Mapped:          " + (resultSet.getLong("WasMapped") != 0) + "\n" +
                        "Distance:        " + decimalFormat.format((resultSet.getLong("DistanceFromArrivalLS"))) + " LS\n" +
                        "Landable:        " + (resultSet.getLong("Landable") != 0) + "\n\n" +

                        "Est. earnings:   " + decimalFormat.format(estEarn) + " Credits\n" +
                        "when mapped:     " + decimalFormat.format(eastMapEarn) + " Credits\n\n" +

                        "Gravity:         " + resultSet.getDouble("SurfaceGravity")/10 + " G\n" +
                        "Earth Masses:    " + resultSet.getDouble("MassEM") + "\n" +
                        "Radius:          " + resultSet.getDouble("Radius") + " KM\n" +
                        "Axial Tilt:      " + resultSet.getDouble("AxialTilt") + "\u00b0\n" +
                        "Semi Major Axis: " + resultSet.getDouble("SemiMajorAxis") + "\n" +
                        "Eccentricity:    " + resultSet.getDouble("Eccentricity") + "\n\n" +

                        "Temperature K:   " + resultSet.getDouble("SurfaceTemperature") + " Kelvin\n" +
                        "Temperature C:   " + (resultSet.getDouble("SurfaceTemperature") - 273.000) + "\u00b0 Celsius\n" +
                        "Atmosphere :     " + resultSet.getString("AtmosphereType") + "\n" +
                        "Pressure:        " + resultSet.getDouble("SurfacePressure") + "\n" +
                        "Volcanism:       " + resultSet.getString("Volcanism") + "\n\n" +

                        "Rotation Period: " + resultSet.getDouble("RotationPeriod")/100000+ " Days\n" +
                        "Orbital Period:  " + resultSet.getDouble("OrbitalPeriod")/100000 + " Days\n" +
                        "Inclination:     " + resultSet.getDouble("OrbitalInclination") + "\u00b0\n\n" +

                        "Mean Anomaly:    " + resultSet.getDouble("MeanAnomaly") + "\n\n" +

                        "---Body Signals---" + "\n");

            }
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
        }

        query = "SELECT * FROM BODYSIGNAL WHERE BodyName = ?";
        try (PreparedStatement statement = DatabaseSingleton.getInstance().databaseConnection.prepareStatement(query)){
            statement.setString(1,bodyName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                stringBuilder.append("\n").append(resultSet.getString("Type_Localised")).append(":\t").append(resultSet.getLong("Count"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
        }
        generatedText = stringBuilder.toString();
    }

    public void setTextforStar(String starName) {
        bodyType = BodyType.Star;
        bodyName = starName;
        //Est. earnings missing

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

        String query = "SELECT * FROM STAR WHERE BodyName = ?";
        try (PreparedStatement statement = DatabaseSingleton.getInstance().databaseConnection.prepareStatement(query)){
            statement.setString(1,starName);

            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){
                generatedText = "---" + resultSet.getString("BodyName") + "---" + "\n" +
                        "Star Type:       " + resultSet.getString("StarType") + "\n" +
                        "Subclass:        " + resultSet.getLong("Subclass") + "\n" +
                        "Age:             " + decimalFormat.format(resultSet.getLong("Age_MY")) + " Million Years\n" +
                        "Luminosity:      " + resultSet.getString("Luminosity") + "\n\n" +

                        "Discovered:      " + (resultSet.getLong("WasDiscovered") != 0) + "\n" +
                        "Distance:        " + decimalFormat.format(resultSet.getLong("DistanceFromArrivalLS")) + " LS\n" +

                        "Est. earnings:    n/a yet\n" +

                        "Stellar Mass:    " + resultSet.getDouble("StellarMass") + "\n" +
                        "Radius:          " + resultSet.getDouble("Radius") + " KM\n" +
                        "Axial Tilt:      " + resultSet.getDouble("AxialTilt") + "\u00b0\n" +
                        "Semi Major Axis: " + resultSet.getDouble("SemiMajorAxis") + "\n" +
                        "Eccentricity:    " + resultSet.getDouble("Eccentricity") + "\n\n" +

                        "Temperature K:   " + resultSet.getDouble("SurfaceTemperature") + " Kelvin\n" +
                        "Temperature C:   " + ( resultSet.getDouble("SurfaceTemperature") - 273.000) + "\u00b0Celsius\n" +

                        "Rotation Period: " + resultSet.getDouble("RotationPeriod") / 100000 + " Days\n" +
                        "Orbital Period:  " + resultSet.getDouble("OrbitalPeriod") / 100000 + " Days\n" +
                        "Inclination:     " + resultSet.getDouble("OrbitalInclination") + "\u00b0\n\n" +

                        "Mean Anomaly:    " + resultSet.getDouble("MeanAnomaly");
            }else
                generatedText = "";
        }catch (SQLException e){
            generatedText = "";
            JOptionPane.showMessageDialog(null,e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
        }
    }

    public void setTextForImaginary(String imaginaryBodyName) {
        bodyType = BodyType.Imaginary;
        bodyName = imaginaryBodyName;
        generatedText = "---" + imaginaryBodyName + "---" + "\nNo data available";
    }

    public void updateText(){
        switch (bodyType){
            case Planet: setTextForPlanet(bodyName);
            break;
            case Star: setTextforStar(bodyName);
            break;
            case Imaginary: break;
        }
    }
}
