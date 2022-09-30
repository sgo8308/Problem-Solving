package algorythm.Practice.programmers.step3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class ClimbingRoute {

    public static void main(String[] args) {
        int n = 6;
        int[][] paths = {{1, 2, 3}, {2, 3, 5}, {2, 4, 2}, {2, 5, 4}, {3, 4, 4}, {4, 5, 3}, {4, 6, 1}, {5, 6, 1}};
        int[] gates = {1, 3};
        int[] summits = {5};

        ClimbingRoute c = new ClimbingRoute();
        int[] solution = c.solution(n, paths, gates, summits);

    }

    Map<Integer, List<Integer[]>> map = new HashMap<>();
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        for (int[] path : paths) {
            map.putIfAbsent(path[0], new ArrayList<>());
            map.get(path[0]).add(new Integer[]{path[1], path[2]});
            map.putIfAbsent(path[1], new ArrayList<>());
            map.get(path[1]).add(new Integer[]{path[0], path[2]});
        }

        for (int summit : summits) {
            map.remove(summit);
        }

        int[] intensity = new int[n + 1];
        Arrays.fill(intensity, Integer.MAX_VALUE);

        dijkstra(intensity, gates);

        int min = Integer.MAX_VALUE;
        int ansSummint = 0;
        for (int summit : summits) {
            if(min > intensity[summit]) {
                min = intensity[summit];
                ansSummint = summit;
            } else if (min == intensity[summit]) {
                if(ansSummint > summit) {
                    ansSummint = summit;
                }
            }
        }

        return new int[]{ansSummint, min};
    }

    private void dijkstra(int[] intensities, int[] gates) {
        PriorityQueue<Node> pq = new PriorityQueue(); // intensity, node

        for (int gate : gates) {
            pq.add(new Node(0, gate));
        }

        while (!pq.isEmpty()) {
            Node poll = pq.poll();
            int intensity = poll.intensity;
            int node = poll.node;

            if(intensities[node] <= intensity) continue;

            intensities[node] = intensity;

            if(map.get(node) == null) continue;

            for (Integer[] nodeInfo  : map.get(node)) {
                int nextNode = nodeInfo[0];
                int nextIntensity = nodeInfo[1];
                if(intensities[nextNode] > Math.max(intensity, nextIntensity)) {
                    pq.add(new Node(Math.max(intensity, nextIntensity), nextNode));
                }
            }
        }
    }

    class Node implements Comparable<Node> {
        int intensity;
        int node;

        public Node(int intensity, int node) {
            this.intensity = intensity;
            this.node = node;
        }

        @Override
        public int compareTo(Node o) {
            return this.intensity - o.intensity;
        }
    }
}
