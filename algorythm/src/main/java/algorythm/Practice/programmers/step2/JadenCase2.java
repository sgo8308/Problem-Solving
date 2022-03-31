package algorythm.Practice.programmers.step2;

public class JadenCase2 {
    public static void main(String[] args) {
        String[] s = {"3people unFollowed me", " people he", "pe gr  ", "pe g", "pe  g  "};

        for (int i = 0; i < s.length; i++) {

            System.out.println(solution(s[i]));
        }
    }


    public static String solution(String s) {
        s = s.toLowerCase();
        boolean isBlankUntilNow = true;

        for (int i = 0; i < s.length(); i++) {
            if (isBlankUntilNow && s.charAt(i) != ' ') {
                s = s.substring(0, i) + Character.toUpperCase(s.charAt(i)) + s.substring(i + 1);
                isBlankUntilNow = false;
            } else if(s.charAt(i) == ' '){
                isBlankUntilNow = true;
            }
        }
        return s;
    }
}
