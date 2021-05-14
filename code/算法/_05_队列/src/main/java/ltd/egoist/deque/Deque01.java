package ltd.egoist.deque;

import ltd.egoist.list.LinkedList;
import ltd.egoist.list.List;

public class Deque01<E> {
    List<E> list = new LinkedList<>();

    public int size() {
        return list.size();
    } // 元素的数量

    public boolean isEmpty() {
        return list.size() == 0;
    } // 是否为空

    public void clear() {
        list.clear();
    } // 清空

    public void enQueueRear(E element) {
        list.add(list.size() -1,element);
    } // 从队尾入队

    public E deQueueFront() {
        return list.remove(0);
    } // 从队头出队

    public void enQueueFront(E element) {
        list.add(0,element);
    } // 从队头入队

    public E deQueueRear() {
        return list.remove(list.size() - 1);
    }// 从队尾出队

    public E front() {
        return list.get(0);
    } // 获取队列的头元素

    public E rear() {
        return list.get(list.size() - 1);
    }
}
