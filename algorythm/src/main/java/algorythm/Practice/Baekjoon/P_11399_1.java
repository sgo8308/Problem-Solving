package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P_11399_1 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        Integer[] times = new Integer[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < times.length; i++) {
            times[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(times);

        int totalTime = 0;
        int waitTime = 0;
        for (int i = 0; i < N; i++) {
            totalTime += waitTime + times[i];
            waitTime += times[i];
        }

        System.out.println(totalTime);
    }
}
