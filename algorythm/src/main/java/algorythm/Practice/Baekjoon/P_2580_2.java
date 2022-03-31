package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class P_2580_2 {

    static int[][] sudoku = new int[9][9];
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 9; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                sudoku[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        sudoku(0,0);
        printSudoku();
    }

    private static void printSudoku() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(sudoku[i][j] + " ");
            }
            System.out.println();
        }
    }

    static void sudoku(int row, int col) {
        System.out.println(row + " : " +col);
        if (row >= 9) {
            printSudoku();
            return;
        }

        if (col >= 9) {
            sudoku(row + 1, 0);
            return;
        }

        if (sudoku[row][col] != 0) {
            sudoku(row, col + 1);
            return;
        }

        for (int candidate = 1; candidate < 10; candidate++) {
            if (isPromising(candidate, row, col)) {
                sudoku[row][col] = candidate;
                sudoku(row, col + 1);
                sudoku[row][col] = 0;
            }
        }
    }

    static boolean isPromising(int candidate, int row, int col) {
        //가로 체크
        if (!lineCheck((column, cand) -> sudoku[row][column] == cand, candidate)) {
            return false;
        }
        //세로 체크
        if (!lineCheck((row2, cand) -> sudoku[row2][col] == cand, candidate)) {
            return false;
        }
        //사각형 체크
        int[] startPoint = {row - row % 3, col - col % 3};
        for (int i = startPoint[0]; i < startPoint[0] + 3; i++) {
            for (int j = startPoint[1]; j < startPoint[1] + 3; j++) {
                if (sudoku[i][j] == candidate) {
                    return false;
                }
            }
        }

        return true;
    }

    static boolean lineCheck(BiPredicate<Integer,Integer> condition, int candidate) {
        for (int i = 0; i < 9; i++) {
            if (condition.test(i, candidate)) {
                return false;
            }
        }
        return true;
    }
}
