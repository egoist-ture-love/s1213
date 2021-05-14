package ltd.egoist.fit;

import ltd.egoist.utils.Compare;

import java.util.*;

/**
 * @Classname BestFit
 * @Description 最佳使用算法
 * @Date 2021/5/9 10:10
 * @Created by Lenovo
 */
public class BestFit extends Fit implements Comparator<Integer>{


    /**
     * 最佳适应分配算法的链表实现
     *
     * @param size       需要分配的内存大小
     * @param linkedList 链表大小
     */
    @Override
    public void storageAllocationByList(int size, LinkedList<Integer> linkedList) {
        /**
         * 链表排序
         */
        Collections.sort(linkedList,Integer::compareTo);
        int length = linkedList.size(), i = 0;
        if (length <= 1) {
            System.out.println("无法分配");
            return;
        }
        /**
         * 循环查找分配代码
         */
        while (!linkedList.isEmpty()) {
            if (i == length) break;
            Integer partitionSize = linkedList.get(i);
            if (partitionSize >= size) {
                partitionSize = partitionSize - size;
                linkedList.set(i, partitionSize);
                if (partitionSize == 0) {
                    linkedList.remove(i);
                }
                /**
                 * 再排序
                 */
                Collections.sort(linkedList,Integer::compareTo);
                return;
            }
            ++i;
        }
        /**
         * 内存不足报错
         */
        error();
    }

    /**
     * 最佳适应算法的HashMap实现
     * @param size 需要分配的内存大小
     * @param oldMap 旧的链表
     */
    @Override
    public void storageAllocationByMap(int size, Map<Object, Integer> oldMap) {
        Map<Object, Integer> newMap = new HashMap<>();
        List<Map.Entry<Object, Integer>> list = new ArrayList<>(oldMap.entrySet());
        /**
         * 改变比较方法按照值的大小进行排序
         */
        Collections.sort(list,new Compare());

        /**
         * 重新赋值
         */
        int i = 0;
        for (Map.Entry<Object, Integer> entry : list) {
            Integer value = entry.getValue();
            Object key = entry.getKey();
            if (value >= size && i == 0) {
                i++;
                value = value - size;
                if (value == 0) {
                    continue;
                }
            }
            newMap.put(key,value);
        }
        list = new ArrayList<>(newMap.entrySet());

        Collections.sort(list,new Compare());
        for (Map.Entry<Object, Integer> entry : list){
            Integer value = entry.getValue();
            Object key = entry.getKey();
            System.out.println(key+":"+value);
            newMap.put(key,value);
        }
    }


    @Override
    public int compare(Integer o1, Integer o2) {
        return o1 - o2;
    }
}
