package algorythm.Practice.programmers.step3;

public class InsertAd_2 {
     public static void main(String[] args) {
        String p = "02:03:55";
        String c = "00:14:15";
        String[] logs = {"01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29", "01:30:59-01:53:29", "01:37:44-02:02:30"};

        InsertAd_2 i = new InsertAd_2();
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
        int playTime = strToSecond(play_time);
        int advTime = strToSecond(adv_time);
        int[] counts = new int[playTime + 1]; // playTime의 값까지 포함해야함으로 +1

        for (String log : logs) {
            String[] splits = log.split("-");
            int startViewTime = strToSecond(splits[0]);
            int endViewTime = strToSecond(splits[1]);

            // viewer 의 시작부터 끝까지 +1 - 종료 시점은 본것아니므로 < 부등호 사용
            for (int i = startViewTime; i < endViewTime; i++) {
                counts[i]++;
            }
        }

        // 0초에 광고를 넣는 다고가정 했을때 누적 값을 계산
        int startTime = 0;
        int endTime = advTime;
        long sum = 0;
        for (int i = startTime; i < endTime; i++) {
            sum += counts[i];
        }

        // 누적값에서 앞에 값을 빼고 뒤에 값을 추가하면서 각초마다 광고를 넣었을때의 누적값을 구하고 비교
        long max = sum;
        int maxStartTime = 0;
        while (endTime <= playTime) {
            sum -= counts[startTime];
            sum += counts[endTime];
            if(sum > max) {
                max = sum;
                maxStartTime = startTime + 1;
            }
            startTime++;
            endTime++;
        }
        return secondToStr(maxStartTime);
    }

    static int strToSecond(String str) {
        String[] split = str.split(":");
        return Integer.parseInt(split[0]) * 60 * 60 + Integer.parseInt(split[1]) * 60 + Integer.parseInt(split[2]);
    }

    static String secondToStr(int time) {
        int hour = time / 3600;
        time %= 3600;
        int minute = time / 60;
        int second = time % 60;

        String strHour = hour > 9 ?  String.valueOf(hour) : "0" + hour;
        String strMinute = minute > 9 ?  String.valueOf(minute) : "0" + minute;
        String strSecond = second > 9 ?  String.valueOf(second) : "0" + second;

        return String.join(":", strHour, strMinute, strSecond);
    }
}
