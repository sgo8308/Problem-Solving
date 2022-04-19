package algorythm.Practice.Leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ThreeSum_1 {

    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
//        int[] nums = {1, 1, -2, 0, 2, 2, -1, -1};
//        int[] nums = {0, 0};
        List<List<Integer>> lists = threeSum(nums);
        for (int i = 0; i < lists.size(); i++) {
            System.out.println(lists.get(i).toString());
        }
    }

    //O(n^2)
    static public List<List<Integer>> threeSum(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.putIfAbsent(nums[i], 0);
            map.put(nums[i], map.get(nums[i]) + 1);
        }

        Set<List<Integer>> answerSet = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            int target = nums[i];

            for (int j = i + 1; j < nums.length; j++) {
                int cand1 = nums[j];
                int cand2 = -target - cand1;

                if (!map.containsKey(cand2)) continue;

                if ((target == cand2 || cand1 == cand2) && map.get(cand2) < 2) continue;

                if(target == cand2 && cand1 == cand2 && map.get(cand2) < 3) continue;

                List<Integer> t = Arrays.asList(target, cand1, cand2);
                Collections.sort(t);
                answerSet.add(t);
            }
        }

        return new ArrayList<>(answerSet);
    }
}
