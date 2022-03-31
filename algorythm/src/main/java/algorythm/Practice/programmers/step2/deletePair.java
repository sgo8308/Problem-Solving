package algorythm.Practice.programmers.step2;

import java.util.Stack;

public class deletePair {
    public static void main(String[] args) {
        Stack<Character> stack = new Stack<>();


    }


    public int solution(String s)
    {
        int answer = -1;
        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        stack.push(chars[0]);
        for (int i = 1; i < chars.length; i++) {
            if(stack.size() != 0 && stack.peek().equals(chars[i]))
                stack.pop();
            else{
                stack.push(chars[i]);
            }
        }

        if (stack.size() == 0) return 1;
        else return 0;
    }
}
