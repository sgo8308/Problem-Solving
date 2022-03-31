package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class P_14889_1 {
    static int N;
    static int[][] abilityMap;
    static int minDiff = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        abilityMap = new int[N + 1][N + 1];
        for (int i = 1; i < N + 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j < N + 1; j++) {
                abilityMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(1, new ArrayList<>(), 0);

        System.out.println(minDiff);
    }

    private static void dfs(int start, List<Integer> comb, int depth) {
        if (depth >= N / 2) {

            minDiff = Math.min(minDiff, calculateDiff(comb));
            return;
        }
        for (int i = start; i < N + 1; i++) {
            if (comb.contains(i)) continue;
            comb.add(i);
            dfs(start + 1, comb, depth + 1);
            comb.remove(comb.size() - 1);
        }
    }

    private static int calculateDiff(List<Integer> startComb) {
        int teamStartAbility = calculateAbility(startComb);

        List<Integer> linkComb = new ArrayList<>();
        for (int i = 1; i < N + 1; i++) {
            if (!startComb.contains(i)) {
                linkComb.add(i);
            }
        }
        int teamLinkAbility = calculateAbility(linkComb);

        return Math.abs(teamStartAbility - teamLinkAbility);
    }

    private static int calculateAbility(List<Integer> startComb) {
        int ability = 0;
        for (int i = 0; i < startComb.size(); i++) {
            for (int j = 0; j < startComb.size(); j++) {
                if(i == j) continue;
                ability += abilityMap[startComb.get(i)][startComb.get(j)];
            }
        }
        return ability;
    }
}
