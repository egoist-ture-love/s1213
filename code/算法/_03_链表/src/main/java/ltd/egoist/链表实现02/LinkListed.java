package ltd.egoist.链表实现02;

public class LinkListed<E> extends AbstractList<E> {

    private Node<E> first;

    private static class Node<E> {
        E element;
        Node<E> next;

        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }
    }


    @Override
    public E get(int index) {
        Node<E> node = node(index);
        return node.element;
    }

    @Override
    public E set(int index, E element) {
        E Old = node(index).element;
        node(index).element = element;
        return Old;
    }

    @Override
    public void add(int index, E element) {
        if (index == 0) {
            first = new Node<E>(element, first);
        } else {
            Node<E> pre = node(index - 1);
            pre.next  = new Node<E>(element, pre.next);
        }
        size++;

    }

    @Override
    public E remove(int index) {
            Node<E> node = first;
            if(index == 0){
                first = first.next;
            }else {
                Node<E> pre = node(index - 1);
                node = pre.next;
                pre.next = node.next;
            }
            size --;
            return node.element;
    }

    @Override
    public int indexOf(E element) {
        Node<E> node = first;
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (element == first.element) {
                    return i;
                }
                node = node.next;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(first.element)) {
                    return i;
                }
                node = node.next;
            }
        }
        return ELEMENT_NOT_FOUND;
    }

    @Override
    public void clear() {
        size = 0;
        first = null;
    }
    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("size=").append(size).append(", [");
        Node<E> node = first;
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                string.append(", ");
            }

            string.append(node.element);

            node = node.next;
        }
        string.append("]");
        return string.toString();
    }

    protected Node<E> node(int index) {
        rangeCheck(index);
        Node<E> node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }
}
