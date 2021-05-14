package list;

public interface List<E> {
    static final int ELEMENT_NOT_FOUND = -1;
    int size();//元素容量
    boolean isEmpty();//是否为空
    boolean contains(E element);//是否包括某个元素
    void add(E element);//添加元素
    E get(int index);//返回index位置元素
    E set(int index, E element);//设置index位置元素
    void add(int index, E element);//在index位置添加元素
    E remove(int index);//移出index位置元素
    int indexOf(E element);//查看元素位置
    void clear();//清空元素
}
