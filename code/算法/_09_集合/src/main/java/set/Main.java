package set;

import org.junit.Test;

public class Main {
    @Test
    public void method() {
        Set<Integer> listSet = new ListSet<>();
        listSet.add(1);
        listSet.add(1);
        listSet.add(2);
        listSet.add(1);
        listSet.traversal(new Set.Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.println(element);
                return false;
            }
        });
    }

    @Test
    public void method01() {
        Set<Integer> treeSet = new TreeSet<>();
        treeSet.add(10);
        treeSet.add(9);
        treeSet.add(9);
        treeSet.add(8);
        treeSet.traversal(new Set.Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.println(element);
                return false;
            }
        });
    }
}
