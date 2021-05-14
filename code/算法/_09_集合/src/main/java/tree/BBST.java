package tree;

import java.util.Comparator;

public class BBST<E> extends BinarySearchTree<E>{
    public BBST() {
        this(null);
    }
    public BBST(Comparator<E> comparator){
        super(comparator);
    }
    /**
     * 进行左旋转
     * @param grand
     */
    protected void rotateLeft(Node<E> grand){
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
    protected void rotateRight(Node<E> grand){
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
    protected void afterRotate(Node<E> parent,Node<E> child,Node<E> grand){
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


    }

    protected void rotate(
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

        // e-f-g
        f.left = e;
        if(e != null){
            e.parent = f;
        }
        f.right = g;
        if(g != null){
            g.parent = f;
        }

        // b-d-f
        d.left = b;
        d.right = f;
        b.parent = d;
        f.parent = d;

    }
}
