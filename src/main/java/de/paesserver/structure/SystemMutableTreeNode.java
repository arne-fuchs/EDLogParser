package de.paesserver.structure;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;

public class SystemMutableTreeNode extends BodyMutableTreeNode {
    final public System system;
    public SystemMutableTreeNode(System system){
        super(null);
        this.system = system;
        this.setUserObject(system.toString());
        this.setAllowsChildren(true);
    }
}
