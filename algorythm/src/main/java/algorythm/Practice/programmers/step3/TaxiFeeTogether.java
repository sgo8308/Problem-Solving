package algorythm.Practice.programmers.step3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class TaxiFeeTogether {

    public static void main(String[] args) {
        int[][] fares = {{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}};

        solutionDijekstra(6,4,6,2, fares);

    }

    static public int solutionFloid(int n, int s, int a, int b, int[][] fares) {
        int[][] minFees = new int[n + 1][n + 1];

        for (int i = 0; i < n; i++) {
            Arrays.fill(minFees[i], Integer.MAX_VALUE);
        }

        for (int i = 0; i < fares.length; i++) {
            int[] fare = fares[i];
            minFees[fare[0]][fare[1]] = fare[2];
            minFees[fare[1]][fare[0]] = fare[2];
        }

        for (int k = 1; k < n + 1; k++) {
            for (int i = 1; i < n + 1; i++) {
                for (int j = 1; j < n + 1; j++) {
                    if (minFees[i][j] > minFees[i][k] + minFees[k][j]) {
                        minFees[i][j] = minFees[i][k] + minFees[k][j];
                    }
                }
            }
        }

        int ret = Integer.MAX_VALUE;
        for (int i = 1; i < n + 1; i++) {
            int fee = minFees[s][i] + minFees[i][a] + minFees[i][b];
            ret = Math.min(ret, fee);
        }

        return ret;
    }

    public static void solutionDijekstra(int n, int s, int a, int b, int[][] fares) {
        Map<Integer, List<Integer[]>> map = new HashMap<>();

        for (int i = 0; i < fares.length; i++) {
            int start = fares[i][0];
            int end = fares[i][1];
            int fee = fares[i][2];

            map.putIfAbsent(start, new ArrayList<>());
            map.get(start).add(new Integer[]{end, fee});

            map.putIfAbsent(end, new ArrayList<>());
            map.get(end).add(new Integer[]{start, fee});
        }

        int[][] minDistArr = new int[n + 1][];

        int[] minDists = new int[n + 1];
        Arrays.fill(minDists, Integer.MAX_VALUE);

        PriorityQueue<Pair> q = new PriorityQueue<>();
        q.add(new Pair(s, 0));
        minDists[s] = 0;

        while (!q.isEmpty()){
            Pair nowN = q.poll();
            int nowNode = nowN.node;
            int distFromStart = nowN.dist;

            if(minDists[nowNode] < distFromStart) continue;

            for (Integer[] no : map.get(nowNode)) {
                int nextNode = no[0];
                int nextDist = no[1];

                if (minDists[nextNode] > nextDist + distFromStart){
                    minDists[nextNode] = nextDist + distFromStart;
                    q.add(new Pair(nextNode, minDists[nextNode]));
                }
            }
        }

        for (int i = 0; i < minDists.length; i++) {
            System.out.print(minDists[i] + " ");
        }
        System.out.println();
    }


    static class Pair implements Comparable<Pair>{
        int node;
        int dist;

        public Pair(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }

        @Override
        public int compareTo(Pair o) {
            return Integer.compare(this.dist, o.dist);
        }
    }

}
