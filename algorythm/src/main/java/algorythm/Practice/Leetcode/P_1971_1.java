package algorythm.Practice.Leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P_1971_1 {

    public static void main(String[] args) {
//        int n = 3;
//        int[][] edges = {{0, 1}, {1, 2}, {2, 0}};
//        int source = 0;
//        int dest = 2;
        int n = 6;
        int[][] edges = {{0, 1}, {0, 2}, {3, 5}, {5, 4}, {4, 3}};
        int source = 0;
        int dest = 5;
        System.out.println(validPath(n, edges, source, dest));
    }
    //질문 n이 20만이고 모든 노드들이 서로 연결되어 있으면 메모리초과 날거 같은데 어떻게 해결?
    static public boolean validPath(int n, int[][] edgeArray, int source, int destination) {
        Map<Integer, List<Integer>> edges = new HashMap<>();
        for (int i = 0; i < edgeArray.length; i++) {
            int[] edge = edgeArray[i];
            edges.putIfAbsent(edge[0], new ArrayList());
            edges.get(edge[0]).add(edge[1]);
            edges.putIfAbsent(edge[1], new ArrayList());
            edges.get(edge[1]).add(edge[0]);
        }

        return dfs(source, destination, edges, new boolean[n]);
    }

    static private boolean dfs(int nowNode, int destination, Map<Integer, List<Integer>> edges, boolean[] visited) {
        if (nowNode == destination) {
            return true;
        }
        List<Integer> neighbors = edges.get(nowNode);
        for (int i = 0; i < neighbors.size(); i++) {
            int nextNode = neighbors.get(i);
            if (!visited[nextNode]) {
                visited[nextNode] = true;
                if (dfs(nextNode, destination, edges, visited)) {
                    return true;
                }
            }
        }

        return false;
    }
}
