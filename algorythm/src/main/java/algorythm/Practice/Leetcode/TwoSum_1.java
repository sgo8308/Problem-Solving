package algorythm.Practice.Leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TwoSum_1 {

    public static void main(String[] args) {
        int[] nums = {3, 2, 4};
        int target = 6;
        System.out.println(Arrays.toString(twoSum(nums, target)));
    }
    static public int[] twoSum(int[] nums, int target) {
       Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.putIfAbsent(nums[i], new ArrayList<>());
            map.get(nums[i]).add(i);
        }

        for (Integer num : map.keySet()) {
            if ((num == target - num && map.get(num).size() >= 2)) {
                return new int[]{map.get(num).get(0), map.get(num).get(1)};
            } else if (map.containsKey(target - num)) {
                return new int[]{map.get(num).get(0), map.get(target - num).get(0)};
            }
        }

        return new int[]{0, 0};
    }
}
