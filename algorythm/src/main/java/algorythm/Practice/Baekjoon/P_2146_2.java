package algorythm.Practice.Baekjoon;

import java.io.*;
import java.util.*;

public class P_2146_2 {
    static int n;
    static int[][] board;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        board = new int[n][n];
        Bridge[][] bridges = new Bridge[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                bridges[i][j] = new Bridge(0, 0);
            }
        }

        //섬 이름 구분하기
        int islandNum = 2;
        Deque<Integer[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 1 && !visited[i][j]) {
                    mark(i, j, islandNum++, bridges, visited, q);
                }
            }
        }

        int minLen = Integer.MAX_VALUE;
        while (!q.isEmpty()) {
            Integer[] nowNode = q.removeFirst();
            int nowRow = nowNode[0];
            int nowCol = nowNode[1];

            for (int i = 0; i < 4; i++) {
                int nx = nowRow + dx[i];
                int ny = nowCol + dy[i];

                if (isInRange(nx, ny, board) && bridges[nx][ny].from != bridges[nowRow][nowCol].from) {
                    if (bridges[nx][ny].from == 0) {
                        q.addLast(new Integer[]{nx, ny});
                        bridges[nx][ny].from = bridges[nowRow][nowCol].from;
                        bridges[nx][ny].length = bridges[nowRow][nowCol].length + 1;
                    } else if (bridges[nx][ny].from != 0) {
                        minLen = Math.min(minLen, bridges[nx][ny].length + bridges[nowRow][nowCol].length);
                    }
                }
            }
        }

        System.out.println(minLen);
    }

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    private static void mark(int row, int col, int islandNum, Bridge[][] bridges, boolean[][] visited
                                ,Deque<Integer[]> q) {
        bridges[row][col].from = islandNum;
        q.addLast(new Integer[]{row, col});

        for (int i = 0; i < 4; i++) {
            int nx = row + dx[i];
            int ny = col + dy[i];

            if (isInRange(nx, ny, board) && !visited[nx][ny] && board[nx][ny] == 1) {
                visited[nx][ny] = true;
                mark(nx, ny, islandNum, bridges, visited, q);
            }
        }
    }

    static public boolean isInRange(int row, int col, int[][] board){
      return row >= 0 && row < board.length && col >= 0 && col < board[0].length;
    }

    static class Bridge {
        int from;
        int length;

        public Bridge(int from, int length) {
            this.from = from;
            this.length = length;
        }
    }
}

