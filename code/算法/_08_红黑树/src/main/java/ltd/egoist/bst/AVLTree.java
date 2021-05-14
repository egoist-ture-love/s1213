package ltd.egoist.bst;

import java.util.Comparator;

public class AVLTree<E> extends BBST<E>{
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

    @Override
    protected void afterRotate(Node<E> parent, Node<E> child, Node<E> grand) {
        super.afterRotate(parent, child, grand);
        updateHeight(grand);
        updateHeight(parent);
    }

    @Override
    protected void rotate(Node<E> r, Node<E> a, Node<E> b, Node<E> c, Node<E> d, Node<E> e, Node<E> f, Node<E> g) {
        super.rotate(r, a, b, c, d, e, f, g);
        updateHeight(b);
        updateHeight(f);
        updateHeight(d);
    }
}
