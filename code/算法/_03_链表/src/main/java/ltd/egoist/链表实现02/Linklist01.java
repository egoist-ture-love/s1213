package ltd.egoist.链表实现02;

/**
 * 虚拟头节点
 *
 * @param <E>
 */
public class Linklist01<E> extends AbstractList<E> {
    private Node<E> first;

    static class Node<E> {
        E elements;
        Node<E> next;

        public Node(E elements, Node<E> next) {
            this.elements = elements;
            this.next = next;
        }
    }

    public Linklist01() {
        first = new Node<>(null, null);
    }

    @Override
    public E get(int index) {
        Node<E> node = node(index);
        return node.elements;
    }

    @Override
    public E set(int index, E element) {
        Node<E> node = node(index);
        E Old = node.elements;
        node.elements = element;
        return Old;
    }

    @Override
    public void add(int index, E element) {
        rangeCheckAdd(index);
        Node<E> preNode = index == 0 ? first : node(index - 1);
        Node<E> node = new Node<>(element, preNode.next);
        preNode.next = node;
        size++;
    }

    @Override
    public E remove(int index) {
        Node<E> preNode = index == 0 ? first : node(index - 1);
        Node<E> node = node(index);
        preNode.next = node.next;
        size--;
        return node.elements;

    }

    @Override
    public int indexOf(E element) {
        if (element == null) {
            Node<E> node = first;
            for (int i = 0; i < size; i++) {
                if (node.elements == null) return i;

                node = node.next;
            }
        } else {
            Node<E> node = first;
            for (int i = 0; i < size; i++) {
                if (element.equals(node.elements)) return i;

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

    private Node<E> node(int index) {
        rangeCheck(index);
        Node<E> node = first.next;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

}
