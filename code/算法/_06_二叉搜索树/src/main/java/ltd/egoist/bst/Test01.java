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
//        bst.preorderTraversal();
//        System.out.println("---------------------");
//        bst.inorderTraversal();
//        System.out.println("---------------------");
//        bst.postorderTraversal();
//        bst.postorder(new BinarySearchTree.Victor<Integer>() {
//            @Override
//            public boolean visit(Integer element) {
//                if(element ==8 ){
//                    return true;
//                }
//                System.out.print(element+"__");
//                return false;
//            }
//        });

    }
}
