package algorythm.Practice.programmers.step2;

public class VisitLength {
    public static void main(String[] args) {
        String d = "LULLLLLLU";
        System.out.println(solution(d));
    }

    static public int solution(String dirs) {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        int firstStep = 0;
        int[] now = {5,5};
        int[][][] grid = new int[11][11][4];
        for (int i = 0; i < dirs.length(); i++) {
            int direction = getDirection(dirs.charAt(i));
            int xx = now[0] + dx[direction];
            int yy = now[1] + dy[direction];

            if(xx < 0 || xx > 10 || yy < 0 || yy > 10) continue;

            if(grid[now[0]][now[1]][direction] != 1 && grid[xx][yy][flipDirection(direction)] != 1){
                firstStep++;
                grid[now[0]][now[1]][direction] = 1;
            }

            now[0] = xx;
            now[1] = yy;
        }

        return firstStep;
    }

    static int getDirection(char direction){
        if(direction == 'U') return 0;
        if(direction == 'D') return 1;
        if(direction == 'R') return 2;
        if(direction == 'L') return 3;
        return 0;
    }

    static int flipDirection(int direction){
        if(direction == 0) return 1;
        if(direction == 1) return 0;
        if(direction == 2) return 3;
        if(direction == 3) return 2;
        return 0;
    }
}
