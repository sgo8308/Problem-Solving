package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P_14499_1 {
    static int n;
    static int m;
    static int[][] board;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int count = Integer.parseInt(st.nextToken());


        board = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] directions = new int[count];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < count; i++) {
            directions[i] = Integer.parseInt(st.nextToken());
        }

        Dice dice = new Dice();
        int[] dx = {0, 0, -1, 1};
        int[] dy = {1, -1, 0, 0};

        for (int i = 0; i < count; i++) {
            int dir = directions[i];
            int xx = x + dx[dir - 1];
            int yy = y + dy[dir - 1];

            if (!isInRange(xx, yy, board)) {
                continue;
            }

            x = xx;
            y = yy;

            dice.move(dir);

            System.out.println(dice.top);

            if (board[xx][yy] == 0) {
                board[xx][yy] = dice.bottom;
            } else {
                dice.bottom = board[xx][yy];
                board[xx][yy] = 0;
            }
        }


    }
    static public boolean isInRange(int row, int col, int[][] board){
      return row >= 0 && row < board.length && col >= 0 && col < board[0].length;
    }

}

class Dice {
    int top = 0;
    int bottom = 0;
    int east = 0;
    int west = 0;
    int north = 0;
    int south = 0;


    public void move(int dir) {
        switch (dir) {
            case 1:
                moveToEast();
                break;
            case 2:
                moveToWest();
                break;
            case 3:
                moveToNorth();
                break;
            case 4:
                moveToSouth();
                break;
        }
    }

    private void moveToEast() {
        int tmp1 = east;
        int tmp2;
        east = top;
        tmp2 = bottom;
        bottom = tmp1;
        tmp1 = west;
        west = tmp2;
        top = tmp1;
    }


    private void moveToWest() {
        int tmp1 = west;
        int tmp2;
        west = top;
        tmp2 = bottom;
        bottom = tmp1;
        tmp1 = east;
        east = tmp2;
        top = tmp1;
    }


    private void moveToSouth() {
        int tmp1 = south;
        int tmp2;
        south = top;
        tmp2 = bottom;
        bottom = tmp1;
        tmp1 = north;
        north = tmp2;
        top = tmp1;
    }


    private void moveToNorth() {
        int tmp1 = north;
        int tmp2;
        north = top;
        tmp2 = bottom;
        bottom = tmp1;
        tmp1 = south;
        south = tmp2;
        top = tmp1;
    }
}
