package algorythm.Practice.Leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P_438_1 {

    /**
     * 시간복잡도 nlogn 아래로 나와야 함
     * 엣지케이스 s = aab p = aabc,  s = abbc p = aabc
     *
     * 시간복잡도 = o(26 * 2 * n) 매번 윈도우를 한칸씩 움직일 때 마다 map끼리 비교
     *
     * s가 계속 사용할 map과 p의 map를 만들어줌 모든 알파벳이 key이고 그 알파벳이 몇 개 나왔는지가 value
     * s에 p크기 만큼의 윈도우를 만들고 한칸씩 옮기면서 s의 윈도우를 map에 사상한 것과 p의 맵을 계속 비교
     */
    public static void main(String[] args) {
        List<Integer> anagrams = findAnagrams("cbaebabacd", "abc");

        for (int i = 0; i < anagrams.size(); i++) {
            System.out.println(anagrams.get(i));
        }
    }

    static public List<Integer> findAnagrams(String s, String p) {
        List<Integer> answer = new ArrayList<>();
        if (s.length() < p.length()) {
            return answer;
        }

        Map<Character, Integer> sMap = new HashMap<>();
        Map<Character, Integer> pMap = new HashMap<>();

        createMap(sMap, pMap, s, p);

        int left = 0, right = p.length() - 1;
        while (right < s.length()) {
            if (sMap.equals(pMap)) {
                answer.add(left);
            }
            sMap.put(s.charAt(left), sMap.get(s.charAt(left)) - 1);
            left++;
            right++;
            if(right >= s.length()) break;
            sMap.put(s.charAt(right), sMap.get(s.charAt(right)) + 1);
        }

        return answer;
    }

    private static void createMap(Map<Character, Integer> sMap, Map<Character, Integer> pMap, String s, String p) {
        for (int i = 97; i <= 122; i++) {
            sMap.put((char)i, 0);
            pMap.put((char)i, 0);
        }

        for (int i = 0; i < p.length(); i++) {
            sMap.put(s.charAt(i), sMap.get(s.charAt(i)) + 1);
            pMap.put(p.charAt(i), pMap.get(p.charAt(i)) + 1);
        }
    }
}
