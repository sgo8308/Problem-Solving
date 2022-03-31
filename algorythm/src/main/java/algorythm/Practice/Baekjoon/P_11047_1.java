package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class P_11047_1 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        int N = Integer.parseInt(s[0]), K = Integer.parseInt(s[1]);


        int[] coins = new int[N];
        for (int i = 0; i < N; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        int answer = 0;
        for(int i = N - 1; i >= 0; i--){
            if(coins[i] <= K){
                int coinCount = K / coins[i];
                answer += coinCount;
                K -= coins[i] * coinCount;
            }

            if(K == 0){
                System.out.println(answer);
                return;
            }
        }
    }
}
