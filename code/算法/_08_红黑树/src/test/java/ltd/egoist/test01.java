package ltd.egoist;

import ltd.egoist.bst.RBTree;
import ltd.egoist.printer.BinaryTrees;
import org.junit.Test;

public class test01 {
    @Test
    public void method(){
        Integer data[] = new Integer[] {
                55, 87, 56, 74, 96, 22, 62, 20, 70, 68, 90, 50
        };

        RBTree<Integer> rb = new RBTree<>();
        for (int i = 0; i < data.length; i++) {
            rb.add(data[i]);
            System.out.println("---------------------------------------");
            System.out.println("【" + data[i] + "】");
            BinaryTrees.println(rb);
        }
    }
}
