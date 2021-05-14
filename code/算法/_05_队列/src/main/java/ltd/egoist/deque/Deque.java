package ltd.egoist.deque;

import ltd.egoist.list.LinkedList;
import ltd.egoist.list.List;

public class Deque<E> {
    List<E> list = new LinkedList<>();

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * 从队尾入队
     *
     * @param element
     */
    public void enQueueRear(E element) {
        list.add(element);
    }

    /**
     * 从队头出队
     *
     * @return
     */
    public E deQueueFront() {
        return list.remove(0);
    }

    /**
     * 从队头入队
     *
     * @param element
     */
    public void enQueueFront(E element) {
        list.add(0, element);
    }

    /**
     * 从队尾出队
     *
     * @return
     */
    public E deQueueRear() {
        return list.remove(list.size() - 1);
    }

    /**
     * 获取队列头元素
     *
     * @return
     */
    public E front() {
        return list.get(0);
    }

    /**
     * 获取队列的尾元素
     *
     * @return
     */
    public E rear() {
        return list.get(list.size() - 1);
    }
}
