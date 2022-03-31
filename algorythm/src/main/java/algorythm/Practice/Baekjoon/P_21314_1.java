package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class P_21314_1 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String minGyuemNum = br.readLine();

        String max = "";
        for (int i = 0, pointer = 0; i < minGyuemNum.length(); i++) {
            if (minGyuemNum.charAt(i) == 'K') {
                max += "5";
                int zeroNum = i - pointer;
                for (int j = 0; j < zeroNum; j++) {
                    max += "0";
                }

                pointer = i + 1;
            }
        }
        max = fillRemainder(minGyuemNum, max, "max");

        String min = "";
        for (int i = 0, pointer = 0; i < minGyuemNum.length(); i++) {
            if (minGyuemNum.charAt(i) == 'K') {
                int mNum = i - pointer;

                if (mNum > 0) {
                    min += "1";
                }

                for (int j = 0; j < mNum - 1; j++) {
                    min += "0";
                }

                min += "5";

                pointer = i + 1;
            }
        }

        min = fillRemainder(minGyuemNum, min, "min");

        System.out.println(max);
        System.out.println(min);
    }

    private static String fillRemainder(String minGyuemNum, String num, String maxMin) {
        if (num.length() != minGyuemNum.length()) {
            num += "1";
        }

        String addNum = "0";
        if (maxMin.equals("max")) {
            addNum = "1";
        }

        while (num.length() != minGyuemNum.length()) {
            num += addNum;
        }
        return num;
    }
}
