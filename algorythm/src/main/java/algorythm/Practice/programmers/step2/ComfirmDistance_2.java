package algorythm.Practice.programmers.step2;

import java.util.Arrays;

public class ComfirmDistance_2 {
    public static void main(String[] args) {
        String[][] place = {{"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"},
        {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"},
                {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"},
        {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"},
                {"OOPOO", "OPOOO", "OOOOO", "OOOOO", "OOOOO"}};

        System.out.println(Arrays.toString(solution(place)));
    }
    static int isDist;
    public static int[] solution(String[][] places) {
        int[] result = new int[5];

        for (int i = 0; i < places.length; i++) {
            String[] place = places[i];
            boolean[][] visited = new boolean[5][5];

            isDist = 1;

            place:
            for (int j = 0; j < visited.length; j++) {
                for (int k = 0; k < visited.length; k++) {
                    if(place[j].charAt(k) != 'P') continue ;

                    visited[j][k] = true;
                    dfss(j, k, place, visited, 0);

                    if(isDist == 0) break place;
                }
            }

            result[i] = isDist;
        }

        return result;
    }

    static void dfs(int startRow, int startCol, int row, int col, String[] place, boolean[][] visited, int depth){
        int[] dx = {1, -1, 0, 0, 1, 1, -1, -1, 2, -2 ,0 ,0 };
        int[] dy = {0 , 0, 1, -1, 1, -1, 1, -1,0, 0, 2, -2 };

        if(depth == 0){
            for (int i = 0; i < dx.length; i++) {
                int xx = row + dx[i];
                int yy = col + dy[i];

                if(xx >= 0 && yy >= 0 && xx < 5 && yy < 5){
                    visited[xx][yy] = true;
                    if(i < 4) dfs(startRow, startCol, xx, yy, place, visited, depth + 1);
                    else dfs(startRow, startCol, xx, yy, place, visited, depth + 2);
                    visited[xx][yy] = false;
                }
            }
        }else if(depth == 1){
            if(place[row].charAt(col) == 'P') isDist = 0;

        }else if(depth == 2){
            if(place[row].charAt(col) != 'P')
                return ;

            for (int i = 0; i < 4; i++) {
                int xx = row + dx[i];
                int yy = col + dy[i];

                if(xx >= 0 && yy >= 0 && xx < 5 && yy < 5
                        && isInDistance(startRow, startCol, xx, yy)){
                    visited[xx][yy] = true;
                    dfs(startRow, startCol, xx, yy, place, visited, depth + 1);
                }
            }
        }else if(depth == 3){
            if(place[row].charAt(col) == 'O') isDist = 0;
        }
    }

    static boolean isInDistance(int startRow, int startCol, int targetRow, int targetCol){
        return Math.abs(startRow - targetRow) + Math.abs(startCol - targetCol) <= 2;
    }

    static void dfss(int row, int col, String[] place, boolean[][] visited, int depth){
        if(depth > 2) return;

        if(depth > 0 && place[row].charAt(col) == 'P'){
            isDist = 0;
            return;
        }

        int[] dx = {1, -1, 0, 0};
        int[] dy = {0 , 0, 1, -1};

        for (int i = 0; i < 4; i++) {
            int xx = row + dx[i];
            int yy = col + dy[i];

            if(xx >= 0 && yy >= 0 && xx < 5 && yy < 5
                    && !visited[xx][yy] && place[xx].charAt(yy) != 'X'){
                visited[xx][yy] = true;
                dfss(xx,yy, place, visited, depth + 1);
                visited[xx][yy] = false;
            }
        }
    }
}
