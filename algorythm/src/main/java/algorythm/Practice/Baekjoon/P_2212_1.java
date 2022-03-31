package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class P_2212_1 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        if (N <= K) {
            System.out.println(0);
            return;
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        List<Integer> sensorDistances = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            sensorDistances.add(Integer.valueOf(st.nextToken()));
        }
        Collections.sort(sensorDistances, Collections.reverseOrder());
        ArrayList<Integer> sensorDistanceDiffs = new ArrayList<>();
        for (int i = 1; i < N; i++) {
            sensorDistanceDiffs.add(Math.abs(sensorDistances.get(i) - sensorDistances.get(i - 1)));
        }
        Collections.sort(sensorDistanceDiffs, Collections.reverseOrder());
        long diffSum = sensorDistanceDiffs.stream().mapToLong(x -> x).sum();
        for (int i = 0; i < K - 1; i++) {
            diffSum -= sensorDistanceDiffs.get(i);
        }

        System.out.println(diffSum);
    }
}
