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
//        int[] nums = {-1, 0, 1, 2, -1, -4};
        int[] nums = {1, 1, -2, 0, 2, 2, -1, -1};
//        int[] nums = {0, 0};
        List<List<Integer>> lists = threeSum2(nums);
        for (int i = 0; i < lists.size(); i++) {
            System.out.println(lists.get(i).toString());
        }
    }

    static public List<List<Integer>> threeSum2(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.putIfAbsent(nums[i], 0);
            map.put(nums[i], map.get(nums[i]) + 1);
        }

        Set<List<Integer>> answerSet = new HashSet<>(nums.length * nums.length);
        for (Integer target : map.keySet()) {
            for (Integer cand1 : map.keySet()) {
                int cand2 = -target - cand1;
                if (target == cand1 && target == cand2) {
                    if (map.get(0) >= 3) {
                        answerSet.add(Arrays.asList(0, 0, 0));
                    }
                } else if (target == cand1) {
                    if (map.get(target) >= 2 && map.containsKey(cand2)) {
                        putThree(target, cand1, cand2, answerSet);
                    }
                } else if (cand1 == cand2 || target == cand2) {
                    if (map.get(cand2) >= 2) {
                        putThree(target, cand1, cand2, answerSet);
                    }
                } else {
                    if (map.containsKey(cand2)) {
                        putThree(target, cand1, cand2, answerSet);
                    }
                }
            }
        }

        return new ArrayList<>(answerSet);
    }

    private static void putThree(Integer target, int cand1, int cand2,
            Set<List<Integer>> answerSet) {
        List<Integer> t = Arrays.asList(target, cand1, cand2);
        Collections.sort(t);
        answerSet.add(t);
    }
}
