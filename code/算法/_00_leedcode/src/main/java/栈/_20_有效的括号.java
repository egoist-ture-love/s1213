package 栈;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/valid-parentheses/
 */
public class _20_有效的括号 {
//    static Map<Character,Character> map = new HashMap<>();
//    static {
//        map.put('(',')');
//        map.put('[',']');
//        map.put('{','}');
//    }
//    public boolean isValid(String s) {
//        Stack<Character> stack = new Stack<>();
//        int length= s.length();
//        for(int i = 0; i < length; i++){
//            char c = s.charAt(i);
//            if(map.containsKey(c)){
//                stack.push(c);
//            }else {
//                if(stack.isEmpty()) return false;
//                char left = stack.pop();
//                if(c != map.get(left)) return false;
//            }
//            return stack.isEmpty();
//        }








        /**
         * 1.完全使用栈的特性解题
         */
//        Stack<Character> stack = new Stack<>();
//        int length = s.length();
//        for (int i = 0; i < length; i++) {
//            char c = s.charAt(i);
//            if ( c == '(' || c =='{' || c=='['){
//                stack.push(c);
//            }else {
//                if(stack.size() == 0) return false;
//                char left = stack.pop();
//                if(left =='(' && c != ')') return false;
//                if(left =='[' && c != ']') return false;
//                if(left =='{' && c != '}') return false;
//            }
//        }
//        return stack.isEmpty();
//    }
}
