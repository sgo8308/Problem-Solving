package algorythm.Practice.programmers.step3;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.PriorityQueue;

public class JingumBridge {

    public static void main(String[] args) {
        int[] stones = {2, 4, 5, 3, 2, 1, 4, 2, 5, 1};
        System.out.println(solutionBS(stones, 3));
    }

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

    static public int solutionMQ(int[] stones, int k) {
        int head = 0;
        int tail = 0;

        Deque<Integer[]> q = new ArrayDeque<>();

        int min = Integer.MAX_VALUE;
        while (head < stones.length) {
            //윈도우 벗어난 값 제거
            if (!q.isEmpty() && q.peek()[1] < tail) {
                q.removeFirst();
            }

            //자신보다 큰 값이 나올 떄까지 뒤에서부터 파고들어 집어 넣기
            int newVal = stones[head];
            while (!q.isEmpty() && newVal >= q.peekLast()[0]) {
                q.removeLast();
            }

            q.addLast(new Integer[]{newVal, head});

            if (head - tail < k - 1) {
                head++;
            } else {
                head++;
                tail++;
                min = Math.min(min, q.peekFirst()[0]);
            }
        }

        return min;
    }

    static public int solutionBS(int[] stones, int k) {
        int right = 200_000_001;
        int left = 0;
        int mid = (right + left) / 2;

        while (left + 1 < right) {
            //연속으로 k개 이상 0이되면 이 인원은 무리 [2, 4, 5, 3, 2, 1, 4, 2, 5, 1]
            int cnt = 0;
            boolean canCross = true;
            for (int i = 0; i < stones.length; i++) {
                if (stones[i] <= mid) {
                    cnt++;
                } else {
                    cnt = 0;
                }

                if (cnt == k) {
                    canCross = false;
                    break;
                }
            }

            if (canCross) {
                left = mid;
            } else {
                right = mid;
            }

            mid = (left + right) / 2;
        }

        return left;
    }

}
