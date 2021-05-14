package heap;

/**
 * @Classname Heap
 * @Description TODO
 * @Date 2021/5/8 18:24
 * @Created by Lenovo
 */
public interface Heap<E> {
    int size();
    boolean isEmpty();
    void clear();
    void add(E element);
    E get();
    E remove();
    E replace(E element);
}
