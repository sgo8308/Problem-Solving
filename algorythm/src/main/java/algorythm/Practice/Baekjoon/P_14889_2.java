package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P_14889_2 {

    static int N;
    static int minDiff = Integer.MAX_VALUE;
    static int[] members;
    static int[][] abilityBoard;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        members = new int[N];
        abilityBoard = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                abilityBoard[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0);
        System.out.println(minDiff);
    }

    private static void dfs(int startIndex, int depth) {
        if (minDiff == 0) {
            return;
        }

        if (depth == N / 2) {
            int diff = calculateDiff();
            minDiff = Math.min(minDiff, diff);
            return;
        }

        for (int i = startIndex; i < N; i++) {
            members[i] = 1;
            dfs(i + 1, depth + 1);
            members[i] = 0;
        }
    }

    private static int calculateDiff() {
        int startTeamScore = 0;
        int linkTeamScore = 0;

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (members[i] == 1 && members[j] == 1) {
                    startTeamScore += abilityBoard[i][j] + abilityBoard[j][i];
                }

                if (members[i] == 0 && members[j] == 0) {
                    linkTeamScore += abilityBoard[i][j] + abilityBoard[j][i];
                }
            }
        }

        return Math.abs(startTeamScore - linkTeamScore);
    }
}
