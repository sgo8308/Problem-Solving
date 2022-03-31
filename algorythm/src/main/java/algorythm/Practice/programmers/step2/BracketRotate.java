package algorythm.Practice.programmers.step2;

import java.util.Stack;

public class BracketRotate {
    public static void main(String[] args) {
        System.out.println(solution("[](){}"));
        System.out.println(solution("}]()[{"));
        System.out.println(solution("[)(]"));
        System.out.println(solution("}}}"));
    }

    static public int solution(String s) {
        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            if(isRightBracket(s)) count++;

            s = rotate(s);
        }

        return count;
    }

    static String rotate(String s){
        return s.substring(1) + s.substring(0,1);
    }

    static boolean isRightBracket(String s){
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            if(stack.size() == 0 || s.charAt(i) == '[' || s.charAt(i) == '(' || s.charAt(i) == '{'){
                stack.push(s.charAt(i));
            }else{
                char cha = stack.peek();
                if( (s.charAt(i) == ']' && cha == '[')  ||
                        s.charAt(i) == '}' && cha == '{' || s.charAt(i) == ')' && cha == '('){
                    stack.pop();
                }else{
                    stack.push(s.charAt(i));
                }
            }
        }
        if (stack.size() == 0) return true;
        return false;
    }
}
