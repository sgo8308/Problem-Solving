package algorythm.Practice.Baekjoon;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P_17143_1 {

    /**
     * 가장 큰 문제는 상어가 이동하는 것을 그대로 시뮬레이션하면 안되고 구현해내야 한다는 것 어떻게 할까
     */

    static int r, c, m;
    static Shark[][] ocean;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r= Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        ocean = new Shark[r][c];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int speed = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken()) - 1;
            int size = Integer.parseInt(st.nextToken());
            ocean[x][y] = new Shark(x, y, speed, dir, size);
        }

        SharkPosition sharkPosition = new SharkPosition();

        int allSharkSize = 0;
        for (int i = 0; i < c; i++) {
            allSharkSize += Fisher.fishing(i);

            Arrays.stream(ocean)
                    .flatMap(Arrays::stream)
                    .forEach(shark -> {if(shark != null) shark.move(sharkPosition);});

            Shark[][] newOcean = new Shark[r][c];

            Arrays.stream(ocean)
                    .flatMap(Arrays::stream)
                    .forEach(shark -> {
                        if (shark != null) {
                            int x = shark.x;
                            int y = shark.y;

                            if (newOcean[x][y] == null || (newOcean[x][y] != null && newOcean[x][y].size < shark.size)) {
                                newOcean[x][y] = shark;
                            }
                        }});

            ocean = newOcean;
        }

        System.out.println(allSharkSize);
    }

    static class Fisher {

        public static int fishing(int line) {
            int size = 0;
            for (int i = 0; i < r; i++) {
                if (ocean[i][line] != null) {
                    size = ocean[i][line].size;
                    ocean[i][line] = null;
                    break;
                }
            }

            return size;
        }
    }


    static class Shark {

        int x, y, speed,dir ,size;
        public Shark(int x, int y, int speed, int dir, int size) {
            this.x = x;
            this.y = y;
            this.speed = speed;
            this.dir = dir;
            this.size = size;
        }

        public Shark(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }

        public void move(SharkPosition sharkPosition) {
            Shark pos = sharkPosition.getPosition(x, y, speed, dir);

            this.x = pos.x;
            this.y = pos.y;
            this.dir = pos.dir;
        }
    }

    static class SharkPosition {

        Shark[][][][] sharkPositions = new Shark[r][c][4][];

        public SharkPosition() {
            init();
        }

        private void init() {
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    sharkPositions[i][j][0] = init(i, j, (r - 1) * 2, 0);
                    sharkPositions[i][j][1] = init(i, j, (r - 1) * 2, 1);
                    sharkPositions[i][j][2] = init(i, j, (c - 1) * 2, 2);
                    sharkPositions[i][j][3] = init(i, j, (c - 1) * 2, 3);
                }
            }
        }

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        private Shark[] init(int row, int col, int arrSize, int dir) {
            Shark[] pos = new Shark[arrSize];

            pos[0] = new Shark(row, col, dir);

            for (int k = 1; k < arrSize; k++) {
                if (!isInRange(row + dx[dir], col + dy[dir])) {
                    dir = changeDirection(dir);
                }

                row += dx[dir];
                col += dy[dir];

                pos[k] = new Shark(row, col ,dir);
            }
            return pos;
        }

        private int changeDirection(int dir) {
            if(dir == 0 || dir == 2) dir++;
            else if(dir == 1 || dir == 3) dir--;
            return dir;
        }

        private boolean isInRange(int row, int col){
          return row >= 0 && row < r && col >= 0 && col < c;
        }

        public Shark getPosition(int x, int y, int speed, int dir) {
            Shark[] cand = sharkPositions[x][y][dir];

            return cand[speed % cand.length];
        }
    }
}
