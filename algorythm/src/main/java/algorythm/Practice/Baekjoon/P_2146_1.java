package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class P_2146_1 {
    /**
     * 설계
     * DFS 돌면서 섬의 가장자리에 도착하면 BFS항해를 시작함. 항해 거리가 다리 개수.
     * 다른 섬에 도착할 때마다 최소 다리 개수를 업데이트.
     * 이런 식으로 모든 섬에 대해 최소 다리  업데이트.
     * 유형 - BFS
     * 시간복잡도 - 가장자리 찾는 시간 N^2 * 항해 시간 N^2 = N^4
     *
     * 상세 구현
     * DFS 한번 돌면서 나라를 1, 2, 3 이런식으로 새로 업데이트
     * DFS돌면서 다음 노드가 바다이면 BFS 시작
     *
     * BFS
     * 출발하는 나라번호 기록 후 출발 1번에서 출발하면 1번이 아닌 노드만 가다가 0번이 아닌 노드 도착하면 시간 기록
     * BFS는 각 섬마다 시간맵을 사용하는데
     * 자신이 그 노드에 도착할 때 더 빨리 도착할 수 있을 때만 그 노드를 가고, 시간을 업데이트 해줌
     */

    static int N;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        int[][] board = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        nameIslands(board);

        findEdgeAndSail(board);

        System.out.println(minBridge);
    }

    private static void findEdgeAndSail(int[][] board) {
        boolean[][] visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j] && board[i][j] != 0) {
                    findEdgeDFS(i, j, board, visited, new int[N][N], board[i][j]);
                }
            }
        }
    }

    private static void findEdgeDFS(int row, int col, int[][] board, boolean[][] visited, int[][] times,
            int islandName) {
        for (int i = 0; i < 4; i++) {
            int nx = row + dx[i];
            int ny = col + dy[i];

            if (isInRange(nx, ny) && !visited[nx][ny] && board[nx][ny] == islandName) {
                visited[nx][ny] = true;
                findEdgeDFS(nx, ny, board, visited, times, islandName);
                continue;
            }

            if (isInRange(nx, ny) && !visited[nx][ny] && board[nx][ny] == 0) {
                times[nx][ny] = 1;
                visited[nx][ny] = true;
                sail(nx, ny, board, times,  islandName);

            }
        }
    }

    static int minBridge = Integer.MAX_VALUE;
    private static void sail(int row, int col, int[][] board, int[][] times,
            int startIsland) {
        Deque<Integer[]> q = new ArrayDeque<>();
        q.addLast(new Integer[]{row, col});
        boolean[][] seaVisited = new boolean[N][N];
        seaVisited[row][col] = true;

        while (!q.isEmpty()) {
            Integer[] node = q.removeFirst();

            for (int i = 0; i < 4; i++) {
                int nx = node[0] + dx[i];
                int ny = node[1] + dy[i];

                if (isInRange(nx, ny) && board[nx][ny] != startIsland && !seaVisited[nx][ny] &&
                        ((times[nx][ny] == 0) ||
                                ((times[nx][ny] != 0) && (times[nx][ny] > (times[node[0]][node[1]] + 1)))))
                {
                    if (board[nx][ny] != 0) {
                        minBridge = Math.min(minBridge, times[node[0]][node[1]]);
                        return;
                    }
                    q.addLast(new Integer[]{nx, ny});
                    times[nx][ny] = times[node[0]][node[1]] + 1;
                    seaVisited[nx][ny] = true;
                }
            }
        }

    }

    private static void nameIslands(int[][] board) {
        boolean[][] visited = new boolean[N][N];
        int name = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j] && board[i][j] != 0) {
                    namingDFS(i, j, board, visited, name);
                    name++;
                }
            }
        }
    }

    private static void namingDFS(int row, int col, int[][] board, boolean[][] visited, int name) {

        board[row][col] = name;
        visited[row][col] = true;
        for (int i = 0; i < 4; i++) {
            int nx = row + dx[i];
            int ny = col + dy[i];

            if (isInRange(nx, ny) && !visited[nx][ny] && board[nx][ny] == 1) {
                namingDFS(nx, ny, board, visited, name);
            }
        }
    }

    private static boolean isInRange(int row, int col) {
        return row >= 0 && row < N && col >= 0 && col < N;
    }
}
