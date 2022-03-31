package algorythm.Practice.programmers.step2;

import java.util.ArrayList;
import java.util.Arrays;

public class RotateMatrix {
    public static void main(String[] args) {
//        int rows = 6;
//        int cols = 6;
//        int[][] q = {{2,2,5,4},{3,3,6,6},{5,1,6,3}};

        int rows = 100;
        int cols = 97;
        int[][] q = {{1,1,100,97}};

        System.out.println(Arrays.toString(solution(rows,cols,q)));
    }
    static int[] answer;
    static public int[] solution(int rows, int columns, int[][] queries) {
        answer = new int[queries.length];
        int[][] matrix = createMatrix(rows, columns);

        for (int i = 0; i < queries.length; i++) {
            rotate(matrix, queries[i], i);
        }
        return answer;
    }

    static int[][] createMatrix(int rows, int columns){
        int[][] matrix = new int[rows + 1][columns + 1];
        int count = 1;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if(i == 0 || j == 0){
                    matrix[i][j] = 0;
                    continue;
                }
                matrix[i][j] = count++;
            }
        }
        return matrix;
    }

    static void rotate(int[][] matrix, int[] query, int order){
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        int[] routes = {query[3] - query[1], query[2] - query[0],
                query[3] - query[1], query[2] - query[0]};

        int[] nowRowCol = {query[0], query[1]};
        int now = matrix[nowRowCol[0]][nowRowCol[1]];
        int direction = 0;
        int min = now;
        for (int i = 0; i < routes.length; i++) {
            for (int j = 0; j < routes[i]; j++) {
                int[] nextRowCol = {nowRowCol[0] + dx[direction],
                        nowRowCol[1] + dy[direction]};
                int temp = matrix[nextRowCol[0]][nextRowCol[1]];
                matrix[nextRowCol[0]][nextRowCol[1]] = now;
                nowRowCol = nextRowCol;
                now = temp;
                min = Math.min(min, now);
            }
            direction++;
        }
        answer[order] = min;
    }
}
