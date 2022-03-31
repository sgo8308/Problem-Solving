package algorythm.Practice.programmers.step2;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class TGDayTraffic {
    public static void main(String[] args) {
        //예외처리
        //1초는 04.000 ~ 05.000 이 아니라 04.001 ~ 05.000임
        //가장 낮은 시간부터 1초씩 0.001초마다 잘라가면서 몇개 있는지 보는것은 시간들이 여기저기 흩어져있는 경우 안됨
        int test = 7;
        if(test == 5 || test == 4 && test == 7){
            System.out.println(test);
        }
    }

    public static int solution(String[] lines) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < lines.length; i++) {
            float startTime = getStartTime(lines[i]);
            float endTime = getEndTime(lines[i]);
            int count = 0;
            for (int j = 0; j < lines.length; j++) {
                float startTimeTarget = getStartTime(lines[j]);
                float endTimeTarget = getEndTime(lines[j]);
                if(isBelong(startTime, startTime + 0.999f, startTimeTarget, endTimeTarget)){
                    count++;
                };

            }
            max = Math.max(max, count);

            count = 0;

            for (int j = 0; j < lines.length; j++) {
                float startTimeTarget = getStartTime(lines[j]);
                float endTimeTarget = getEndTime(lines[j]);

                if(isBelong(endTime, endTime + 0.999f, startTimeTarget, endTimeTarget)){
                    count ++;
                }
            }

            max = Math.max(max, count);
        }

        return max;
    }

    static float getStartTime(String s){
        String[] times = s.split(" ");
        String endTime = times[1];
        String duration = times[2];
        float endTimefloat = makeTimeToFloat(endTime);
        float durationfloat = Float.parseFloat(duration.replaceAll("s",""));
        float startTime = endTimefloat - durationfloat + 0.001f;
        return startTime;
    }

    static float getEndTime(String s){
        String[] times = s.split(" ");
        String endTime = times[1];
        float endTimefloat = makeTimeToFloat(endTime);
        return endTimefloat;
    }

    static float makeTimeToFloat(String time){
        float timefloat = Float.parseFloat(time.substring(0,2)) * 3600;
        timefloat += Float.parseFloat(time.substring(3,5)) * 60;
        timefloat += Float.parseFloat(time.substring(6,8));
        timefloat += Float.parseFloat("0." + time.substring(9));

        return timefloat;
    }

    static boolean isBelong(float startTime, float endTime, float startTimeTarget, float endTimeTarget){
        if((startTimeTarget <= startTime && endTimeTarget >=startTime)
                || (startTimeTarget >= startTime && startTimeTarget <= endTime)  )
            return true;

        return false;
    }
}
