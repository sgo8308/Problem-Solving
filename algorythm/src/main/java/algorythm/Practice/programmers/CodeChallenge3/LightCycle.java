package algorythm.Practice.programmers.CodeChallenge3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class LightCycle {
    public static void main(String[] args) {
        String[] grid = {"SL", "LR"};
//        String[] grid = {"S"};
//        String[] grid = {"R", "R"};
        System.out.println(Arrays.toString(solution(grid)));
    }

    static String[] s_grid;
    static public int[] solution(String[] grid) {
        ArrayList<Integer> answerList = new ArrayList<>();
        s_grid = grid;
        int[] directions = {0, 1, 2, 3};

        int[][][] visited = new int[s_grid.length][s_grid[0].length()][4];

        for (int i = 0; i < s_grid.length; i++) {
            for (int j = 0; j < s_grid[0].length(); j++) {
                for (int dir : directions) {
                    if(visited[i][j][dir] == 0){
                        int[] coord = {i, j};
                        int count = 0;
                        while(visited[coord[0]][coord[1]][dir] != 1){
                            visited[coord[0]][coord[1]][dir] = 1;
                            coord = move(coord, dir);
                            dir = rotate(s_grid[coord[0]].charAt(coord[1]), dir);
                            count++;
                        }
                        answerList.add(count);
                    }
                }
            }
        }
        return answerList.stream().sorted().mapToInt(t->t).toArray();
    }

    static int[] move(int[] nowCoord, int direction){
        int[] dx = {0, 1, 0, -1};
        int[] dy = {-1, 0, 1, 0};
        int[] nextCoord = new int[2];
        nextCoord[0] = (nowCoord[0] + dx[direction] + s_grid.length) % s_grid.length;
        nextCoord[1] = (nowCoord[1] + dy[direction] + s_grid[0].length()) % s_grid[0].length();
        return  nextCoord;
    }

    static int rotate(char letter, int previousDir){
        int nextDir = previousDir;

        if(letter == 'L') nextDir++;
        else if(letter == 'R') nextDir--;

        nextDir = nextDir < 0 ? 3 : nextDir % 4;
        return nextDir;
    }
}
