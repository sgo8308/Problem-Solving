package algorythm.Practice.Leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class KthLargestElementInAnArray_1 {

    public static void main(String[] args) {
//        int[] nums = {3, 2, 1, 5, 6, 4};
//        int k = 2;
        int[] nums = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        int k = 4;
        System.out.println(findKthLargest(nums, k));

    }

    static public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> a = new PriorityQueue<>(Comparator.reverseOrder());
        a.addAll(Arrays.stream(nums).mapToObj(x -> x).collect(Collectors.toList()));
        for (int i = 0; i < k - 1; i++) {
            a.poll();
        }

        return a.poll();
    }
}
