package ltd.egoist.动态数组实现;



public class ArrayList<E> {
    //数组元素个数
    private int size;
    //数组的存储
    private E elements[];
    //元素没找到
    private static final int ELEMENT_NOT_FIND = -1;
    //默认的数组大小
    private final static int DEFAULT_CAPACITY = 10;

    //默认初始化数组
    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    //直接初始化数组
    public ArrayList(int capacity) {
        capacity = capacity < DEFAULT_CAPACITY ? DEFAULT_CAPACITY : capacity;
        elements = (E[]) new Object[capacity];
    }

    /**
     * 数组元素个数方法
     *
     * @return 元素个数
     */
    public int size() {
        return size;
    }

    /**
     * 判断数组是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 是否包含某个元素
     *
     * @param element 传入的元素
     * @return
     */
    public boolean contains(E element) {
        return indexOf(element) != ELEMENT_NOT_FIND;
    }

    /**
     * 添加元素到最后面
     *
     * @param element
     */
    public void add(E element) {
        add(size, element);
    }

    /**
     * 返回index位置对应的元素
     *
     * @param index
     * @return
     */
    public E get(int index) {
        checkIndex(index);
        return elements[index];
    }

    /**
     * 设置index位置的元素
     *
     * @param index
     * @param element
     * @return
     */
    public E set(int index, E element) {
        checkIndex(index);
        E old = elements[index];
        elements[index] = element;
        return old;
    }

    /**
     * 往index位置添加元素
     *
     * @param index
     * @param element
     */
    public void add(int index, E element) {
        rangeForAdd(index);
        checkCapacity(size + 1);
        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }
        elements[index] = element;
        size++;

    }

    /**
     * 删除index位置对应的元素
     *
     * @param index
     * @return
     */
    public E remove(int index) {
        checkIndex(index);
        E old = elements[index];
        for (int i = index + 1; i < size; i++) {
            elements[i - 1] = elements[i];
        }
        elements[--size] = null;
        return old;
    }

    /**
     * 查看元素的位置
     *
     * @param element
     * @return
     */
    public int indexOf(E element) {
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(elements[i])) {
                    return i;
                }
            }
        }
        return ELEMENT_NOT_FIND;
    }

    /**
     * 清除所有元素
     */
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    /**
     * 检查下标是否合法
     *
     * @param index
     * @return
     */
    private void checkIndex(int index) {
        if (index < 0 || index > size + 1) {
            outOfBounds(index);
        }

    }

    /**
     * 下标越界处理
     *
     * @param index
     */
    private void outOfBounds(int index) {
        throw new IndexOutOfBoundsException("Index=" + index + "Size=" + size);
    }

    /**
     * 检查添加位置
     *
     * @param index
     */
    private void rangeForAdd(int index) {
        if (index < 0 || index > size) {
            outOfBounds(index);
        }
    }

    /**
     * 检查容量
     *
     * @param capacity
     * @return
     */
    private void checkCapacity(int capacity) {
        int oldCapacity = elements.length;
        if (oldCapacity >= capacity) return;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        E[] newElements = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;
        System.out.println("容量扩张为" + newCapacity);

    }


    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("size = ").append(size).append("  [");
        for (int i = 0; i < size; i++){
            if(i == size -1){
                stringBuffer.append(elements[size-1]);
            }else {
                stringBuffer.append(elements[i]+", ");
            }
        }
        stringBuffer.append("]");
        return stringBuffer.toString();
    }
}
