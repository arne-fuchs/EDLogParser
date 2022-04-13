package de.paesserver.structure;

import de.paesserver.structure.body.BodyMutableTreeNode;

public class SystemMutableTreeNode extends BodyMutableTreeNode {
    final public StarSystem starSystem;
    public SystemMutableTreeNode(StarSystem starSystem){
        super(null);
        this.starSystem = starSystem;
        this.setUserObject(starSystem.toString());
        this.setAllowsChildren(true);
    }
}
