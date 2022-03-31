package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P_21758_3 {

    /**
     * 틀리는 이유
     *
     *
     */

    static ArrayList<Integer> honeys;
    static ArrayList<Integer> accumulatedHoneys;
    static int N;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        honeys = new ArrayList<>(100001);
        accumulatedHoneys = new ArrayList<>(100001);
        for (int i = 0; i < N; i++) {
            int honey = Integer.parseInt(st.nextToken());
            honeys.add(honey);
            int previousHoney = 0;

            if (!accumulatedHoneys.isEmpty()) {
                previousHoney = accumulatedHoneys.get(i - 1);
            }
            accumulatedHoneys.add(previousHoney + honey);
        }

        //벌 벌 꿀인 경우
        int max = Integer.MIN_VALUE;
        for (int beePosition = 1; beePosition < N - 1; beePosition++) {
            max = Math.max(max, carculateHoneyBBH(beePosition));
        }

        //벌 꿀 벌인 경우
        for (int honeyPosition = 1; honeyPosition < N - 1; honeyPosition++) {
            max = Math.max(max, carculateHoneyBHB(honeyPosition));
        }

        //꿀 벌 벌인 경우
        for (int beePosition = 1; beePosition < N - 1; beePosition++) {
            max = Math.max(max, carculateHoneyHBB(beePosition));
        }

        System.out.println(max);
    }

    private static int carculateHoneyBBH(int beePosition) {
        int firstBeeHoney = accumulatedHoneys.get(N - 1) - honeys.get(0) - honeys.get(beePosition);
        int secondBeeHoney = accumulatedHoneys.get(N - 1) - accumulatedHoneys.get(beePosition);

        return firstBeeHoney + secondBeeHoney;
    }

    private static int carculateHoneyHBB(int beePosition) {
        int firstBeeHoney = accumulatedHoneys.get(beePosition - 1);
        int secondBeeHoney = accumulatedHoneys.get(N - 2) - honeys.get(beePosition);

        return firstBeeHoney + secondBeeHoney;
    }

    private static int carculateHoneyBHB(int honeyPosition) {
        int firstBeeHoney = accumulatedHoneys.get(honeyPosition) - honeys.get(0);
        int secondBeeHoney = accumulatedHoneys.get(N - 1)
                - accumulatedHoneys.get(honeyPosition - 1) - honeys.get(N - 1);

        return firstBeeHoney + secondBeeHoney;
    }
}
