package tree;



import java.util.Comparator;


public class BinarySearchTree<E> extends BinaryTree<E> {

    private Comparator<E> comparator;

    public BinarySearchTree() {
        this(null);
    }

    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }


    // 添加元素
    public void add(E element) {
        elementNotFound(element);
        if (root == null) {
            root = createNode(element,null);
            afterAdd(root);
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
        Node<E> newNode = createNode(element,preNode);
        if (cmp > 0) {
            preNode.right = newNode;
        } else {
            preNode.left = newNode;
        }
        size++;
        afterAdd(newNode);
    }

    /**
     * avl树调整平衡方法
     * @param node
     */
    protected void afterAdd(Node<E> node){ }


    // 删除元素
    public void remove(E element) {
        remove(node(element));
    }

    private void remove(Node<E> node) {
        if (node == null) return;

        size--;

        if (node.hasTwoChildren()) { // 度为2的节点
            // 找到后继节点
            Node<E> s = successor(node);
            // 用后继节点的值覆盖度为2的节点的值
            node.element = s.element;
            // 删除后继节点
            node = s;
        }

        // 删除node节点（node的度必然是1或者0）
        Node<E> replacement = node.left != null ? node.left : node.right;

        if (replacement != null) { // node是度为1的节点
            // 更改parent
            replacement.parent = node.parent;
            // 更改parent的left、right的指向
            if (node.parent == null) { // node是度为1的节点并且是根节点
                root = replacement;
            } else if (node == node.parent.left) {
                node.parent.left = replacement;
            } else { // node == node.parent.right
                node.parent.right = replacement;
            }

            // 删除节点之后的处理
            afterRemove(replacement);
        } else if (node.parent == null) { // node是叶子节点并且是根节点
            root = null;

            // 删除节点之后的处理
            afterRemove(node);
        } else { // node是叶子节点，但不是根节点
            if (node == node.parent.left) {
                node.parent.left = null;
            } else { // node == node.parent.right
                node.parent.right = null;
            }

            // 删除节点之后的处理
            afterRemove(node);
        }
    }

    protected  void afterRemove(Node<E> node){}

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

    private int compare(E e1, E e2) {
        if (comparator != null) {
            return comparator.compare(e1, e2);
        }
        return ((Comparable<E>) e1).compareTo(e2);
    }


    private void elementNotFound(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element must not be null");
        }
    }
}
