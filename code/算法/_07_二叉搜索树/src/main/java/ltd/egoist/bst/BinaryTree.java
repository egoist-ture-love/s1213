package ltd.egoist.bst;

import ltd.egoist.printer.BinaryTreeInfo;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree<E> implements BinaryTreeInfo {
    protected int size;
    protected Node<E> root;
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

        public boolean isLeafChild(){
            return parent != null && this == parent.left;
        }

        public boolean isRightChild(){
            return parent != null && this == parent.right;
        }
    }

    /**
     * 新建节点方法
     */
    protected Node<E> createNode(E element, Node<E> parent) {
        return new Node<>(element, parent);
    }

    // 元素的数量
    public int size() {
        return size;
    }

    // 是否为空
    public boolean isEmpty() {
        return size == 0;
    }
    // 清空所有元素
    public void clear() {
        root = null;
        size = 0;
    }
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
    //找前驱节点
    protected Node<E> predecessor(Node<E> node) {
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
    protected Node<E> successor(Node<E> node) {
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


}
