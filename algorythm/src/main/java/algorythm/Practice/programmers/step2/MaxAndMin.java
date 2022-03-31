package algorythm.Practice.programmers.step2;

import java.util.stream.Stream;

public class MaxAndMin {
    public static void main(String[] args) {
        System.out.println(solution("1 2 3 4"));
    }

    static public String solution(String s) {
        Integer[] ints = Stream.of(s.split(" ")).map(Integer::parseInt).toArray(Integer[]::new);
        return "" + Stream.of(ints).mapToInt(x->x).min().getAsInt() + " " + Stream.of(ints).mapToInt(x->x).max().getAsInt();
    }
}
