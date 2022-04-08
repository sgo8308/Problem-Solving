package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P_17090_1 {
    public static void main(String[] args) throws Exception{
        //유형 -> 탐색 시간복잡도 O(N^2) -> 이중포문으로 배열돌고 탐색하는데 탐색이 총 n^2 따라서 2n^2
        //N,M 최대 500
        //사이클에 참여하는 모든 애들은 탈출 불가, 사이클로 들어가도록 이동되는 애도 탈출 불가
        //탐색 시 초기화된 방문배열 이용해서 방문한데 또 방문하면 얘는 탈출 불가, 탈출 가능,불가 원소 기록하는 배열도 같이 이용

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        String[][] board = new String[N][M]; // 탈출하면 W, 탈출 못하면 N로 바꿀 예정
        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().split("");
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!board[i][j].equals("W") && !board[i][j].equals("N")) {
                    escape(i, j, board, new boolean[N][M]);
                }
            }
        }
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j].equals("W")) {
                    count++;
                }
            }
        }

        System.out.println(count);
    }

    private static String escape(int row, int col, String[][] board, boolean[][] visited) {
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length || board[row][col].equals("W")) {
            return "W";
        }

        if (visited[row][col] || board[row][col].equals("N")) {
            return "N";
        }

        visited[row][col] = true;

        int nRow = 0;
        int nCol = 0;
        switch (board[row][col]) {
            case "U":
                nRow = row - 1;
                nCol = col;
                break;
            case "R":
                nRow = row;
                nCol = col + 1;
                break;
            case "D":
                nRow = row + 1;
                nCol = col;
                break;
            case "L":
                nRow = row;
                nCol = col - 1;
                break;
        }

        return board[row][col] = escape(nRow, nCol, board, visited);
    }
}

