package de.paesserver.structure;

import org.json.simple.JSONObject;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;

public class BodyTree extends DefaultMutableTreeNode {
    @Override
    public void add(MutableTreeNode newChild){
        if(newChild instanceof Body){

        }else{
            super.add(newChild);
        }
    }
}
