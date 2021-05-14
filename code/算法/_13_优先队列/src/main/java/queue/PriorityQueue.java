package queue;

import heap.BinaryHeap;

import java.util.Comparator;

/**
 * @Classname PriorityQueue
 * @Description TODO
 * @Date 2021/5/11 19:58
 * @Created by Lenovo
 */
public class PriorityQueue<E> {

    private BinaryHeap<E> binaryHeap ;

    public PriorityQueue() {
        this(null);
    }

    public PriorityQueue(Comparator<E> comparator) {
        binaryHeap = new BinaryHeap<>(comparator);
    }

    public int size(){
        return binaryHeap.size();
    }

    public boolean isEmpty(){
        return binaryHeap.isEmpty();
    }

    /**
     * 入队
     * @param element
     */
    public void enQueue(E element){
        binaryHeap.add(element);
    }

    /**
     * 出队
     * @return
     */
    public E deQueue(){
        return binaryHeap.remove();
    }

    /**
     * 获取队列头元素
     * @return
     */
    public E front(){
        return binaryHeap.get();
    }

    /**
     * 清空
     */
    public void clear(){
        binaryHeap.clear();
    }
}
