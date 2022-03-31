package algorythm.Practice.programmers.step2;

import java.util.LinkedList;
import java.util.Queue;

public class GameMap_2 {
    public static void main(String[] args) {
        int[][] maps = {{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,1},{0,0,0,0,1}};
//        int[][] maps = {{1,1,1,1,1},{1,1,1,1,1},{1,1,1,1,1},{1,1,1,1,1},{1,1,1,1,1}};
        System.out.println(solution(maps));
    }

    static public int solution(int[][] maps) {
        int[] dx = {1, -1, 0, 0,};
        int[] dy = {0, 0, 1, -1};
        Queue<Integer[]> q = new LinkedList<>();
        q.add(new Integer[] {0, 0});

        while(!q.isEmpty()){
            Integer[] coord = q.poll();

            if(coord[0] == maps.length -1 && coord[1] == maps[0].length - 1)
                return maps[coord[0]][coord[1]];

            for (int i = 0; i < 4; i++) {
                int nextRow = coord[0] + dx[i];
                int nextCol = coord[1] + dy[i];

                if(nextRow >= 0 && nextRow < maps.length && nextCol >= 0 && nextCol < maps[0].length
                        && maps[nextRow][nextCol] == 1){
                    q.add(new Integer[] {nextRow, nextCol});
                    maps[nextRow][nextCol] = maps[coord[0]][coord[1]] + 1;
                }
            }
        }

        return -1;
    }

}
