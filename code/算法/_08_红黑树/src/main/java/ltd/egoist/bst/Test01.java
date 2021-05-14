package ltd.egoist.bst;


import ltd.egoist.printer.BinaryTrees;
import org.junit.Test;

public class Test01 {
    @Test
    public void method01(){
        Integer data[] = new Integer[] {
                7, 4, 9, 2, 5, 8, 11, 3, 12, 1
        };

        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (int i = 0; i < data.length; i++) {
            bst.add(data[i]);
        }
        BinaryTrees.print(bst);
        System.out.println("---------------------");
        bst.levelOrderTraversal(new BinarySearchTree.Victor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                if(element ==8 ){
                    return true;
                }
                System.out.print(element+"__");
                return false;
            }
        });
        System.out.println("---------------------");
        System.out.println(bst.isComplete());

    }
    @Test
    public void method2(){
        Integer data[] = new Integer[] {
                67, 52, 92, 96, 53, 95, 13, 63, 34, 82, 76, 54, 9, 68, 39
        };
        AVLTree<Integer> avl = new AVLTree<>();
        for (int i = 0; i < data.length; i++) {
            avl.add(data[i]);
        }
        BinaryTrees.println(avl);
    }
    @Test
    public void method3(){
        Integer data[] = new Integer[] {
                55, 87, 56, 74, 96, 22, 62, 20, 70, 68, 90, 50
        };

        RBTree<Integer> rb = new RBTree<>();
        for (int i = 0; i < data.length; i++) {
            rb.add(data[i]);
            System.out.println("【 " + data[i] + "】");
            BinaryTrees.println(rb);
            System.out.println("---------------------------------------");
        }
    }
    @Test
    public void method04(){
        Integer data[] = new Integer[] {
                55, 87, 56, 74, 96, 22, 62, 20, 70, 68, 90, 50
        };

        RBTree<Integer> rb = new RBTree<>();
        for (int i = 0; i < data.length; i++) {
            rb.add(data[i]);
        }

        BinaryTrees.println(rb);

        for (int i = 0; i < data.length; i++) {
            rb.remove(data[i]);
            System.out.println("---------------------------------------");
            System.out.println("【" + data[i] + "】");
            BinaryTrees.println(rb);
        }
    }
}
