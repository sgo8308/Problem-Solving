package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class P_9461_1 {
    static int n;
    public static void main(String[] args) throws Exception{
        long[] cache = new long[101];

        cache[1] = 1;
        cache[2] = 1;
        cache[3] = 1;
        cache[4] = 2;
        cache[5] = 2;

        for (int i = 6; i < 101; i++) {
            cache[i] = cache[i - 1] + cache[i - 5];
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            System.out.println(cache[Integer.parseInt(br.readLine())]);
        }
    }
}

// i = 물품의 번호  ,  j = 배낭에 넣은 무게의 합
// D[i][j] = 배낭에 넣은 물품의 무게가 j까지 넣을 수 있고 i번째까지 물품들이 존재한다고 할 때 최대값
// D[i][j] = D[i-1][j]                               if j < W[i]
//           max(D[i-1][j], D[i-1][j - W[i]] + V[i]) if j >= W[i]
