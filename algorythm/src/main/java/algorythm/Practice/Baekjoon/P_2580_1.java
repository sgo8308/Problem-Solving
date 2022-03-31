package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class P_2580_1 {
    private static int[][] sudoku = new int[9][9];
    private static List<Limit> limits = new ArrayList<>();
    private static boolean[] checks = new boolean[9];
    private static int[][] squareDelta = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public static void main(String[] args) throws Exception{
        //스도쿠를 9가지 면으로 나누고 각 지점에 가로 세로 한계를 체크해놓는다.
        //0으로 되어 있는 곳마다 가로, 세로, 사각형을 dfs로 1~9 중에 있는 부분을 체크한다.
        //사각형을 체크할 때는 9가지 면에 한계만큼 x와y를 제한하여 체크한다.
        //1~9 중에 체크 안된 부분이 1개라면 그 숫자를 입력하고 아니라면 건너뛴다.
        //모든 빈칸을 채울 때까지 반복한다.
        createSudoku();
        createLimits();
        boolean isBlankLeft = true;
        boolean[][] visit = new boolean[9][9];
        while (isBlankLeft) {
            isBlankLeft = false;
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    initialize(visit);
                    if (sudoku[i][j] == 0) {
                        isBlankLeft = true;
                        columnCheck(i, j, 1);
                        columnCheck(i, j, -1);
                        rowCheck(i,j, 1);
                        rowCheck(i,j, -1);
                        squareCheck(i,j, visit);
                    }

                    if (isOnlyOneNumLeft()) {
                        sudoku[i][j] = getNumLeft();
                    }
                    Arrays.fill(checks, false);
                }
            }
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(sudoku[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void initialize(boolean[][] visit) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                visit[i][j] = false;
            }
        }
    }

    private static boolean isOnlyOneNumLeft() {
        int count = 0;
        for (int i = 0; i < 9; i++) {
            if (!checks[i]) {
                count++;
            }

            if (count > 1) {
                return false;
            }
        }
        return true;
    }

    private static int getNumLeft() {
        for (int i = 0; i < 9; i++) {
            if (!checks[i]) {
                return i + 1;
            }
        }
        return -1;
    }

    private static int getSquareNum(int x, int y) {
        for (int i = 0; i < 9; i++) {
            Limit limit = limits.get(i);
            if (x >= limit.xFrom && x < limit.xTo && y >= limit.yFrom && y < limit.yTo) {
                return i;
            }
        }

        return 0;
    }

    private static void rowCheck(int x, int y, int direction) {
        check(x, y);
        int yy = y + direction;
        if (yy >= 0 && yy < 9) {
            rowCheck(x, yy, direction);
        }
    }

    private static void columnCheck(int x, int y, int direction) {
        check(x, y);
        int xx = x + direction;
        if (xx >= 0 && xx < 9) {
            columnCheck(xx, y, direction);
        }
    }

    private static void squareCheck(int x, int y, boolean[][] visit) {
        if (visit[x][y]) {
            return;
        }

        check(x, y);
        visit[x][y] = true;
        int sqareNum = getSquareNum(x, y);
        Limit limit = limits.get(sqareNum);

        for (int i = 0; i < 4; i++) {
            int xx = x + squareDelta[i][0];
            int yy = y + squareDelta[i][1];
            if (xx >= limit.xFrom && xx < limit.xTo && yy >= limit.yFrom && yy < limit.yTo) {
                squareCheck(xx,yy, visit);
            }
        }
    }

    private static void check(int x, int y) {
        if (sudoku[x][y] == 0) {
            return;
        }
        checks[sudoku[x][y] - 1] = true;
    }

    private static void createSudoku() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 9; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                sudoku[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static void createLimits() {
        int xFrom = 0;
        int xTo = 3;
        int yFrom = 0;
        int yTo = 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                limits.add(new Limit(xFrom + j * 3, xTo + j * 3,
                        yFrom + i * 3, yTo + i * 3 ));
            }
        }
    }

    static class Limit {
        int xFrom;
        int xTo;
        int yFrom;
        int yTo;

        public Limit(int xFrom, int xTo, int yFrom, int yTo) {
            this.xFrom = xFrom;
            this.xTo = xTo;
            this.yFrom = yFrom;
            this.yTo = yTo;
        }
    }
}
