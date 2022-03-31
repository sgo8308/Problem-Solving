package algorythm.Practice.programmers.step2;

import java.util.Arrays;

public class SliceN2Array {

    public static void main(String[] args) {

        int n = 3;
        int left = 2;
        int right = 5;
        System.out.println(Arrays.toString(solution(n, left, right)));
     }

    static public int[] solution(int n, long left, long right) {
        int[] answer = create1DArray(n, left, right);

        return answer;

    }

    static private int[] create1DArray(int n, long left, long right) {
        int[] newArr = new int[(int)(right - left + 1)];
        for (int i = 0; i < right - left + 1; i++) {
            int[] point = to2DPoint(n, left + i);

            newArr[i] = Math.max(point[0], point[1]) + 1;
        }

        return newArr;
    }

    static private int[] to2DPoint(int n, long point) {
        int[] point2D = new int[2];
        point2D[0] = (int)(point / n);
        point2D[1] = (int)point - (int)(point / n) * n;

        return point2D;
    }
}
