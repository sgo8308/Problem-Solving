package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class P_11559_1 {

    /**
     * 2중 for문 dfs로 4방향 탐색하면서 큐에 넣고 큐 크기가 4개 이상이면 큐 돌면서 .으로 바꺼주기
     * 아래로 끌어내리기
     * 1연쇄 추가
     * 아무 연쇄도 없을 때까지 반복
     */

    static int n = 12;
    static int m = 6;
    static char[][] board;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        board = new char[n][m];
        for (int i = 0; i < n; i++) {
            board[i] = br.readLine().toCharArray();
        }

        int cnt = 0;
        while (true) {
            boolean isExploded = explode();

            if(!isExploded) break;

            fall();

            cnt++;
        }

        System.out.println(cnt);
    }

    private static boolean explode() {
        Deque<Integer[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][m];
        boolean isExploded = false;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] != '.' && !visited[i][j]) {
                    dfs(i, j, q, visited);
                    if (q.size() >= 4) {
                        isExploded = true;
                        while (!q.isEmpty()) {
                            Integer[] coord = q.poll();
                            board[coord[0]][coord[1]] = '.';
                        }
                    }
                    q.clear();
                }
            }
        }
        return isExploded;
    }

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    private static void dfs(int row, int col, Deque<Integer[]> q, boolean[][] visited) {
        q.addLast(new Integer[]{row, col});
        visited[row][col] = true;
        for (int i = 0; i < 4; i++) {
            int nx = row + dx[i];
            int ny = col + dy[i];

            if (isInRange(nx, ny) && board[nx][ny] == board[row][col] && !visited[nx][ny]) {
                dfs(nx, ny, q, visited);
            }
        }
    }

    private static void fall() {
        for (int i = 0; i < m; i++) {
            Deque<Character> q = new ArrayDeque<>();
            for (int j = n-1; j >= 0; j--) {
                if (board[j][i] != '.') {
                    q.addLast(board[j][i]);
                }
            }

            for (int j = n-1; j >= 0; j--) {
                if (q.isEmpty()) {
                    board[j][i] = '.';
                    continue;
                }
                board[j][i] = q.poll();
            }
        }
    }

    static public boolean isInRange(int row, int col){
      return row >= 0 && row < board.length && col >= 0 && col < board[0].length;
    }
}
