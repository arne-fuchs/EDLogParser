package de.paesserver.frames;

import de.paesserver.structure.body.Planet;
import de.paesserver.structure.signal.body.BodySignal;

import javax.swing.*;
import java.util.Objects;

public class BodyPane {
    private final JTextArea textArea;
    public Planet planet = null;

    public BodyPane(JTextArea textArea){
        this.textArea = textArea;
    }

    public void setTextForPlanet(Planet planet){
        this.planet = planet;
        setText(planet);
    }

    public void updateText(){
        setText(planet);
    }

    public void wipeText(){
        textArea.setText("");
    }

    private void setText(Planet planet) {
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
                //TODO Terraformable
                if(planet.wasDiscovered){
                    //FSS || FSS+DSS
                    estEarn = 99747 ;
                    eastMapEarn = 415613  ;
                }else {
                    //FSS+FDD || FSS+FD+DSS
                    estEarn = 259343 ;
                    eastMapEarn = 1199337  ;
                }
                break;
            case HighMetalContentPlanet:
                //TODO Terraformable
                if(planet.wasDiscovered){
                    //FSS || FSS+DSS
                    estEarn = 14070 ;
                    eastMapEarn = 58624 ;
                }else {
                    //FSS+FDD || FSS+FD+DSS
                    estEarn = 36581;
                    eastMapEarn = 169171;
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
                //TODO Terraformable
                if(planet.wasDiscovered){
                    //FSS || FSS+DSS
                    estEarn = 500;
                    eastMapEarn = 1476;
                }else {
                    //FSS+FDD || FSS+FD+DSS
                    estEarn = 1300;
                    eastMapEarn = 4260;
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

        StringBuilder stringBuilder = new StringBuilder("---" + planet.bodyName + "---" + "\n" +
                "Body Class:     \t" + planet.planetClass + "\n" +
                "Terraform State:\t" + planet.planetClass + "\n\n" +

                "Discovered:     \t" + planet.wasDiscovered + "\n" +
                "Mapped:         \t" + planet.wasMapped + "\n" +
                "Distance:       \t" + (long)planet.distanceFromArrivalLS + " LS\n" +
                "Landable:       \t" + planet.landable + "\n\n" +

                "Est. earnings:  \t" + estEarn + " Credits\n" +
                "when mapped:    \t" + eastMapEarn + " Credits\n\n" +

                "Gravity:        \t" + planet.surfaceGravity/10 + " G\n" +
                "Earth Masses:   \t" + planet.massEM + "\n" +
                "Radius:         \t" + planet.radius + " KM\n" +
                "Axial Tilt:     \t" + planet.axialTilt + "\u00b0\n" +
                "Semi Major Axis:\t" + planet.semiMajorAxis + "\n" +
                "Eccentricity:   \t" + planet.eccentricity + "\n\n" +

                "Temperature K:  \t" + planet.surfaceTemperature + " Kelvin\n" +
                "Temperature C:  \t" + (planet.surfaceTemperature - 273.000) + "\u00b0Celsius\n" +
                "Atmosphere :    \t" + planet.atmosphereType + "\n" +
                "Pressure:       \t" + planet.surfacePressure + "\n" +
                "Volcanism:      \t" + planet.volcanism + "\n\n" +

                "Rotation Period:\t" + planet.rotationPeriod/100000+ " Days\n" +
                "Orbital Period: \t" + planet.orbitalPeriod/100000 + " Days\n" +
                "Inclination:    \t" + planet.orbitalInclination + "\u00b0\n\n" +

                "Mean Anomaly:   \t" + planet.meanAnomaly + "\n\n" +

                "---Body Signals---" + "\n");
        for(BodySignal bodySignal : planet.bodySignals){
            stringBuilder.append("\n")
                    .append(bodySignal.type_Localised).append(":\t").append(bodySignal.count);
        }
        textArea.setText(stringBuilder.toString());
    }
}
