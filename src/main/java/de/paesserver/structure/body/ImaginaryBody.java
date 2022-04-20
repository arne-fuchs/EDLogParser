package de.paesserver.structure.body;

import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

public class ImaginaryBody {
    final String bodyName;
    public ImaginaryBody(String bodyName){
        this.bodyName = bodyName;
    }

    @Override
    public String toString(){
        return bodyName;
    }

}
