package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class P_4991_2 {
    static int W;
    static int H;
    static int minDist = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception{
        //같은 칸 여러번 가능, 필요한 이동 횟수의 최솟값

        /**
         * 1. BFS를 통해 진공청소기와, 더러운 칸들을 기준으로 최적 경로 맵 최대 11개 만들기
         * 2. 더러운 칸들 순열을 통해서 가장 최소값을 갖을 경로 구하기
         * 3. 유형 - 플루드 필
         * 4. 시간 복잡도 = O(N^2) + 10P10 * 10 = 대략 5천만번 안에 가능
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            if(W == 0 && H == 0) break;
            minDist = Integer.MAX_VALUE;
            char[][] board = new char[H][W];

            List<Point> points = new ArrayList<>();

            for (int i = 0; i < H; i++) {
                st = new StringTokenizer(br.readLine());
                board[i] = st.nextToken().toCharArray();

                for (int j = 0; j < W; j++) {
                    if (board[i][j] == 'o') {
                        points.add(0, new Point(i, j));
                    }

                    if (board[i][j] == '*') {
                        points.add(new Point(i, j));
                    }
                }
            }

            Map<Point, Integer[][]> fastTimesMap = new HashMap<>();

            for (int i = 0; i < points.size(); i++) {
                Point point = points.get(i);
                fastTimesMap.put(point, getFastTimes(point, board));
            }

            calMinDist(points, fastTimesMap,fastTimesMap.size() - 1);
            System.out.println(minDist);
        }
    }
    private static void calMinDist(List<Point> points, Map<Point, Integer[][]> fastTimesMap, int size) {
        perm(points, fastTimesMap, new Integer[size + 1], new boolean[size + 1], 1);
    }
    private static void perm(List<Point> points, Map<Point, Integer[][]> fastTimesMap,
            Integer[] perm, boolean[] visited, int depth) {
        if (depth == perm.length) {
            int dist = 0;
            perm[0] = 0; //청소기 첫 위치부터 시작하도록 넣기

            for (int j = 0; j < perm.length - 1; j++) {
                Point startPoint = points.get(perm[j]);
                Point nextPoint = points.get(perm[j+1]);

                Integer[][] fastTimes = fastTimesMap.get(startPoint);
                if (fastTimes[nextPoint.row][nextPoint.col] == 0) {
                    dist = -1;
                    break;
                }
                dist += fastTimes[nextPoint.row][nextPoint.col];
            }
            minDist = Math.min(minDist, dist);
            return;
        }

        for (int i = 1; i < perm.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                perm[depth] = i;
                perm(points, fastTimesMap, perm, visited, depth + 1);
                visited[i] = false;
            }
        }
    }
    private static Integer[][] getFastTimes(Point point, char[][] board) {
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        Integer[][] fastTimes = new Integer[board.length][board[0].length];
        for (int i = 0; i < fastTimes.length; i++) {
            Arrays.fill(fastTimes[i], 0);
        }
        Deque<Point> q = new ArrayDeque<>();
        q.addLast(point);
        fastTimes[point.row][point.col] = -1;

        int dist = 0;
        while (!q.isEmpty()) {
            dist++;
            int qSize = q.size();
            for (int i = 0; i < qSize; i++) {
                Point nowPoint = q.poll();
                for (int j = 0; j < 4; j++) {
                    int nx = nowPoint.row + dx[j];
                    int ny = nowPoint.col + dy[j];

                    if (nx >= 0 && nx < board.length && ny >= 0 && ny < board[0].length
                            && fastTimes[nx][ny] == 0 && board[nx][ny] != 'x') {
                        fastTimes[nx][ny] = dist;
                        q.addLast(new Point(nx, ny));
                    }
                }
            }
        }

        return fastTimes;
    }

    //0번 인덱스는 청소기 첫 시작 포인트를 위해 비워두기

    static class Point {
        int row;
        int col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}