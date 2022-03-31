package algorythm.Practice.programmers.step2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tuple {
    public static void main(String[] args) {
        //원소 갯수 중복안됨
        //

        String s = " {{2},{2,1},{2,1,3},{2,1,3,4}} ";

        System.out.println(Arrays.toString(solution(s)));
    }

    static public int[] solution(String s) {
        Pattern pattern = Pattern.compile("[0-9]");
        Matcher matcher = pattern.matcher(s);

        HashMap<String, Element> map = new HashMap<>();
        while (matcher.find()){
            String element = matcher.group();
            map.putIfAbsent(element, new Element(Integer.parseInt(element), 0));
            map.get(element).count += 1;
        }
        ArrayList<Element> list = new ArrayList<>();

        for (String key :
                map.keySet()) {
            list.add(map.get(key));
        }

        Collections.sort(list);
        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++)
            answer[i] = list.get(i).element;

        return answer;
    }
}

class Element implements Comparable<Element>{
    int element;
    int count;

    public Element(int element, int count) {
        this.element = element;
        this.count = count;
    }

    @Override
    public int compareTo(Element o) {
        return o.count - this.count;
    }
}
