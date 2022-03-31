package algorythm.Practice.programmers.step2;

import java.util.HashMap;

public class FailAthlete {
    public static void main(String[] args) {
        String[] p = {"leo", "kiki", "eden"};
        String[] c = {"eden", "kiki"};
        String r = "leo";

        if (solution(p,c).equals(r))
            System.out.println("정답입니다.");
    }


    static public String solution(String[] participant, String[] completion) {
        String answer = "";
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < participant.length; i++) {
            map.put(participant[i], map.getOrDefault(participant[i],0) + 1);
        }

        for (int i = 0; i < completion.length; i++) {
            map.put(completion[i], map.get(completion[i]) - 1);
        }

        for (String key :
                map.keySet()) {
            if (map.get(key) != 0)
                answer = key;
        }

        return answer;
    }
}
