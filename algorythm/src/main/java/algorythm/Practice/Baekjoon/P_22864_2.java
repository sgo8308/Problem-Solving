package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P_22864_2 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int fatiguePlus = Integer.parseInt(st.nextToken());
        int throughput = Integer.parseInt(st.nextToken());
        int fatigueMinus = Integer.parseInt(st.nextToken());
        int fatigueLimit = Integer.parseInt(st.nextToken());

        //엣지 케이스 a = 100 b = 1 c = 1 m = 1
        int fatigue = 0;
        int answer = 0;

        for (int i = 0; i < 24; i++) {
            if (fatigue + fatiguePlus >= fatigueLimit) {
                if (fatigue - fatigueMinus <= 0) {
                    fatigue = 0;
                    continue;
                }
                fatigue -= fatigueMinus;
                continue;
            }

            answer += throughput;
            fatigue += fatiguePlus;
        }

        System.out.println(answer);
    }
}
