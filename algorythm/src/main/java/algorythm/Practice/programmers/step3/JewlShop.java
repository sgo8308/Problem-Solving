package algorythm.Practice.programmers.step3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class JewlShop {
     public int[] solution(String[] gems) {
        int[] answer = new int[2];

        int left = 0;
        int right = 0;

        Map<String, Integer> map = new HashMap<>();

        int min = Integer.MAX_VALUE;
        int cnt = (int) Arrays.stream(gems).distinct().count();

        while(true){
            if(map.size() < cnt){
                if(right == gems.length)
                    break;

                map.putIfAbsent(gems[right], 0);
                map.merge(gems[right], 1, Integer::sum);
                right++;
            }else{
                if(left == gems.length)
                    break;

                if(right - left < min){
                   min = right - left;
                   answer = new int[]{left + 1, right};
                }

                map.merge(gems[left], -1, Integer::sum);

                if(map.get(gems[left]) == 0){
                    map.remove(gems[left]);
                }

                left++;
            }
        }
        return answer;
    }

}
