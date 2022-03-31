package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.Objects;
import java.util.StringTokenizer;

public class P_15653_1 {

    /**
     * 최소 시간이므로 BFS로 모든 경우의 수 탐색 시간복잡도는 O(N^5) 빼낼 수 없으면 -1을 출력
     */

    public static void main(String[] args) throws Exception {
        ArrayList<Integer> a = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        String[] board = new String[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            board[i] = st.nextToken();
        }

        //첫 빨간 구슬 파란 구슬 구멍의 위치 구하기
        RedBlueCoordinate rbc = new RedBlueCoordinate();
        int holeRow = 0;
        int holeCol = 0;
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                if (board[row].charAt(col) == 'R') {
                    rbc.redRow = row;
                    rbc.redCol = col;
                }

                if (board[row].charAt(col) == 'B') {
                    rbc.blueRow = row;
                    rbc.bluecol = col;
                }

                if (board[row].charAt(col) == 'O') {
                    holeRow = row;
                    holeCol = col;
                }
            }
        }
        Deque<RedBlueCoordinate> q = new ArrayDeque<>();
        q.addLast(rbc);

        int minTime = -1;
        HashSet<RedBlueCoordinate> visited = new HashSet<>();
        visited.add(rbc);
        int count = 1;
        while (!q.isEmpty()) {
            RedBlueCoordinate rb = q.poll();

            for (int direction = 0; direction < 4; direction++) {
                RedBlueCoordinate newRb = rb.slide(direction, board, N ,M);
                if (visited.contains(newRb)) {
                    continue;
                }

                if (newRb.isBlueOnHole(holeRow, holeCol)) {
                    continue;
                } else if (newRb.isRedOnHole(holeRow, holeCol)) {
                    System.out.println(count);
                    return;
                }

                q.addLast(newRb);
                visited.add(newRb);
            }

            count++;
        }
        System.out.println(minTime);
    }

    static class Ball {
        int row;
        int col;

        public boolean isOnHole(int holeRow, int holeCol) {
            return row == holeRow && col == holeCol;
        }

        public Ball(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }


    static class RedBlueCoordinate {

        int redRow;
        int redCol;
        int blueRow;
        int bluecol;

        public RedBlueCoordinate(int redRow, int redCol, int blueRow, int bluecol) {
            this.redRow = redRow;
            this.redCol = redCol;
            this.blueRow = blueRow;
            this.bluecol = bluecol;
        }

        public RedBlueCoordinate() {

        }

        public boolean isRedOnHole(int holeRow, int holeCol) {
            return redRow == holeRow && redCol == holeCol;
        }

        public boolean isBlueOnHole(int holeRow, int holeCol) {
            return blueRow == holeRow && bluecol == holeCol;
        }

        public RedBlueCoordinate slide(int direction, String[] board,int n , int m) {
            int[] dx = {1, -1, 0, 0};
            int[] dy = {0, 0, 1, -1};
            int newRedRow = redRow;
            int newRedCol = redCol;
            int newBlueRow = blueRow;
            int newBlueCol = bluecol;

            int blueDist = 0;
            while (newBlueRow >= 0 && newBlueCol >= 0 && newBlueRow < n && newBlueCol < n && board[newBlueRow].charAt(newBlueCol) != '#') {
                blueDist++;
                newBlueRow += dx[direction];
                newBlueCol += dy[direction];
                if (board[newBlueRow].charAt(newBlueCol) == 'O') {
                    return new RedBlueCoordinate(newRedRow, newRedCol, newBlueRow, newBlueCol);
                }
            }

            newBlueRow -= dx[direction];
            newBlueCol -= dy[direction];

            int redDist = 0;
            while (newRedRow >= 0 && newRedCol >= 0 && newRedRow < m && newRedCol < m && board[newRedRow].charAt(newRedCol) != '#') {
                redDist++;
                newRedRow += dx[direction];
                newRedCol += dy[direction];
                if (board[newRedRow].charAt(newRedCol) == 'O') {
                    return new RedBlueCoordinate(newRedRow, newRedCol, newBlueRow, newBlueCol);
                }
            }

            newRedRow -= dx[direction];
            newRedCol -= dy[direction];

            if (redRow == blueRow && redCol == bluecol) {
                if (blueDist > redDist) {
                    newBlueRow -= dx[direction];
                    newBlueCol -= dy[direction];
                } else {
                    newRedRow -= dx[direction];
                    newRedCol -= dy[direction];
                }
            }

            return new RedBlueCoordinate(newRedRow, newRedCol, newBlueRow, newBlueCol);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            RedBlueCoordinate that = (RedBlueCoordinate) o;
            return redRow == that.redRow && redCol == that.redCol && blueRow == that.blueRow
                    && bluecol == that.bluecol;
        }

        @Override
        public int hashCode() {
            return Objects.hash(redRow, redCol, blueRow, bluecol);
        }
    }
}


