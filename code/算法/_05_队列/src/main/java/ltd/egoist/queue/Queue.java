package ltd.egoist.queue;

import ltd.egoist.list.LinkedList;
import ltd.egoist.list.List;

public class Queue<E> {
    List<E> list = new LinkedList<>();
    public int size(){
        return list.size();
    }
    public boolean isEmpty(){
        return list.isEmpty();
    }

    /**
     * 入队
     * @param element
     */
    public void enQueue(E element){
        list.add(element);
    }

    /**
     * 出队
     * @return
     */
    public E deQueue(){
        return list.remove(0);
    }

    /**
     * 获取队列头元素
     * @return
     */
    public E front(){
        return list.get(0);
    }

    /**
     * 清空
     */
    public void clear(){
        list.clear();
    }
}
