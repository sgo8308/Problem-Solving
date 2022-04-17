package algorythm.Practice.Leetcode;

import java.util.Arrays;

public class MergeSortedArray_1 {

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 0, 0, 0};
        int m = 3;
        int[] nums2 = {2, 5, 6};
        int n = 3;
        merge(nums1, m, nums2, n);
        System.out.println(Arrays.toString(nums1));
    }
    static public void merge(int[] nums1, int m, int[] nums2, int n) {
        int pointer1 = 0;
        int pointer2 = 0;
        for (int i = m; i < nums1.length; i++) {
            if (nums1[i] == 0) {
                nums1[i] = Integer.MAX_VALUE;
            }
        }
        while (pointer1 < m + n && pointer2 < n) {
            if (nums2[pointer2] < nums1[pointer1]) {
                for (int i = nums1.length - 2; i >= pointer1; i--) {
                    nums1[i + 1] = nums1[i];
                }
                nums1[pointer1] = nums2[pointer2];
                pointer2++;
            }
            pointer1++;
        }
    }
}
