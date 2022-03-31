package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;


public class P_16768_1 {

    static int N;
    static int K;
    public static void main(String[] args) throws Exception{

        String korean = "한글";

        String english = "en";
        byte[] kBytes = korean.getBytes("UTF-16");
        byte[] eBytes = english.getBytes("UTF-16");

        byte[] kBytes8 = korean.getBytes();
        byte[] eBytes8 = english.getBytes("UTF-8");
        for (int i = 0; i < kBytes.length; i++) {
            System.out.print(kBytes[i] + " ");
        }
        System.out.println();

        for (int i = 0; i < eBytes.length; i++) {
            System.out.print(eBytes[i] + " ");
        }
        System.out.println();
        for (int i = 0; i < kBytes8.length; i++) {
            System.out.print(kBytes8[i] + " ");
        }
        System.out.println();
        for (int i = 0; i < eBytes8.length; i++) {
            System.out.print(eBytes8[i] + " ");
        }
        System.out.println();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());


        String[][] arr = new String[N][10];
        for (int i = 0; i < N; i++) {
            String[] splits = br.readLine().split("");
            for (int j = 0; j < 10; j++) {
                arr[i][j] = splits[j];
            }
        }


        for (int i = N - 1; i >= 1 ; i--) {
            for (int j = 9; j >= 0; j--) {
                if (arr[i][j].equals("0") && !arr[i - 1][j].equals("0")) {
                    String tmp = arr[i - 1][j];
                    arr[i - 1][j] = "0";
                    arr[i][j] = tmp;
                }
            }
        }
        for (int i = 0; i < N; i++) {
            String[] a = arr[i];
            for (int j = 0; j < 10; j++) {
                System.out.print(a[j] + " ");
            }
            System.out.println();
        }
    }


}
