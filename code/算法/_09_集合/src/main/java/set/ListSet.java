package set;

import list.LinkListed;
import list.List;

public class ListSet<E> implements Set<E>{
    private List<E> linkListed = new LinkListed<>();
    @Override
    public int size() {
        return linkListed.size();
    }

    @Override
    public boolean isEmpty() {
        return linkListed.isEmpty();
    }

    @Override
    public void clear() {
        linkListed.clear();
    }

    @Override
    public boolean contains(E element) {
        return linkListed.contains(element);
    }

    @Override
    public void add(E element) {
        int index = linkListed.indexOf(element);
        if(index != List.ELEMENT_NOT_FOUND){
            linkListed.set(index,element);
        }else {
            linkListed.add(element);
        }
    }

    @Override
    public void remove(E element) {
        int index = linkListed.indexOf(element);
        if(index != List.ELEMENT_NOT_FOUND){
            linkListed.remove(index);
        }
    }

    @Override
    public void traversal(Visitor visitor) {
        if(visitor == null) return;
        int size = linkListed.size();
        for(int i = 0; i < size; i++){
            if(visitor.visit(linkListed.get(i))) return;
        }
    }
}
