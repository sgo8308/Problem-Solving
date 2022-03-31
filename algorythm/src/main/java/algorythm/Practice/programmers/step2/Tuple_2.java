package algorythm.Practice.programmers.step2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Tuple_2 {
    public static void main(String[] args) {
        String s = " {{2},{2,1},{2,1,3},{2,1,3,4}} ";
        System.out.println(Arrays.toString(solution(s)));
    }

    static public int[] solution(String s) {
        Set<String> set = new HashSet<>();
        String[] ss = s.replaceAll("[{}]", " ").trim().split(" , ");
        Arrays.sort(ss, (a,b) -> a.length() - b.length());

        int[] answer = new int[ss.length];

        for (int i = 0; i < ss.length; i++) {
            String[] p = ss[i].split(",");
            for (int j = 0; j < p.length; j++) {
                if(set.add(p[j]))
                    answer[i] = Integer.parseInt(p[j]);
            }
        }

        return answer;
    }
}

