package heap;

import org.junit.Test;
import printer.BinaryTreeInfo;
import printer.BinaryTrees;

/**
 * @Classname Main
 * @Description TODO
 * @Date 2021/5/8 18:56
 * @Created by Lenovo
 */
public class Main {
    @Test
    public void method01(){
        BinaryHeap<Integer> binaryHeap = new BinaryHeap<>();
        binaryHeap.add(1);
        binaryHeap.add(3);
        binaryHeap.add(56);
        binaryHeap.add(45);
        binaryHeap.add(39);
        binaryHeap.add(65);
        BinaryTrees.println(binaryHeap);
//        binaryHeap.remove();
        System.out.println(binaryHeap.replace(7));
        BinaryTrees.println(binaryHeap);
    }
}
