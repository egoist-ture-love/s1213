package ltd.egoist.test01;

import org.junit.Test;
import pojo.Person;
import queue.PriorityQueue;


/**
 * @Classname Test01
 * @Description TODO
 * @Date 2021/5/11 20:08
 * @Created by Lenovo
 */
public class Test01 {
    @Test
    public void method(){
        PriorityQueue<Person> queue = new PriorityQueue<Person>();
        queue.enQueue(new Person("j1",16));
        queue.enQueue(new Person("j2",10));
        queue.enQueue(new Person("j3",18));
        queue.enQueue(new Person("j4",11));

        while(!queue.isEmpty()){
            System.out.println(queue.deQueue());
        }
    }
}
