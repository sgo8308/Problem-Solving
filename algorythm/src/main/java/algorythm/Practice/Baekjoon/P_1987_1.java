package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class P_1987_1 {

    private static int R;
    private static int C;
    private static int answer = 0;
    private static Map<Character, Integer> alphas = new HashMap<>();
    private static String[] board;

    private static int[] dx = {1, -1, 0, 0};
    private static int[] dy = {0, 0, 1, -1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new String[R];
        for (int i = 0; i < R; i++) {
            board[i] = br.readLine();
        }

        for (int i = 65; i < 91; i++) {
            alphas.put((char) i, 0);
        }

        dfs(0, 0, 0);

        System.out.println(answer);
    }

    private static void dfs(int x, int y, int depth) {
        if (alphas.get(board[x].charAt(y)) == 1) {
            answer = Math.max(answer, depth);
            return;
        }

        alphas.put(board[x].charAt(y), 1);
        for (int i = 0; i < 4; i++) {
            int xx = x + dx[i];
            int yy = y + dy[i];
            if (xx >= 0 && xx < R && yy >= 0 && yy < C) {
                dfs(xx, yy, depth + 1);
            }
        }
        alphas.put(board[x].charAt(y), 0);
    }
}
