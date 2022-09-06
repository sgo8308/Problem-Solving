package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P_5373_1 {

    static int n;
    static int m;
    static int[][] board;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int tc = Integer.parseInt(st.nextToken());

        for (int i = 0; i < tc; i++) {
            Cube cube = new Cube();
            st = new StringTokenizer(br.readLine());
            int turnCnt = Integer.parseInt(st.nextToken());

            for (int j = 0; j < turnCnt; j++) {
                st = new StringTokenizer(br.readLine());
                String line = st.nextToken();
                char dir = line.charAt(0);
                char subDir = line.charAt(1);

                cube.turnLayer(dir, subDir);
            }

            StringBuilder sb = new StringBuilder();
            sb.append(cube.back[5].color);
            sb.append(cube.back[4].color);
            sb.append(cube.back[3].color);
            sb.append("\n");
            sb.append(cube.left[4].color);
            sb.append('w');
            sb.append(cube.right[4].color);
            sb.append("\n");
            sb.append(cube.front[3].color);
            sb.append(cube.front[4].color);
            sb.append(cube.front[5].color);
            System.out.println(sb);
        }
    }

    static char[] upColor = {'g', 'o', 'b', 'r'};
    static char[] downColor = {'g', 'r', 'b', 'o'};
    static char[] leftColor = {'o', 'w', 'r', 'y'};
    static char[] rightColor = {'r', 'w', 'o', 'y'};
    static char[] frontColor = {'g', 'w', 'b', 'y'};
    static char[] backColor = {'b', 'w', 'g', 'y'};
    static class Cube {
        Point[] up = new Point[12];
        Point[] down = new Point[12];
        Point[] left = new Point[12];
        Point[] right = new Point[12];
        Point[] front = new Point[12];
        Point[] back = new Point[12];

        Cube() {
            allocate(up, upColor);
            allocate(down, downColor);
            allocate(left, leftColor);
            allocate(right, rightColor);
            allocate(front, frontColor);
            allocate(back, backColor);
        }

        void allocate(Point[] layer, char[] colors) {
            for (int i = 0, idx = 0; i < 4; i++) {
                for (int j = 0; j < 3; j++) {
                    layer[idx++] = new Point(colors[i]);
                }
            }
        }

        void turnLayer(char dir, char subDir) {
            switch (dir) {
                case 'U':
                    move(up, subDir);
                    break;
                case 'D':
                    move(down, subDir);
                    break;
                case 'L':
                    move(left, subDir);
                    break;
                case 'R':
                    move(right, subDir);
                    break;
                case 'F':
                    move(front, subDir);
                    break;
                case 'B':
                    move(back, subDir);
                    break;
            }

        }
        // + 일 때는 --->> ,  - 일 때는 <<--- 방향으로 3칸씩 옮기기

        private void move(Point[] layer, char subDir) {
            if(subDir == '+'){
                char[] temp = {layer[0].color, layer[1].color, layer[2].color};
                for (int i = 9; i >= 0 ; i -= 3) {
                    for (int j = i; j < 3; j++) {
                        layer[(j + 3) % 12].color = layer[j].color;

                    }
                }
                for (int i = 0; i < 3; i++) {
                    layer[(i + 3) % 12].color = temp[i];
                }
            }else{
                char[] temp = {layer[9].color, layer[10].color, layer[11].color};
                for (int i = 0; i < 12 ; i += 3) {
                    for (int j = i; j < 3; j++) {
                        layer[(j + 9) % 12].color = layer[j].color;
                    }
                }
                for (int i = 6; i < 9; i++) {
                    layer[(i + 9) % 12].color = temp[i - 6];
                }
            }
        }
    }

    class Layer {

        Point[] points = new Point[9];
        Point[] top;
        Point[] left;
        Point[] right;
        Point[] bottom;

        Layer(char color) {
            for (int i = 0; i < 9; i++) {
                points[i] = new Point(color);
            }

            top = new Point[]{points[0], points[1], points[2]};
            left = new Point[]{points[0], points[3], points[6]};
            right = new Point[]{points[2], points[5], points[8]};
            bottom = new Point[]{points[6], points[7], points[8]};
        }
    }

    static class Point {

        char color;

        public Point(char color) {
            this.color = color;
        }
    }


}


