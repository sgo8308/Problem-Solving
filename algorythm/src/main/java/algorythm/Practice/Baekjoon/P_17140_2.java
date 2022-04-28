package algorythm.Practice.Baekjoon;

import java.io.*;
import java.util.*;

public class P_17140_2 {
    static int[][] board;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        board = new int[3][3];
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int time = 0;
        while (time < 101) {
            if(r-1 <= board.length && c-1 <board[0].length && board[r-1][c-1] == k) break;
            time++;
            if (board.length >= board[0].length) {
                ROperation(board);
            } else {
                COperation(board);
            }
        }

        if (time > 100) time = -1;
        System.out.println(time);
    }

    private static void ROperation(int[][] bd) {
        List<TreeSet<Num>> list = new ArrayList<>();
        for (int i = 0; i < bd.length; i++) {
            list.add(new TreeSet<>(Comparator.comparingInt((Num x)-> x.count).thenComparingInt(x->x.value)));
        }

        int maxLen = 0;
        for (int i = 0; i < bd.length; i++) {
            TreeSet<Num> set = list.get(i);
            Map<Integer, Num> map = new HashMap<>();
            for (int j = 0; j < bd[0].length; j++) {
                if(bd[i][j] == 0) continue;
                map.putIfAbsent(bd[i][j], new Num(bd[i][j], 0));
                map.get(bd[i][j]).count++;
            }

            for (Num value : map.values()) {
                set.add(value);
            }

            maxLen = Math.max(maxLen, set.size() * 2);
        }

        int[][] newBoard = new int[bd.length][maxLen];
        for (int i = 0; i < list.size(); i++) {
            TreeSet<Num> set = list.get(i);
            int j = 0;
            for (Num num : set) {
                newBoard[i][j] = num.value;
                newBoard[i][j+1] = num.count;
                j += 2;
            }
        }

        board = newBoard;
    }

    private static void COperation(int[][] bd) {
        ROperation(flipBoard(bd));
        board = flipBoard(board);
    }

    private static int[][] flipBoard(int[][] board) {
        int[][] newBoard = new int[board[0].length][board.length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                newBoard[j][i] = board[i][j];
            }
        }
        return newBoard;
    }


    static class Num {
        public int value;
        public int count;

        public Num(int value, int count) {
            this.value = value;
            this.count = count;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Num num = (Num) o;
            return value == num.value && count == num.count;
        }

        @Override
        public int hashCode() {
            return Objects.hash(value, count);
        }
    }
}
