package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class P_17140_1 {

    static int r;
    static int c;
    static int k;
    static int[][] board = new int[3][3];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int time = 0;
        //배열의 인덱스는 1부터 시작
        while (condition(time)) {
            time++;

            Sorter sorter = getSorter(board);
            board = sorter.sort();
        }

        if (time > 100) {
            System.out.println(-1);
        } else {
            System.out.println(time);
        }
    }

    private static boolean condition(int time) {
       if(time > 100) return false;
       if(board.length < r || board[0].length < c) return true;
       if(board[r - 1][c - 1] == k) return false;

       return true;
    }

    private static Sorter getSorter(int[][] board) {
        if (board.length >= board[0].length) {
            return new RowSorter(board);
        } else {
            return new ColSorter(board);
        }
    }

    abstract static class Sorter {

        int[][] board;

        abstract int[][] sort();

        //정렬시 0은 무시
        protected void rowSort() {
            int[][] newBoard = new int[board.length][];
            int maxRowLen = 0;

            for (int i = 0; i < board.length; i++) {
                int[] row = board[i];

                Map<Integer, Integer> map = createCountMap(row);

                Set<Num> nums = createNumSet(map);

                newBoard[i] = createNewRow(nums);
                maxRowLen = Math.max(maxRowLen, newBoard[i].length);
            }

            //0 채워 넣기
            align(newBoard, maxRowLen);

            board = newBoard;
        }

        private Map<Integer, Integer> createCountMap(int[] row) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int j = 0; j < row.length; j++) {
                if(row[j] == 0) continue;

                map.putIfAbsent(row[j], 0);
                map.put(row[j], map.get(row[j]) + 1);
            }
            return map;
        }

        private Set<Num> createNumSet(Map<Integer, Integer> map) {
            Set<Num> nums = new TreeSet<>();
            for (Entry<Integer, Integer> entry : map.entrySet()) {
                nums.add(new Num(entry.getKey(), entry.getValue()));
            }
            return nums;
        }

        private int[] createNewRow(Set<Num> nums) {
            int[] newRow = new int[nums.size() * 2];
            Iterator<Num> it = nums.iterator();
            int j = 0;
            while (it.hasNext()) {
                Num num = it.next();
                newRow[j] = num.value;
                newRow[j + 1] = num.count;
                j += 2;
            }
            return newRow;
        }

        private void align(int[][] newBoard, int maxRowLen) {
            for (int i = 0; i < newBoard.length; i++) {
                newBoard[i] = Arrays.copyOf(newBoard[i], maxRowLen);
            }
        }
    }

    static class RowSorter extends Sorter {

        public RowSorter(int[][] board) {
            this.board = board;
        }

        public int[][] sort() {
            rowSort();
            return board;
        }
    }

    static class ColSorter extends Sorter {

        public ColSorter(int[][] board) {
            this.board = board;
        }

        public int[][] sort() {
            rotateToRight();
            rowSort();
            rotateToLeft();
            return board;
        }

        private void rotateToRight() {
            int[][] newBoard = new int[board[0].length][board.length];
            for (int i = 0; i < board[0].length; i++) {
                for (int j = 0; j < board.length; j++) {
                    newBoard[i][j] = board[board.length - 1 - j][i];
                }
            }
            board = newBoard;
        }

        private void rotateToLeft() {
            int[][] newBoard = new int[board[0].length][board.length];
            for (int i = 0; i < newBoard.length; i++) {
                for (int j = 0; j < newBoard[0].length; j++) {
                    newBoard[i][j] = board[j][i];
                }
            }
            board = newBoard;
        }
    }

    static class Num implements Comparable<Num>{

        int value;
        int count;

        public Num(int value, int count) {
            this.value = value;
            this.count = count;
        }

        @Override
        public int compareTo(Num o) {
            if (this.count == o.count) {
                    return Integer.compare(this.value, o.value);
                }

                return Integer.compare(this.count, o.count);
        }
    }
}
