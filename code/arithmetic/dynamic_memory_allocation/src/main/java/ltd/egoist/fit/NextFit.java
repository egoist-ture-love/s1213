package ltd.egoist.fit;

import java.util.LinkedList;
import java.util.Map;

/**
 * @Classname NextFit
 * @Description 下次适应分配算法
 * @Date 2021/5/9 11:33
 * @Created by Lenovo
 */
public class NextFit extends Fit {
    /**
     * 记录当前节点的位置
     */
    private int currentList = 0;
    private int currentMap = 0;

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
        while (true) {
            if (i == 2 * length) break;
            currentList = currentList % length;
            Integer partitionSize = linkedList.get(currentList);
            if (partitionSize >= size) {
                partitionSize = partitionSize - size;
                linkedList.set(currentList, partitionSize);
                if (partitionSize == 0) {
                    linkedList.remove(currentList);
                }
                return;
            }
            currentList++;
            i++;
        }
        /**
         * 空间不足报错
         */
        error();
    }

    @Override
    public void storageAllocationByMap(int size, Map<Object, Integer> map) {
        int length = map.size();
        if (length <= 1) {
            System.out.println("无法分配");
            return;
        }
        /**
         * 定义两个数组方便查找
         */
        Object[] objects = new Object[length];
        Integer[] integers = new Integer[length];
        /**
         * 使用循环将map里面的内容输入到数组中去
         */
        int arrNum = 0;
        for (Map.Entry<Object, Integer> entry : map.entrySet()) {
            Object mapKey = entry.getKey();
            Integer mapValue = entry.getValue();
            objects[arrNum] = mapKey;
            integers[arrNum] = mapValue;
            arrNum++;
        }
        /**
         * 记录是否删除了 integers 的元素
         */
        int temp = 0;
        /**
         * 记录需要级联删除的objects的下标
         */
        int k = 0;
        /**
         * 用来判断是否找到了大于size的value
         */
        int g = 0;
        for(int i = currentMap; i < length * 2; i++){
            int currentNum = i % length;
            int num = integers[currentNum];
            if(num >= size){
                integers[currentNum] = num - size;
                if(num == size){
                    k = currentNum;
                    /**
                     * 将数组的那位元素删除
                     */
                    for (int j = currentNum; j < length - 1; j++) {
                        integers[j] = integers[j + 1];
                    }
                    integers[length - 1] = null;
                    temp++;
                }
                g++;
                currentMap = currentNum;
                break;
            }
            currentMap = currentNum;
        }
        /**
         * 如果temp发生了变化,就要进行删除Objects的元素
         */
        if(temp != 0){
            for(int i = k; i < length - 1; i++){
                objects[i] = objects[i + 1];
            }
            objects[length - 1] = null;
            map.clear();
            for(int i = 0; i < length - 1; i++){
//                System.out.println("objects的第"+i+"个元素为:"+objects[i]);
//                System.out.println("integers的第"+i+"个元素为:"+integers[i]);
                map.put(objects[i],integers[i]);
            }
            return;
        }else if(g == 0){
            /**
             * 循环了两次数组发现找不到,进行报错
             */
            error();
        }
        /**
         * 先将原始的map清空,之后在进行赋值
         */
        map.clear();
        for(int i = 0; i < length; i++){
            map.put(objects[i],integers[i]);
        }

    }
}
