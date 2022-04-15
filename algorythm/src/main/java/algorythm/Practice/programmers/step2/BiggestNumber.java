package algorythm.Practice.programmers.step2;

import java.util.Arrays;
import java.util.stream.Collectors;

public class BiggestNumber {

    public static void main(String[] args) {
    }

    public String solution(int[] numbers) {
        String[] a = Arrays.stream(numbers).mapToObj(x -> "" + x).toArray(String[]::new);
        Arrays.sort(a, (str1 ,str2) -> -(str1 + str2).compareTo(str2 + str1));
        if(a[0].equals("0")) return "0";
        return Arrays.stream(a).collect(Collectors.joining());
    }

}
