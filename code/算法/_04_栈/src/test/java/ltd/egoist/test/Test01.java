package ltd.egoist.test;

import ltd.egoist.stack.Stack;
import org.junit.Test;

public class Test01 {
    public static void main(String[] args) {
        boolean valid = isValid("()");
        System.out.println(valid);
    }
    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        int length = s.length();
        for (int i = 0; i < length; i++) {
            Character c = s.charAt(i);
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (stack.size() == 0) return false;
                String str = stack.pop()+ c +  "";
                if (str.equals("()")  || str.equals("[]")  || str.equals("{}") ) {

                }else {
                    return false;
                }
            }
        }
        if (stack.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

}
