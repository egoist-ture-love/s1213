package 链表;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/add-two-numbers-ii/description/
 */
public class _445_两数相加II {
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        /**
         * 两链表相加主要是使用栈先进后出的思想进行求解
         */
        Stack<Integer> stackl1 = getStack(l1);
        Stack<Integer> stackl2 = getStack(l2);
        int carry = 0;
        ListNode temp = new ListNode(0);
        while (!stackl1.isEmpty() || !stackl2.isEmpty() || carry != 0) {
            int x = stackl1.isEmpty() ? 0 : stackl1.pop();
            int y = stackl2.isEmpty() ? 0 : stackl2.pop();
            int sum = x + y + carry;
            ListNode listNode = new ListNode(sum % 10);
            listNode.next = temp.next;
            temp.next = listNode;
            carry = sum / 10;
        }
        return temp.next;

    }

    public Stack<Integer> getStack(ListNode l) {
            Stack<Integer> stack = new Stack<>();
            while (l != null) {
                stack.push(l.val);
                l = l.next;
            }
            return stack;
    }
}
