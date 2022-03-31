package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P_14916_1 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int change = Integer.parseInt(st.nextToken());

        //처음에 거르기
        if(change == 1 || change == 3){
            System.out.println(-1);
            return;
        }

        int count5 = change / 5;

        int count2 = 0;
        for (int i = count5; i >= 0; i--) {
            int tempChange = change;
            count5 = i;
            tempChange -= 5 * count5;

            count2 = tempChange / 2;
            tempChange -= 2 * count2;

            if(tempChange == 0) break;
        }

        System.out.println(count2 + count5);
    }


}
