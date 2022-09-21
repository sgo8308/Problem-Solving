package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class P_16236_1 {

    /**
     * 아기 상어 처음 크기 2, 1초마다 상하좌우, 자기보다 작은 물고기만 먹고, 크기 같으면 지나가기만, 크면 못지나감
     * <p>
     * 설계 1. 먹을 수 있는 물고기가 있는지 탐색한다. 이 때 한 마리라면 바로 먹으러 간다. 2. 먹을 수 있는 물고기 중 최단 거리의 물고기를 bfs로 탐색한다. 이
     * 때 상어보다 몸집이 큰 물고기는 못 지나간다. 3. 최단 거리의 물고기 중 가장 위면서 가장 왼쪽에 있는 물고기를 선택한다. 4. 상어가 이동한다. 상어를 물고기
     * 위치로 이동시키고
     * <p>
     * 시간복잡도 위,왼 물고기 찾기 가능한 물고기가 최대 400마리라면 위쪽 찾기 연산 400 + 왼쪽 찾기 연산 400 + bfs 노드 400 x 간선 4 = 1600
     * => 2400 물고기 최대 400마리라면 2400 x 400 = 대략 100만
     */

    /**
     * 복기
     *
     * 1. toSring()을 이용한 디버깅
     * 2. 이차원 배열에서 무언가가 이동하는 문제에서 막혀서 못가는 경우 고려하기
     */
    static int n;
    static int time = 0;
    static int[][] board;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        board = new int[n][n];
        Shark babyShark = new Shark();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int num = Integer.parseInt(st.nextToken());
                board[i][j] = num;
                if (num == 9) {
                    babyShark = new Shark(i, j);
                }
            }
        }


        Integer[] foundFish = babyShark.findFish();
        while (foundFish != null) {
            babyShark.moveTo(foundFish);
            babyShark.eatFish();
            babyShark.sizeUpIfPossible();

            foundFish = babyShark.findFish();
        }

        System.out.println(time);
    }

    static class Shark {

        int size = 2;
        int x;
        int y;
        int eatCount = 0;

        public Shark() {
        }

        public Shark(int x, int y) {
            this.x = x;
            this.y = y;
        }

        Integer[] findFish() {
            List<Integer[]> fishes = findClosestFish();
            if (fishes.size() == 0) {
                return null;
            } else if (fishes.size() == 1) {
                return fishes.get(0);
            } else {
                return findUpLeftFish(fishes);
            }
        }

        private List<Integer[]> findClosestFish() {
            int[] dx = {0, 1, 0, -1};
            int[] dy = {1, 0, -1, 0};
            boolean[][] visited = new boolean[n][n];
            int[][] distances = new int[n][n];

            Deque<Integer[]> q = new ArrayDeque<>();
            q.addLast(new Integer[]{x,y});
            distances[x][y] = 0;
            visited[x][y] = true;

            while (!q.isEmpty()) {
                Integer[] now = q.pollFirst();

                for (int i = 0; i < 4; i++) {
                    int xx = now[0] + dx[i];
                    int yy = now[1] + dy[i];

                    if (isInRange(xx, yy) && !visited[xx][yy] && board[xx][yy] <= size) {
                        q.addLast(new Integer[]{xx, yy});
                        visited[xx][yy] = true;
                        distances[xx][yy] = distances[now[0]][now[1]] + 1;
                    }
                }
            }

            int minDist = Integer.MAX_VALUE;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (distances[i][j] != 0 && board[i][j] != 0 && board[i][j] < size) {
                        minDist = Math.min(minDist, distances[i][j]);
                    }
                }
            }

            List<Integer[]> foundFishes = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (board[i][j] != 0 && board[i][j] < size && distances[i][j] == minDist) {
                        foundFishes.add(new Integer[]{i, j});
                    }
                }
            }
            if(minDist != Integer.MAX_VALUE)
                time += minDist;

            return foundFishes;
        }
        private boolean isInRange(int row, int col){
          return row >= 0 && row < board.length && col >= 0 && col < board[0].length;
        }

        private Integer[] findUpLeftFish(List<Integer[]> foundFishes) {
            //가장 위쪽
            final int maxUp = foundFishes.stream().mapToInt((fish) -> x - fish[0]).max().getAsInt();
            List<Integer[]> cand = foundFishes.stream()
                    .filter((fish) -> x - fish[0] == maxUp)
                    .collect(Collectors.toList());

           //가장 왼쪽
            final int maxLeft = cand.stream().mapToInt((fish) -> y - fish[1]).max().getAsInt();
            Integer[] ret = cand.stream().filter((fish) -> y - fish[1] == maxLeft).findAny().get();
            return ret;
        }

        void eatFish() {
            eatCount++;
        }

        public void sizeUpIfPossible() {
            if (eatCount == size) {
                size++;
                eatCount = 0;
            }
        }

        public void moveTo(Integer[] fish) {
            board[x][y] = 0;
            x = fish[0];
            y = fish[1];
            board[fish[0]][fish[1]] = 9;
        }


        @Override
        public String toString() {
            return "Shark{" +
                    "size=" + size +
                    ", x=" + x +
                    ", y=" + y +
                    ", eatCount=" + eatCount +
                    '}';
        }
    }
}
