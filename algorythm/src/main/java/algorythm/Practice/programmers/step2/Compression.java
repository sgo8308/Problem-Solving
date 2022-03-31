package algorythm.Practice.programmers.step2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Compression {
    public static void main(String[] args) {
        String msg = "TOBEORNOTTOBEORTOBEORNOT";
//        String msg = "AB";
        System.out.println(Arrays.toString(solution(msg)));
    }
    static HashMap<String, Integer> map;
    static public int[] solution(String msg) {
        ArrayList<Integer> answerList = new ArrayList<>();

        map = createMap();

        int index = 27;
        while (msg != ""){
            // msg가 1일 때 예외처리, 다돌고 나서 마지막 것도 사전에 있을 때 예외처리

            if(msg.length() == 1){
                answerList.add(map.get(msg));
                break;
            }

            String wordsToAdd = getWordsToAdd(msg);
            answerList.add(map.get(wordsToAdd));

            if(msg.equals(wordsToAdd))
                break;

            map.put(msg.substring(0, wordsToAdd.length() + 1), index++);
            msg = msg.substring(wordsToAdd.length());
        }


        int[] answer = new int[answerList.size()];
        for (int i = 0; i < answerList.size(); i++) {
            answer[i] = answerList.get(i);
        }
        return answer;
    }

    static HashMap<String, Integer> createMap(){
        HashMap<String, Integer> map = new HashMap<>();

        for (int i = 0; i < 26; i++) {
            map.put(""+(char)(i+65), i + 1);
        }
        return map;
    }

    static String getWordsToAdd(String msg){
        String w = "";
        for (int i = 1; i < msg.length() + 1; i++) {
            w = msg.substring(0,i);
            if(!map.containsKey(w))
                return w.substring(0, w.length() - 1);
        }

        return w;
    }
}
