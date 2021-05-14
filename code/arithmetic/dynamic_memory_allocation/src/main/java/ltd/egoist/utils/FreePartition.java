package ltd.egoist.utils;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @Classname FreePartition
 * @Description 空闲分区类
 * @Date 2021/5/9 8:48
 * @Created by Lenovo
 */
public class FreePartition {
    public LinkedList<Integer> linkedList;
    public Map<Object,Integer> map;

    public FreePartition(){
        linkedList = new LinkedList<>();
        map = new HashMap<>();
    }

    public void addLinkedList(int[] arr){
        for(int i = 0; i < arr.length; i++){
            linkedList.add(arr[i]);
        }
    }

    public void addMap(Object[] objects,int[] integers){
        for(int i = 0; i < integers.length; i++){
            map.put(objects[i],integers[i]);
        }
    }

    /**
     * 空闲分区表的打印方法
     */
    public void printByLinkedList(LinkedList<Integer> linkedList) {
        StringBuffer stringBuffer = new StringBuffer();
        int length = linkedList.size() - 1;
        for (int i = 0; i < length; i++) {
            stringBuffer.append(linkedList.get(i)).append("--->");
        }
        stringBuffer.append(linkedList.getLast());
        System.out.println(stringBuffer);
    }

    /**
     * 空闲分区表打印方法
     * @param map
     */
    public void printByMap(Map<Object, Integer> map) {
        for (Map.Entry<Object, Integer> entry : map.entrySet()) {
            Object mapKey = entry.getKey();
            Integer mapValue = entry.getValue();
            System.out.println(mapKey + "：" + mapValue);
        }
    }

}
