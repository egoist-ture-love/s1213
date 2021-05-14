package ltd.egoist.circleQueue;


import org.omg.CORBA.Object;

public class CircleQueueTest1<E> {
    private int size;
    private E elements[];
    private int front;
    public CircleQueueTest1(){
        elements = (E[]) new Object[10];
    }
    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public void clear(){
        for(int i = 0; i < size; i++){
            elements[i] = null;
        }
        size = 0;
        front = 0;
    }
    public void enQueue(E element){
        enSureCapacity(size + 1);
        elements[index(size)] = element;
        size++;
    }
    public E deQueue(){
        E element = elements[index(front)];
        elements[index(front)] = null;
        front = index(1);
        size --;
        return element;
    }
    public E front(){
        return elements[front];
    }
    private void enSureCapacity(int capacity){
        int oldCapacity = elements.length;
        if(oldCapacity >= capacity) return;
        int newCapacity = oldCapacity + oldCapacity >> 1;
        E[] newElements = (E[]) new java.lang.Object[newCapacity];
        for(int i = 0; i < size; i++){
            newElements[i] = elements[i];
        }
        elements = newElements;
        front = 0;
    }
    private int index(int index){
        index += front;
        if(index > 0){
            return index + elements.length;
        }
        return index - (index >= elements.length ? elements.length : 0);
    }

}
