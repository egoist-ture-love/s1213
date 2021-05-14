package 栈;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/min-stack/description/
 */
public class _155_最小栈 {
    /** initialize your data structure here. */
    //使用双栈来实现   一个存储最小值  另一个存储元素
    //注意peek 和 pop区别
    Stack<Integer> stack = new Stack<>();
    Stack<Integer> min_Stack = new Stack<>();
    public _155_最小栈() {

    }

    public void push(int val) {
        if(stack.isEmpty()){
            min_Stack.push(val);
        }else {
            Integer peek = min_Stack.peek();
            if(peek >= val){
                min_Stack.push(val);
            }
        }
        stack.push(val);
    }

    public void pop() {
        int temp = stack.pop();
        if(temp == min_Stack.peek()){
            min_Stack.pop();
        }

    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min_Stack.peek();
    }
}
