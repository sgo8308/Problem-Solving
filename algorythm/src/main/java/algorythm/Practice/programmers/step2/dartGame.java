package algorythm.Practice.programmers.step2;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class dartGame {
    public static void main(String[] args) {
        int[][] test = new int[3][];
        test[0] = new int[3];
        test[1] = new int[4];
        test[2] = new int[5];
    }
    public static int solution(String dartResult) {
        // 나눠진 기회를 각각 점수,문자, 특수문자로 나눠서 규칙에 따라 계산한다.
        // 몇번째 기회인지 확인하면서 점수를 업데이트 해준다.
        int score1, score2, score3, finalScore = 0;
        int[] scores = {0, 0, 0, 0};
        Map<String, Integer> areaMap = new HashMap();
        areaMap.put("S", 1);
        areaMap.put("D", 2);
        areaMap.put("T", 3);

        Pattern pattern = Pattern.compile("([1-9]?[0-9])([A-Z])([#*])?");
        Matcher matcher = pattern.matcher(dartResult);
        int cnt = 1;
        while(matcher.find()){
//            System.out.println(cnt);
            if (matcher.group(4) == null) System.out.println("null");
//            int score = Integer.parseInt(matcher.group(1));
//            score = (int)Math.pow(score, areaMap.get(matcher.group(2)));
//
//            if(matcher.group(3).equals("*")){
//                score *= 2;
//                scores[cnt -1] *= 2;
//            }else if(matcher.group(3).equals("#")){
//                score *= -1;
//            }
//            scores[cnt] = score;
//            cnt++;
        }

        int answer = Arrays.stream(scores).sum();

        return answer;
    }

}
