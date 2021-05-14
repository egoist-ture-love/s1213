package ltd.egoist.动态数组实现;


import java.util.Arrays;


public class ArrayListTest01<E> {
    //数组的元素的个数
    private int size;
    //存储数据的数组
    private E elements[];
    private static final int DEFAULT_CAPACITY = 10;
    private static final int ELEMENT_NOT_EXIST = -1;

    /**
     * 使用构造函数初始化数组
     */
    public ArrayListTest01() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayListTest01(int capacity) {
        capacity = capacity < DEFAULT_CAPACITY ? DEFAULT_CAPACITY : capacity;
        elements = (E[]) new Object[capacity];
    }

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

    /**
     * 是否包含某个元素
     *
     * @param element
     * @return
     */
    public boolean contains(E element) {
        return indexOf(element) != -1;
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
        rangeCheck(index);
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
        rangeCheck(index);
        E OldElement = elements[index];
        elements[index] = element;
        return OldElement;
    }

    /**
     * 往index位置添加元素
     *
     * @param index
     * @param element
     */
    public void add(int index, E element) {
        rangeCheckForAdd(index);
        ensureCapacity(size + 1);

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
        rangeCheck(index);
        E OldElement = elements[index];
        for (int i = index; i < size; i++) {
            elements[i] = elements[i + 1];
        }
        size--;
        trim();
        return OldElement;
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
                if (element == elements[i]) {
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
        return ELEMENT_NOT_EXIST;
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
     * 下标越界警告
     *
     * @param index
     */
    private void outOfBounds(int index) {
        throw new IndexOutOfBoundsException("size = " + size + " Index = " + index);
    }

    /**
     * 下标范围检擦
     *
     * @param index
     */
    private void rangeCheck(int index) {
        if (index >= size || index < 0) {
            outOfBounds(index);
        }
    }

    /**
     * 检查下标范围(添加)
     *
     * @param index
     */
    private void rangeCheckForAdd(int index) {
        if (index < 0 || index > size) {
            outOfBounds(index);
        }
    }

    private void ensureCapacity(int capacity) {
//        int oldCapacity = elements.length;
        int oldCapacity = elements.length;
        if (oldCapacity >= capacity) return;
        int newCapacity = oldCapacity + oldCapacity >> 1;
        E[] newElements = (E[]) new Object[newCapacity];
        for(int i = 0; i < oldCapacity; i++){
            newElements[i] = elements[i];
        }
        System.out.println(oldCapacity + "扩容为" + newCapacity);
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("size=").append(size).append(", [");
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                string.append(", ");
            }
            string.append(elements[i]);
        }
        string.append("]");
        return string.toString();
    }
    private void trim(){
        int capacity = elements.length;
        if(size >= (capacity>>1) || capacity <= DEFAULT_CAPACITY){
            return;
        }
        int newCapacity = capacity >> 1;
        E[] newArray = (E[])new Object[newCapacity];
        for (int i = 0; i < size; i++){
            newArray[i] = elements[i];
        }
        elements = newArray;
        System.out.println("缩容为"+newCapacity);
    }
}
