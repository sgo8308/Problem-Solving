package algorythm.Practice.programmers.step2;

import java.util.*;

public class MenuRenew {
    public static void main(String[] args) {
        //최소 2명 이상이 같이 주문
        //최소 2가지 이상의 단품메뉴
        //가장 많이 같이 주문한 것
        String[] orders = {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
//        String[] orders = {"ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"};
//        String[] orders = {"XYZ", "XWY", "WXA"};
        int[] course = {2,3,4};
        System.out.println(Arrays.toString(solution(orders, course)));

    }

    static public String[] solution(String[] orders, int[] course) {
        HashMap<String, Integer> map = new HashMap<>();

        for (int i = 0; i < course.length; i++) {
            for (int j = 0; j < orders.length; j++) {
                if(orders[j].length() < course[i]) continue;
                char[] tmp = orders[j].toCharArray();
                Arrays.sort(tmp);
                String order = new String(tmp);

                ArrayList<String> combs = getComb(order, new ArrayList<>(), new ArrayList<>() , course[i], 0);

                for (int k = 0; k < combs.size(); k++) {
                    map.put(combs.get(k), map.getOrDefault(combs.get(k), 0) + 1);
                }
            }
        }
        ArrayList<String> answerList = new ArrayList<>();
        for (int i = 0; i < course.length; i++) {
            int max = Integer.MIN_VALUE;
            ArrayList<String> temp = new ArrayList<>();
            for (String key :
                    map.keySet()) {
                if (key.length() != course[i] || map.get(key) < 2) continue;

                if(map.get(key) > max){
                    temp.clear();
                    temp.add(key);
                    max = map.get(key);
                }else if(map.get(key) == max){
                    temp.add(key);
                }
            }

            for (int j = 0; j < temp.size(); j++)
                answerList.add(temp.get(j));
        }

        Collections.sort(answerList);
        return answerList.toArray(new String[answerList.size()]);
    }

    static ArrayList<String> getComb(String order, ArrayList<String> combs,
                     ArrayList<Character> comb , int combNum, int count){

        if(comb.size() == combNum){
            String temp = "";
            for (int i = 0; i < comb.size(); i++) {
                temp += comb.get(i);
            }
            combs.add(temp);
            return combs;
        }

        for (int i = count; i < order.length(); i++) {
                comb.add(order.charAt(i));
                getComb(order, combs, comb, combNum, i + 1);
                comb.remove(comb.size() - 1);
        }

        return combs;
    }
}
