package ltd.egoist.circleQueue;

/**
 * 循环队列
 *
 * @param <E>
 */
public class CircleQueue<E> {
    private int size;
    private E[] elements;
    private int front;

    public CircleQueue() {
        elements = (E[]) new Object[10];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 入队
     *
     * @param element
     */
    public void enQueue(E element) {
        ensureCapacity(size + 1);
        elements[index(size)] = element;
        size++;
    }

    /**
     * 出队
     *
     * @return
     */
    public E deQueue() {
        E element = elements[front];
        elements[front] = null;
        front = index(1);
        size--;
        return element;
    }

    /**
     * 获取队列头元素
     *
     * @return
     */
    public E front() {
        return elements[front];
    }
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[index(i)] = null;
        }
        front = 0;
        size = 0;
    }

    private int index(int index) {
        index += front;
        if (index < 0) {
            return index + elements.length;
        }
        return index - (index >= elements.length ? elements.length : 0);
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("capcacity=").append(elements.length)
                .append(" size=").append(size)
                .append(" front=").append(front)
                .append(", [");
        for (int i = 0; i < elements.length; i++) {
            if (i != 0) {
                string.append(", ");
            }

            string.append(elements[i]);
        }
        string.append("]");
        return string.toString();
    }

    private void ensureCapacity(int capacity) {
        int oldCapacity = elements.length;
        if (oldCapacity >= capacity) return;

        // 新容量为旧容量的1.5倍
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        E[] newElements = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[index(i)];
        }
        elements = newElements;
        front = 0;
        System.out.println(oldCapacity + "扩容为" + newCapacity);
    }

}
