package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class P_2210_1 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[][] board = new int[5][5];

        for (int i = 0; i < 5; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Set<String> nums = new HashSet<>();

        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                dfs(row, col, board, new StringBuilder().append(board[row][col]), nums);
            }
        }

        System.out.println(nums.size());
    }

    private static void dfs(int row, int col, int[][] board, StringBuilder sb, Set<String> nums) {
        if (sb.length() == 6) {
            nums.add(sb.toString());
            return;
        }

        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        for (int i = 0; i < 4; i++) {
            int xx = row + dx[i];
            int yy = col + dy[i];

            if (xx >= 0 && xx < board.length && yy >= 0 && yy < board[0].length) {
                sb.append(board[xx][yy]);
                dfs(xx, yy, board, sb, nums);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }


}
