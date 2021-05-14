package 链表;

/**
 * https://leetcode-cn.com/problems/reverse-linked-list/
 */
public class _206_反转链表 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }

        public ListNode reverseList(ListNode head) {
            /**
             * 使用递归来进行
             * 1 2  3 4 5
             */
//            if(head ==  null || head.next == null){
//               return head;
//            }else {
            /**
             * 先把 2 3 4 5 进行反转
             */
//                ListNode listNode = reverseList(head.next);
            /**
             * 之后将2的next指向1
             */
//                head.next.next =  head;
            /**
             * 1与2的连线指向空
             */
//                head.next = null;

//                return listNode;
//            }
            /**
             * 使用迭代来进行遍历
             * 需要定义三个变量来进行循环指向
             *  pre     curt    next
             *  null    head1---->head2---->head3---->head4---->head5---->null
             *  第一次循环需要让head1 的 next 为 pre,   pre为head1的位置   curt 和 next向前移动一位
             *   head1----> pre(null)
             *   pre    curt    next
             *   head1  head2---->head3---->head4---->head5---->null
             *   依次向前遍历,直到curt==null为止
             */
            if(head == null || head.next == null){
                return head;
            }
            ListNode pre = null;
            ListNode curt = head;
            while (curt != null){
                ListNode next = curt.next;
                curt.next = pre;
                pre = curt;
                curt = next;
            }
            return pre;
        }
    }
}
