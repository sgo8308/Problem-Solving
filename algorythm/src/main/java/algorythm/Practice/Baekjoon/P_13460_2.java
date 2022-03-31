package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P_13460_2 {

    static int N;
    static int M;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        String[] board = new String[N];
        for (int i = 0; i < N; i++) {
            board[i] = br.readLine();
        }
        boolean[][][][] visited = new boolean[10][10][10][10];
        Marble red = new Marble();
        Marble blue = new Marble();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i].charAt(j) == 'R') {
                    red.row = i;
                    red.col = j;
                }

                if (board[i].charAt(j) == 'B') {
                    blue.row = i;
                    blue.col = j;
                }
            }
        }

        dfs(red, blue, board, visited, 0);
        if (answer == Integer.MAX_VALUE) {
            System.out.println(-1);
            return;
        }
        System.out.println(answer);
    }

    private static void dfs(Marble red, Marble blue, String[] board, boolean[][][][] visited, int depth) {
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        if (answer == 1 || depth >= 10) {
            return;
        }

        for (int i = 0; i < 4; i++) {
            Marble newRed = red.copy();
            Marble newBlue = blue.copy();

            int redMoveDistance = newRed.slide(dx[i], dy[i], board);
            int blueMoveDistance = newBlue.slide(dx[i], dy[i], board);

            if(newBlue.isOnHole) continue;

            if (newRed.isOnHole) {
                answer = Math.min(answer, depth + 1);
                return;
            }

            if (newRed.isSamePositionAs(newBlue)) {
                if (redMoveDistance < blueMoveDistance) {
                    newBlue.move(-1 * dx[i], -1 * dy[i]);
                } else {
                    newRed.move(-1 * dx[i], -1 * dy[i]);
                }
            }

            if(visited[newRed.row][newRed.col][newBlue.row][newBlue.col]) continue;

            visited[newRed.row][newRed.col][newBlue.row][newBlue.col] = true;
            dfs(newRed, newBlue, board, visited, depth + 1);
            visited[newRed.row][newRed.col][newBlue.row][newBlue.col] = false;
        }
    }

    static class Marble {

        int row;
        int col;
        boolean isOnHole = false;

        public Marble copy() {
            Marble marble = new Marble();
            marble.row = this.row;
            marble.col = this.col;
            return marble;
        }

        public int slide(int dx, int dy, String[] board) {
            int moveDist = 0;
            while (board[row + dx].charAt(col + dy) != '#' && board[row].charAt(col) != 'O') {
                move(dx, dy);
                moveDist++;
            }

            if (board[row].charAt(col) == 'O') {
                isOnHole = true;
            }

            return moveDist;
        }

        public boolean isSamePositionAs(Marble marble) {
            return this.row == marble.row && this.col == marble.col;
        }

        public void move(int dx, int dy) {
            row += dx;
            col += dy;
        }
    }
}