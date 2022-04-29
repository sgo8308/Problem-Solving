package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P_1780_2 {

    static int n;
    static int[] cnt = new int[3];
    static int[][] board;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        board = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        checkAndDivied(0, 0 , board.length);
        System.out.println(cnt[0]);
        System.out.println(cnt[1]);
        System.out.println(cnt[2]);
    }

    private static void checkAndDivied(int row, int col, int length) {
        if (length == 1 || checkAllSame(row, col, length) ) {
            cnt[board[row][col] + 1]++;
            return;
        }

        int newLen = length / 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                checkAndDivied(row + newLen * i, col + newLen * j, newLen);
            }
        }
    }

    private static boolean checkAllSame(int row, int col, int length) {
        int firstVal = board[row][col];
        for (int i = row; i < row + length; i++) {
            for (int j = col; j < col + length; j++) {
                if (board[i][j] != firstVal) {
                    return false;
                }
            }
        }

        return true;
    }
}
