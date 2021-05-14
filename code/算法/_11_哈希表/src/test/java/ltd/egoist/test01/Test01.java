package ltd.egoist.test01;

import ltd.egoist.Asserts;
import map.HashMap;
import map.Map;
import model.Key;
import model.Person;
import org.junit.Test;

/**
 * @Classname Test01
 * @Description TODO
 * @Date 2021/5/2 12:00
 * @Created by Lenovo
 */
public class Test01 {
    @Test
    public void method01(){
        Map<Object,Integer> map = new HashMap<>();
        for(int i = 1; i < 19; i++){
            map.put(new Key(i),i);
        }
        System.out.println(map.size());
    }
    @Test
    public void method02(){
        /**
         * 计算整数的哈希值：直接使用
         */
        /**
         * 计算浮点数哈希值:先转成二进制,在通过int函数转成哈希值
         */
//        int i = Float.floatToIntBits(2.3f);
//        String hashCode = Integer.toBinaryString(i);
//        System.out.println(hashCode);
        /**
         * Long类型和Double类型
         * double类型先通过函数转成Long类型
         * 之后Long类型先无符号右移动32位---之后异或运算
         */
//        long l = Double.doubleToLongBits(2.5);
//        System.out.println(l);
//        long l1 = l ^ (l >>> 32);
//        System.out.println(l1);
//        int l2 = (int)l1;
//        System.out.println(l2);
        /**
         * String计算哈希值
         *  hashcode = hashcode * 31  + char
         */
        String str = "jack";
        int len = str.length();
        int hashcode = 0;
        for(int i = 0; i < len; i++){
            char c = str.charAt(i);
            hashcode = hashcode * 31 + c;
        }
        System.out.println(hashcode);
        System.out.println(str.hashCode());
    }

    @Test
    public void method03(){
        Person person = new Person(12,22f,"j1");
        Person person1 = new Person(12,22f,"j1");
        //默认拿地址来算
        System.out.println(person1.hashCode());
        System.out.println(person.hashCode());
        System.out.println("-----------------");
        System.out.println(person.equals(person1));
        Map<Object,Object> map = new HashMap<>();
        map.put(person,"11");
        map.put(person1,"11");
        System.out.println(map.size());
    }

    @Test
    public void method4(){
        Person person1 = new Person(12,22f,"j1");
        Person person2 = new Person(12,22f,"j1");
        HashMap<Object,Object> map = new HashMap<>();
        map.put(person1,"1");
        map.put(person2,"5");
        map.put(1,"1");
        map.put(2,"1");
        map.put(null,3);
//        System.out.println(map.size());
//        System.out.println(map.get(null));
//        System.out.println(map.get(1));
//        System.out.println(map.get(2));
//        System.out.println(map.get(person1));
        map.traversal(new Map.Visitor<Object, Object>() {

            @Override
            public boolean visit(Object key, Object value) {
                System.out.println(value);
                return false;
            }
        });
        System.out.println(map.containsKey(1));
        System.out.println(map.containsValue(3));
    }

    @Test
    public void method5(){
        HashMap<Object,Integer> map = new HashMap<>();
        for(int i = 1; i <= 19; i++){
            map.put(new Key(i),i);
        }
        map.put(new Key(4),100);
        Asserts.test(map.size() == 19);
        Asserts.test(map.get(new Key(4)) == 10);
    }
}
