package ltd.egoist.链表实现01;

public class LinkList<E> extends AbstractList<E>{
    //链表第一个所指向
    private Node<E> first;
    //声明一个节点的静态内部类
    private static class Node<E>{
        //节点所存储的元素
        E element;
        //节点所指向的下一个节点地址
        Node<E> next;
        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }
    }

    /***
     * 清空元素
     */
    @Override
    public void clear() {
        size = 0;
        first = null;
    }

    @Override
    public E get(int index) {
        return node(index).element;
    }

    @Override
    public E set(int index, E element) {
        E OldNode  = node(index).element;
        node(index).element = element;
        return OldNode;
    }

    @Override
    public void add(int index, E element) {
        rangeForAdd(index);
        if(index == 0){
            first = new Node<>(element,first);
        }else {
            Node<E> pre = node(index -1);
            Node<E> node = new Node<>(element, pre.next);
            pre.next = node;
        }
        size++;
    }

    @Override
    public E remove(int index) {
        checkIndex(index);
        Node<E> Oldnode = first;
        if(index == 0){
            first = first.next;
        }else {
            Node<E> pre = node(index - 1);
            Oldnode = pre.next;
            pre.next = Oldnode.next;
        }
        size--;
        return Oldnode.element;

    }

    @Override
    public int indexOf(E element) {
        if (element == null) {
            Node<E> node = first;
            for (int i = 0; i < size; i++) {
                if (node.element == null) return i;

                node = node.next;
            }
        } else {
            Node<E> node = first;
            for (int i = 0; i < size; i++) {
                if (element.equals(node.element)) return i;

                node = node.next;
            }
        }
        return ELEMENT_NOT_FIND;
    }

    /**
     * 返回index节点的指向
     * @param index
     * @return
     */
    private Node<E> node(int index){
        checkIndex(index);
        Node<E> node = first;
        for (int i = 0; i < index; i++){
            node = node.next;
        }
        return node;
    }

}
