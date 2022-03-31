package algorythm.Practice.Leetcode;

import java.util.EmptyStackException;
import java.util.LinkedList;

public class MaxStackProblem {

    public static void main(String[] args) {
        MaxStack stack = new MaxStack();
        stack.push(5);
        stack.push(1);
        stack.push(5);
        System.out.println(stack.top());
        System.out.println(stack.popMax());
        System.out.println(stack.top());
        System.out.println(stack.peekMax());
        System.out.println(stack.pop());
        System.out.println(stack.top());
    }
}

class MaxStack {

    private LinkedList<Integer> list = new LinkedList<>();
    private Integer maxValue = Integer.MIN_VALUE;

    public void push(int value) {
        maxValue = Math.max(maxValue, value);
        list.addFirst(value);
    }

    public int pop() {
        checkException();
        return list.removeFirst();
    }

    public int top() {
        checkException();
        return list.getFirst();
    }

    public int peekMax() {
        checkException();
        return maxValue;
    }

    public int popMax() {
        checkException();
        list.remove(maxValue);
        return maxValue;
    }

    private void checkException() {
        if (list.isEmpty()) {
            throw new EmptyStackException();
        }
    }
}
