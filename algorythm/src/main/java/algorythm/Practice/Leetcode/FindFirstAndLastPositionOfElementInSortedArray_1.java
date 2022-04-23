package algorythm.Practice.Leetcode;

import java.util.Arrays;

public class FindFirstAndLastPositionOfElementInSortedArray_1 {

    public static void main(String[] args) {
        int[] nums = {5, 7, 7, 8, 8, 10};
        int target = 8;
//        int[] nums = {};
//        int target = 0;
        System.out.println(Arrays.toString(searchRange(nums, target)));
    }

    //왼쪽 끝과 오른쪽 끝을 찾기
    // 0 1 2 3 4 5 6 7 8 9
    static public int[] searchRange(int[] nums, int target) {
        int idx1 = findStartIndex(nums, target);
        int idx2 = findEndIndex(nums, target);
        return new int[]{idx1, idx2};
    }

    static private int findStartIndex(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (nums[mid] == target && (mid == 0 || nums[mid - 1] < nums[mid])) {
                return mid;
            }

            if (nums[mid] >= target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }
    private static int findEndIndex(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (nums[mid] == target && (mid == nums.length - 1 || nums[mid + 1] > nums[mid])) {
                return mid;
            }

            if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }
}
