package ltd.egoist.test01;

import ltd.egoist.fit.BestFit;
import ltd.egoist.fit.FirstFit;
import ltd.egoist.fit.NextFit;
import ltd.egoist.utils.FreePartition;
import ltd.egoist.fit.WorstFit;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @Classname Test
 * @Description TODO
 * @Date 2021/5/9 8:58
 * @Created by Lenovo
 */
public class Test01 {
    @Test
    public void method01(){
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(20);
        linkedList.add(28);
        linkedList.add(25);
        linkedList.add(10);
        linkedList.add(30);
        FreePartition freePartition = new FreePartition();
        freePartition.printByLinkedList(linkedList);
        BestFit bestFit = new BestFit();
        bestFit.storageAllocationByList(10,linkedList);
        freePartition.printByLinkedList(linkedList);
//        FirstFit firstFit = new FirstFit();
//        firstFit.storageAllocation(40,linkedList);
//        freePartition.printByLinkedList(linkedList);
    }
    @Test
    public void method2(){
        Map<Object,Integer> map = new HashMap<>();
        map.put("1",20);
        map.put("2",28);
        map.put("3",25);
        map.put("4",10);
        map.put("5",30);
        map.put("6",40);
        // 10 20 25 28 30 40
        FreePartition freePartition = new FreePartition();
        freePartition.printByMap(map);
        System.out.println("-------------------------");
        BestFit bestFit = new BestFit();
//        bestFit.sortMap(18,map);
        bestFit.storageAllocationByMap(18,map);
//        freePartition.printByMap(map1);
    }
    @Test
    public void method3(){
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(20);
        linkedList.add(28);
        linkedList.add(25);
        linkedList.add(10);
        linkedList.add(30);//15
        FreePartition freePartition = new FreePartition();
        freePartition.printByLinkedList(linkedList);
        WorstFit worstFit = new WorstFit();
        // 28 25 20 15 10
        worstFit.storageAllocationByList(15,linkedList);
        freePartition.printByLinkedList(linkedList);
    }
    @Test
    public void method4(){
        Map<Object,Integer> map = new HashMap<>();
        map.put("1",20);
        map.put("2",28);
        map.put("3",25);
        map.put("4",10);
        map.put("5",30);
        map.put("6",40);
        //40 30 28 25 20 10
        FreePartition freePartition = new FreePartition();
        freePartition.printByMap(map);
        WorstFit worstFit = new WorstFit();
        System.out.println("-------------------------");
        //30 28 25 22 20 10
        worstFit.storageAllocationByMap(200,map);
    }
    @Test
    public void method5(){
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(20);
        linkedList.add(28);
        linkedList.add(25);
        linkedList.add(10);
        linkedList.add(30);
        FreePartition freePartition = new FreePartition();
        freePartition.printByLinkedList(linkedList);
        NextFit nextFit = new NextFit();
        nextFit.storageAllocationByList(29,linkedList);
        freePartition.printByLinkedList(linkedList);
        nextFit.storageAllocationByList(28,linkedList);
        freePartition.printByLinkedList(linkedList);
    }
    @Test
    public void method6(){
        Map<Object,Integer> map = new HashMap<>();
        map.put("1",20);
        map.put("2",28);
        map.put("3",25);
        map.put("4",10);
        map.put("5",30);
        map.put("6",40);
        FreePartition freePartition = new FreePartition();
        freePartition.printByMap(map);
        NextFit nextFit = new NextFit();
        System.out.println("-------------------------");
        nextFit.storageAllocationByMap(39,map);
        freePartition.printByMap(map);
        System.out.println("-------------------------");
        nextFit.storageAllocationByMap(20,map);
        freePartition.printByMap(map);
        nextFit.storageAllocationByMap(30,map);
        System.out.println("-------------------------");
        freePartition.printByMap(map);
        //28 25 10  1
    }

    @Test
    public void method7(){
        FirstFit firstFit = new FirstFit();
        System.out.print("空闲分区表地址:");
        for(int i = 0; i < firstFit.address.length; i++){
            System.out.print(firstFit.address[i]+"--->");
        }
        System.out.println();
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(8);
        linkedList.add(18);
        linkedList.add(28);
        linkedList.add(38);
        linkedList.add(48);
        linkedList.add(58);
        FreePartition freePartition = new FreePartition();
        System.out.print("空闲分区表大小:");
        freePartition.printByLinkedList(linkedList);
    }
    public void method8(){
        /**
         *          我要默认输入一个空闲分区表,和一个使用作业表
         *  根据需要进行新的作业分配---更改空闲分区表和使用作业表
         *  之后根据需要进行作业回收----更改使用作业表和空闲分区表
         *
         *
         *  最后的界面效果
         *          1.欢迎界面
         *          2.默认输入空闲分区表和作业表【或者默认固定也行】
         *          3.进行  作业分配方法选择   或者   作业回收选择  或者  结束程序1
         *              3.1如果选择作业分配请选择使用哈希表或者链表【放在3条件】两种方式其中一种--
         *                  3.3.1作业分配循环进行【不想继续输入N退出到选择回收 或者 其他分配方式  或者退出】
         *
         */
    }
}
