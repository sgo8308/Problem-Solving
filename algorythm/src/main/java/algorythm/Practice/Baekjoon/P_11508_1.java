package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class P_11508_1 {
    public static void main(String[] args) throws Exception{
        //1 10, 2 5 9, 3 6 7 8,
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        Integer[] dairies = new Integer[N];
        for (int i = 0; i < dairies.length; i++) {
            dairies[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(dairies, Collections.reverseOrder());
        long totalPrice = 0;
        for (int i = 0; i < dairies.length; i++) {
            if(i % 3 == 2) continue;
            totalPrice += dairies[i];
        }

        System.out.println(totalPrice);
    }
}
