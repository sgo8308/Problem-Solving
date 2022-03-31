package algorythm.Practice.programmers.step2;

import java.util.ArrayList;

public class NGame {
    public static void main(String[] args) {
        int n = 16;
        int t = 16;
        int m = 2;
        int p = 1;
        System.out.println(solution(n, t, m, p));

    }
    static public String solution(int n, int t, int m, int p) {
        int length = t * m +10;
        String s = create(length, n);
        StringBuffer answer = new StringBuffer();
        for (int i = 0; i < t; i++) {
            answer.append(s.charAt((p - 1) + m * i));
        }

        return answer.toString();
    }

    static String create(int length, int n){
        String num = "";
        int i = 0;
        while (num.length() < length)
            num += transformNum(i++, n);

        return num;
    }

    static String transformNum(int num , int n){
        String s = "";
        while (true){
            if(n > num){
                s = transformNumOver10(num) + s;
                break;
            }
            String p = transformNumOver10(num % n);
            s = p + s;
            num /= n;

        }
        return s;
    }

    static String transformNumOver10(int m){
        String num = ""+m;
        if(m == 10) num = "A";
        if(m == 11) num = "B";
        if(m == 12) num = "C";
        if(m == 13) num = "D";
        if(m == 14) num = "E";
        if(m == 15) num = "F";

        return num;
    }
}
