package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class P_9663_2 {

    static int N;
    static int count;
    private static int[][] board;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        board = new int[N][N];
        dfs(0);
        System.out.println(count);
    }

    private static void dfs(int row) {
        if (row == N) {
            count++;
            return;
        }

        for (int col = 0; col < N; col++) {
            if (isPromising(row, col)) {
                board[row][col] = 1;
                dfs(row + 1);
                board[row][col] = 0;
            }
        }
    }

    private static boolean isPromising(int row, int col) {
        //위 체크
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 1) {
                return false;
            }
            if (isInRange(row - (i + 1), col + (i + 1 )) && board[row - (i + 1)][col + (i + 1)] == 1) {
                return false;
            }
            if (isInRange(row - (i + 1), col - (i + 1)) && board[row - (i + 1)][col - (i + 1)] == 1) {
                return false;
            }
        }

        return true;
    }

    private static boolean isInRange(int row, int col) {
        return row >= 0 && row < N && col >= 0 && col < N;
    }
}
