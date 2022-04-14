package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.function.Function;

public class P_17144_1 {

    /**
     * 공기 청정기 항상 1번열, 크기는 두 행,
     *
     * 확산
     * 동시에 퍼지는거니까 바로 더해버리면 안됨 -> 새로운 보드에 마킹하고 기존보드랑 한번에 더하기
     * 밖이나 공청에는 확산 x
     *
     * 바람
     * 공청에 먼지 들어가면 사라짐
     * dxdy 인덱스
     * 윗 공청 0 3 2 1 순으로
     * 아랫 공청 0 1 2 3
     */
    static int r;
    static int c;
    static int t;
    static int[][] board;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        AirCon acUp = null;
        AirCon acDown = null;

        board = new int[r][c];
        boolean flag = false;
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < c; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());

                if (board[i][j] == -1 && !flag) {
                    acUp = new AirCon(i, j);
                    acDown = new AirCon(i + 1, j);
                    flag = true;
                }
            }
        }

        while (t--  != 0) {
            spreadDust(board);
            wind(acUp, acDown, board);
        }

        System.out.println(sum(board));
    }

    private static void spreadDust(int[][] board) {
        int[][] newBoard = new int[r][c];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                int cnt = 0;
                int spreadAmount = board[i][j] / 5;
                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    if (isInRange(nx, ny) && board[nx][ny] != -1) {
                        newBoard[nx][ny] += spreadAmount;
                        cnt += 1;
                    }
                }
                board[i][j] -= spreadAmount * cnt;
            }
        }

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                board[i][j] += newBoard[i][j];
            }
        }
    }

    private static void wind(AirCon acUp, AirCon acDown, int[][] board) {
        wind(acUp, board, dir -> (dir + 3) % 4);
        wind(acDown, board, dir -> (dir + 1) % 4);
    }

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    private static void wind(AirCon ac, int[][] board, Function<Integer, Integer> calDir) {
        int dir = 0;
        int nowRow = ac.row + dx[dir];
        int nowCol = ac.col + dy[dir];

        int changeVal = 0;
        while (nowRow != ac.row || nowCol != ac.col ) {
            int temp = changeVal;
            changeVal = board[nowRow][nowCol];
            board[nowRow][nowCol] = temp;

            if (!isInRange(nowRow + dx[dir], nowCol + dy[dir])) {
                dir = calDir.apply(dir);
            }
            nowRow = nowRow + dx[dir];
            nowCol = nowCol + dy[dir];
        }
    }

    private static boolean isInRange(int row, int col) {
        return row >= 0 && row < r && col >= 0 && col < c;
    }

    private static int sum(int[][] board) {
        int sum = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                sum += board[i][j];
            }
        }

        return sum + 2;
    }

    private static class AirCon {
        int row;
        int col;

        public AirCon(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
