package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class P_20365_1 {
    public static void main(String[] args) throws Exception {
        //1 B  , 4 BRBR , 5 RRRRR, 6 RRRBBB, 6 RBBBBR
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String color = br.readLine();
        int blueCount = 0, redCount = 0;
        boolean isBlueCounted = false, isRedCounted = false;
        for (int i = 0; i < color.length(); i++) {
            if (!isBlueCounted && color.charAt(i) == 'B') {
                blueCount++;
                isBlueCounted = true;
                isRedCounted = false;
            } else if (!isRedCounted && color.charAt(i) == 'R') {
                redCount++;
                isRedCounted = true;
                isBlueCounted = false;
            }
        }
        int answer = (blueCount <= redCount) ? blueCount + 1 : redCount + 1;
        System.out.println(answer);
    }
}
