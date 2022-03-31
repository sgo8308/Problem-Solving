package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P_16956_1 {

    public static void main(String[] args) throws Exception {
        char[] ss = {'a', 'b'};
        String[] pp = {"a", "b"};
        StringBuilder sb = new StringBuilder();
        sb.append(ss);
        System.out.println(ss);
        System.out.println(pp);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        String[][] farm = new String[R][C];
        for (int i = 0; i < R; i++) {
            farm[i] = br.readLine().split("");
        }
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        for (int row = 0; row < R; row++) {
            for (int col = 0; col < C; col++) {
                if (farm[row][col].equals("S")) {

                    for (int k = 0; k < 4; k++) {
                        int xx = row + dx[k];
                        int yy = col + dy[k];
                        if (xx >= 0 && xx < R && yy >= 0 && yy < C && farm[xx][yy].equals("W")) {
                            System.out.println(0);
                            return;
                        }

                        if (xx >= 0 && xx < R && yy >= 0 && yy < C && farm[xx][yy].equals(".")) {
                            farm[xx][yy] = "D";
                        }
                    }
                }
            }
        }
        System.out.println(1);
        StringBuilder sb2= new StringBuilder();
        for (int i = 0; i < R; i++) {
            sb.append(farm[i]);
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
