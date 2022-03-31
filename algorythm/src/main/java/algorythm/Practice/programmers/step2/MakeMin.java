package algorythm.Practice.programmers.step2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

public class MakeMin {
    public static void main(String[] args) {
        int[] A = {1,4,2};
        int[] B = {5,4,4};

        System.out.println(solution(A, B));
    }

    static public int solution(int []A, int []B)
    {
        Arrays.sort(A);
        Integer[] b = Arrays.stream(B).boxed().toArray(Integer[]::new);
        Arrays.sort(b, Comparator.reverseOrder());

        int answer = 0;
        for (int i = 0; i < A.length; i++) {
            answer += b[i] * A[i];
        }
        return answer;
    }
}
