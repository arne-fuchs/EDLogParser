package de.paesserver.structure;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;
import java.util.Enumeration;
import java.util.Iterator;

public class BodyMutableTreeNode extends DefaultMutableTreeNode {
    final public Body body;
    public BodyMutableTreeNode(Body body){
        this.body = body;
    }

    @Override
    public void add(MutableTreeNode node){
        //Floall QC-R b48-0 A 1
        //Floall QC-R b48-0 A 1 a
        //Floall QC-R b48-0 A 3

        //Floall QC-R b48-0 B
        //Floall QC-R b48-0 B 3
        //Floall QC-R b48-0 B 3 a
        //Floall QC-R b48-0 B 4

        //diff = 3 a
        //diffChild = 3
        String diffWithNewNode = node.toString().replace(this.toString(),"").trim();
        //If one letter is remaining, it has to be a child of this element
        if(diffWithNewNode.length() == 1){
            super.add(node);
            node.setUserObject(node.toString());
            ((DefaultMutableTreeNode)node).setAllowsChildren(true);
        }else{
            //if more letters are remaining it has to be a child of a child.
            Iterator<TreeNode> treeNodeIterator = children().asIterator();
            while(treeNodeIterator.hasNext()){
                TreeNode child =  treeNodeIterator.next();
                char firstCharOfChild = child.toString().replace(this.toString(),"").trim().charAt(0);
                if(firstCharOfChild == diffWithNewNode.charAt(0)){
                    //If boths first chars after substraction are the same, one of the childs of the childs must get the new node
                    ((DefaultMutableTreeNode)child).add(node);
                    //can break out of iteration because the right child has been found
                    break;
                }
                //Else look at next node
            }
        }
    }

    @Override
    @Deprecated
    public void insert(MutableTreeNode child, int index) {
        this.add(child);
    }

    @Override
    public void remove(int index) {
        super.remove(index);
    }

    @Override
    public void remove(MutableTreeNode node) {
        if(node.toString().equals(this.toString())){
            this.removeFromParent();
        }else{
            String diffWithNewNode = node.toString().replace(this.toString(),"").trim();
            Iterator<TreeNode> treeNodeIterator = children().asIterator();
            while(treeNodeIterator.hasNext()){
                BodyMutableTreeNode child = (BodyMutableTreeNode) treeNodeIterator.next();
                char firstCharOfChild = child.toString().replace(this.toString(),"").trim().charAt(0);
                if(firstCharOfChild == diffWithNewNode.charAt(0)){
                    //If boths first chars after substraction are the same, one of the childs of the childs must get the new node
                    child.remove(node);
                    //can break out of iteration because the right child has been found
                    break;
                }
                //Else look at next node
            }
        }
    }

    @Override
    public void setUserObject(Object object) {
        super.setUserObject(object);
    }

    @Override
    public void removeFromParent() {
        super.removeFromParent();
    }

    @Override
    public void setParent(MutableTreeNode newParent) {
        super.setParent(newParent);
    }

    @Override
    public TreeNode getChildAt(int childIndex) {
        return super.getChildAt(childIndex);
    }

    @Override
    public int getChildCount() {
        return super.getChildCount();
    }

    @Override
    public TreeNode getParent() {
        return super.getParent();
    }

    @Override
    public int getIndex(TreeNode node) {
        return super.getIndex(node);
    }

    @Override
    public boolean getAllowsChildren() {
        return super.getAllowsChildren();
    }

    @Override
    public boolean isLeaf() {
        return super.isLeaf();
    }

    @Override
    public Enumeration<TreeNode> children() {
        return super.children();
    }
}
