package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P_2468_1 {
    static int N;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[][] board = new int[N][N];
        int maxHeight = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                maxHeight = Math.max(maxHeight, board[i][j]);
            }
        }

        int maxSafeArea = Integer.MIN_VALUE;
        for (int rainHeight = 0; rainHeight <= maxHeight; rainHeight++) {
            boolean[][] visited = new boolean[N][N];
            int count = 0;
            for (int row = 0; row < N; row++) {
                for (int col = 0; col < N; col++) {
                    if (!visited[row][col] && board[row][col] > rainHeight) {
                        dfs(row, col, rainHeight, board, visited);
                        count++;
                    }
                }
            }

            maxSafeArea = Math.max(maxSafeArea, count);
        }

        System.out.println(maxSafeArea);
    }

    private static void dfs(int row, int col, int rainHeight, int[][] board, boolean[][] visited) {
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        visited[row][col] = true;

        for (int i = 0; i < 4; i++) {
            int xx = row + dx[i];
            int yy = col + dy[i];
            if (xx >= 0 && xx < N && yy >= 0 && yy < N && board[xx][yy] > rainHeight && !visited[xx][yy]) {
                dfs(xx, yy, rainHeight, board, visited);
            }
        }
    }
}
