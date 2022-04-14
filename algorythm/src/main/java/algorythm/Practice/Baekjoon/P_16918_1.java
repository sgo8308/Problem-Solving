package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P_16918_1 {

    /**
     * 폭탄 3초 후 인접한 4칸 파괴, 인접한 폭탄은 그냥 파괴, 봄버맨 아무데나 이동
     * 빈칸 .  폭탄 O
     *
     * 폭탄 설치한 시간을 배열에 적기
     * 시간 - 원소 가 3인 애들 터뜨리기 터뜨린 공간은 .으로 업데이트
     * 터뜨릴 때 폭탄을 그냥 파괴시키는 거 조심
     *
     * 시간복잡도 O(N^3)
     */
    static int r;
    static int c;
    static int n;
    static int[][] board;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        board = new int[r][c];
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            String a = st.nextToken();
            for (int j = 0; j < c; j++) {
                board[i][j] = (a.charAt(j) == 'O') ? 0 : -1;
            }
        }

        int time = 1;
        while (time != n) {
            time++;
            installBomb(board, time);

            if(time == n) break;

            time++;
            explode(board, time);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (board[i][j] == -1) {
                    sb.append('.');
                } else {
                    sb.append('O');
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void explode(int[][] board, int time) {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (board[i][j] == time - 3) {
                    explode(i, j, board);
                }
            }
        }
    }

    private static void explode(int i, int j, int[][] board) {
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        int time = board[i][j];
        board[i][j] = -1;

        for (int k = 0; k < 4; k++) {
            int nx = i + dx[k];
            int ny = j + dy[k];

            if (nx >= 0 && nx < r && ny >= 0 && ny < c && board[nx][ny] != time) {
               board[nx][ny] = -1;
            }
        }
    }

    private static void installBomb(int[][] board, int time) {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if(board[i][j] == -1)
                    board[i][j] = time;
            }
        }
    }
}
