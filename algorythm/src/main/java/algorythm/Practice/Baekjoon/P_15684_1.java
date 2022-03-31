package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P_15684_1 {
    static int N;
    static int M;
    static int H;
    private static int answer = -1;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        Node[][] board = new Node[H][N];
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = new Node();
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken()) - 1;
            int col = Integer.parseInt(st.nextToken()) - 1;

            board[row][col].hasRightLine = true;
            board[row][col + 1].hasLeftLine = true;
        }

        for (int i = 0; i < 4; i++) {
            dfs(board, 0, 0, i);
            if (answer != -1) {
                break;
            }
        }

        System.out.println(answer);
    }

    private static void dfs(Node[][] board, int start, int nowDepth, int targetDepth) {
        if (answer != -1) {
            return;
        }
        if (nowDepth == targetDepth) {
            if (canConnectAllSameNum(board)) {
                answer = targetDepth;
            }
            return;
        }

        for (int i = start; i < (N - 1) * H; i++) {
            int row = i / (N - 1);
            int col = i % (N - 1);
            if(board[row][col].hasRightLine) continue;

            if (board[row][col].hasLeftLine || board[row][col + 1].hasRightLine) continue;

            board[row][col].hasRightLine = true;
            board[row][col + 1].hasLeftLine = true;
            dfs(board, i + 1, nowDepth + 1, targetDepth);
            board[row][col].hasRightLine = false;
            board[row][col + 1].hasLeftLine = false;
        }
    }

    private static boolean canConnectAllSameNum(Node[][] board) {
        for (int startCol = 0; startCol < N; startCol++) {
            int nowRow = 0;
            int nowCol = startCol;
            while (nowRow < H) {
                if (board[nowRow][nowCol].hasRightLine)
                    nowCol++;
                else if (board[nowRow][nowCol].hasLeftLine)
                    nowCol--;

                nowRow++;
            }

            if (nowCol != startCol) {
                return false;
            }
        }
        return true;
    }


    static class Node {
        boolean hasRightLine;
        boolean hasLeftLine;
    }
}
