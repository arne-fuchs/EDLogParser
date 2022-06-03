package de.paesserver.frames.logframe;

import org.jetbrains.annotations.NotNull;

public class SystemTreeUserObject {
    BodyType bodyType;
    String nodeName;
    long bodyID;
    PlanetClass planetClass;

    public SystemTreeUserObject(String nodeName, BodyType bodyType, long bodyID, @NotNull PlanetClass planetClass){
        this.nodeName = nodeName;
        this.bodyType = bodyType;
        this.bodyID = bodyID;
        this.planetClass = planetClass;
    }

    public SystemTreeUserObject(String nodeName, BodyType bodyType, long bodyID){
        this.nodeName = nodeName;
        this.bodyType = bodyType;
        this.bodyID = bodyID;
    }

    @Override
    public String toString(){
        return nodeName;
    }
}
