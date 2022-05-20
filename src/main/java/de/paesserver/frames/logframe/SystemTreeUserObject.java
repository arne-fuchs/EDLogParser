package de.paesserver.frames.logframe;

public class SystemTreeUserObject {
    BodyType bodyType;
    String nodeName;
    long bodyID;
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
