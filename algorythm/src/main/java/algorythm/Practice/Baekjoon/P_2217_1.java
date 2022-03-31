package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class P_2217_1 {
    public static void main(String[] args) throws Exception{
        //testcase
        // 50
        // 1,2
        // 9998, 9999 , 10000
        // 5000, 5000
        // 5000, 5000, 5000

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        ArrayList<Integer> ropes = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            ropes.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(ropes, Comparator.reverseOrder());

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < ropes.size(); i++) {
            max = Math.max(max, ropes.get(i) * (i + 1));
        }

        System.out.println(max);

        /**
         * 줄 수 1개 - 그 줄의 최대 중량
         * 줄 수 2개 - 나누기 2해서 가장 중량을 덜 들 수 있는 줄이 나오는 값
         * 줄 수 3개 - 나누기 3해서 가장 중량을 덜 들 수 있는 줄이 나오는 값
         * ...
         * 줄 수 1개 2개 이런식으로 차례대로 값을 다 구하고 그 중에서 최댓값을 구한다.
         * 값 구하고 최댓값 할당하는데 반복문 100000번
         */



    }
}
