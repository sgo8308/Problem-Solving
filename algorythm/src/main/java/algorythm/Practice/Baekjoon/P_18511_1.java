package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class P_18511_1 {

    private static int k;
    private static int n;
    private static List<Integer> numbers = new ArrayList<>();
    private static int max = Integer.MIN_VALUE;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            numbers.add(Integer.valueOf(st.nextToken()));
        }
        int nLength = ("" + n).length();
        for (int i = 0; i < nLength; i++) {
            dfs("", nLength - i, 1);
            if (max != Integer.MIN_VALUE) {
               break;
            }
        }
        System.out.println(max);
    }

    private static void dfs(String candidate, int maxDepth, int depth) {
        if (depth > maxDepth)  {
            if (Integer.parseInt(candidate) <= n) {
                max = Math.max(max, Integer.parseInt(candidate));
            }
            return;
        }

        for (int i = 0; i < k; i++) {
            dfs(candidate + (int) numbers.get(i), maxDepth, depth + 1);
        }
    }
}
