package heap;


//import printer.BinaryTreeInfo;
//
import java.util.Comparator;

/**
 * @Classname BinaryHeap
 * @Description TODO
 * @Date 2021/5/8 18:25
 * @Created by Lenovo
 */
public class BinaryHeap<E> extends AbstractHeap<E>{

    private E[] elements;

    private static final int DEFAULT_CAPACITY = 10;
    public BinaryHeap(E[] elements,Comparator<E> comparator){
        super(comparator);
        if(elements == null || elements.length == 0){
            this.elements = (E[]) new Object[DEFAULT_CAPACITY];
        }else {
            int capacity = Math.max(elements.length,DEFAULT_CAPACITY);
            this.elements = (E[]) new Object[capacity];
            for(int i = 0; i < elements.length; i++){
                this.elements[i] = elements[i];
            }
            heapify();
        }
    }

    private void heapify() {
        //自上而下的上滤
//        for(int i = 1; i < size; i++){
//            siftUp(i);
//        }
        //自下而上的下率
        for(int i = (size >> 1) - 1; i >= 0; i--){
            siftDown(i);
        }
    }

    public BinaryHeap(E[] elements){
        this(elements,null);
    }
    public BinaryHeap(Comparator<E> comparator){
        this(null,comparator);
    }

    public BinaryHeap(){
        this(null,null);
    }


    @Override
    public void clear() {
        for(int i = 0; i < size; i++){
            elements[i] = null;
        }
        size = 0;
    }

    @Override
    public void add(E element) {
        elementNotNullCheck(element);
        ensureCapacity(size + 1);
        elements[size++] = element;
        siftUp(size - 1);
    }

    /**
     * 让index位置元素上滤
     * @param index
     */
    private void siftUp(int index){
        E e = elements[index];
        while (index > 0){
            int pindex = (index - 1) >> 1;
            E p = elements[pindex];
            if(compare(e,p) <= 0) break;
            elements[index] = p;
            index = pindex;
        }
        elements[index] = e;
    }

    @Override
    public E get() {
        return null;
    }

    @Override
    public E remove() {
        emptyCheck();
        int lastIndex = --size;
        E root = elements[0];
        elements[0] = elements[lastIndex];
        elements[lastIndex] = null;
        siftDown(0);
        return root;
    }

    private void siftDown(int index){
        E element = elements[index];
        int half = size >> 1;
        //第一个叶子节点的索引等于非叶子节点的数量
        //index < 第一个叶子节点的索引
        //必须保证 index 位置的是非叶子节点
        while (index < half){
            //1.只有左子节点
            //2.同时有左右子节点
            //默认使用左子节点进行比较
            int childIndex = (index << 1) + 1;
            E child = elements[childIndex];
            //右子节点
            int rightIndex = childIndex + 1;
            //选出左右子节点最大的进行比较
            if(rightIndex < size && compare(elements[rightIndex],child) > 0){
                child = elements[childIndex = rightIndex];
            }
            if(compare(element,child) >= 0) break;
            //将子节点的值存放到index位置
            elements[index] = child;
            //重新设置index
            index = childIndex;
        }
        elements[index] = element;

    }
    @Override
    public E replace(E element) {
        elementNotNullCheck(element);
        E root = null;
        if(size == 0){
            elements[0] = element;
            size++;
        }else {
            root = elements[0];
            elements[0] = element;
            siftDown(0);
        }
        return root;
    }

    private void emptyCheck(){
        if(size == 0){
            throw new IndexOutOfBoundsException("Heap is Empty");
        }
    }

    private void ensureCapacity(int capacity) {
        int oldCapacity = elements.length;
        if (oldCapacity >= capacity) return;

        // 新容量为旧容量的1.5倍
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        E[] newElements = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;
    }

    private void elementNotNullCheck(E element){
        if(element == null){
            throw new IllegalArgumentException("element is empty");
        }
    }


//    @Override
//    public Object root() {
//        return 0;
//    }
//
//    @Override
//    public Object left(Object node) {
//        int index = ((int)node << 1) + 1;
//        return index >= size ? null : index;
//    }
//
//    @Override
//    public Object right(Object node) {
//        int index = ((int)node << 1) + 2;
//        return index >= size ? null : index;
//    }
//
//    @Override
//    public Object string(Object node) {
//        return elements[(int)node];
//    }
}
