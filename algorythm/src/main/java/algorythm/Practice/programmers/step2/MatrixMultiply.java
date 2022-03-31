package algorythm.Practice.programmers.step2;

import java.util.Arrays;

public class MatrixMultiply {
    public static void main(String[] args) {
        int[][] arr1 = {{2, 3, 2}, {4, 2, 4}, {3, 1, 4}};
        int[][] arr2 = {{5, 4, 3}, {2, 4 ,1}};
        for (int i = 0; i < arr1.length; i++) {
            System.out.println(Arrays.toString(solution(arr1, arr2)[i]));
        }
    }

    static public int[][] solution(int[][] arr1, int[][] arr2) {
        int[][] answer = new int[arr1.length][arr2[0].length];

        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr2[0].length; j++) {
                int[] rowArr = getRowArr(arr1, i);
                int[] colArr = getColArr(arr2, j);

                int value = 0;
                for (int k = 0; k < rowArr.length; k++) {
                    value += rowArr[k] * colArr[k];
                }
                answer[i][j] = value;
            }
        }

        return answer;
    }

    private static int[] getColArr(int[][] arr, int j) {
        int[] ar = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            ar[i] = arr[i][j];
        }
        return ar;
    }

    private static int[] getRowArr(int[][] arr, int i) {
        return arr[i];
    }
}
