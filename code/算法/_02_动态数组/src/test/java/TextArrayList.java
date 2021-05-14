import jdk.nashorn.internal.ir.CallNode;
import ltd.egoist.动态数组实现.ArrayListTest01;
import ltd.egoist.动态数组标准版.ArrayList;
import org.junit.Test;

public class TextArrayList {
    @Test
    public void method(){
        ArrayList<Object> objectArrayList = new ArrayList<>();
        objectArrayList.add("1");

    }
    @Test
    public void method1(){
        ltd.egoist.动态数组实现.ArrayList<Object> objectArrayList = new ltd.egoist.动态数组实现.ArrayList<>();
        objectArrayList.add(1);
        objectArrayList.add(2);
        objectArrayList.add(3);
        objectArrayList.add(4);
        objectArrayList.add(5);
        objectArrayList.add(6);
        System.out.println(objectArrayList.toString());
        boolean empty = objectArrayList.isEmpty();
        System.out.println(empty);
        System.out.println(objectArrayList.size());
        System.out.println(objectArrayList.get(4));
        System.out.println(objectArrayList.contains(7));
        System.out.println(objectArrayList.remove(4));
        System.out.println(objectArrayList.toString());
        objectArrayList.add(4,5);
        System.out.println(objectArrayList.toString());
        objectArrayList.clear();
        System.out.println(objectArrayList.toString());
    }
    @Test
    public void method02(){
        ArrayListTest01<Integer> test01 = new ArrayListTest01<>();
        test01.add(1);
        test01.add(2);
        test01.add(3);
        test01.add(4);
        test01.add(5);
        test01.add(6);
        test01.add(7);
        System.out.println(test01);
        System.out.println(test01.size());
        System.out.println(test01.isEmpty());
        System.out.println(test01.get(1));
        System.out.println(test01.set(1, 23));
        System.out.println(test01.contains(4));
        System.out.println(test01);
        test01.clear();
    }
}
