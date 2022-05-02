package de.paesserver.structure.body;

import de.paesserver.structure.StarSystem;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Iterator;

public class BodyMutableTreeNode extends DefaultMutableTreeNode {
    public Body body;
    public StarSystem starSystem;
    public ImaginaryBody imaginaryBody;
    public BodyMutableTreeNode(Body body){
        this.body = body;
        starSystem = null;
        imaginaryBody = null;
    }

    public BodyMutableTreeNode(ImaginaryBody imaginaryBody){
        this.body = null;
        this.imaginaryBody = imaginaryBody;
    }

    public BodyMutableTreeNode(StarSystem starSystem){
        this.body = null;
        this.imaginaryBody = null;
        this.starSystem = starSystem;
    }

    @Override
    public String toString(){
        if(body != null)
            return this.body.bodyName;
        if(imaginaryBody != null)
            return this.imaginaryBody.toString();
        return starSystem.toString();
    }

    @Override
    public void add(MutableTreeNode node){
        super.add(node);
    }

    @Override
    @Deprecated
    public void insert(MutableTreeNode child, int index) {
        super.insert(child,index);
    }

    @Override
    public void remove(int index) {
        super.remove(index);
    }

    @Override
    public void remove(MutableTreeNode node) {
        super.remove(node);
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

    protected void correctParentsForChildren(){
        if(children != null){
            TreeNode[] localChildren = children.toArray(new TreeNode[0]);
            for(TreeNode treeNode : localChildren){
                ((BodyMutableTreeNode)treeNode).setParent(this);
                ((BodyMutableTreeNode)treeNode).correctParentsForChildren();
            }
        }
    }

    public void sortChildren(){
        if(children != null) {
            children.stream().parallel().forEach(s -> ((BodyMutableTreeNode)s).sortChildren());
            children.sort(Comparator.comparing(Object::toString));
        }
    }
}
