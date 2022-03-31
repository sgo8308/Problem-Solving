package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P_21758_2 {
    static ArrayList<Integer> honeys;
    static ArrayList<Integer> accumulatedHoneys;

    public static void main(String[] args) throws Exception{
        ArrayList<Integer> integers = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            integers.add(5);
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        honeys = new ArrayList<>(100000);
        accumulatedHoneys = new ArrayList<>(100000);
        for (int i = 0; i < N; i++) {
            int honey = Integer.valueOf(st.nextToken());
            honeys.add(honey);

            if (!accumulatedHoneys.isEmpty()) {
                int previousHoney = accumulatedHoneys.get(i - 1);
                accumulatedHoneys.add(previousHoney + honey);
                continue;
            }
            accumulatedHoneys.add(honey);
        }

        //벌 벌 꿀인 경우
        int max = Integer.MIN_VALUE;
        for (int beePosition = 1; beePosition < N - 1; beePosition++) {
            max = Math.max(max, carculateHoneyBBH(beePosition));
        }

        for (int honeyPosition = 1; honeyPosition < N - 1; honeyPosition++) {
            max = Math.max(max, carculateHoneyBHB(honeyPosition));
        }

        for (int beePosition = 1; beePosition < N - 1; beePosition++) {
            max = Math.max(max, carculateHoneyHBB(beePosition));
        }

        System.out.println(max);
    }

    private static int carculateHoneyHBB(int beePosition) {
        int firstBeeHoney = accumulatedHoneys.get(beePosition - 1);
        int secondBeeHoney = accumulatedHoneys.get(accumulatedHoneys.size() - 2)
                - accumulatedHoneys.get(beePosition);
        return firstBeeHoney + secondBeeHoney;
    }

    private static int carculateHoneyBHB(int honeyPosition) {
        int firstBeeHoney = accumulatedHoneys.get(honeyPosition) - honeys.get(0);
        int secondBeeHoney = accumulatedHoneys.get(accumulatedHoneys.size() - 1)
                - accumulatedHoneys.get(honeyPosition - 1) - honeys.get(honeys.size() - 1);

        return firstBeeHoney + secondBeeHoney;
    }

    private static int carculateHoneyBBH(int beePosition) {
        int firstBeeHoney = accumulatedHoneys.get(accumulatedHoneys.size() - 1)
                - honeys.get(0) - honeys.get(beePosition);
        int secondBeeHoney = accumulatedHoneys.get(accumulatedHoneys.size() - 1)
                - accumulatedHoneys.get(beePosition);

        return firstBeeHoney + secondBeeHoney;
    }
}
