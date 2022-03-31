package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P_2231_1 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        for (int candidate = 1; candidate < N; candidate++) {
            if (getConstructor(candidate) == N) {
                System.out.println(candidate);
                return;
            }
        }
        System.out.println(0);
    }

    private static int getConstructor(int i) {
        char[] nums = ("" + i).toCharArray();
        int constructor = i;
        for (int j = 0; j < nums.length; j++) {
            constructor += Character.getNumericValue(nums[j]);
        }
        return constructor;
    }
}
