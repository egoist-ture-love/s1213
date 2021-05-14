package ltd.egoist.链表实现01;

public abstract class AbstractList<E> implements List<E> {
    //链表的大小
    protected int size;

    /**
     * 元素的数量
     *
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * 是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }
    public boolean contains(E element){
        return indexOf(element) != ELEMENT_NOT_FIND;
    }
    /**
     * 添加元素到尾部
     * @param element
     */
    public void add(E element){
        add(size,element);
    }
    /**
     * 下标越界处理
     *
     * @param index
     */
    protected void outOfBounds(int index) {
        throw new IndexOutOfBoundsException("Index=" + index + "Size=" + size);
    }

    /**
     * 检查添加位置
     *
     * @param index
     */
    protected void rangeForAdd(int index) {
        if (index < 0 || index > size) {
            outOfBounds(index);
        }
    }
    /**
     * 检查下标是否合法
     *
     * @param index
     * @return
     */
    protected void checkIndex(int index) {
        if (index < 0 || index > size + 1) {
            outOfBounds(index);
        }

    }

}
