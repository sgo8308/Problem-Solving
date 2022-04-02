package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class P_14502_1 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] board = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //0은 빈칸 , 1은 벽, 2는 바이러스 , 바이러스는 빈칸으로 퍼진다.
        //꼭 3칸의 3벽을 세워야 한다.

        int maxSafe = Integer.MIN_VALUE;

        List<Integer[]> allWallCands = makeAllWallCandidates(board);
        List<Walls> wallsCombi = new ArrayList<>();
        makeWallsCombi(allWallCands, wallsCombi, new Walls(), 0);

        for (int i = 0; i < wallsCombi.size(); i++) {
            Walls walls = wallsCombi.get(i);

            int[][] newBoard = copyOf(board);

            walls.setWalls(newBoard);

            spreadVirus(newBoard);

            maxSafe = Math.max(maxSafe, calculateSafeArea(newBoard));
        }

        System.out.println(maxSafe);
    }

    private static List<Integer[]> makeAllWallCandidates(int[][] board) {
        List<Integer[]> allWallCands = new ArrayList<>();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 0) {
                    allWallCands.add(new Integer[]{i, j});
                }
            }
        }

        return allWallCands;
    }

    private static void makeWallsCombi(List<Integer[]> allWallCands, List<Walls> wallsList, Walls walls, int start) {
        if (walls.size() == 3) {
            wallsList.add(new Walls(walls));
            return;
        }

        for (int i = start; i < allWallCands.size(); i++) {
            walls.add(allWallCands.get(i));
            makeWallsCombi(allWallCands, wallsList, walls, i + 1);
            walls.removeLast();
        }
    }

    private static int calculateSafeArea(int[][] newBoard) {
        int count = 0;
        for (int i = 0; i < newBoard.length; i++) {
            for (int j = 0; j < newBoard[0].length; j++) {
                if (newBoard[i][j] == 0) {
                    count++;
                }
            }
        }
       return count;
    }

    private static void spreadVirus(int[][] newBoard) {
        for (int i = 0; i < newBoard.length; i++) {
            for (int j = 0; j < newBoard[0].length; j++) {
                if (newBoard[i][j] == 2) {
                    dfs(i, j, newBoard);
                }
            }
        }
    }

    private static void dfs(int row, int col, int[][] newBoard) {
        newBoard[row][col] = 2;

        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        for (int i = 0; i < 4; i++) {
            int xx = row + dx[i];
            int yy = col + dy[i];

            if (xx >= 0 && xx < newBoard.length && yy >= 0 && yy < newBoard[0].length && newBoard[xx][yy] == 0) {
                dfs(xx, yy, newBoard);
            }
        }
    }

    private static int[][] copyOf(int[][] board) {
        int[][] newBoard = new int[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                newBoard[i][j] = board[i][j];
            }
        }
        return newBoard;
    }

    static class Walls {
        List<Integer[]> walls = new ArrayList<>();

        public Walls(Walls walls) {
            for (int i = 0; i < walls.size(); i++) {
                this.walls.add(walls.get(i));
            }
        }

        public Walls() {

        }

        private Integer[] get(int i) {
            return walls.get(i);
        }

        void add(Integer[] wall) {
            walls.add(wall);
        }

        void setWalls(int[][] board) {
            for (int i = 0; i < 3; i++) {
                Integer[] rowCol = walls.get(i);
                board[rowCol[0]][rowCol[1]] = 1;
            }
        }

        int size() {
            return walls.size();
        }

        void removeLast() {
            walls.remove(walls.size() - 1);
        }
    }
}
