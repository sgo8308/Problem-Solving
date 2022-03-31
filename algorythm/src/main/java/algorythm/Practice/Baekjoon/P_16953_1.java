package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P_16953_1 {
    public static void main(String[] args) throws Exception{
        //1 1 , 9 9, 3 19,2 162
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        System.out.println("B = " + B);
        System.out.println("A = " + A);
        int operationCount = 0;
        while (B != A && B > 1) {
            if(B % 10 == 1) {
                B = B / 10;
            } else if(B % 2 != 0 ) {
                System.out.println(-1);
                return;
            } else {
                B = B / 2;
            }
            System.out.println("B = " + B);
            operationCount++;
        }

        if(B == A)
            System.out.println(operationCount + 1);
        else
            System.out.println(-1);

    }
}
