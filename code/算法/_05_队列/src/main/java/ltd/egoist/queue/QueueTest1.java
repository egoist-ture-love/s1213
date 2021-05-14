package ltd.egoist.queue;

import ltd.egoist.list.LinkedList;
import ltd.egoist.list.List;

public class QueueTest1<E> {
    List<E> list = new LinkedList<>();
    public int size(){
        return list.size();
    }
    public boolean isEmpty(){
        return list.isEmpty();
    }
    public void clear(){
        list.clear();
    }
    public void enQueue(E element){
        list.add(element);
    }
    public E deQueue(){
        return list.remove(0);
    }
    public E front(){
        return list.get(0);
    }
}
