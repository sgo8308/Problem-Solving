package algorythm.Practice.Leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupAnagrams_1 {

    public static void main(String[] args) {
        String[] a = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> lists = groupAnagrams(a);
        for (int i = 0; i < lists.size(); i++) {
            System.out.println(lists.get(i).toString());
        }
    }

    static public List<List<String>> groupAnagrams(String[] strs) {
        Map<List<String>, List<String>> map = new HashMap<>(10000);
        for (int i = 0; i < strs.length; i++) {
            String str = strs[i];
            List<String> sList = Arrays.stream(str.split("")).sorted().collect(Collectors.toList());

            map.putIfAbsent(sList, new ArrayList<>());
            map.get(sList).add(str);
        }

        return new ArrayList<>(map.values());
    }
}
