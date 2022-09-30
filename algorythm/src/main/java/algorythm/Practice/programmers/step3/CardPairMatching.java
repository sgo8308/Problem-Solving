package algorythm.Practice.programmers.step3;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CardPairMatching {

    private List<List<Integer[]>> allCases;

    /**
     * 문제는 최단 경로를 찾는 것이다. 각 지점에서 다른 지점으로의 최단 경로 그리고 어떤 동물을 선택할지 조합을 구한다. 각 조합의 동물마다 2개 중 어떤 애를 선택할지
     * 구하고 이렇게 구한 조합의 거리들 중 최소값을 반환한다.
     * <p>
     * 각 지점에서 다른 지점까지 최단경로는 BFS로 구한다. 이렇게 할 경우 한 지점에서 BFS 시간복잡도는 O(16 * 4)이다. 조합의 최대 경우의 수는 6개 중에서
     * 탐색 순서 정하기 -> 6! 각 순서에서 2개 중 하나 선택하기 2^6 총 2^6 * 6!개
     */

    public static void main(String[] args) {
        int[][] board ={{1,0,0,3},{2,0,0,0},{0,0,0,2},{3,0,1,0}};

        CardPairMatching c = new CardPairMatching();
//        int solution = c.solution(board, 1, 0);
//        System.out.println(solution);

        int[][] board2 = {{3,0,0,2},{0,0,1,0},{0,1,0,0},{2,0,0,3}};
        int solution2 = c.solution(board2, 0, 1);
        System.out.println(solution2);

    }

    Map<Integer, List<Integer[]>> map = new HashMap<>();
    int[][] boards;

    public int solution(int[][] board, int r, int c) {
        boards = board;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != 0) {
                    map.putIfAbsent(board[i][j], new ArrayList<>());
                    map.get(board[i][j]).add(new Integer[]{i, j});
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (Integer key : map.keySet()) {
            sb.append(key);
        }

        ArrayList<String> perms = perm(sb.toString(), new ArrayList<>(), "", sb.length(),
                new boolean[sb.length()]);

        allCases = new ArrayList<>();

        for (String perm : perms) {
            dfs(0, perm, new ArrayList<>(), 0);
        }

        int minDist = Integer.MAX_VALUE;
        for (List<Integer[]> cass : allCases) {
            int dist = search(r, c, cass);
            minDist = Math.min(minDist, dist);
        }

        return minDist + map.keySet().size() * 2; // 엔터 횟수도 계산해주기
    }

    private void dfs(int idx, String perm, ArrayList<Integer[]> cass, int from) {
        if (idx == perm.length() * 2) {
            allCases.add(new ArrayList<>(cass));
            return;
        }

        int card = Character.getNumericValue(perm.charAt(idx / 2));

        if (from == 0) {
            cass.add(map.get(card).get(0));
            dfs(idx + 1, perm, cass, 1);
            cass.remove(cass.size() - 1);

            cass.add(map.get(card).get(1));
            dfs(idx + 1, perm, cass, 2);
            cass.remove(cass.size() - 1);
        } else if (from == 1) {
            cass.add(map.get(card).get(1));
            dfs(idx + 1, perm, cass, 0);
            cass.remove(cass.size() - 1);
        } else  {
            cass.add(map.get(card).get(0));
            dfs(idx + 1, perm, cass, 0);
            cass.remove(cass.size() - 1);
        }
    }

    private int search(int x, int y, List<Integer[]> cass) {
        int dist = 0;
        int[][] board = newBoard();
        for (int i = 0; i < cass.size(); i++) {
            Integer[] next = cass.get(i);
            dist += bfs(x, y, next, board);
            x = next[0];
            y = next[1];

            if (i % 2 == 1) {
                board[cass.get(i - 1)[0]][cass.get(i - 1)[1]] = 0;
                board[cass.get(i)[0]][cass.get(i)[1]] = 0;
            }
        }

        return dist;
    }

    private int[][] newBoard() {
        int[][] board = new int[boards.length][boards[0].length];
        for (int i = 0; i < boards.length; i++) {
            for (int j = 0; j < boards[0].length; j++) {
                board[i][j] = boards[i][j];
            }
        }
        return board;
    }

    private int bfs(int x, int y, Integer[] next, int[][] board) {
        int[][] dists = new int[4][4];
        for (int[] dist : dists) {
            Arrays.fill(dist, Integer.MAX_VALUE);
        }
        int[] dx = {0, 1, 0, -1}; // 우 하 좌 상
        int[] dy = {1, 0, -1, 0};

        Deque<Integer[]> q = new ArrayDeque<>();
        dists[x][y] = 0;
        q.addLast(new Integer[]{x, y});

        while (!q.isEmpty()) {
            Integer[] now = q.poll();

            for (int i = 0; i < 8; i++) {
                int xx;
                int yy;
                if (i < 4) {
                    xx = now[0] + dx[i];
                    yy = now[1] + dy[i];
                } else {
                    int idx = i - 4;
                    int nx = dx[idx];
                    int ny = dy[idx];
                    xx = now[0] + nx;
                    yy = now[1] + ny;
                    while (xx >= 0 && xx < 4 && yy >= 0 && yy < 4 && board[xx][yy] == 0) {
                        xx += nx;
                        yy += ny;
                    }

                    if(xx < 0 || xx >= 4 || yy < 0 || yy >= 4) {
                        xx -= nx;
                        yy -= ny;
                    }
                }

                if (xx < 0 || xx >= 4 || yy < 0 || yy >= 4) {
                    continue;
                }

                if (dists[xx][yy] > dists[now[0]][now[1]] + 1) {
                    dists[xx][yy] = dists[now[0]][now[1]] + 1;
                    q.addLast(new Integer[]{xx, yy});
                }
            }
        }

        return dists[next[0]][next[1]];
    }

    static ArrayList<String> perm(String s, ArrayList<String> combs, String comb, int limit,
            boolean[] visited) {
        if (comb.length() == limit) {
            combs.add(comb);
            return combs;
        } else {
            for (int i = 0; i < s.length(); i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    char c = s.charAt(i);
                    perm(s, combs, comb + c, limit, visited);
                    visited[i] = false;
                }
            }
        }

        return combs;
    }
}
