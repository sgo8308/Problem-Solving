package algorythm.Practice.programmers.step3;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class InsertAd {

    public static void main(String[] args) {
        String p = "02:03:55";
        String c = "00:14:15";
        String[] logs = {"01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29", "01:30:59-01:53:29", "01:37:44-02:02:30"};

        InsertAd i = new InsertAd();
        String solution = i.solution(p, c, logs);

        String p2 = "99:59:59";
        String c2 = "25:00:00";
        String[] logs2 = {"69:59:59-89:59:59", "01:00:00-21:00:00", "79:59:59-99:59:59", "11:00:00-31:00:00"};
        String solution2 = i.solution(p2, c2, logs2);

        String p3 = "50:00:00";
        String c3 = "50:00:00";
        String[] logs3 = {"15:36:51-38:21:49", "10:14:18-15:36:51", "38:21:49-42:51:45"};
        String solution3 = i.solution(p3, c3, logs3);
    }

    public String solution(String play_time, String adv_time, String[] logs) {
        int[] peoples = new int[toSeconds("99:59:59") + 2];
        int playTimeSec = toSeconds(play_time);

        for (int i = 0; i < logs.length; i++) {
            String[] split = logs[i].split("-");
            int start = toSeconds(split[0]);
            int end = toSeconds(split[1]);
            peoples[start]++;
            peoples[end]--;
        }

        int val = 0;
        for (int i = 0; i < peoples.length; i++) {
            val += peoples[i];
            peoples[i] = val;
        }

        int head = 0;
        int tail = 0;
        int advTimeSec = toSeconds(adv_time);

        long maxSum = Integer.MIN_VALUE;
        int answer = 0;
        long sum = 0;

        for (head = 0; head < advTimeSec; head++) {
            sum += peoples[head];
            if(sum > maxSum) {
                maxSum = sum;
                answer = tail;
            }
        }
        while (head <= playTimeSec) {
            sum += peoples[head];
            sum -= peoples[tail];

            if(sum > maxSum) {
                maxSum = sum;
                answer = tail + 1;
            }

            head++;
            tail++;
        }

        return toTime(answer);
    }

    private String toTime(int answer) {
        int hour = answer / 3600;
        int min = (answer % 3600) / 60;
        int sec = answer % 60;

        return String.format("%02d:%02d:%02d", hour, min, sec);
    }

    private int toSeconds(String time) {
        String[] split = time.split(":");
        int sec = Integer.parseInt(split[0]) * 3600;
        sec += Integer.parseInt(split[1]) * 60;
        sec += Integer.parseInt(split[2]);
        return sec;
    }

}
