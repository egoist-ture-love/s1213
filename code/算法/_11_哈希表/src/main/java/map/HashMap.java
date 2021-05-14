package map;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * @Classname HashMap
 * @Description TODO
 * @Date 2021/5/2 10:33
 * @Created by Lenovo
 */
public class HashMap<K, V> implements Map<K, V> {

    private static final boolean RED = false;
    private static final boolean BLACK = true;
    //记录哈希表存储多少键值对
    private int size;
    //定义一个数组存放元素  一个数组中有索引和数据两个东西
    private Node<K, V>[] table;
    private static final int DEFAULT_CAPACITY = 1 << 4;
    private static final float DEFAULT_LOAD_FACTORY = 0.75f;

    public HashMap() {
        table = new Node[DEFAULT_CAPACITY];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        if (size == 0) return;
        size = 0;
        for (int i = 0; i < table.length; i++) {
            table[i] = null;
        }
    }

    @Override
    public V put(K key, V value) {
        resize();
        int index = index(key);
        Node<K, V> root = table[index];
        if (root == null) {
            root = createNode(key, value, null);
            table[index] = root;
            size++;
            fixAfterPut(root);
            return null;
        }
        Node<K, V> preNode = root;
        Node<K, V> node = root;
        int cmp = 0;
        K k1 = key;
        int h1 = hash(k1);
        Node<K,V> result = null;
        boolean searched = false;
        do {
            preNode = node;
            K k2 = node.key;
            int h2 = node.hash;
            if(h1 > h2){
                cmp = 1;
            }else if(h1 < h2){
                cmp = -1;
            }else if(Objects.equals(k1,k2)){
                cmp = 0;
            }else if(k1 != null && k2 != null
                    && k1.getClass() == k2.getClass()
                    && k1 instanceof Comparable
                    && (cmp = ((Comparable) k1).compareTo(k2)) != 0)
            {

            }else if(searched){
                cmp = System.identityHashCode(k1) - System.identityHashCode(k2);
            }else {
                if((node.left != null && (result = node(node.left,k1)) != null) || (node.right != null && (result = node(node.right,k1)) != null)){
                    node = result;
                    cmp = 0;
                }else {
                    searched = true;
                    cmp = System.identityHashCode(k1) - System.identityHashCode(k2);
                }
            }

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
        } while (node != null);
        Node<K, V> newNode = createNode(key, value, preNode);
        if (cmp > 0) {
            preNode.right = newNode;
        } else {
            preNode.left = newNode;
        }
        size++;
        fixAfterPut(newNode);
        return null;
    }

    private void resize(){
        if(size / table.length <= DEFAULT_LOAD_FACTORY) return;
        Node<K,V> [] oldTable = table;
        table = new Node[oldTable.length << 1];
        Queue<Node<K,V>> queue = new LinkedList<>();
        for(int i = 0; i < oldTable.length; i++){
            if(oldTable[i] == null) continue;
            queue.offer(oldTable[i]);
            while (!queue.isEmpty()){
                Node<K,V> node = queue.poll();
                if(node.left != null){
                    queue.offer(node.left);
                }
                if(node.right != null){
                    queue.offer(node.right);
                }
                moveNode(node);
            }
        }
    }

    protected Node<K,V> createNode(K key,V value,Node<K,V> parent){
        return new Node<>(key,value,parent);
    }

