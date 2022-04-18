package algorythm.Practice.Leetcode;

import java.util.PriorityQueue;

public class KClosestPointsToOrigin_1 {

    public static void main(String[] args) {
        int[][] p = {{3, 3}, {5, -1}, {-2, 4}};
        System.out.println(kClosest(p, 2));
    }

    static public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((x1, x2) -> {
            int x1Dist = x1[0] * x1[0] + x1[1] * x1[1];
            int x2Dist = x2[0] * x2[0] + x2[1] * x2[1];
            return Integer.compare(x1Dist, x2Dist);
        });

        for (int i = 0; i < points.length; i++) {
            pq.add(points[i]);
            if (pq.size() > k) {
                pq.poll();
            }
        }

        return pq.stream().toArray(int[][]::new);
    }
}
