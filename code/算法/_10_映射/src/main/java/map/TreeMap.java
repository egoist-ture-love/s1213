package map;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class TreeMap<K,V> implements Map<K,V>{

    private static final boolean RED = false;
    private static final boolean BLACK = true;
    protected int size;
    protected Node<K,V> root;
    private Comparator<K> comparator;

    public TreeMap() {
        this(null);
    }

    public TreeMap(Comparator<K> comparator) {
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

    // 清空所有元素
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public V put(K key, V value) {
        KeyNotFound(key);
        if (root == null) {
            root = new Node<>(key,value,null);
            afterPut(root);
            size++;
            return null;
        }
        Node<K,V> preNode = root;
        Node<K,V> node = root;
        int cmp = 0;
        while (node != null) {
            preNode = node;
            cmp = compare(key, node.key);
            if (cmp > 0) {
                node = node.right;
            }
            if (cmp < 0) {
                node = node.left;
            }
            if (cmp == 0) {
                node.key = key;
                V oldValue = node.value;
                node.value = value;
                return oldValue;
            }
        }
        Node<K,V> newNode = new Node<>(key,value,preNode);
        if (cmp > 0) {
            preNode.right = newNode;
        } else {
            preNode.left = newNode;
        }
        size++;
        afterPut(newNode);
        return null;
    }

    protected void afterPut(Node<K,V> node) {
        //1.拿到新添加节点的父节点
        Node<K,V> parent = node.parent;
        //2.添加的是根节点 或者 上溢到达了根节点
        if(parent == null){
            black(node);
            return;
        }
        //3.如果父节点是黑色直接返回--4种方法
        if(isBlack(parent)) return;
        //4.找到叔父节点
        Node<K,V> uncle = parent.sibling();
        Node<K,V> grand = red(parent.parent);
        //5.判断叔父节点是否是红色
        if(isRed(uncle)){
            //叔父节点是红色--找到祖父节点
            black(parent);
            black(uncle);
            afterPut(grand);
            return;
        }
        //叔父节点不是红色--需要旋转的那些操作
        if(parent.isLeafChild()){ //L
            if(node.isLeafChild()){     //LL
                black(parent);
            }else { //LR
                black(node);
                rotateLeft(parent);
            }
            rotateRight(grand);
        }else {     //R
            if(node.isLeafChild()){     //RL
                black(node);
                rotateRight(parent);
            }else { //RR
                black(parent);
            }
            rotateLeft(grand);
        }

    }

    @Override
    public V get(K key) {
        Node<K, V> node = node(key);
        return node != null ? node.value : null;
    }

    @Override
    public V remove(K key) {
       return remove(node(key));
    }

    private V remove(Node<K,V> node) {
        if (node == null) return null;
        size--;
        V oldValue = node.value;

        if (node.hasTwoChildren()) { // 度为2的节点
            // 找到后继节点
            Node<K,V> s = successor(node);
            // 用后继节点的值覆盖度为2的节点的值
            node.key = s.key;
            node.value =s.value;
            // 删除后继节点
            node = s;
        }

        // 删除node节点（node的度必然是1或者0）
        Node<K,V> replacement = node.left != null ? node.left : node.right;

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
        return oldValue;
    }

    private void afterRemove(Node<K,V> node) {
        // 如果删除的节点是红色
        // 或者 用以取代删除节点的子节点是红色
        if (isRed(node)) {
            black(node);
            return;
        }

        Node<K,V> parent = node.parent;
        // 删除的是根节点
        if (parent == null) return;

        // 删除的是黑色叶子节点【下溢】
        // 判断被删除的node是左还是右
        boolean left = parent.left == null || node.isLeafChild();
        Node<K,V> sibling = left ? parent.right : parent.left;
        if (left) { // 被删除的节点在左边，兄弟节点在右边
            if (isRed(sibling)) { // 兄弟节点是红色
                black(sibling);
                red(parent);
                rotateLeft(parent);
                // 更换兄弟
                sibling = parent.right;
            }

            // 兄弟节点必然是黑色
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                // 兄弟节点没有1个红色子节点，父节点要向下跟兄弟节点合并
                boolean parentBlack = isBlack(parent);
                black(parent);
                red(sibling);
                if (parentBlack) {
                    afterRemove(parent);
                }
            } else { // 兄弟节点至少有1个红色子节点，向兄弟节点借元素
                // 兄弟节点的左边是黑色，兄弟要先旋转
                if (isBlack(sibling.right)) {
                    rotateRight(sibling);
                    sibling = parent.right;
                }

                color(sibling, colorOf(parent));
                black(sibling.right);
                black(parent);
                rotateLeft(parent);
            }
        } else { // 被删除的节点在右边，兄弟节点在左边
            if (isRed(sibling)) { // 兄弟节点是红色
                black(sibling);
                red(parent);
                rotateRight(parent);
                // 更换兄弟
                sibling = parent.left;
            }

            // 兄弟节点必然是黑色
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                // 兄弟节点没有1个红色子节点，父节点要向下跟兄弟节点合并
                boolean parentBlack = isBlack(parent);
                black(parent);
                red(sibling);
                if (parentBlack) {
                    afterRemove(parent);
                }
            } else { // 兄弟节点至少有1个红色子节点，向兄弟节点借元素
                // 兄弟节点的左边是黑色，兄弟要先旋转
                if (isBlack(sibling.left)) {
                    rotateLeft(sibling);
                    sibling = parent.left;
                }

                color(sibling, colorOf(parent));
                black(sibling.left);
                black(parent);
                rotateRight(parent);
            }
        }
    }

    @Override
    public boolean containsKey(K key){
        return node(key) != null;
    }

    @Override
    public boolean containsValue(V value) {
        if (root == null) return false;

        Queue<Node<K, V>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node<K, V> node = queue.poll();
            if (valEquals(value, node.value)) return true;

            if (node.left != null) {
                queue.offer(node.left);
            }

            if (node.right != null) {
                queue.offer(node.right);
            }
        }

        return false;
    }

    @Override
    public void traversal(Visitor<K, V> visitor) {
        if(visitor == null) return;
        traversal(root,visitor);
    }

    private void traversal(Node<K,V> node,Visitor<K,V> visitor){
        if(node == null || visitor.stop) return;
        traversal(node.left,visitor);
        if(visitor.stop) return;
        visitor.visit(node.key,node.value);
        traversal(node.right,visitor);
    }

    private static class Node<K,V>{
        K key;
        V value;
        boolean color = RED;
        Node<K,V> parent;
        Node<K,V> left;
        Node<K,V> right;
        public Node(K key,V value, Node<K,V> parent) {
            this.key = key;
            this.value = value;
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

        public Node<K,V> sibling(){
            if(isLeafChild()){
                return parent.right;
            }
            if(isRightChild()){
                return parent.left;
            }
            return null;
        }
    }

    private void KeyNotFound(K k) {
        if (k == null) {
            throw new IllegalArgumentException("key must not be null");
        }
    }

    private int compare(K k1, K k2) {
        if (comparator != null) {
            return comparator.compare(k1, k2);
        }
        return ((Comparable<K>) k1).compareTo(k2);
    }

    /**
     * 进行左旋转
     * @param grand
     */
    private void rotateLeft(Node<K,V> grand){
        //因为是左旋转
        Node<K,V> parent = grand.right;
        Node<K,V> child = parent.left;
        grand.right = child;
        parent.left = grand;
        afterRotate(parent,child,grand);

    }

    /**
     * 进行右旋转
     * @param grand
     */
    private void rotateRight(Node<K,V> grand){
        Node<K,V> parent = grand.left;
        Node<K,V> child = parent.right;
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
    private void afterRotate(Node<K,V> parent,Node<K,V> child,Node<K,V> grand){
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

    /**
     * 给节点染色
     * @param node
     * @param color
     * @return
     */
    private Node<K,V> color(Node<K,V> node,boolean color){
        if(node == null) return node;
        node.color = color;
        return node;
    }

    /**
     * 给节点染成红色
     * @param node
     * @return
     */
    private Node<K,V> red(Node<K,V> node){
        return color(node,RED);
    }

    /**
     * 给节点染成黑色
     * @param node
     * @return
     */
    private Node<K,V> black(Node<K,V> node){
        return color(node,BLACK);
    }

    /**
     * 返回节点的颜色
     * @param node
     * @return
     */
    private boolean colorOf(Node<K,V> node){
        return node == null ? BLACK : node.color;
    }

    /**
     * 判断节点是不是黑色
     * @param node
     * @return
     */
    private boolean isBlack(Node<K,V> node){
        return colorOf(node) == BLACK;
    }

    /**
     * 判断节点是不是红色
     * @param node
     * @return
     */
    private boolean isRed(Node<K,V> node){
        return colorOf(node) == RED;
    }

    //找前驱节点
    private Node<K,V> predecessor(Node<K,V> node) {
        if (node == null) return null;
        Node<K,V> p = node.left;
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
    private Node<K,V> successor(Node<K,V> node) {
        if (node == null) return null;
        Node<K,V> p = node.right;
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

    private Node<K,V> node(K key) {
        Node<K,V> node = root;
        int cmp = 0;
        while (node != null) {
            cmp = compare(key, node.key);
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

    private boolean valEquals(V v1, V v2){
        return v1 == null ? v2 == null : v1.equals(v2);
    }
}
