package algorythm.Practice.programmers.step2;

import java.util.ArrayList;

public class HalfElectricity {

    public static void main(String[] args) {
//        int[][] wires = {{1,3},{2,3},{3,4},{4,5},{4,6},{4,7},{7,8},{7,9}};
//        System.out.println(solution(9,wires));

        int[][] wires = {{1,2}};
        System.out.println(solution(2,wires));
    }
    static int top1Num = 0;

    static public int solution(int n, int[][] wires) {
        int minTopNum = Integer.MAX_VALUE;

        for (int i = 0; i < wires.length; i++) {
            boolean[] visited = new boolean[wires.length];
            visited[i] = true;
            top1Num = 0;

            dfs(1, wires, visited);

            minTopNum = Math.min(minTopNum, Math.abs(top1Num - (n - top1Num)));
        }

        return minTopNum;
    }

    private static void dfs(int nowPos, int[][] wires, boolean[] visited) {
        top1Num++;
        for (int i = 0; i < wires.length; i++) {
            if(!visited[i] && (wires[i][0] == nowPos || wires[i][1] == nowPos) ) {
                int nextPos = (wires[i][0] == nowPos) ? wires[i][1] : wires[i][0];

                visited[i] = true;

                dfs(nextPos, wires, visited);
            }
        }
    }
}
