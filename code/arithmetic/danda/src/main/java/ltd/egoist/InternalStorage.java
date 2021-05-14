package ltd.egoist;

import java.util.LinkedList;

/**
 * @Classname InternalStorage
 * @Description TODO
 * @Date 2021/5/10 16:51
 * @Created by Lenovo
 */
public class InternalStorage {
    /**
     * 定义Node来存储细节
     */
    public static class Node{
        /**
         * 内存占用大小
         */
        int size;
        /**
         * 内存的首地址
         */
        int head;
        /**
         * 内存是否被占用
         */
        boolean free;

        public Node(int size, int head, boolean free) {
            this.size = size;
            this.head = head;
            this.free = free;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "size=" + size +
                    ", head=" + head +
                    ", free=" + free +
                    '}';
        }
    }

    /**
     * 整个内存大小
     */
    private int size;

    /**
     * 最小内存大小
     */
    private static final int DEFAULT_MIN_SIZE =  10;

    /**
     * 定义上一个节点存储位置
     */
    int preIndex;

    /**
     * 定义链表存储内存分区
     */
    private LinkedList<Node> linkedList = new LinkedList<>();

    /**
     * 无参构造默认初始化链表
     */
    public InternalStorage() {
        this(100);
    }

    /**
     * 有参构造进行内存大小指定
     * @param size 内存大小
     */
    public InternalStorage(int size) {
        this.size = size;
        linkedList.add(new Node(size,0,true));
    }

    /**
     * 进行首次分配算法分配
     * @param size 要求分配的大小
     */
    public void doFirstFit(int size){
        /**
         * 遍历所有节点
         */
        for(int i = 0; i < linkedList.size(); i++){
            Node node = linkedList.get(i);
            /**
             * 取出节点     先检查是否可用,之后再检查大小是否足够
             */
            if(node.free && node.size >= size){
                /**
                 * 知道是一个空闲分区且大小足够用,新增加一个分区。
                 *      分区大小是size  分区地址是空闲分区的首地址    使用情况是true
                 */
                linkedList.remove(i);
                linkedList.add(i,new Node(size,node.head,false));
                /**
                 * 查看分区大小和需要分配的大小是否相等
                 */
                if(node.size == size){
                    /**
                     * 相等就将空闲分区移走
                     */
                }else {
                    /**
                     * 不相等就对应修改空闲分区
                     *     修改第i个节点的空闲分区     空闲分区大小进行减少size大小    首地址位置进行变动【相对于原先首地址位置+size大小】 free不进行修改
                     */
                    linkedList.add(i+1,new Node(node.size - size,node.head + size,true));
                }
                return;
            }
        }
    }

    /**
     * 打印整个List链表
     */
    public void printLinkList(){
        System.out.println("------------------------------------");
        System.out.println("分区编号\t分区始址\t分区大小\t空闲状态\t");
        System.out.println("------------------------------------");
        for (int i = 0; i <linkedList.size(); i++) {
            Node tmp = linkedList.get(i);
            System.out.println(i + "\t\t" + tmp.head + "\t\t" +
                    tmp.size + "  \t" + tmp.free);
        }
        System.out.println("------------------------------------");
    }

    public void recycleSpace(int id){
        if(id > linkedList.size()){
            System.out.println("没有此编号的内容");
            return;
        }
        Node node = linkedList.get(id);
        /**
         * 如果是空作业无法回收
         */
        if(node.free){
            System.out.println("空作业无法回收");
            return;
        }
        int size = node.size;
        /**
         *如果回收分区不是尾分区且后一个分区为空闲, 则与后一个分区合并
         */
        if (id < linkedList.size() - 1 && linkedList.get(id + 1).free) {
            Node next = linkedList.get(id + 1);
            node.size += next.size;
            linkedList.remove(next);
        }
        /**
         * 如果回收分区不是首分区且前一个分区为空闲, 则与前一个分区合并
         */
        if (id > 0 && linkedList.get(id - 1).free) {
            Node previous = linkedList.get(id - 1);
            previous.size += node.size;
            linkedList.remove(id);
            id--;
        }
        /**
         * 更改作业为空闲
         */
        linkedList.get(id).free = true;
        System.out.println("内存回收成功!, 本次回收了 " + size + "KB 空间!");
    }
}
