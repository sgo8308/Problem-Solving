package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P_20300_1 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        String[] s = br.readLine().split(" ");
        long[] muscleLosses = new long[N];
        for (int i = 0; i < N; i++) {
            muscleLosses[i] = Long.parseLong(s[i]);
        }

        Arrays.sort(muscleLosses);
        if (muscleLosses.length % 2 == 0) {
            int left = 0;
            int right = N - 1;
            ArrayList<Long> muscleLossSums = new ArrayList<>();
            while (left <= right) {
                long muscleLossSum = muscleLosses[left++] + muscleLosses[right--];
                muscleLossSums.add(muscleLossSum);
            }
            System.out.println(muscleLossSums.stream().mapToLong(x->x).max().getAsLong());
        } else {
            int left = 0;
            int right = N - 2;
            ArrayList<Long> muscleLossSums = new ArrayList<>();
            while (left <= right) {
                long muscleLossSum = muscleLosses[left++] + muscleLosses[right--];
                muscleLossSums.add(muscleLossSum);
            }
            muscleLossSums.add(muscleLosses[N - 1]);
            System.out.println(muscleLossSums.stream().mapToLong(x->x).max().getAsLong());
        }
    }
}
