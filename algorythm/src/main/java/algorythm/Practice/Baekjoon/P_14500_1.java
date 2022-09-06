package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P_14500_1 {

    static int n;
    static int m;
    static int[][] board;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean[][] visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                visited[i][j] = true;
                dfs(i, j, visited, board[i][j], 1);
                visited[i][j] = false;
            }
        }

        System.out.println(max);
    }

    static int max = Integer.MIN_VALUE;

    private static void dfs(int row, int col, boolean[][] visited, int sum, int depth) {
        if (depth == 4) {
            max = Math.max(max, sum);
            return;
        }

        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};

        for (int i = 0; i < 4; i++) {
            int xx = row + dx[i];
            int yy = col + dy[i];

            if (isInRange(xx, yy, board) && !visited[xx][yy]) {
                visited[xx][yy] = true;
                dfs(xx, yy, visited, sum + board[xx][yy], depth + 1);
                visited[xx][yy] = false;

                if (depth == 2) {
                    visited[xx][yy] = true;
                    dfs(row, col, visited, sum + board[xx][yy], depth + 1);
                    visited[xx][yy] = false;
                }
            }
        }
    }

    static public boolean isInRange(int row, int col, int[][] board) {
        return row >= 0 && row < board.length && col >= 0 && col < board[0].length;
    }
}
