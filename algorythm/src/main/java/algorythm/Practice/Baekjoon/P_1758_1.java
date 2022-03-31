package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class P_1758_1 {
    public static void main(String[] args) throws Exception {
        //테스트 케이스 1 1 -> 1 , 1 10  -> 10,  3 1 2 3  -> 4 , 3 3 3 3 -> 6 , 4 50 40 30 20 -> 134
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        Integer[] tips = new Integer[N];

        for (int i = 0; i < tips.length; i++) {
            st = new StringTokenizer(br.readLine());
            tips[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(tips, Collections.reverseOrder());

        long allTip = 0;
        for (int i = 0; i < tips.length; i++) {
            int tip = (tips[i] - i >= 0) ? tips[i] - i : 0;
            allTip += tip;
        }

        System.out.println(allTip);
    }
}
