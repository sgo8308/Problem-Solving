package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class P_17142_1 {

    static int n;
    static int m;
    static int[][] board;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        List<Integer[]> allViruses = new ArrayList<>();
        board = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 2) {
                    allViruses.add(new Integer[]{i, j});
                }
            }
        }
        List<Integer[]> selectedViruses = new ArrayList<>();
        selectAndDupVirus(allViruses, selectedViruses, 0);

        if(minTime != Integer.MAX_VALUE) {
            System.out.println(minTime);
        } else {
            System.out.println(-1);
        }
    }

    private static void selectAndDupVirus(List<Integer[]> allViruses,
            List<Integer[]> selectedViruses,
            int index) {

        if (selectedViruses.size() == m) {
            dupVirus(new ArrayDeque<>(selectedViruses));
            return;
        }

        for (int i = index; i < allViruses.size(); i++) {
            selectedViruses.add(allViruses.get(i));
            selectAndDupVirus(allViruses, selectedViruses, i + 1);
            selectedViruses.remove(selectedViruses.size() - 1);
        }
    }

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int minTime = Integer.MAX_VALUE;

    private static void dupVirus(Deque<Integer[]> q) {
        Integer[][] newBoard = new Integer[n][n];

        for (int i = 0; i < newBoard.length; i++) {
            Arrays.fill(newBoard[i], Integer.MAX_VALUE);
        }

        int time = 0;

        while (!q.isEmpty()) {

            int qSize = q.size();
            for (int i = 0; i < qSize; i++) {
                Integer[] virus = q.pollFirst();
                int x = virus[0];
                int y = virus[1];
                if(time >= newBoard[x][y]) {
                    continue;
                }

                newBoard[x][y] = time;

                for (int j = 0; j < 4; j++) {
                    int xx = x + dx[j];
                    int yy = y + dy[j];
                    if (isInRange(xx, yy, board) && board[xx][yy] != 1) {
                        q.addLast(new Integer[]{xx, yy});
                    }
                }
            }

            time++;
        }

        if (isAllSpread(newBoard)) {
            minTime = Math.min(minTime, getMaxTime(newBoard));
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (newBoard[i][j] == Integer.MAX_VALUE) {
                    System.out.print(-1 + "   ");
                } else {
                    System.out.print(newBoard[i][j] + "   ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    private static int getMaxTime(Integer[][] newBoard) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < newBoard.length; i++) {
            for (int j = 0; j < newBoard[i].length; j++) {
                if(board[i][j] == 0) {
                    max = Math.max(max, newBoard[i][j]);
                }
            }
        }
        return max == Integer.MIN_VALUE ? 0 : max;
    }

    private static boolean isAllSpread(Integer[][] newBoard) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (newBoard[i][j] == Integer.MAX_VALUE && board[i][j] == 0) {
                    return false;
                }
            }
        }

        return true;
    }

    static public boolean isInRange(int row, int col, int[][] board) {
        return row >= 0 && row < board.length && col >= 0 && col < board[0].length;
    }

}
