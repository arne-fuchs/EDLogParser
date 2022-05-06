package de.paesserver.structure;


import de.paesserver.Logger;

import de.paesserver.structure.body.BodyMutableTreeNode;
import de.paesserver.structure.body.ImaginaryBody;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

import java.util.*;

public class SystemMutableTreeNode extends BodyMutableTreeNode {
    final public StarSystem starSystem;

    public SystemMutableTreeNode(StarSystem starSystem) {
        super(starSystem);
        this.starSystem = starSystem;
        this.setUserObject(starSystem.toString());
        this.setAllowsChildren(true);
    }

    @Override
    public void add(MutableTreeNode node) {
        DefaultMutableTreeNode defaultMutableTreeNode = (DefaultMutableTreeNode) node;
        insertionProgress(defaultMutableTreeNode);
        if(children != null){
            children.stream().parallel().forEach(s -> ((BodyMutableTreeNode)s).sortChildren());
            children.sort(Comparator.comparing(Object::toString));
        }
        Logger.logWithDepth(defaultMutableTreeNode,"Done adding body");
    }

    private void insertionProgress(MutableTreeNode node){
        assert node.toString().length() != 0;
        Logger.logWithDepth((DefaultMutableTreeNode) node,"----------------------------------------------------------------------------------");
        Logger.logWithDepth((DefaultMutableTreeNode) node,"Started insertion process for node " + node);
        Logger.logWithDepth((DefaultMutableTreeNode) node,"----------------------------------------------------------------------------------");
        node.setUserObject(node.toString());
        ((DefaultMutableTreeNode) node).setAllowsChildren(true);

        //Floall QC-R b48-0 A 1
        //Floall QC-R b48-0 A 1 a
        //Floall QC-R b48-0 A 3

        //Floall QC-R b48-0 B
        //Floall QC-R b48-0 B 3
        //Floall QC-R b48-0 B 3 a
        //Floall QC-R b48-0 B 4

        //diff = 3 a
        //diffChild = 3

        BodyMutableTreeNode closestTreeNode = (BodyMutableTreeNode) getClosestNode(node);
        int differenceBetweenClosestNodeToNode = removeSystemAndClusterName(node.toString().replace(closestTreeNode.toString(),"").trim()).split(" ").length;
        Logger.logWithDepth((DefaultMutableTreeNode) node,"Closest node: " + closestTreeNode);
        Logger.logWithDepth((DefaultMutableTreeNode) node,"Difference to Node: " + differenceBetweenClosestNodeToNode);
        Logger.logWithDepth((DefaultMutableTreeNode) node,closestTreeNode);
        Logger.logWithDepth((DefaultMutableTreeNode) node,node);

        //If direct child of this node super add method will be called
        //otherwise it will call itself recursive and crash
        if(closestTreeNode == this && differenceBetweenClosestNodeToNode <= 1){
            Logger.logWithDepth((DefaultMutableTreeNode) node,"Direct child of system");
            super.add(node);
            node.setParent(this);
            return;
        }

        //If name is equal, old or imaginary data is present. -> replacing it
        if(node.toString().equals(closestTreeNode.toString())){
            Logger.logWithDepth((DefaultMutableTreeNode) node,"Found place holder or old data after path -> replacing");
            closestTreeNode.body = ((BodyMutableTreeNode)node).body;
            return;
        }

        //If difference between names is one, node is direct child
        //closest:  Floall QC-R b48-0 B 3
        //node:     Floall QC-R b48-0 B 3 a
        if(differenceBetweenClosestNodeToNode == 1){
            Logger.logWithDepth((DefaultMutableTreeNode) node,"Direct child for " + closestTreeNode);
            closestTreeNode.add(node);
            node.setParent(closestTreeNode);
            return;
        }

        //If difference is bigger you have to reconstruct a path
        Logger.logWithDepth((DefaultMutableTreeNode) node,"Beginning reconstructing path...");

        String diffWithNewNodeFromClosestChild = node.toString().replace(closestTreeNode.toString(), "").replace(" Belt Cluster", "").trim();
        diffWithNewNodeFromClosestChild = diffWithNewNodeFromClosestChild.substring(0,diffWithNewNodeFromClosestChild.length()-2).trim();

        int missingBodyCount = diffWithNewNodeFromClosestChild.split(" ").length;
        Logger.logWithDepth((DefaultMutableTreeNode) node,"Difference from node to closest node: " + diffWithNewNodeFromClosestChild);
        Logger.logWithDepth((DefaultMutableTreeNode) node,"Number of bodies missing: " + missingBodyCount);

        //Beginning filling tree with imaginary bodies to maintain structure
        //new node name has to beginn with first body missing:
        // found node: Floall QC-R b48-0 A A Belt Cluster 1
        //If no bodies are in there first body to be added: Floall QC-R b48-0 A
        //And then so on... Floall QC-R b48-0 A A

        for(int i = 0; i < missingBodyCount;i++){
            String imaginaryNodeBodyString = removeSystemAndClusterName(diffWithNewNodeFromClosestChild).split(" ")[0];
            String imaginaryNodeBodyName = closestTreeNode + " " + imaginaryNodeBodyString;
            BodyMutableTreeNode imaginaryNode = new BodyMutableTreeNode(new ImaginaryBody(imaginaryNodeBodyName));

            closestTreeNode.add(imaginaryNode);
            imaginaryNode.setParent(closestTreeNode);
            Logger.logWithDepth((DefaultMutableTreeNode) node,"Added interpreted body: " + imaginaryNode);

            closestTreeNode = imaginaryNode;
            diffWithNewNodeFromClosestChild = node.toString().replace(closestTreeNode.toString(), "").replace(" Belt Cluster", "").trim();
        }

        closestTreeNode.add(node);
        node.setParent(closestTreeNode);
    }

    private MutableTreeNode getClosestNode(MutableTreeNode relativeNode){
        MutableTreeNode cloesestNode = this;
        Iterator<? extends TreeNode> iterator = cloesestNode.children().asIterator();
        //Relativ:  Zejae PR-T b49-0 AB 4 c
        //Fehler:   Zejae PR-T b49-0 A
        //Correct:  Zejae PR-T b49-0

        String[] rNodeParts = removeSystemAndClusterName(relativeNode.toString()).split(" ");
        int depth = 0;

        while (iterator.hasNext()) {
            TreeNode itaredTreeNode = iterator.next();
            String[] iNodeParts = removeSystemAndClusterName(itaredTreeNode.toString()).split(" ");
            if(iNodeParts.length > depth && rNodeParts.length > depth && rNodeParts[depth].equals(iNodeParts[depth])){
                depth++;
                cloesestNode = (MutableTreeNode) itaredTreeNode;
                iterator = cloesestNode.children().asIterator();
            }
        }
        return cloesestNode;
    }



    private String removeSystemAndClusterName(String string){
        return string.replace(starSystem.starSystem,"").trim().replace("Belt Cluster ","").trim();
    }

    @Override
    public String toString(){
        return starSystem.starSystem;
    }
}
