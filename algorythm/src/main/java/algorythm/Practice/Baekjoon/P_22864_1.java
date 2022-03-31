package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P_22864_1 {

    private static int fatiguePlus;
    private static int throughput;
    private static int fatigueMinus;
    private static int fatigueLimit;
    private static int throughputMax;
    public static void main(String[] args) throws Exception{
        getInputAndSetVar();
        dfs("", 0, 0, 1);
        System.out.println(throughputMax);
    }

    public static void dfs(String debug, int throughputTotal, int fatigue, int depth ) {
        if (depth > 24) {
            if (throughputTotal == 105) {
                System.out.println(debug);
            }
            if (fatigue <= fatigueLimit) {
                throughputMax = Math.max(throughputMax, throughputTotal);
            }
            return;
        }

        for (int i = 0; i < 2; i++) {
            int tpt = throughputTotal;
            int fati = fatigue;
            if (i == 0 && fatigue + fatiguePlus <= fatigueLimit) { // 일한다
                throughputTotal += throughput;
                fatigue += fatiguePlus;
            } else { //일쉰다
                fatigue -= fatigueMinus;
                if (fatigue < 0) {
                    fatigue = 0;
                }
            }
            dfs(debug + i, throughputTotal, fatigue, depth + 1);
            throughputTotal = tpt;
            fatigue = fati;
        }
    }

    private static void getInputAndSetVar() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        fatiguePlus = Integer.parseInt(st.nextToken());
        throughput = Integer.parseInt(st.nextToken());
        fatigueMinus = Integer.parseInt(st.nextToken());
        fatigueLimit = Integer.parseInt(st.nextToken());
    }
}
