package algorythm.Practice.programmers.step3;

import java.util.Scanner;

public class WalkerHeaven {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String s1 = "";
        while (s.hasNext()) {
           s1.concat(s.nextLine());
        }
        String s2 = s1.replaceAll(" ", "");
        System.out.println(s2);
        WalkerHeaven w = new WalkerHeaven();
        int solution = w.solution(3, 3, new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}});
        int solution1 = w.solution(3, 6,
                new int[][]{{0, 2, 0, 0, 0, 2}, {0, 0, 2, 0, 1, 0}, {1, 0, 0, 2, 2, 0}});
        System.out.println();
    }

    int MOD = 20170805;

    public int solution(int m, int n, int[][] cityMap) {
        long[][][] dp = new long[m][n][2]; // 0:위에서 온, 1: 왼쪽에서 온
        dp[0][0][0] = dp[0][0][1] = 1;
        dp[0][1][0] = 0; dp[0][1][1] = 1;
        dp[1][0][0] = 1; dp[1][0][1] = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(!(dp[i][j][0] == 0 && dp[i][j][1] == 0)) {
                    continue;
                }

                //dp 업데이트
                if (i - 1 < 0 || cityMap[i - 1][j] == 1) { //통행금지
                    dp[i][j][0] = 0;
                } else if(cityMap[i - 1][j] == 2) { //좌회전 우회전 금지
                    dp[i][j][0] = dp[i - 1][j][0];
                } else if(cityMap[i - 1][j] == 0){ // 다 가능
                    dp[i][j][0] += dp[i - 1][j][0] + dp[i - 1][j][1];
                }

                if(j - 1 < 0 || cityMap[i][j - 1] == 1) {
                    dp[i][j][1] = 0;
                } else if (cityMap[i][j - 1] == 2) {
                    dp[i][j][1] = dp[i][j - 1][1];
                } else if (cityMap[i][j - 1] == 0) {
                    dp[i][j][1] += dp[i][j - 1][0] + dp[i][j - 1][1];
                }

                dp[i][j][0] %= MOD;
                dp[i][j][1] %= MOD;
            }
        }

        return (int)(dp[m - 1][n - 1][0] + dp[m - 1][n - 1][1]);
    }

}
