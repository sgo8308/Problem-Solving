package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P_2580 {

    private static int[][] sudoku = new int[9][9];

    public static void main(String[] args) throws Exception {
        createSudoku();
        sudoku(0, 0);
    }

    private static void createSudoku() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 9; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                sudoku[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static void sudoku(int row, int col) {
        if (col >= 9) {
            sudoku(row + 1, 0);
            return;
        }

        if (row >= 9) {
            printSudoku();
            System.exit(0);
        }

        if (sudoku[row][col] != 0) {
            sudoku(row, col + 1);
        }

        for (int candidate = 1; candidate < 10; candidate++) {
            if (canPutNumToSudoku(row, col, candidate)) {
                sudoku[row][col] = candidate;
                sudoku(row, col + 1);
                sudoku[row][col] = 0; // 0으로 바꿔주어야 다음 스도쿠가 이 칸을 탐색가능
            }
        }
    }

    private static boolean canPutNumToSudoku(int row, int col, int candidate) {
        // 같은 행에 있는 원소들 중 겹치는 열 원소가 있는지 검사
		for (int i = 0; i < 9; i++) {
			if (sudoku[row][i] == candidate) {
				return false;
			}
		}

		// 같은 열에 있는 원소들 중 겹치는 행 원소가 있는지 검사
		for (int i = 0; i < 9; i++) {
			if (sudoku[i][col] == candidate) {
				return false;
			}
		}

        //사각형 탐색
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (sudoku[i][j] == candidate) {
                    return false;
                }
            }
        }

        return true;
    }

    private static void printSudoku() {
        StringBuilder sb = new StringBuilder();
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					sb.append(sudoku[i][j]).append(' ');
				}
				sb.append('\n');
			}
			System.out.print(sb);
    }
}
