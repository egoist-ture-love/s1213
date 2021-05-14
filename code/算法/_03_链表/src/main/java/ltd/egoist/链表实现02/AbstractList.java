package ltd.egoist.链表实现02;

public abstract class AbstractList<E> implements List<E> {
    protected int size;

    /**
     * 元素容量
     *
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * 是否为空
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 是否包括某个元素
     * @param element
     * @return
     */
    public boolean contains(E element){
        return indexOf(element) != ELEMENT_NOT_FOUND;
    }

    /**
     * 添加元素
     * @param element
     */
    public void add(E element){
        add(size,element);
    }

    /**
     *
     * @param index
     */
    protected void outOfBound(int index){
        throw new IndexOutOfBoundsException("size = "+size+"index = "+index);
    }
    protected void rangeCheck(int index){
        if(index < 0 || index >= size){
            outOfBound(index);
        }
    }
    protected void rangeCheckAdd(int index){
        if(index < 0 || index > size){
            outOfBound(index);
        }
    }
}
