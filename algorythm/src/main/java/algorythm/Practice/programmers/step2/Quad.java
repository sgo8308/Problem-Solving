package algorythm.Practice.programmers.step2;

import java.util.Arrays;

public class Quad {
    public static void main(String[] args) {
        int[][] arr = {{1,1,0,0},{1,0,0,0},{1,0,0,1},{1,1,1,1}};
        int[][] arr2 = {{1,1,1,1,1,1,1,1},
                        {0,1,1,1,1,1,1,1},
                        {0,0,0,0,1,1,1,1},
                        {0,1,0,0,1,1,1,1},
                        {0,0,0,0,0,0,1,1},
                        {0,0,0,0,0,0,0,1},
                        {0,0,0,0,1,0,0,1},
                        {0,0,0,0,1,1,1,1}} ;
//        System.out.println(Arrays.toString(solution(arr)));
        System.out.println(Arrays.toString(solution(arr2)));
    }

    static int zeroCnt = 0;
    static int oneCnt = 0;
    static public int[] solution(int[][] arr) {
        compression(arr);
        int[] answer = {zeroCnt, oneCnt};
        return answer;
    }

    static void compression(int[][] arr){
        if(arr.length == 1){
            if(arr[0][0] == 0) zeroCnt++;
            else oneCnt++;
            return;
        }

        int num = arr[0][0];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if(arr[i][j] != num){
                    compression(makeSmallQuad(arr, 0,0));
                    compression(makeSmallQuad(arr, arr.length / 2,0));
                    compression(makeSmallQuad(arr, 0,arr.length / 2));
                    compression(makeSmallQuad(arr, arr.length / 2,arr.length / 2));
                    return;
                }
            }
        }

        if(num == 0) zeroCnt++;
        else oneCnt++;
    }

    static int[][] makeSmallQuad(int[][] arr, int row, int col){
        int[][] small = new int[arr.length/ 2][arr.length / 2];

        for (int i = 0; i < arr.length / 2; i++) {
            for (int j = 0; j < arr.length / 2; j++) {
                small[i][j] = arr[i+row][j+col];
            }
        }
        return small;
    }
}
