package algorythm.Practice.programmers.step2;

import java.util.Arrays;

public class RepeatBinaryTransform {
    public static void main(String[] args) {
        String s = "1111111";
        System.out.println(Arrays.toString(solution(s)));
    }
    static int deletedZeroCnt = 0;
    static int trasformCnt = 0;

    static public int[] solution(String s) {
        while(true){
            s = transform(s);
            if(s.equals("1")) break;
        }
        int[] answer = {trasformCnt, deletedZeroCnt};
        return answer;
    }

    static String transform(String s){
        trasformCnt++;
        s = deleteZero(s);
        s = Integer.toBinaryString(s.length());
        return s;
    }

    static String deleteZero(String s){
        int sLen = s.length();
        s = s.replaceAll("0", "");
        deletedZeroCnt += sLen - s.length();
        return s;
    }
}
