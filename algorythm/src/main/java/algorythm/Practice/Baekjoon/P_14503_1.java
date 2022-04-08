package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P_14503_1 {
    static int N;
    static int M;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        Robot rb = new Robot(Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken()));

        int[][] board = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        rb.clean(board);

        while (!rb.isStopped) {
            rb.rotate();
            if (rb.canMove(board)) {
                rb.moveForward();
                rb.clean(board);
                continue;
            }

            if (rb.isRotateEnough()) {
                if (rb.isWallBehindBack(board)) {
                    rb.stop();
                    continue;
                }

                rb.moveBack();
                rb.clean(board);
            }
        }

        System.out.println(rb.cleaningCount);
    }

    static class Robot {
        int row;
        int col;
        int direction;
        int cleaningCount;
        int rotateCount;
        boolean isStopped;

        public Robot(int row, int col, int direction) {
            this.row = row;
            this.col = col;
            this.direction = direction;
        }

        public void clean(int[][] board) {
            if(board[row][col] == -1) return;
            board[row][col] = -1;
            cleaningCount++;
        }

        public void rotate() {
            direction = (direction + 3) % 4;
            rotateCount++;
        }

        public boolean canMove(int[][] board) {
            int frontRow = row + dx[direction];
            int frontCol = col + dy[direction];
            return isInRange(frontRow, frontCol) && (board[frontRow][frontCol] == 0) ;
        }

        private boolean isInRange(int x, int y) {
            return x >= 0 && x < N && y >= 0 && y < M;
        }
        public void moveForward() {
            row += dx[direction];
            col += dy[direction];
            rotateCount = 0;
        }

        public void moveBack() {
            row -= dx[direction];
            col -= dy[direction];
            rotateCount = 0;
        }

        public boolean isWallBehindBack(int[][] board) {
            int backRow = row - dx[direction];
            int backCol = col - dy[direction];
            return !isInRange(backRow, backCol) || board[backRow][backCol] == 1;
        }

        public void stop() {
            isStopped = true;
        }

        public boolean isRotateEnough() {
            return rotateCount == 4;
        }
    }
}
