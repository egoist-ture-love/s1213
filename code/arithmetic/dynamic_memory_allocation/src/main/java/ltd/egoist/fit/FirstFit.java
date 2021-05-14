package ltd.egoist.fit;

import java.util.LinkedList;
import java.util.Map;

/**
 * @Classname FirstFit
 * @Description 使用首次适应分配算法进行分区分配
 * @Date 2021/5/9 8:46
 * @Created by Lenovo
 */
public class FirstFit extends Fit {

    /**
     * 假设的内存地址
     */
    public Integer[] address = new Integer[]{0,10,30,60,100,150};

    /**
     * 使用链表进行首次分配适应
     *
     * @param size       要分配的大小
     * @param linkedList 进行分配的链表
     */
    @Override
    public void storageAllocationByList(int size, LinkedList<Integer> linkedList) {
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
                return;
            }
            ++i;
        }
        /**
         * 空间不足报错
         */
        error();
    }

    /**
     * 使用Map表进行首次分配适应
     *
     * @param size 要分配的大小
     * @param map  进行分配的map
     */
    @Override
    public void storageAllocationByMap(int size, Map<Object, Integer> map) {
        int length = map.size();
        if(length <= 1){
            System.out.println("无法分配");
            return;
        }
        for (Map.Entry<Object, Integer> entry : map.entrySet()) {
            Object key = entry.getKey();
            Integer value = entry.getValue();
            if (value >= size) {
                value = value - size;
                map.replace(key, value);
                if (value == 0) {
                    map.remove(key);
                }
                return;
            }
        }
        error();
    }


}
