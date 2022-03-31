package algorythm.Practice.programmers.step2;

import java.util.Arrays;
import java.util.HashSet;

public class EnglishInARow {
    public static void main(String[] args) {
        //이전에 말했던 단어 말하면 탈락.
        //바로 전 사람의 뒷글자로 시작 안하면 탈락.
        int n = 2;
        String[] w = {"hello", "one", "even", "never", "now", "world", "draw"};
        System.out.println(Arrays.toString(solution(n, w)));
    }

    static public int[] solution(int n, String[] words) {
        int[] answer = new int[2];
        int nowPerson;
        int nowOrder;
        HashSet<String> s = new HashSet<>();
        for (int i = 0; i < words.length; i++) {
            nowPerson = i % n + 1;
            nowOrder = i / n + 1;
            if(i == 0){
                s.add(words[i]);
                continue;
            }
            if(s.add(words[i]) && words[i].charAt(0) == words[i - 1].charAt(words[i - 1].length() - 1))
                continue;

            answer[0] = nowPerson;
            answer[1] = nowOrder;
            return answer ;
        }

        return answer;
    }
}
