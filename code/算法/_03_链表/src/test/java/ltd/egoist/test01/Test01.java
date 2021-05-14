package ltd.egoist.test01;

import ltd.egoist.链表实现02.LinkListed;
import ltd.egoist.链表实现02.List;
import org.junit.Test;

public class Test01 {
    @Test
    public void method(){
        List<Integer> list = new LinkListed<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.remove(0);
        System.out.println(list.isEmpty());
        System.out.println(list.contains(3));
        System.out.println(list.toString());
        list.clear();
        System.out.println(list.toString());
    }
}
