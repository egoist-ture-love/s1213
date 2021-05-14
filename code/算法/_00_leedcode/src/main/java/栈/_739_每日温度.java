package 栈;

import java.util.Stack;

public class _739_每日温度 {
    public int[] dailyTemperatures(int[] T) {
        int[] arr = new int[T.length];
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < T.length; i++){
            while(!stack.isEmpty() && T[i] > T[stack.peek()]){
                int index = stack.pop();
                arr[index] = i - index;
            }
            stack.push(i);
        }
        return arr;
    }
}
