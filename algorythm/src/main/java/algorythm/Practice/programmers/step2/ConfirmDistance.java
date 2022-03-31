package algorythm.Practice.programmers.step2;
import java.util.*;

public class ConfirmDistance {
    public static void main(String[] args) {
        String[][] places =
                {{"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"},
                        {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"},
                        {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"},
                        {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"},
                        {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}};

        System.out.println(Arrays.toString(solution(places)));
    }

    public static int[] solution(String[][] places) {
        int[] answer = {1, 1, 1, 1, 1};

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    if(places[i][j].substring(k, k + 1).equals("P")){
                        answer[i] = confirm(places[i], j, k);
                    }
                }
            }
        }

        return answer;
    }
    //1. 모든 P마다 맨해튼 거리 2마다 다른 P가 있는지 확인한다.(DFS 이용)
    public static int confirm(String[] places, int row, int column){
        int[] dx1 = {1, -1, 0, 0};
        int[] dy1 = {0, 0, 1, -1};
        int[] dx2 = {1, -1, 1, -1};
        int[] dy2 = {1, 1, -1, -1};
        int[] dx3 = {2, -2, 0, 0};
        int[] dy3 = {0, 0, 2, -2};

        //동서남북으로 먼저 보는데 다른 P가 존재하면 바로 0을 RETURN
        for (int i = 0; i < 4; i++) {
            int xx = row + dx1[i];
            int yy = column + dy1[i];

            if (xx >= 0 && xx < 5 && yy >= 0 && yy < 5) {
                if(places[xx].substring(yy, yy + 1).equals("P")){
                    return 0;
                }
            }
        }

        //대각선에 존재할 경우 그 사이 2개의 지점에 X가 있는지 확인
        for (int i = 0; i < 4; i++) {
            int xx = row + dx2[i];
            int yy = column + dy2[i];

            if (xx >= 0 && xx < 5 && yy >= 0 && yy < 5 && places[xx].substring(yy, yy + 1).equals("P")) {
                int[] diff = {xx - row, yy - column};
                int[] move1 = {diff[0], 0};
                int[] move2 = {0, diff[1]};

                if(!places[row + move1[0]].substring(column + move1[1], column + move1[1] + 1).equals("X")
                        || !places[row + move2[0]].substring(column + move2[1], column + move2[1] + 1).equals("X")){
                    return 0;
                }
            }
        }

        //동서남북이지만 2칸 떨어져 있을 경우 그 사이 1개의 지점에 X가 있는지 확인
        for (int i = 0; i < 4; i++) {
            int xx = row + dx3[i];
            int yy = column + dy3[i];

            if (xx >= 0 && xx < 5 && yy >= 0 && yy < 5) {
                if(places[xx].substring(yy, yy + 1).equals("P")){
                    int[] diff = {xx - row, yy - column};
                    int[] move = {(int)(1.0/2.0 * diff[0]), (int)(1.0/2.0 * diff[1])};

                    if(!places[row + move[0]].substring(column + move[1], column + move[1] + 1).equals("X")){
                        return 0;
                    }
                }
            }
        }

        return 1;
    }
}
