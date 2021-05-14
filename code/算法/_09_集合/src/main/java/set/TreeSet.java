package set;

import tree.BinaryTree;
import tree.RBTree;

import java.util.Comparator;

public class TreeSet<E> implements Set<E>{
    private RBTree<E> rbTree;
    public TreeSet(){
        this(null);
    }
    public TreeSet(Comparator<E> comparator){
        rbTree = new RBTree<>(comparator);
    }
    @Override
    public int size() {
        return rbTree.size();
    }

    @Override
    public boolean isEmpty() {
        return rbTree.isEmpty();
    }

    @Override
    public void clear() {
        rbTree.clear();
    }

    @Override
    public boolean contains(E element) {
        return rbTree.contains(element);
    }

    @Override
    public void add(E element) {
        rbTree.add(element);
    }

    @Override
    public void remove(E element) {
        rbTree.remove(element);
    }

    @Override
    public void traversal(Visitor<E> visitor) {
        rbTree.inorder(new BinaryTree.Victor<E>() {
            @Override
            public boolean visit(E element) {
                return visitor.visit(element);
            }
        });
    }
}
