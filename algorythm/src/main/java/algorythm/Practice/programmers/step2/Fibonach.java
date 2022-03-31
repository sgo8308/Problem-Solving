package algorythm.Practice.programmers.step2;

import java.util.HashMap;
import java.util.Map;

public class Fibonach {
    public static void main(String[] args) {
        System.out.println(5 / 2);
        System.out.println(5 % 2);
//        for (int i = 2; i < 50; i++) {
//            System.out.println(solution(i));
//        }
    }
    static Map<Integer, Long> fiboMap = new HashMap<>();
    static public int solution(int n) {
        fiboMap.put(2, 1l);
        fiboMap.put(3, 2l);

        for (int i = 4; i < n + 1; i++) {
            fiboMap.put(i, fiboMap.get(i - 1) + fiboMap.get(i - 2));
        }

        return (int)(fiboMap.get(n) / 1234567);
    }

}
