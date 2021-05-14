package ltd.egoist.stack;

public class Stack1<E> {
    ArrayList<E> arrayList = new ArrayList<>();
    public int Size(){
        return arrayList.size;
    }
    public boolean isEmpty(){
        return arrayList.isEmpty();
    }
    public void push(E element){
        arrayList.add(element);
    }
    public E pop(){
        return arrayList.remove(arrayList.size -1);
    }
    public E top(){
        return arrayList.get(arrayList.size - 1);
    }
    public void clear(){
        arrayList.clear();
    }
}
