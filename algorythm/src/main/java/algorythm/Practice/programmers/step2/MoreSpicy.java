package algorythm.Practice.programmers.step2;

import java.util.PriorityQueue;

public class MoreSpicy {
    public static void main(String[] args) {
        //ksms 10억 이하
        int[] scovile = {1,2,3,9,10,12};
        int k = 7;

        PriorityQueue<Integer> heap = new PriorityQueue();

        for (int i = 0; i < scovile.length; i++) {
            heap.add(scovile[i]);
        }
        int answer = 0;
        while(heap.peek() < k){
            int min1 = heap.poll();
            int min2 = heap.poll();

            int newFoodScovile = mix(min1, min2);

            heap.add(newFoodScovile);
            answer++;
        }
        System.out.println(answer);
    }

    static Integer mix(int min1, int min2){
        return min1 + min2 * 2;
    }

}
