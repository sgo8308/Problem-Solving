package algorythm.Practice.programmers.step3;


import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;


public class BuildRacingRoad {

    public static void main(String[] args) {
        BuildRacingRoad b = new BuildRacingRoad();

        int[][] board = {
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 1}

        };

        System.out.println(b.solution(board));
    }


     public int solution(int[][] board) {

        int[][][] minCosts = new int[board.length][board.length][4];
        for (int i = 0; i < minCosts.length; i++) {
            for (int j = 0; j < minCosts.length; j++) {
                Arrays.fill(minCosts[i][j], Integer.MAX_VALUE);
            }
        }

        bfs(minCosts, board);

         for (int i = 0; i < minCosts.length; i++) {
             for (int j = 0; j < minCosts.length; j++) {
                 System.out.print(minCosts[i][j] + " ");
             }
             System.out.println();
         }

        return Arrays.stream(minCosts[minCosts.length - 1][minCosts.length - 1]).min().getAsInt();
    }

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    private void bfs(int[][][] minCosts, int[][] board) {
        Deque<Integer[]> q = new ArrayDeque<>();

        q.add(new Integer[]{0, 0, 0, 0}); //x ,y, cost, direction

        while (!q.isEmpty()) {
            Integer[] node = q.removeFirst();

            if(node[2] > minCosts[node[0]][node[1]][node[3]]) continue;

            minCosts[node[0]][node[1]][node[3]] = node[2];

            for (int i = 0; i < 4; i++) {
                int xx = node[0] + dx[i];
                int yy = node[1] + dy[i];

                int nextCost = node[2] + 100;
                if (i != node[3] && !(node[0] == 0 && node[1] == 0)) {
                    nextCost += 500;
                }

                if (isInRange(xx, yy, minCosts.length) && minCosts[xx][yy][i] > nextCost && board[xx][yy] != 1 ) {
                    q.add(new Integer[]{xx, yy, nextCost, i});
                }

            }
        }
    }

    static public boolean isInRange(int row, int col, int length){
      return row >= 0 && row < length && col >= 0 && col < length;
    }

}