    private void moveNode(Node<K,V> newNode){
        newNode.parent = null;
        newNode.left = null;
        newNode.right = null;
        newNode.color = RED;

        int index = index(newNode);
        Node<K, V> root = table[index];
        if (root == null) {
            root = newNode;
            table[index] = root;
            fixAfterPut(root);
            return;
        }
        Node<K, V> preNode = root;
        Node<K, V> node = root;
        int cmp = 0;
        K k1 = newNode.key;
        int h1 = hash(k1);
        Node<K,V> result = null;
        boolean searched = false;
        do {
            preNode = node;
            K k2 = node.key;
            int h2 = node.hash;
            if(h1 > h2){
                cmp = 1;
            }else if(h1 < h2){
                cmp = -1;
            }else if(k1 != null && k2 != null
                    && k1.getClass() == k2.getClass()
                    && k1 instanceof Comparable
                    && (cmp = ((Comparable) k1).compareTo(k2)) != 0)
            {
            }else{
                cmp = System.identityHashCode(k1) - System.identityHashCode(k2);
            }

            if (cmp > 0) {
                node = node.right;
            }
            if (cmp < 0) {
                node = node.left;
            }

        } while (node != null);
        newNode.parent = preNode;
        if (cmp > 0) {
            preNode.right = newNode;
        } else {
            preNode.left = newNode;
        }
        fixAfterPut(newNode);
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

    @Override
    public boolean containsKey(K key) {
        return node(key) != null;
    }

    @Override
    public boolean containsValue(V value) {
        if(size == 0) return false;
        Queue<Node<K,V>> queue = new LinkedList<>();
        for(int i = 0; i < table.length; i++){
            if(table[i] == null) continue;
            queue.offer(table[i]);
            while (!queue.isEmpty()){
                Node<K,V> node = queue.poll();
                if(Objects.equals(value,node.value)) return true;
                if(node.left != null){
                    queue.offer(node.left);
                }
                if(node.right != null){
                    queue.offer(node.right);
                }
            }
        }
        return false;
    }

    @Override
    public void traversal(Visitor<K, V> visitor) {
        if(size == 0 || visitor == null) return;
        Queue<Node<K,V>> queue = new LinkedList<>();
        for(int i = 0; i < table.length; i++){
            if(table[i] == null) continue;
            queue.offer(table[i]);
            while (!queue.isEmpty()){
                Node<K,V> node = queue.poll();
                if(visitor.visit(node.key,node.value)) return;
                if(node.left != null){
                    queue.offer(node.left);
                }
                if(node.right != null){
                    queue.offer(node.right);
                }
            }
        }
        return;
    }

    public static class Node<K, V> {
        int hash;
        K key;
        V value;
        boolean color = RED;
        Node<K, V> parent;
        Node<K, V> left;
        Node<K, V> right;

        public Node(K key, V value, Node<K, V> parent) {
            this.key = key;
            int hash = key == null ? 0 : key.hashCode();
            this.hash = hash ^ (hash >>> 16);
            this.value = value;
            this.parent = parent;
        }

        public boolean isLeaf() {
            return left == null && right == null;
        }

        public boolean hasTwoChildren() {
            return left != null && right != null;
        }

        public boolean isLeafChild() {
            return parent != null && this == parent.left;
        }

        public boolean isRightChild() {
            return parent != null && this == parent.right;
        }

        public Node<K, V> sibling() {
            if (isLeafChild()) {
                return parent.right;
            }
            if (isRightChild()) {
                return parent.left;
            }
            return null;
        }
    }

    private int index(K key) {
        return hash(key) & (table.length - 1);
    }

    private int hash(K key){
        if(key == null) return 0;
        int hash = key.hashCode();
        return hash ^ (hash >>> 16);
    }

    private int index(Node<K,V> node) {
        return node.hash & (table.length - 1);
    }

    protected void fixAfterPut(Node<K, V> node) {
        //1.拿到新添加节点的父节点
        Node<K, V> parent = node.parent;
        //2.添加的是根节点 或者 上溢到达了根节点
        if (parent == null) {
            black(node);
            return;
        }
        //3.如果父节点是黑色直接返回--4种方法
        if (isBlack(parent)) return;
        //4.找到叔父节点
        Node<K, V> uncle = parent.sibling();
        Node<K, V> grand = red(parent.parent);
        //5.判断叔父节点是否是红色
        if (isRed(uncle)) {
            //叔父节点是红色--找到祖父节点
            black(parent);
            black(uncle);
            fixAfterPut(grand);
            return;
        }
        //叔父节点不是红色--需要旋转的那些操作
        if (parent.isLeafChild()) { //L
            if (node.isLeafChild()) {     //LL
                black(parent);
            } else { //LR
                black(node);
                rotateLeft(parent);
            }
            rotateRight(grand);
        } else {     //R
            if (node.isLeafChild()) {     //RL
                black(node);
                rotateRight(parent);
            } else { //RR
                black(parent);
            }
            rotateLeft(grand);
        }

    }

    /**
     * 进行左旋转
     *
     * @param grand
     */
    private void rotateLeft(Node<K, V> grand) {
        //因为是左旋转
        Node<K, V> parent = grand.right;
        Node<K, V> child = parent.left;
        grand.right = child;
        parent.left = grand;
        afterRotate(parent, child, grand);

    }

    /**
     * 进行右旋转
     *
     * @param grand
     */
    private void rotateRight(Node<K, V> grand) {
        Node<K, V> parent = grand.left;
        Node<K, V> child = parent.right;
        grand.left = child;
        parent.right = grand;
        afterRotate(parent, child, grand);
    }

    /**
     * 旋转之后的操作
     *
     * @param parent
     * @param child
     * @param grand
     */
    private void afterRotate(Node<K, V> parent, Node<K, V> child, Node<K, V> grand) {
        //更新parent父节点
        parent.parent = grand.parent;
        if (grand.isLeafChild()) {
            grand.parent.left = parent;
        } else if (grand.isRightChild()) {
            grand.parent.right = parent;
        } else {
            table[index(grand)] = parent;
        }
        //更新child的父节点
        if (child != null) {
            child.parent = grand;
        }
        //更新grand的parent
        grand.parent = parent;
        //更新高度


    }

    /**
     * 给节点染色
     *
     * @param node
     * @param color
     * @return
     */
    private Node<K, V> color(Node<K, V> node, boolean color) {
        if (node == null) return node;
        node.color = color;
        return node;
    }

    /**
     * 给节点染成红色
     *
     * @param node
     * @return
     */
    private Node<K, V> red(Node<K, V> node) {
        return color(node, RED);
    }

    /**
     * 给节点染成黑色
     *
     * @param node
     * @return
     */
    private Node<K, V> black(Node<K, V> node) {
        return color(node, BLACK);
    }

    /**
     * 返回节点的颜色
     *
     * @param node
     * @return
     */
    private boolean colorOf(Node<K, V> node) {
        return node == null ? BLACK : node.color;
    }

    /**
     * 判断节点是不是黑色
     *
     * @param node
     * @return
     */
    private boolean isBlack(Node<K, V> node) {
        return colorOf(node) == BLACK;
    }

    /**
     * 判断节点是不是红色
     *
     * @param node
     * @return
     */
    private boolean isRed(Node<K, V> node) {
        return colorOf(node) == RED;
    }

    private int compare(K k1, K k2, int h1, int h2) {
        //1.先比较哈希值
        int result = h1 - h2;
        if(result != 0) return result;
        //2.比较equals
        if(Objects.equals(k1,k2)) return 0;//他们两个相等--返回覆盖就行
        //3.哈希值相等但是不是equals
        //比较类名
        if(k1 != null && k2 != null){
            String k1Cls = k1.getClass().getName();
            String k2Cls = k2.getClass().getName();
            result  = k1Cls.compareTo(k2Cls);
            if(result != 0) return result; //不是同种类型
            //是同种类型且具备可比较性
            if(k1 instanceof Comparable){
                return ((Comparable)k1).compareTo(k2);
            }

        }
        //同一种类型 哈希值相等 但是不具备可比较性
        //k1 != null k2 == null
        //k1 == null k2 != null
       return System.identityHashCode(k1) - System.identityHashCode(k2); //直接内存地址相减
    }

    private Node<K,V> node(K key){
        Node<K,V> root = table[index(key)];
        return root == null ? null : node(root,key);
    }

    private Node<K,V> node(Node<K,V> node,K k1){
        int h1 = hash(k1);
        Node<K,V> result = null;
        int cmp = 0;
        while (node != null){
            K k2 = node.key;
            int h2 = node.hash;
            //先比较哈希值
            if(h1 > h2){
                node = node.right;
            }else if(h1 < h2){
                node = node.left;
            }else if(Objects.equals(k1,k2)){
                return node;
            }else if(k1 != null && k2 != null
                    && k1.getClass() == k2.getClass()
                    && k1 instanceof Comparable
                    && (cmp = ((Comparable) k1).compareTo(k2)) != 0)
            {
                node = cmp > 0 ? node.right : node.left;
            }else if(node.right != null && (result = node(node.right,k1)) != null){//哈希值相等不具备可比较性
                return result;
            }else {
                node = node.left;
            }
        }
        return null;
    }

    protected V remove(Node<K,V> node){
        if (node == null) return null;
        Node<K,V> willNode = node;
        size--;
        V oldValue = node.value;

        if (node.hasTwoChildren()) { // 度为2的节点
            // 找到后继节点
            Node<K,V> s = successor(node);
            // 用后继节点的值覆盖度为2的节点的值
            node.key = s.key;
            node.value =s.value;
            node.hash = s.hash;
            // 删除后继节点
            node = s;
        }

        // 删除node节点（node的度必然是1或者0）
        Node<K,V> replacement = node.left != null ? node.left : node.right;
        int index = index(node);
        if (replacement != null) { // node是度为1的节点
            // 更改parent
            replacement.parent = node.parent;
            // 更改parent的left、right的指向
            if (node.parent == null) { // node是度为1的节点并且是根节点
                table[index] = replacement;
            } else if (node == node.parent.left) {
                node.parent.left = replacement;
            } else { // node == node.parent.right
                node.parent.right = replacement;
            }

            // 删除节点之后的处理
            fixAfterRemove(replacement);
        } else if (node.parent == null) { // node是叶子节点并且是根节点
            table[index] = null;
            // 删除节点之后的处理
            fixAfterRemove(node);
        } else { // node是叶子节点，但不是根节点
            if (node == node.parent.left) {
                node.parent.left = null;
            } else { // node == node.parent.right
                node.parent.right = null;
            }

            // 删除节点之后的处理
            fixAfterRemove(node);
        }
        afterRemove(willNode,node);
        return oldValue;
    }

    private void fixAfterRemove(Node<K,V> node) {
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
                    fixAfterRemove(parent);
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
                    fixAfterRemove(parent);
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

    protected void afterRemove(Node<K, V> willNode, Node<K, V> removedNode) { }
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
}
