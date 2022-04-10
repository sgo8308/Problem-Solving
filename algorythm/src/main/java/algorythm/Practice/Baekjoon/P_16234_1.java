package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class P_16234_1 {

    /**
     * 문제 조건
     * 각각의 땅에 나라 하나씩 1x1, NxN , r행 c열에 사람 수, 국경선 존재
     *
     * 1. dfs로 l, r 조건에 맞는 나라만 돌아다니면서 리스트에 위치 넣기. 총 인구수 체크,
     * 2. 총 인구수/ 리스트 사이즈해서 리스트 포인트마다 업데이트
     * 3. 이런 식으로 배열의 끝까지 진행했으면 인구이동 한 번 완료
     * 3. dfs로 연합을 한 번도 못만들때까지 반복
     */
    static int n; // 1~50
    static int l; // 1~100
    static int r; // 1~100
    static int[][] A; // 원소는 0 ~ 100
    static int moveCnt = 0;
    private static int[] dx = new int[]{1, -1, 0, 0};
    private static int[] dy = new int[]{0, 0, -1, 1};
    static int humansInUnion = 0;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        A = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean isMoved = true;
        while (isMoved) {
            isMoved = false;

            boolean visited[][] = new boolean[n][n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if(visited[i][j]) continue;

                    List<Country> union = new ArrayList<>();
                    checkUnions(i, j, visited, union);
                    if (union.size() > 1) {
                        isMoved = true;
                    }

                    int newHumanCnt = humansInUnion / union.size();

                    for (int k = 0; k < union.size(); k++) {
                        Country country = union.get(k);
                        A[country.row][country.col] = newHumanCnt;
                    }

                    humansInUnion = 0;
                }
            }

            if(isMoved) moveCnt++;
        }

        System.out.println(moveCnt);
    }

    private static void checkUnions(int row, int col, boolean[][] visited,
            List<Country> countries) {

        visited[row][col] = true;
        countries.add(new Country(row, col));
        humansInUnion += A[row][col];

        for (int i = 0; i < 4; i++) {
            int nx = row + dx[i];
            int ny = col + dy[i];
            if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                int diff = Math.abs(A[row][col] - A[nx][ny]);

                if (!visited[nx][ny] && diff >= l && diff <= r) {
                    checkUnions(nx, ny, visited, countries);
                }
            }
        }
    }

    static class Country {
        int row;
        int col;

        public Country(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
