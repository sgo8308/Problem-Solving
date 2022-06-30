package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P_11053_1 {

    /**
     * L(n) = L(n-1) + 1  if max(n-1) <= A[n]
     * L(n) = L(n-1)  if max(n-1) > A[n]
     */
    static int n;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        int[] dp = new int[n];

        for (int i = 1; i < n; i++) {
        }

        System.out.println(dp[n - 1]);
    }
}
