package algorythm.Practice.programmers.step3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BuildGidungAndBoe {

    /**
     * 기둥은 바닥, 보의 한쪽 끝, 또는 다른 기둥 위
     * 보는 한쪽 끝 부분이 기둥 위, 양쪽 끝 부분이 다른 보와 연결
     *
     * x, y가 좌표평면으로 바뀜 ,0부터 시작
     *
     * 작업은 최대 1000번
     *
     * 시뮬레이션으로 판단됨
     *
     * 2차원 좌표에 기둥과 보 설치유무 넣기
    */

    public static void main(String[] args) {

        BuildGidungAndBoe b = new BuildGidungAndBoe();
        int n = 5;
        int[][] bf = {{1,0,0,1},{1,1,1,1},{2,1,0,1},{2,2,1,1},{5,0,0,1},{5,1,0,1},{4,2,1,1},{3,2,1,1}};
        int[][] bf2 =
                {{0,0,0,1},{2,0,0,1},{4,0,0,1},{0,1,1,1},{1,1,1,1},{2,1,1,1},{3,1,1,1},{2,0,0,0},{1,1,1,0},{2,2,0,1}};
        int[][] solution = b.solution(n, bf);
        int[][] solution2 = b.solution(n, bf2);
        System.out.println(solution);
    }

    public int[][] solution(int n, int[][] build_frame) {
        boolean[][][] board = new boolean[n + 3][n + 3][2]; // 기둥, 보 둘 다 있으면 true, true

        for (int i = 0; i < build_frame.length; i++) {
            int x = build_frame[i][0] + 1;
            int y = build_frame[i][1] + 1;
            int type = build_frame[i][2];
            int instruction = build_frame[i][3];

            work(board, x, y, type, instruction);
        }

        List<Integer[]> ans = new ArrayList<>();

        for (int i = 1; i < n + 2; i++) {
            for (int j = 1; j < n + 2; j++) {
                if (board[i][j][0]) {
                    ans.add(new Integer[]{i, j, 0});
                }

                if (board[i][j][1]) {
                    ans.add(new Integer[]{i, j, 1});
                }
            }
        }

        Collections.sort(ans, (x, y) ->{
            if (x[0] == y[0]) {
                if (x[1] == y[1]) {
                     return Integer.compare(x[2],y[2]);
                }

                return Integer.compare(x[1],y[1]);
            }

            return Integer.compare(x[0], y[0]);
        });

        int[][] answer = new int[ans.size()][3];
        for (int i = 0; i < ans.size(); i++) {
            answer[i] = Arrays.stream(ans.get(i)).mapToInt(x -> x).toArray();
        }

        return answer;
    }

    private void work(boolean[][][] board, int x, int y, int type, int instruction) {
        if (type == 0) {
            //기둥 일 때 유효성 체크
            //삭제
            if (instruction == 0) {
                board[x][y][0] = false; //먼저 삭제해보고 유효한지 체크

                if (!isAllValid(board)) {
                    return ;
                }

                board[x][y][0] = true;
            }

            //생성
            if (instruction == 1) {
                if (isValidGidung(board, x, y)) {
                    board[x][y][0] = true;
                }
            }
        } else {
            //보 일 때 유효성 체크
            //삭제
            if (instruction == 0) {
                board[x][y][1] = false;

                if (!isAllValid(board)) {
                    return;
                }

                board[x][y][1] = true;
            }

            //생성
            if (instruction == 1) {
                if (isValidBoe(board, x, y)) {
                    board[x][y][1] = true;
                }
            }

        }
    }

    private boolean isAllValid(boolean[][][] board) {
        for (int i = 1; i < board.length - 1; i++) {
            for (int j = 1; j < board[0].length - 1; j++) {
                if (board[i][j][0]) {
                    if (!isValidGidung(board, i, j)) return false;
                }

                if (board[i][j][1]) {
                    if (!isValidBoe(board, i, j)) return false;
                }
            }
        }

        return true;
    }

    private boolean isValidGidung(boolean[][][] board, int x, int y) {
        return y == 0 || board[x][y - 1][0] || (board[x - 1][y][1]);
    }

    private boolean isValidBoe(boolean[][][] board, int x, int y) {
        return board[x][y - 1][0] || board[x + 1][y - 1][0] ||
                (board[x - 1][y][1] && board[x + 1][y][1]);
    }

}
