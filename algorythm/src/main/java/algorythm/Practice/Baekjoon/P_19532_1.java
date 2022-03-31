package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.StringTokenizer;

public class P_19532_1 {
    static int N;
    static int K;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int answer = 0;
        LocalDateTime referenceTime = LocalDateTime.of(2021, 3, 3, N, 59, 59);
        LocalDateTime time = LocalDateTime.of(2021, 3, 3, 0, 0, 0);
        while (referenceTime.isAfter(time) || referenceTime.equals(time)) {
            if (hasK(time)) {
                answer++;
            }
            time = time.plusSeconds(1);
        }
        System.out.println(answer);
    }

    private static boolean hasK(LocalDateTime time) {
        return hasK(time.getHour()) || hasK(time.getMinute()) || hasK(time.getSecond());
    }

    private static boolean hasK(int num) {
        String s = "" + num;
        if (num < 10) {
            s = "0" + num;
        }
        return s.contains(""+K);
    }
}
