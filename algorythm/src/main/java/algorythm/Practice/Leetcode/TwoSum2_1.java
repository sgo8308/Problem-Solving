package algorythm.Practice.Leetcode;

import java.util.Arrays;
import java.util.Comparator;

public class TwoSum2_1 {

    public static void main(String[] args) {
        int[] a = {-1, 0};
        System.out.println(Arrays.toString(twoSum(a, -1)));
    }

    static public int[] twoSum(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; i++) {
            int complement = target - numbers[i];
            int idx = Arrays.binarySearch(numbers, i + 1, numbers.length, complement);
            if (idx >= 0 && i != idx) {
                return new int[]{i + 1, idx + 1};
            }
        }
        return null;
    }
}
