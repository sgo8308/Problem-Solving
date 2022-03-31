package algorythm.Practice.programmers.step2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewsClusttering {
    public static void main(String[] args) {
        String str1 = 	"FRANCE";
        String str2 =   "french";
        System.out.println(solution(str1,str2));
    }

    public static int solution(String str1, String str2) {
        String[] sstr1 = separate(str1.toLowerCase());
        String[] sstr2 = separate(str2.toLowerCase());

        if(sstr1.length == 0 && sstr2.length == 0) return 65536;

        String[] intersection = getIntersection(sstr1, sstr2);
        String[] union = getUnion(sstr1, sstr2, intersection);

        double jacardScore = (double)intersection.length / union.length;
        return (int)(jacardScore * 65536);
    }
    //1. 각 문자를 2개씩 나누고 특수문자가 들어간 것은 제외
    static String[] separate(String s){
        //substring, for문으로 패턴매칭해서 제외
        ArrayList<String> strings = new ArrayList<>();
        Pattern pattern = Pattern.compile("[^A-Za-z]");
        for (int i = 0; i < s.length() - 1; i++) {
            String str = s.substring(i, i + 2);
            Matcher matcher = pattern.matcher(str);

            if (matcher.find()) continue;

            strings.add(str);
        }

        String[] ret = strings.toArray(new String[strings.size()]);
        return ret;
    }

    //3. 교집합 갯수 구하기 (동일한게 작은 쪽의 갯수, 대소문자 구분 x)
    static String[] getIntersection(String[] str1, String[] str2){
        //모두 소문자로,정렬, 포인터 사용
        Arrays.sort(str1);
        Arrays.sort(str2);

        int pointer = 0;
        ArrayList<String> intersection = new ArrayList<>();
        for (int i = 0; i < str1.length; i++) {
            for (int j = pointer; j < str2.length; j++) {
                if(str1[i].equals(str2[j])){
                    intersection.add(str1[i]);
                    pointer = j + 1;
                    break;
                }
            }
        }

        return intersection.toArray(new String[intersection.size()]);
    }

    //4. 합집합 갯수 구하기 (동일한게 큰 쪽의 갯수, 대소문자 구분 x)
    static String[] getUnion(String[] str1, String[] str2, String[] intersection){
        //두 원소를 모두 합, 교집합 빼기
        ArrayList<String> union = new ArrayList<>();
        for (int i = 0; i < str1.length; i++)
            union.add(str1[i]);

        for (int i = 0; i < str2.length; i++)
            union.add(str2[i]);

        for (int i = 0; i < intersection.length; i++)
            union.remove(intersection[i]);

        return union.toArray(new String[union.size()]);
    }
}
