package ltd.egoist.test01;

import ltd.egoist.circleDeque.CircleDeque;
import ltd.egoist.circleQueue.CircleQueue;
import ltd.egoist.deque.Deque;
import ltd.egoist.queue.Queue;
import org.junit.Test;

public class Test01 {
//    @Test
//    public void method(){
//        Queue<Integer> queue = new Queue<>();
//        queue.enQueue(1);
//        queue.enQueue(2);
//        queue.enQueue(3);
//        queue.enQueue(4);
//        while (!queue.isEmpty()){
//            System.out.println(queue.deQueue());
//        }
//    }
//    @Test
//    public void method1(){
//        Deque<Integer> deque = new Deque<>();
//        deque.enQueueFront(11);
//        deque.enQueueFront(22);
//        deque.enQueueRear(33);
//        deque.enQueueRear(44);
//        while (!deque.isEmpty()){
//            System.out.println(deque.deQueueFront());
//        }
//    }
//    @Test
//    public void method02(){
//        CircleQueue<Integer> queue = new CircleQueue<Integer>();
//        // 0 1 2 3 4 5 6 7 8 9
//        for (int i = 0; i < 10; i++) {
//            queue.enQueue(i);
//        }
//        // null null null null null 5 6 7 8 9
//        for (int i = 0; i < 5; i++) {
//            queue.deQueue();
//        }
//        // 15 16 17 18 19 5 6 7 8 9
//        for (int i = 15; i < 20; i++) {
//            queue.enQueue(i);
//        }
//        while (!queue.isEmpty()) {
//            System.out.println(queue.deQueue());
//        }
//        System.out.println(queue);
//    }
    @Test
    public void method03(){
        CircleDeque<Integer> queue = new CircleDeque<>();
        // 头5 4 3 2 1  100 101 102 103 104 105 106 8 7 6 尾

        // 头 8 7 6  5 4 3 2 1  100 101 102 103 104 105 106 107 108 109 null null 10 9 尾
        for (int i = 0; i < 10; i++) {
            queue.enQueueFront(i + 1);
            queue.enQueueRear(i + 100);
        }

        // 头 null 7 6  5 4 3 2 1  100 101 102 103 104 105 106 null null null null null null null 尾
        for (int i = 0; i < 3; i++) {
            queue.deQueueFront();
            queue.deQueueRear();
        }

        // 头 11 7 6  5 4 3 2 1  100 101 102 103 104 105 106 null null null null null null 12 尾
        queue.enQueueFront(11);
        queue.enQueueFront(12);
        System.out.println(queue);
        while (!queue.isEmpty()) {
            System.out.println(queue.deQueueFront());
        }
    }

}
