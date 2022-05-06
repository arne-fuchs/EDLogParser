package de.paesserver.frames.logframe.components;

import de.paesserver.frames.logframe.LogFrameComponentsSingleton;
import de.paesserver.structure.body.*;
import de.paesserver.structure.signal.body.BodySignal;

import java.text.DecimalFormat;
import java.util.Objects;

public class BodyInfo {
    public static Body body = null;


    public static void updateText(){
        if(body instanceof Star)
            setTextforStar((Star)body);
        else if (body instanceof Planet) {
            setTextForPlanet((Planet) body);
        }else if (body instanceof BeltCluster)
            setTextForBeltCluster((BeltCluster) body);
    }

    public static void wipeText(){
        LogFrameComponentsSingleton.getPlanetInfoTextArea().setText("");
    }

    public static void setTextForPlanet(Planet planet) {
        long estEarn = 0;
        long eastMapEarn = 0;
        switch (Objects.requireNonNull(planet.planetClass)){
            case AmmoniaWorld:
                if(planet.wasDiscovered){
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
                if(planet.wasDiscovered){
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
                if("Terraformable".equals(planet.terraformState)){
                    if(planet.wasDiscovered){
                        //FSS || FSS+DSS
                        estEarn = 268616;
                        eastMapEarn = 1119231;
                    }else {
                        //FSS+FDD || FSS+FD+DSS
                        estEarn = 698400  ;
                        eastMapEarn = 3229773   ;
                    }
                }else {
                    if(planet.wasDiscovered){
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
                if("Terraformable".equals(planet.terraformState)){
                    if(planet.wasDiscovered){
                        //FSS || FSS+DSS
                        estEarn = 163948;
                        eastMapEarn = 683116 ;
                    }else {
                        //FSS+FDD || FSS+FD+DSS
                        estEarn = 426264 ;
                        eastMapEarn = 1971272;
                    }
                }else {
                    if(planet.wasDiscovered){
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
                if(planet.wasDiscovered){
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
                if(planet.wasDiscovered){
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
                if("Terraformable".equals(planet.terraformState)){
                    if(planet.wasDiscovered){
                        //FSS || FSS+DSS
                        estEarn = 129504;
                        eastMapEarn = 539601;
                    }else {
                        //FSS+FDD || FSS+FD+DSS
                        estEarn = 336711;
                        eastMapEarn = 1557130;
                    }
                }else {
                    if(planet.wasDiscovered){
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
                if(planet.wasDiscovered){
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
                if(planet.wasDiscovered){
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
                if(planet.wasDiscovered){
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
                if(planet.wasDiscovered){
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
                if(planet.wasDiscovered){
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
                if(planet.wasDiscovered){
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
                if(planet.wasDiscovered){
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
                if(planet.wasDiscovered){
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
                if(planet.wasDiscovered){
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
                if(planet.wasDiscovered){
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

        StringBuilder stringBuilder = new StringBuilder("---" + planet.bodyName + "---" + "\n" +
                "Body Class:     " + PlanetClass.getString(planet.planetClass) + "\n" +
                "Terraform State:" + planet.terraformState + "\n\n" +

                "Discovered:     " + planet.wasDiscovered + "\n" +
                "Mapped:         " + planet.wasMapped + "\n" +
                "Distance:       " + decimalFormat.format((long)planet.distanceFromArrivalLS) + " LS\n" +
                "Landable:       " + planet.landable + "\n\n" +

                "Est. earnings:  " + decimalFormat.format(estEarn) + " Credits\n" +
                "when mapped:    " + decimalFormat.format(eastMapEarn) + " Credits\n\n" +

                "Gravity:        " + planet.surfaceGravity/10 + " G\n" +
                "Earth Masses:   " + planet.massEM + "\n" +
                "Radius:         " + planet.radius + " KM\n" +
                "Axial Tilt:     " + planet.axialTilt + "\u00b0\n" +
                "Semi Major Axis:" + planet.semiMajorAxis + "\n" +
                "Eccentricity:   " + planet.eccentricity + "\n\n" +

                "Temperature K:  " + planet.surfaceTemperature + " Kelvin\n" +
                "Temperature C:  " + (planet.surfaceTemperature - 273.000) + " \u00b0Celsius\n" +
                "Atmosphere :    " + planet.atmosphereType + "\n" +
                "Pressure:       " + planet.surfacePressure + "\n" +
                "Volcanism:      " + planet.volcanism + "\n\n" +

                "Rotation Period:" + planet.rotationPeriod/100000+ " Days\n" +
                "Orbital Period: " + planet.orbitalPeriod/100000 + " Days\n" +
                "Inclination:    " + planet.orbitalInclination + "\u00b0\n\n" +

                "Mean Anomaly:   " + planet.meanAnomaly + "\n\n" +

                "---Body Signals---" + "\n");

        for(BodySignal bodySignal : planet.bodySignals){
            stringBuilder.append("\n")
                    .append(bodySignal.type_Localised).append(":\t").append(bodySignal.count);
        }
        LogFrameComponentsSingleton.getPlanetInfoTextArea().setText(stringBuilder.toString());
    }

    public static void setTextforStar(Star star) {
        //Est. earnings missing

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

        String stringBuilder = "---" + star.bodyName + "---" + "\n" +
                "Star Type:      " + star.starType + "\n" +
                "Subclass:       " + star.subclass + "\n" +
                "Age:            " + decimalFormat.format(star.age_MY) + " Million Years\n" +
                "Luminosity:     " + star.luminosity + "\n\n" +

                "Discovered:     " + star.wasDiscovered + "\n" +
                "Distance:       " + decimalFormat.format((long) star.distanceFromArrivalLS) + " LS\n" +

                "Est. earnings:   n/a yet\n" +

                "Stellar Mass:   " + star.stellarMass + "\n" +
                "Radius:         " + star.radius + " KM\n" +
                "Axial Tilt:     " + star.axialTilt + "\u00b0\n" +
                "Semi Major Axis:" + star.semiMajorAxis + "\n" +
                "Eccentricity:   " + star.eccentricity + "\n\n" +

                "Temperature K:  " + star.surfaceTemperature + " Kelvin\n" +
                "Temperature C:  " + (star.surfaceTemperature - 273.000) + "\u00b0Celsius\n" +

                "Rotation Period:" + star.rotationPeriod / 100000 + " Days\n" +
                "Orbital Period: " + star.orbitalPeriod / 100000 + " Days\n" +
                "Inclination:    " + star.orbitalInclination + "\u00b0\n\n" +

                "Mean Anomaly:   " + star.meanAnomaly + "\n\n" +

                "---Body Signals---" + "\n";
        LogFrameComponentsSingleton.getPlanetInfoTextArea().setText(stringBuilder);
    }

    public static void setTextForImaginary(ImaginaryBody body) {
        String stringBuilder = "---" + body.bodyName + "---" + "\n" +
                "No data available";

        LogFrameComponentsSingleton.getPlanetInfoTextArea().setText(stringBuilder);
    }

    public static void setTextForBeltCluster(BeltCluster body) {

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

        String stringBuilder = "---" + body.bodyName + "---" + "\n" +

                "Discovered:     " + body.wasDiscovered + "\n" +
                "Distance:       " + decimalFormat.format((long)body.distanceFromArrivalLS) + " LS";

        LogFrameComponentsSingleton.getPlanetInfoTextArea().setText(stringBuilder);
    }
}