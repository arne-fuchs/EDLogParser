package de.paesserver.frames;

import de.paesserver.structure.body.Planet;

import javax.swing.*;

public class BodyPane {
    private final JTextArea textArea;

    public BodyPane(JTextArea textArea){
        this.textArea = textArea;
    }

    public void setTextForPlanet(Planet planet){
        String stringBuilder = "---" + planet.bodyName + "---" + "\n" +
                "Discovered:    \t" + planet.wasDiscovered + "\n" +
                "Mapped:        \t" + planet.wasMapped + "\n" +
                "Distance:      \t" + planet.distanceFromArrivalLS + "\n" +
                "Landable:      \t" + planet.landable + "\n\n" +

                "Gravity:       \t" + planet.surfaceGravity + "\n" +
                "Radius:        \t" + planet.radius + "\n" +
                "Rotational:    \t" + planet.rotationPeriod + "\n" +
                "Orbital Period:\t" + planet.orbitalPeriod + "\n" +
                "Atmosphere:    \t" + planet.atmosphereType + "\n" +
                "Body Class:    \t" + planet.planetClass;
        textArea.setText(stringBuilder);
    }
}
