package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class P_5568_1 {
    static List<Integer> numbers = new ArrayList<>();
    private static int n;
    private static int k;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            numbers.add(Integer.parseInt(br.readLine()));
        }

        Set<Integer> nums = new HashSet<>();

        dfs(new ArrayList<>(), nums, "", 1);
        System.out.println(nums.size());
    }

    private static void dfs(List<Integer> comb,Set<Integer> nums,String num, int depth) {
        if (depth > k) {
            nums.add(Integer.valueOf(num));
            return;
        }

        for (int i = 0; i < n; i++) {
            if (comb.contains(i)) {
                continue;
            }
            comb.add(i);
            dfs(comb, nums, num + (int) numbers.get(i), depth + 1);
            comb.remove(comb.size() - 1);
        }
    }
}
