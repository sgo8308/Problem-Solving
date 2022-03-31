package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class P_9663_1 {
    static int N;
    static int answer = 0;
    private static int[][] chessBoard;
    private static List<String> queens = new ArrayList<>();
    public static void main(String[] args) throws Exception{
        N = 5;
        int row = 2;
        int col = 3;
        chessBoard = new int[N][N];
        checkRtoLCross(row, col, 1);
        chessBoard = new int[N][N];
        checkLtoRCross(row, col, 1);
        chessBoard = new int[N][N];
        checkChessBoard(row, col);
        chessBoard = new int[N][N];
        checkChessBoard(row, col);


        //체스판을 만든다.
        //퀸을 놓을 수 있는지 체스판을 체크하고 퀸을 놓으면 퀸이 갈수 있는 모든 곳을 체크한다.
        //체스판을 한칸씩 옆으로 진행하면서 재귀를 들어간다.
        //다시 돌아왔을 땐 체크했떤 체스판을 원상복귀한다.
        //놓은 퀸 수가 N개가 되면 카운트를 올려주고 재귀를 빠져나온다.
        //카운트를 출력한다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        chessBoard = new int[N][N];
        queen(0,0,0);
        System.out.println(answer);
    }


    private static void queen(int row, int col, int queenCount) {
        if (queenCount == N) {
            answer++;
            for (int i = 0; i < N; i++) {
                System.out.print(queens.get(i) + " ");
            }
            System.out.println();
            return;
        }

        if (col == N) {
            queen(row +1, 0, queenCount);
            return;
        }

        if (row == N) {
            return;
        }

        if (canPutQueen(row, col)) {
            checkChessBoard(row, col);
            queens.add(row + "" + col);
            queen(row + 1, 0, queenCount + 1);
            queens.remove(queens.size() - 1);
            unCheckChessBoard(row, col);
        }

        queen(row, col + 1, queenCount);
    }

    private static void checkChessBoard(int row, int col) {
        checkChessBoardAs(row, col, 1);
    }

    private static void unCheckChessBoard(int row, int col) {
        checkChessBoardAs(row, col, 0);
    }

    private static void checkChessBoardAs(int row, int col, int checkNumber) {
        for (int i = 0; i < N; i++) {
            chessBoard[row][i] = checkNumber;
            chessBoard[i][col] = checkNumber;
        }
        checkLtoRCross(row, col, checkNumber);
        checkRtoLCross(row, col, checkNumber);
    }

    static int[] dx = {1, -1, 1, -1};
    static int[] dy = {1, -1, -1, 1};
    private static void checkLtoRCross(int row, int col, int checkNumber) {
        chessBoard[row][col] = checkNumber;
        for (int i = 0; i < 2; i++) {
            int xx = row + dx[i];
            int yy = col + dy[i];
            if (xx >= 0 && yy >= 0 && xx < N && yy < N && chessBoard[xx][yy] == (checkNumber == 1 ? 0 : 1)) {
                checkLtoRCross(xx, yy, checkNumber);
            }
        }
    }

    private static void checkRtoLCross(int row, int col, int checkNumber) {
        chessBoard[row][col] = checkNumber;
        for (int i = 2; i < 4; i++) {
            int xx = row + dx[i];
            int yy = col + dy[i];
            if (xx >= 0 && yy >= 0 && xx < N && yy < N && chessBoard[xx][yy] == (checkNumber == 1 ? 0 : 1)) {
                checkRtoLCross(xx, yy, checkNumber);
            }
        }
    }

    private static boolean canPutQueen(int row, int col) {
        return chessBoard[row][col] == 1 ? false : true;
    }
}
