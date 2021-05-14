package ltd.egoist.test01;

import ltd.egoist.fit.FirstFit;
import ltd.egoist.tree.RBTree;
import org.junit.Test;

/**
 * @Classname Test01
 * @Description TODO
 * @Date 2021/5/9 21:56
 * @Created by Lenovo
 */
public class Test01 {
    @Test
   public void method01(){
        FirstFit firstFit = new FirstFit();
        firstFit.add1();
        firstFit.add2();
        firstFit.recycleSquare(30,20);
    }

    @Test
    public void method02(){
        FirstFit.Node n1 = new FirstFit.Node("n1", 12, 30);
        FirstFit.Node n2 = new FirstFit.Node("n2", 13, 40);
        FirstFit.Node n3 = new FirstFit.Node("n3", 14, 15);
        FirstFit.Node n4 = new FirstFit.Node("n3", 17, 15);
        RBTree<FirstFit.Node> nodeRBTree = new RBTree<>();
        nodeRBTree.add(n1);
        nodeRBTree.add(n2);
        nodeRBTree.add(n3);
        boolean contains = nodeRBTree.contains(n4);
        System.out.println(contains);
    }
}
