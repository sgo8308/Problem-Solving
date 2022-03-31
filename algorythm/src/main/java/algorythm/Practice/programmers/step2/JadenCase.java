package algorythm.Practice.programmers.step2;

import java.util.Arrays;

public class JadenCase {
    public static void main(String[] args) {
        String s = "3people unFollowed me";
        String s2 = "for the last week";
        String s3 = "aAa  AaA";
        String s4 = "aaa o ope";
        String s5 = "35 3   lds ";
        String s6 = "35lds      ";

        System.out.println(solution(s));
        System.out.println(solution(s2));
        System.out.println(solution(s3));
        System.out.println(solution(s4));
        System.out.println(solution(s5));
        System.out.println(solution(s6));
    }

    //공백이 한개만 있을 경우
//    static public String solution(String s) {
//
//        return Arrays.stream(s.split("[ ]"))
//                .filter(x -> x != "")
//                .map(String::toLowerCase)
//                .map(x -> Character.toUpperCase(x.charAt(0)) + x.substring(1))
//                .reduce((a, b) -> a + " " + b)
//                .orElse("");
//    }

    //공백이 여러개가 연속으로 나올 수 있는 경우
    static public String solution(String s) {
        boolean isChanged = false;
        s.toLowerCase();
        for (int i = s.length() - 1; i >= 0; i--) {
            if(s.charAt(i) != ' '){
                s = s.substring(0, i + 1);
                break;
            }
        }
        for (int i = 0; i < s.length(); i++) {
            if(!isChanged && s.charAt(i) != (' ')){
                s = s.substring(0, i) + Character.toUpperCase(s.charAt(i)) + s.substring(i + 1);
                isChanged = true;
                continue;
            }

            if(s.charAt(i) == ' ')
                isChanged = false;
        }

        return s;
    }
}
