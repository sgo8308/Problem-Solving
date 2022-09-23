package algorythm.Practice.programmers.step3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindWayGame {

    public static void main(String[] args) {
        FindWayGame f = new FindWayGame();
        int[][] nodeinfo = {{5, 3}, {11, 5}, {13, 3}, {3, 5}, {6, 1}, {1, 3}, {8, 6}, {7, 2},
                {2, 2}};

        int[][] solution = f.solution(nodeinfo);
        System.out.println(solution);
    }

    public int[][] solution(int[][] nodeinfo) {
        int[][] nodeinfos = new int[nodeinfo.length][3];

        for (int i = 0; i < nodeinfo.length; i++) {
            nodeinfos[i] = new int[]{nodeinfo[i][0], nodeinfo[i][1], i + 1};
        }

        Arrays.sort(nodeinfos, (node1, node2) -> {
            if (node1[1] == node2[1]) {
                return Integer.compare(node1[0], node2[0]);
            }
            return -Integer.compare(node1[1], node2[1]);
        });

        Node bt = new Node(nodeinfos[0]);
        for (int i = 1; i < nodeinfos.length; i++) {
            bt.insert(nodeinfos[i]);
        }
        List<Integer> preOrder = new ArrayList<>();
        List<Integer> postOrder = new ArrayList<>();
        bt.preOrder(preOrder, nodeinfo.length);
        bt.postOrder(postOrder, nodeinfo.length);

        return new int[][]{preOrder.stream().mapToInt(x -> x).toArray(),
                postOrder.stream().mapToInt(x -> x).toArray()};
    }

    class Node {

        int num;
        int x;
        Node left = null;
        Node right = null;

        public Node(int[] nodeinfo) {
            this.num = nodeinfo[2];
            this.x = nodeinfo[0];
        }

        public void insert(int[] nodeinfo) {
            if (this.x > nodeinfo[0]) {
                if (left == null) {
                    left = new Node(nodeinfo);
                } else {
                    left.insert(nodeinfo);
                }
            } else {
                if (right == null) {
                    right = new Node(nodeinfo);
                } else {
                    right.insert(nodeinfo);
                }
            }
        }

        void preOrder(List<Integer> preOrder, int limit) {
            if (limit == preOrder.size()) return;

            preOrder.add(num);

            if (left != null) left.preOrder(preOrder, limit);
            if (right != null) right.preOrder(preOrder, limit);
        }

        void postOrder(List<Integer> postOrder, int limit) {
            if (limit == postOrder.size()) return;

            if (left != null) left.postOrder(postOrder, limit);
            if (right != null) right.postOrder(postOrder, limit);

            postOrder.add(num);
        }
    }

}
