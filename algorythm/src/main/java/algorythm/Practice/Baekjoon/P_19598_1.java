package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P_19598_1 {
    /**
     * ------
     *    -------
     *        -------
     *           -------
     *             ---
     *
     * 시작 시간이 빠른 순서대로 회의를 배정할건데
     * 어떤 회의실에 들어가느냐는 가장 끝나는 시간이 빠른 회의실을 기준으로 이 회의의 시작 시간이
     * 가장 끝나는 시간이 빠른 회의실의 종료 시간보다 늦으면 집어 넣는다.
     * 나머지 회의실은 볼 필요가 없는게, 가장 끝나는 시간이 빠른 회의실에 배정할 수 없다면 어차피 나머지 회의실에도
     * 넣을 수 없기 때문이다.
     * 따라서 회의실을 배정 못했다면 새 회의실을 만든다.
     * 회의실은 끝나는 시각을 우선순위 큐 (최소힙)에 넣은 후 각 원소를 회의실로 규정한다.
     * 최소힙이기 때문에 가장 끝나는 시각이 빠른 회의가 우선순위 큐 가장 앞에 위치하게 된다.
     */
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        ArrayList<Conference> conferences = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            conferences.add(new Conference(Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())));
        }

        Collections.sort(conferences);

        PriorityQueue<Integer> conferenceEndTimes = new PriorityQueue<>();
        conferenceEndTimes.add(conferences.get(0).endTime);
        for (int i = 1; i < conferences.size(); i++) {
            int endTime = conferenceEndTimes.peek();
            Conference conference = conferences.get(i);

            if (conference.startTime >= endTime) {
                conferenceEndTimes.poll();
            }
            conferenceEndTimes.add(conference.endTime);
        }

        System.out.println(conferenceEndTimes.size());
    }
}

class Conference implements Comparable<Conference>{
    int startTime;
    int endTime;

    public Conference(int startTime, int endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public int compareTo(Conference o) {
        return Integer.compare(this.startTime, o.startTime);
    }
}