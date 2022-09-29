package algorythm.Practice.programmers.step3;

import java.util.HashMap;
import java.util.Map;

public class SheepAndWolf {

    public static void main(String[] args) {
        int[] info = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int[][] edges = {{0, 1}, {0, 2}, {1, 3}, {1, 4}, {2, 5}, {2, 6}, {3, 7}, {3, 8}, {4, 9},
                {4, 10}, {5, 11}, {5, 12}, {6, 13}, {6, 14}, {7, 15}, {7, 16}};

        SheepAndWolf s = new SheepAndWolf();
        int solution = s.solution(info, edges);
        System.out.println(solution);
    }


    int answer = 1;
    int[] visited;
    int n;
    int[] infos;
    private Map<Integer, Integer[]> connectedMap;

    public int solution(int[] info, int[][] edges) {
        visited = new int[1 << info.length];
        n = info.length;
        infos = info;

        connectedMap = new HashMap<>();
        for (int[] edge : edges) {
            connectedMap.putIfAbsent(edge[0], new Integer[2]);
            if (connectedMap.get(edge[0])[0] == null) {
                connectedMap.get(edge[0])[0] = edge[1];
            } else {
                connectedMap.get(edge[0])[1] = edge[1];
            }
        }

        dfs(1);

        return answer;
    }
    private void dfs(int state) {
        if(visited[state] == 1) return;

        visited[state] = 1;

        //현재 모은 양과 늑대 수 찾기
        int sheep = 0;
        int wolf = 0;
        for (int i = 0; i < n; i++) {
            if ((state & 1 << i) != 0) {
                sheep += (1 ^ infos[i]);
                wolf += infos[i];
            }
        }

        if (wolf >= sheep) return;

        answer = Math.max(answer, sheep);

        for (Integer node : connectedMap.keySet()) {
            if((state & 1 << node) == 0) continue;

            Integer leftNode = connectedMap.get(node)[0];
            Integer rightNode = connectedMap.get(node)[1];
            if (leftNode != null) {
                dfs(state | 1 << leftNode);
            }

            if (rightNode != null) {
                dfs(state | 1 << rightNode);
            }
        }
    }
}
