package algorythm.Practice.programmers.step2;

import jdk.swing.interop.SwingInterOpUtils;

import java.util.*;

public class GameMap {
    public static void main(String[] args) {
        Integer[] s = {1,2,3};
        System.out.println(Arrays.deepEquals(s, new Integer[] {1,2,3}));
//        int[][] maps = {{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,1},{0,0,0,0,1}};
        int[][] maps = {{1,1,1,1,1},{1,1,1,1,1},{1,1,1,1,1},{1,1,1,1,1},{1,1,1,1,1}};
//        System.out.println(solution(maps));

        int[] t = new int[100];
        int[][] y = new int[100][100];

        for (int i = 0; i < 100; i++) {
            t[i] = 1;
        }
        for (int i = 0; i < 100; i++) {
            y[i] = t.clone();
        }
        System.out.println("----");
        System.out.println(solution(y));
    }

    static public int solution(int[][] maps) {
        return bfs(maps);
    }

    static int bfs(int[][] maps){
        int[] dx = {1, -1 ,0, 0};
        int[] dy = {0, 0, 1, -1};
        Queue<Integer[]> queue = new LinkedList<>();
        queue.add(new Integer[] {0, 0});
        int distance = 0;

        while(!queue.isEmpty()){
            distance++;

            int qSize = queue.size();
            for (int i = 0; i < qSize; i++) {
                Integer[] nowPos = queue.poll();

                if(Arrays.deepEquals(nowPos, new Integer[] {maps.length - 1 , maps[0].length - 1}))
                    return distance;

                maps[nowPos[0]][nowPos[1]] = 0;

                for (int j = 0; j < 4; j++) {
                    int xx = nowPos[0] + dx[j];
                    int yy = nowPos[1] + dy[j];

                    if(xx >= 0 && yy >= 0 && xx < maps.length && yy < maps[0].length && maps[xx][yy] == 1){
                        maps[xx][yy] = 0;
                        queue.add(new Integer[] {xx, yy});
                    }
                }
            }

            System.out.println("dist = " + distance);
        }

        return -1;
    }
}
