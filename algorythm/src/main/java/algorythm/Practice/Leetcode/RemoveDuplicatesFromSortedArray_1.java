package algorythm.Practice.Leetcode;

public class RemoveDuplicatesFromSortedArray_1 {

    public static void main(String[] args) {
        int[] nums = {00, 1, 1, 1, 2, 2, 3, 3, 4};
        System.out.println(removeDuplicates(nums));
    }
    static public int removeDuplicates(int[] nums) {
        int k = 0;
        for (int i = nums.length-1; i > 0; i--) {
            if (nums[i] == nums[i - 1]) {
                nums[i] = -1000;
                k++;
            }
        }

        int pointer = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != -1000) {
                nums[pointer] = nums[i];
                pointer++;
            }

        }

        return k;
    }
}
