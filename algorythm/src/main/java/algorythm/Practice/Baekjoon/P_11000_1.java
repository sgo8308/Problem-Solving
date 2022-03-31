package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P_11000_1 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<Ban> bans = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            bans.add(new Ban(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        Collections.sort(bans);

        PriorityQueue<Integer> endTimes = new PriorityQueue<>();
        endTimes.add(bans.get(0).endTime);
        for (int i = 1; i < bans.size(); i++) {
            if (bans.get(i).startTime >= endTimes.peek()) {
                endTimes.poll();
                endTimes.add(bans.get(i).endTime);
                continue;
            }
            endTimes.add(bans.get(i).endTime);
        }
        System.out.println(endTimes.size());
    }

}

class Ban implements Comparable<Ban> {

    int startTime;
    int endTime;

    public Ban(int startTime, int endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public int compareTo(Ban o) {
        return Integer.compare(startTime, o.startTime);
    }
}