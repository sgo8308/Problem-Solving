package algorythm.Practice.programmers.step2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class KeyCandidates {
    public static void main(String[] args) {
        String[] s = {"a", "b", "c"};
        ArrayList test = new ArrayList();
        for (int i = 1; i < (1<<s.length); i++) {
            System.out.println(Integer.toBinaryString(i));
        }

    }

    public int solution(String[][] relation) {
        int answer = 0;
        return answer;
    }
}
