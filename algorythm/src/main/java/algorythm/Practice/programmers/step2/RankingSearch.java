package algorythm.Practice.programmers.step2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class RankingSearch {
    public static void main(String[] args) {
        String[] info = {"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"};
        String[] query = {"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"};

        System.out.println(Arrays.toString(solution(info, query)));
        Integer[] integers = new Integer[50000];
        for (int i = 0; i < integers.length; i++) {
            integers[i] = i;
        }
        ArrayList<Integer> candidates = new ArrayList<>(Arrays.asList(integers));
        binarySearch(candidates,-200);
        System.out.println(Math.pow(2,20));
    }
    static HashMap<String, ArrayList<Integer>> map = new HashMap<>();
    static public int[] solution(String[] info, String[] query) {
        for (int i = 0; i < info.length; i++) {
            String[] infoArr = info[i].split(" ");
            comb("", infoArr, 0);
        }

        for (String key : map.keySet())
            Collections.sort(map.get(key));

        int[] result = new int[query.length];

        for (int i = 0; i < query.length; i++) {
            String qry = query[i].replaceAll("[0-9]+", "")
                                 .replaceAll(" and ", "")
                                 .trim();
            int condition = Integer.parseInt(query[i].replaceAll("[^0-9]+", ""));

            if(!map.containsKey(qry)){
                result[i] = 0;
                continue;
            }

            ArrayList<Integer> candidates = map.get(qry);

            result[i] = binarySearch(candidates, condition);
        }

        return result;
    }

    static void comb(String str, String[] infoArr, int depth){
        if(depth == 4){
            map.putIfAbsent(str, new ArrayList<>());
            map.get(str).add(Integer.parseInt(infoArr[4]));
            return ;
        }

        comb(str + infoArr[depth], infoArr, depth + 1);
        comb(str + "-", infoArr, depth + 1);
    }

    static int binarySearch(ArrayList<Integer> candidates, int condition){
        int left = 0;
        int right = candidates.size() - 1;

        while(left <= right){
            int idx = (left + right) / 2;
            if(idx == 0){
                if(candidates.get(idx) >= condition)
                    return candidates.size() - idx;
            }else{
                if(candidates.get(idx) >= condition && candidates.get(idx - 1) < condition)
                    return candidates.size() - idx;
            }

            if(candidates.get(idx) < condition){
                left = idx + 1;
            }else{
                right = idx - 1;
            }
        }
        return 0;
    }
}
