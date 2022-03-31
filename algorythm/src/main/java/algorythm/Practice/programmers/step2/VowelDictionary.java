package algorythm.Practice.programmers.step2;

import java.util.ArrayList;
import java.util.HashMap;

public class VowelDictionary {
    public static void main(String[] args) {
        System.out.println(solution("I"));
    }

    static public int solution(String word) {
        String[] vowels = {"A", "E", "I", "O", "U"};
        HashMap<String, Integer> dict = new HashMap<>();
        int order = 1;
        for (int i = 0; i < 5; i++) {
            String s = "" + vowels[i];
            dict.put(s, order++);
            for (int j = 0; j < 5; j++) {
                String s2 = s + vowels[j];
                dict.put(s2, order++);
                for (int k = 0; k < 5; k++) {
                    String s3 = s2 + vowels[k];
                    dict.put(s3, order++);
                    for (int l = 0; l < 5; l++) {
                        String s4 = s3 + vowels[l];
                        dict.put(s4, order++);
                        for (int m = 0; m < 5; m++) {
                            String s5 = s4 + vowels[m];
                            dict.put(s5, order++);
                        }
                    }
                }
            }
        }
        
        return dict.get(word);
    }
}
