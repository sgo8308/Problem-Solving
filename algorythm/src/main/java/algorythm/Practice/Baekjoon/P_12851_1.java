package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.function.Function;

public class P_12851_1 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int subinPosition = Integer.parseInt(st.nextToken());
        int brotherPosition = Integer.parseInt(st.nextToken());

        if (subinPosition == brotherPosition) {
            System.out.println(0);
            System.out.println(1);
            return;
        }

        if (subinPosition > brotherPosition) {
            System.out.println(subinPosition - brotherPosition);
            System.out.println(1);
            return;
        }

        int[] answer = bfs(subinPosition, brotherPosition);

        System.out.println(answer[0]);
        System.out.println(answer[1]);
    }

    private static int[] bfs(int subin, int brother) {
        Function<Integer, Integer>[] operations = new Function[3];
        operations[0] = x -> x * 2;
        operations[1] = x -> x + 1;
        operations[2] = x -> x - 1;

        Queue<Integer> q = new LinkedList<>();

        int[] times = new int[100001];
        int minTime = 0;
        int count = 0;

        for (int i = 0; i < operations.length; i++) {
            int nextSubin = operations[i].apply(subin);
            if (nextSubin >= 0 && nextSubin <= 100000) {
                times[nextSubin] = 1;
                q.add(nextSubin);
            }
        }
        while (!q.isEmpty()) {
            int nowSubin = q.poll();
            int nowTime = times[nowSubin];
            if (minTime != 0 && nowTime > minTime) {
                break;
            }
            if (nowSubin == brother) {
                minTime = nowTime;
                count++;
            }

            for (int i = 0; i < operations.length; i++) {
                int nextSubin = operations[i].apply(nowSubin);
                if (nextSubin >= 0 && nextSubin <= 100000 &&
                        (times[nextSubin] == nowTime + 1 || times[nextSubin] == 0)) {
                    times[nextSubin] = nowTime + 1;
                    q.add(nextSubin);
                }
            }
        }

        return new int[]{minTime, count};
    }
}
