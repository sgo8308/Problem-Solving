package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class P_12100_1 {
    static int N;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int[][] board = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        List<Integer> candidate = new ArrayList<>();
        dfs(board, 0, candidate);

        System.out.println(candidate.stream().mapToInt(x -> x).max().getAsInt());
    }

    private static void dfs(int[][] board, int depth, List<Integer> candidiate) {
        if (depth >= 5) {
            candidiate.add(calculateMax(board));
            return;
        }

        for (int i = 0; i < 4; i++) {
            int[][] newBoard = new int[N][N];
            deepCopy(board, newBoard);
            slide(newBoard);
            dfs(newBoard, depth + 1, candidiate);
            board = rotate90(board);
        }
    }

    private static void deepCopy(int[][] src, int[][] dest) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                dest[i][j] = src[i][j];
            }
        }
    }

    private static int calculateMax(int[][] board) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                max = Math.max(max, board[i][j]);
            }
        }
        return max;
    }

    private static void slide(int[][] board) {
        move(board);
        putTogether(board);
        move(board);
    }

    private static void move(int[][] board) {
        List<Integer> a = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j <N; j++) {
                if (board[i][j] != 0) {
                    a.add(board[i][j]);
                }
            }

            for (int j = 0; j < N; j++) {
                if (j < a.size()) {
                    board[i][j] = a.get(j);
                } else {
                    board[i][j] = 0;
                }
            }

            a.clear();
        }
    }

    private static void putTogether(int[][] board) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N - 1; j++) {
                if (board[i][j] != 0 && board[i][j] == board[i][j + 1]) {
                    board[i][j] = board[i][j] * 2;
                    board[i][j + 1] = 0;
                }
            }
        }
    }

    private static int[][] rotate90(int[][] board) {
        int[][] newBoard = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                newBoard[j][N - 1 - i] = board[i][j];
            }
        }

        return newBoard;
    }
}
