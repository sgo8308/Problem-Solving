package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.Timer;

public class P_1092_2 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        Integer[] crainWeightLimits = new Integer[N];
        boolean[] canMoves = new boolean[N];
        Arrays.fill(canMoves, true);
        for (int i = 0; i < N; i++) {
            crainWeightLimits[i] = Integer.valueOf(st.nextToken());
        }

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        Integer[] boxWeights = new Integer[M];
        boolean[] isMoveds = new boolean[M];
        for (int i = 0; i < M; i++) {
            boxWeights[i] = Integer.valueOf(st.nextToken());
        }

        Arrays.sort(crainWeightLimits);
        Arrays.sort(boxWeights, Comparator.reverseOrder());

        int moveCount = 0;
        boolean canMove = true;
        int time = 0;
        while (moveCount < M && canMove) {
            canMove = false;
            for (int i = 0; i < N; i++) {
                if (!canMoves[i]) {
                    continue;
                }
                canMoves[i] = false;
                int crainWeightLimit = crainWeightLimits[i];

                for (int j = 0; j < M; j++) {
                    if (!isMoveds[j] && boxWeights[j] <= crainWeightLimit) {
                        isMoveds[j] = true;
                        canMove = true;
                        canMoves[i] = true;
                        moveCount++;
                        break;
                    }
                }
            }
            time++;
        }

        System.out.println((moveCount == M) ? time : -1);
    }
}
