package algorythm.Practice.programmers.step3;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class JingumBridge {

    static public int solutionPQ(int[] stones, int k) {
        PriorityQueue<Integer> window = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < k; i++) {
            window.add(stones[i]);
        }

        int min = window.peek();

        for (int i = k; i < stones.length; i++) {
            int leftmostVal = stones[i - k];
            int newVal = stones[i];

            window.remove(leftmostVal);
            window.add(newVal);

            min = Math.min(min, window.peek());
        }

        return min;
    }


}
