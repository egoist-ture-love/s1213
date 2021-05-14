package ltd.egoist.stack;

public class Stack<E> {
    ArrayList<E> arrayList = new ArrayList<>();
    //可以通过继承实现,也可以通过组合实现-----组合明显更好一些
    public int size(){
        return arrayList.size();
    }

    public boolean isEmpty(){
        return arrayList.isEmpty();
    }
    public void push(E element){
       arrayList.add(element);
    }

    /**
     * 移出栈顶元素
     * @return
     */
    public E pop(){
        return arrayList.remove(arrayList.size -1);
    }

    /**
     * 获取栈顶元素
     * @return
     */

    public E top(){
        return arrayList.get(arrayList.size-1);
    }
    public String toString(){
        return arrayList.toString();
    }
}
