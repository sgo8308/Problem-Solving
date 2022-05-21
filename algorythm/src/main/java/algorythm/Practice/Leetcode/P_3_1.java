package algorythm.Practice.Leetcode;

import java.util.HashSet;
import java.util.Set;

public class P_3_1 {

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("pwwkew"));
    }

    public static int lengthOfLongestSubstring(String s) {
        char[] chars = s.toCharArray();
        Set<Character> window = new HashSet<>();
        int ptr1 = 0, ptr2 = 0, max = 0;

        while (ptr2 < s.length()) {
            if (!window.contains(chars[ptr2])) {
                window.add(chars[ptr2]);
                ptr2++;
                max = Math.max(max, window.size());
            } else {
                while (window.contains(chars[ptr2])) {
                    window.remove(chars[ptr1]);
                    ptr1++;
                }
            }
        }
        return max;
    }
}


