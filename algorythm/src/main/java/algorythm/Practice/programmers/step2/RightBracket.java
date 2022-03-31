package algorythm.Practice.programmers.step2;

import java.util.ArrayDeque;
import java.util.Stack;

public class RightBracket {
    public static void main(String[] args) {
        System.out.println(solution("()()"));
        System.out.println(solution("(())()"));
        System.out.println(solution(")()("));
        System.out.println(solution("(()("));
    }

    static boolean solution(String s) {
//        Stack<Character> stack2 = new Stack<>();
        ArrayDeque<Character> stack2 = new ArrayDeque<>(100000);


        for (int i = 0; i < s.length(); i++) {
            if (processEmptyStack(s, stack2, i)) continue;

            Character left = stack2.peek();
            Character right = s.charAt(i);

            checkAndPopAndPush(stack2, left, right);
        }
        
        if(stack2.isEmpty()) return true;
        
        return false;
    }

    static private boolean processEmptyStack(String s, ArrayDeque<Character> stack, int i) {
        if(stack.isEmpty()) {
            stack.push(s.charAt(i));
            return true;
        }
        return false;
    }

    static private void checkAndPopAndPush(ArrayDeque<Character> stack, Character left, Character right) {
        if (left.equals('(') && right.equals(')')) {
            stack.pop();
        } else {
            stack.push(right);
        }
    }
}
