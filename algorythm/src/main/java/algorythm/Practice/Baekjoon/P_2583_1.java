package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class P_2583_1 {

    static int N;
    static int M;
    static int K;
    static int[][] coordinates;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        coordinates = new int[N][M];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int[] from = {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
            int[] to = {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
            markCoordinates(from, to);
        }
        int areaCount = 0;
        List<Integer> extents = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (coordinates[i][j] == 0) {
                    extents.add(dfs(new int[]{i, j}) + 1);
                    areaCount++;
                }
            }
        }

        Collections.sort(extents);
        System.out.println(areaCount);
        for (int i = 0; i < extents.size(); i++) {
            System.out.print(extents.get(i) + " ");
        }
    }

    private static int dfs(int[] node) {
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        int extent = 0;
        coordinates[node[0]][node[1]] = 1;

        for (int i = 0; i < 4; i++) {
            int xx = node[0] + dx[i];
            int yy = node[1] + dy[i];

            if (xx < N && xx >= 0 && yy < M && yy >= 0 && coordinates[xx][yy] == 0) {
                int[] newNode = {xx, yy};
                extent += 1;
                extent += dfs(newNode);
            }
        }
        return extent;
    }

    private static void markCoordinates(int[] from, int[] to) {
        for (int i = from[0]; i < to[0]; i++) {
            for (int j = from[1]; j < to[1]; j++) {
                coordinates[i][j] = 1;
            }
        }
    }
}
