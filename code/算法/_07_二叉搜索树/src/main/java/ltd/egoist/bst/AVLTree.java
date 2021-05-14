package ltd.egoist.bst;

import java.util.Comparator;

public class AVLTree<E> extends BinarySearchTree<E>{
    public AVLTree() {
        this(null);
    }
    public AVLTree(Comparator<E> comparator){
        super(comparator);
    }

    public static class AVLNode<E> extends Node<E>{
        int height = 1;
        public AVLNode(E element, Node<E> parent) {
            super(element, parent);
        }
        public int balanceFactor(){
            int leftHeight = left == null ? 0 : ((AVLNode<E>)left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>)right).height;
            return leftHeight - rightHeight;
        }
        public void updateHeight(){
            int leftHeight = left == null ? 0 : ((AVLNode<E>)left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>)right).height;
            height = 1 + Math.max(leftHeight,rightHeight);
        }

        public Node<E> tallerChild(){
            int leftHeight = left == null ? 0 : ((AVLNode<E>)left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>)right).height;
            if(leftHeight > rightHeight) return left;
            if(leftHeight < rightHeight) return right;
            return isLeafChild() ? left : right;
        }

        @Override
        public String toString() {
            String parentString = "null";
            if (parent != null) {
                parentString = parent.element.toString();
            }
            return element + "_p(" + parentString + ")_h(" + height + ")";
        }
    }


    @Override
    protected Node createNode(Object element, Node parent) {
        return new AVLNode(element,parent);
    }

    /**
     * 判断节点是否平衡
     * @param node
     * @return
     */
    private boolean isBalanced(Node<E> node){
        return Math.abs(((AVLNode<E>)node).balanceFactor()) <= 1;
    }

    /**
     * 更新高度
     * @param node
     */
    private void updateHeight(Node<E> node){
        ((AVLNode<E>)node).updateHeight();
    }

    /**
     * 在添加节点后进行平衡操作
     * @param node
     */
    @Override
    protected void afterAdd(Node<E> node){
        while((node = node.parent) != null){
            if(isBalanced(node)){
                //更新高度
                updateHeight(node);
            }else {
                //恢平衡
                rebalance(node);
                break;
            }
        }
    }

    @Override
    protected void afterRemove(Node<E> node) {
        while((node = node.parent) != null){
            if(isBalanced(node)){
                //更新高度
                updateHeight(node);
            }else {
                //恢平衡
                rebalance(node);
            }
        }
    }

    /**
     * 平衡节点
     * @param grand
     */
    private void rebalance1(Node<E> grand){
        Node<E> parent = ((AVLNode<E>) grand).tallerChild();
        Node<E> node = ((AVLNode<E>) parent).tallerChild();
       if(parent.isLeafChild()){   //L
           if(node.isLeafChild()){ //LL
                rotateRight(grand);
           }else { //LR
                rotateLeft(parent);
                rotateRight(grand);
           }
       }else { //R
           if(node.isLeafChild()){// RL
                rotateRight(parent);
                rotateLeft(grand);
           }else { //RR
                rotateLeft(grand);
           }
       }
    }
    private void rebalance(Node<E> grand) {
        Node<E> parent = ((AVLNode<E>)grand).tallerChild();
        Node<E> node = ((AVLNode<E>)parent).tallerChild();
        if (parent.isLeafChild()) { // L
            if (node.isLeafChild()) { // LL
                rotate(grand, node.left, node,node.right, parent, parent.right, grand,grand.right);
            } else { // LR
                rotate(grand, parent.left,parent, node.left, node, node.right, grand,grand.right);
            }
        } else { // R
            if (node.isLeafChild()) { // RL
                rotate(grand, grand.left, grand,node.left, node, node.right, parent,parent.right);
            } else { // RR
                rotate(grand, grand.left,grand, parent.left, parent, node.left, node,node.right);
            }
        }
    }
    /**
     * 进行左旋转
     * @param grand
     */
    private void rotateLeft(Node<E> grand){
        //因为是左旋转
        Node<E> parent = grand.right;
        Node<E> child = parent.left;
        grand.right = child;
        parent.left = grand;
        afterRotate(parent,child,grand);

    }

    /**
     * 进行右旋转
     * @param grand
     */
    private void rotateRight(Node<E> grand){
        Node<E> parent = grand.left;
        Node<E> child = parent.right;
        grand.left = child;
        parent.right = grand;
        afterRotate(parent,child,grand);

    }

    /**
     * 旋转之后的操作
     * @param parent
     * @param child
     * @param grand
     */
    private void afterRotate(Node<E> parent,Node<E> child,Node<E> grand){
        //更新parent父节点
        parent.parent = grand.parent;
        if(grand.isLeafChild()){
            grand.parent.left = parent;
        }else if(grand.isRightChild()){
            grand.parent.right = parent;
        }else {
            root = parent;
        }
        //更新child的父节点
        if(child != null){
            child.parent = grand;
        }
        //更新grand的parent
        grand.parent = parent;
        //更新高度
        updateHeight(grand);
        updateHeight(parent);

    }

    private void rotate(
           Node<E> r, //子树的根节点
           Node<E> a, Node<E> b,Node<E> c,
           Node<E> d,
           Node<E> e,Node<E> f,Node<E> g) {
        //让d成为这颗子树的根节点
        d.parent = r.parent;
        if(r.isLeafChild()){
            r.parent.left = d;
        }else if(r.isRightChild()){
            r.parent.right = d;
        }else{
            root = d;
        }
        // a-b-c
        b.left = a;
        if(a != null){
            a.parent = b;
        }
        b.right = c;
        if(c != null){
            c.parent = b;
        }
        updateHeight(b);
        // e-f-g
        f.left = e;
        if(e != null){
            e.parent = f;
        }
        f.right = g;
        if(g != null){
            g.parent = f;
        }
        updateHeight(f);
        // b-d-f
        d.left = b;
        d.right = f;
        b.parent = d;
        f.parent = d;
        updateHeight(d);
    }

}
