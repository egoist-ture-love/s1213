package tree;

import java.util.Comparator;

public class RBTree<E> extends BBST<E>{

    private static final boolean RED = false;
    private static final boolean BLACK = true;

    public RBTree() {
        this(null);
    }

    public RBTree(Comparator<E> comparator) {
        super(comparator);
    }
    //2.RBNode<E> 特有的方法通过继承实现
    private static class RBNode<E> extends Node<E> {
        boolean color = RED;
        public RBNode(E element, Node<E> parent) {
            super(element, parent);
        }

        @Override
        public String toString() {
            String str = "";
            if (color == RED) {
                str = "R_";
            }
            return str + element.toString();
        }
    }

    @Override
    protected Node createNode(Object element, Node parent) {
        return new RBNode(element,parent);
    }
    /**
     *一些辅助函数
     */

    /**
     * 给节点染色
     * @param node
     * @param color
     * @return
     */
    private Node<E> color(Node<E> node,boolean color){
        if(node == null) return node;
        ((RBNode<E>)node).color = color;
        return node;
    }

    /**
     * 给节点染成红色
     * @param node
     * @return
     */
    private Node<E> red(Node<E> node){
       return color(node,RED);
    }

    /**
     * 给节点染成黑色
     * @param node
     * @return
     */
    private Node<E> black(Node<E> node){
        return color(node,BLACK);
    }

    /**
     * 返回节点的颜色
     * @param node
     * @return
     */
    private boolean colorOf(Node<E> node){
        return node == null ? BLACK : ((RBNode<E>)node).color;
    }

    /**
     * 判断节点是不是黑色
     * @param node
     * @return
     */
    private boolean isBlack(Node<E> node){
        return colorOf(node) == BLACK;
    }

    /**
     * 判断节点是不是红色
     * @param node
     * @return
     */
    private boolean isRed(Node<E> node){
        return colorOf(node) == RED;
    }

    /**
     * 添加红黑树节点
     * @param node
     */
    @Override
    protected void afterAdd(Node<E> node) {
        //1.拿到新添加节点的父节点
        Node<E> parent = node.parent;
        //2.添加的是根节点 或者 上溢到达了根节点
        if(parent == null){
            black(node);
            return;
        }
        //3.如果父节点是黑色直接返回--4种方法
        if(isBlack(parent)) return;
        //4.找到叔父节点
        Node<E> uncle = parent.sibling();
        Node<E> grand = red(parent.parent);
        //5.判断叔父节点是否是红色
        if(isRed(uncle)){
            //叔父节点是红色--找到祖父节点
            black(parent);
            black(uncle);
            afterAdd(grand);
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
    protected void afterRemove(Node<E> node) {
        // 如果删除的节点是红色
        // 或者 用以取代删除节点的子节点是红色
        if (isRed(node)) {
            black(node);
            return;
        }

        Node<E> parent = node.parent;
        // 删除的是根节点
        if (parent == null) return;

        // 删除的是黑色叶子节点【下溢】
        // 判断被删除的node是左还是右
        boolean left = parent.left == null || node.isLeafChild();
        Node<E> sibling = left ? parent.right : parent.left;
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

//	protected void afterRemove(Node<E> node, Node<E> replacement) {
//		// 如果删除的节点是红色
//		if (isRed(node)) return;
//
//		// 用以取代node的子节点是红色
//		if (isRed(replacement)) {
//			black(replacement);
//			return;
//		}
//
//		Node<E> parent = node.parent;
//		// 删除的是根节点
//		if (parent == null) return;
//
//		// 删除的是黑色叶子节点【下溢】
//		// 判断被删除的node是左还是右
//		boolean left = parent.left == null || node.isLeftChild();
//		Node<E> sibling = left ? parent.right : parent.left;
//		if (left) { // 被删除的节点在左边，兄弟节点在右边
//			if (isRed(sibling)) { // 兄弟节点是红色
//				black(sibling);
//				red(parent);
//				rotateLeft(parent);
//				// 更换兄弟
//				sibling = parent.right;
//			}
//
//			// 兄弟节点必然是黑色
//			if (isBlack(sibling.left) && isBlack(sibling.right)) {
//				// 兄弟节点没有1个红色子节点，父节点要向下跟兄弟节点合并
//				boolean parentBlack = isBlack(parent);
//				black(parent);
//				red(sibling);
//				if (parentBlack) {
//					afterRemove(parent, null);
//				}
//			} else { // 兄弟节点至少有1个红色子节点，向兄弟节点借元素
//				// 兄弟节点的左边是黑色，兄弟要先旋转
//				if (isBlack(sibling.right)) {
//					rotateRight(sibling);
//					sibling = parent.right;
//				}
//
//				color(sibling, colorOf(parent));
//				black(sibling.right);
//				black(parent);
//				rotateLeft(parent);
//			}
//		} else { // 被删除的节点在右边，兄弟节点在左边
//			if (isRed(sibling)) { // 兄弟节点是红色
//				black(sibling);
//				red(parent);
//				rotateRight(parent);
//				// 更换兄弟
//				sibling = parent.left;
//			}
//
//			// 兄弟节点必然是黑色
//			if (isBlack(sibling.left) && isBlack(sibling.right)) {
//				// 兄弟节点没有1个红色子节点，父节点要向下跟兄弟节点合并
//				boolean parentBlack = isBlack(parent);
//				black(parent);
//				red(sibling);
//				if (parentBlack) {
//					afterRemove(parent, null);
//				}
//			} else { // 兄弟节点至少有1个红色子节点，向兄弟节点借元素
//				// 兄弟节点的左边是黑色，兄弟要先旋转
//				if (isBlack(sibling.left)) {
//					rotateLeft(sibling);
//					sibling = parent.left;
//				}
//
//				color(sibling, colorOf(parent));
//				black(sibling.left);
//				black(parent);
//				rotateRight(parent);
//			}
//		}
//	}


}
