package ltd.egoist.fit;

import ltd.egoist.tree.BinaryTree;
import ltd.egoist.tree.RBTree;


import java.util.LinkedList;
import java.util.Objects;

/**
 * @Classname FirstFit
 * @Description TODO
 * @Date 2021/5/9 20:42
 * @Created by Lenovo
 */
public class FirstFit extends Fit {
    /**
     * 我需要两个
     * 空闲表
     * 空闲的     首地址【】   和     大小
     * 使用表
     * 使用表的    首地址   和    大小
     */
//    public LinkedList<Node> freeLinkedList = new LinkedList<>();
//    public LinkedList<Node> useLinkedList = new LinkedList<>();
//

    public void add1() {
        freeRBTree.add(new Node("f1", 10, 20));
        freeRBTree.add(new Node("f2", 50, 40));
        freeRBTree.add(new Node("f3", 100, 15));
    }
//
    public void add2() {
        usedRBTree.add(new Node("u1", 30, 20));
        usedRBTree.add(new Node("u2", 90, 10));
        usedRBTree.add(new Node("u3", 115, 50));
    }
    public RBTree<Node> freeRBTree = new RBTree<>();
    public RBTree<Node> usedRBTree = new RBTree<>();

    public void recycleSquare(int address,int size) {

        if(freeRBTree.contains(new Node("t1",address,0))){
            System.out.println("有空白的上届");
            freeRBTree.remove(new Node("t1",address,0));
            //查询有没有上届
        }else {//没有上届
            System.out.println("没有空白的上界");

        }

    }

    public static class Node implements Comparable{
        String name;
        int address;
        int size;

        public Node(String name, int address, int size) {
            this.name = name;
            this.address = address;
            this.size = size;
        }

        @Override
        public String toString() {
            return "{" +
                    "name='" + name + '\'' +
                    ", address='" + address + '\'' +
                    ", size=" + size +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return address == node.address &&
                    size == node.size;
        }

        @Override
        public int hashCode() {
            return Objects.hash(address, size);
        }

        @Override
        public int compareTo(Object o) {
            return (size+address) - (((Node)o).size+((Node)o).address);
        }
    }
}
