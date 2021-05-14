package ltd.egoist.bst;

import ltd.egoist.printer.BinaryTreeInfo;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree<E> implements BinaryTreeInfo {
    private int size;
    private Node<E> root;
    private Comparator<E> comparator;

    public BinarySearchTree() {
        this(null);
    }

    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    // 元素的数量
    public int size() {
        return size;
    }

    // 是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    // 添加元素
    public void add(E element) {
        elementNotFound(element);
        if (root == null) {
            root = new Node<>(element, null);
            size++;
            return;
        }
        Node<E> preNode = root;
        Node<E> node = root;
        int cmp = 0;
        while (node != null) {
            preNode = node;
            cmp = compare(element, node.element);
            if (cmp > 0) {
                node = node.right;
            }
            if (cmp < 0) {
                node = node.left;
            }
            if (cmp == 0) {
                node.element = element;
                return;
            }
        }
        Node<E> newNode = new Node<>(element, preNode);
        if (cmp > 0) {
            preNode.right = newNode;
        } else {
            preNode.left = newNode;
        }
        size++;
    }

    /**
     * 初版前中后层遍历
     */
//    //层序遍历
//    public void levelOrderTraversal(){
//        if(root == null) return;
//        Queue<Node<E>> queue = new LinkedList<>();
//        queue.offer(root);
//        while(!queue.isEmpty()){
//            Node<E> node = queue.poll();
//            System.out.println(node.element);
//            if(node.left != null){
//                queue.offer(node.left);
//            }
//            if(node.right != null){
//                queue.offer(node.right);
//            }
//        }
//    }
//    //前序遍历
//    public void preorderTraversal(){
//        preorderTraversal(root);
//    }
//    private void preorderTraversal(Node<E> root){
//        if(root == null) return;
//        System.out.println(root.element);
//        preorderTraversal(root.left);
//        preorderTraversal(root.right);
//    }
//    //中序遍历
//    public void inorderTraversal(){
//        inorderTraversal(root);
//    }
//    private void inorderTraversal(Node<E> root){
//        if(root == null) return;
//        inorderTraversal(root.left);
//        System.out.println(root.element);
//        inorderTraversal(root.right);
//    }
//    //后序遍历
//    public void postorderTraversal(){
//        postorderTraversal(root);
//    }
//    private void postorderTraversal(Node<E> root){
//        if(root == null) return;
//        postorderTraversal(root.left);
//        postorderTraversal(root.right);
//        System.out.println(root.element);
//    }

    public static abstract class Victor<E> {
        boolean stop;

        public abstract boolean visit(E element);
    }

    public void levelOrderTraversal(Victor<E> victor) {
        if (root == null || victor == null) return;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            if (victor.visit(node.element)) return;
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }

    public void preorder(Victor<E> visitor) {
        if (visitor == null) return;
        postorder(root, visitor);
    }

    private void preorder(Node<E> node, Victor<E> visitor) {
        if (node == null || visitor.stop) return;
        if (visitor.stop) return;
        visitor.stop = visitor.visit(node.element);
        preorder(node.left, visitor);
        preorder(node.right, visitor);
    }

    public void postorder(Victor<E> visitor) {
        if (visitor == null) {
            return;
        }
        postorder(root, visitor);
    }

    private void postorder(Node<E> node, Victor<E> visitor) {
        if (node == null || visitor.stop) return;
        postorder(node.left, visitor);
        postorder(node.right, visitor);
        if (visitor.stop) return;
        visitor.stop = visitor.visit(node.element);
    }

    public void inorder(Victor<E> visitor) {
        if (visitor == null) return;
        inorder(root, visitor);
    }

    private void inorder(Node<E> node, Victor<E> visitor) {
        if (node == null || visitor.stop) return;

        inorder(node.left, visitor);
        if (visitor.stop) return;
        visitor.stop = visitor.visit(node.element);
        inorder(node.right, visitor);
    }

    //  判断一棵树是否为完全二叉树
    public boolean isComplete() {
        if (root == null) return false;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        boolean leaf = false;
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            if (leaf && !node.isLeaf()) {
                return false;
            }
            if (node.left != null) {
                queue.offer(node.left);
            } else {
                if (node.right != null) {
                    return false;
                }
            }
            if (node.right != null) {
                queue.offer(node.right);
            } else {
                leaf = true;
            }
        }
        return true;
    }

    //找前驱节点
    private Node<E> predecessor(Node<E> node) {
        if (node == null) return null;
        Node<E> p = node.left;
        if (p != null) {
            while (p.right != null) {
                p = p.right;
            }
            return p;
        }
        //发现父节点不为空  并且发现你是父节点左子树
        while (node.parent != null && node == node.parent.left) {
            node = node.parent;
        }
        return node.parent;
    }

    //找后继节点
    private Node<E> successor(Node<E> node) {
        if (node == null) return null;
        Node<E> p = node.right;
        if (p != null) {
            while (p.left != null) {
                p = p.left;
            }
            return p;
        }
        //发现父节点不为空  并且发现你是父节点左子树
        while (node.parent != null && node == node.parent.right) {
            node = node.parent;
        }
        return node.parent;
    }


    // 删除元素
    public void remove(E element) {
        remove(node(element));
    }

    private void remove(Node<E> node) {
        if (node == null) return;
        size--;
        //如果是度为2的节点
        if (node.hasTwoChildren()) {
            Node<E> successor = successor(node);
            //用后继节点的值覆盖掉他的值
            node.element = successor.element;
            //删除它
            node = successor;
        }
        //删除node节点
        Node<E> replacement = node.left != null ? node.left : node.right;
        if (replacement != null) {
            //度为一的节点
            // 更改parent
            replacement.parent = node.parent;
            if (node.parent == null) {//还是根节点
                root = replacement;
            } else if (node == node.parent.left) {
                node.parent.left = replacement;
            } else { // node == node.parent.right
                node.parent.right = replacement;
            }
        } else { // node是叶子节点，但不是根节点
            if (node == node.parent.left) {
                node.parent.left = null;
            } else { // node == node.parent.right
                node.parent.right = null;
            }
        }
    }

    private Node<E> node(E element) {
        Node<E> node = root;
        int cmp = 0;
        while (node != null) {
            cmp = compare(element, node.element);
            if (cmp == 0) {
                return node;
            } else if (cmp > 0) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
        return null;
    }

    // 是否包含某元素
    public boolean contains(E element) {
        return node(element) != null;
    }

    // 清空所有元素
    public void clear() {
    }

    private int compare(E e1, E e2) {
        if (comparator != null) {
            return comparator.compare(e1, e2);
        }
        return ((Comparable<E>) e1).compareTo(e2);
    }

    @Override
    public Object root() {
        return (Node<E>) root;
    }

    @Override
    public Object left(Object node) {
        return ((Node<E>) node).left;
    }

    @Override
    public Object right(Object node) {
        return ((Node<E>) node).right;
    }

    @Override
    public Object string(Object node) {
        Node<E> myNode = (Node<E>) node;
        String parentString = "null";
        if (myNode.parent != null) {
            parentString = myNode.parent.element.toString();
        }
        return myNode.element + "_p(" + parentString + ")";
    }


    public static class Node<E> {
        E element;
        Node<E> parent;
        Node<E> left;
        Node<E> right;

        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }

        public boolean isLeaf() {
            return left == null && right == null;
        }

        public boolean hasTwoChildren() {
            return left != null && right != null;
        }
    }

    private void elementNotFound(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element must not be null");
        }
    }
}
