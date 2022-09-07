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

                for (int k = 0; k < 12; k++) {
                    System.out.print(cube.leftRound[k].color + " ");
                }
                System.out.println();
                cube.turn(dir, subDir);
                for (int k = 0; k < 12; k++) {
                    System.out.print(cube.leftRound[k].color + " ");
                }
            }

            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    sb.append(cube.upSection[j][k].color);
                }
                sb.append("\n");
            }
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

        Point[] upRound = new Point[12];
        Point[] downRound = new Point[12];
        Point[] leftRound = new Point[12];
        Point[] rightRound = new Point[12];
        Point[] frontRound = new Point[12];
        Point[] backRound = new Point[12];

        Point[][] upSection;
        Point[][] downSection;
        Point[][] leftSection;
        Point[][] rightSection;
        Point[][] frontSection;
        Point[][] backSection;

        Cube() {
            allocate(upRound, upColor);
            allocate(downRound, downColor);
            allocate(leftRound, leftColor);
            allocate(rightRound, rightColor);
            allocate(frontRound, frontColor);
            allocate(backRound, backColor);

            Point[][] forUp = {
                    {backRound[5], backRound[4], backRound[3]},
                    {leftRound[4], new Point('w'), rightRound[4]},
                    {frontRound[3], frontRound[4], frontRound[5]}
            };

            upSection = forUp;

            Point[][] forDown = {
                    {frontRound[11], frontRound[10], frontRound[9]},
                    {leftRound[10], new Point('y'), rightRound[10]},
                    {backRound[9], backRound[10], backRound[11]}
            };

            downSection = forDown;

            Point[][] forLeft = {
                    {upRound[2], upRound[1], upRound[0]},
                    {backRound[7], new Point('g'), frontRound[1]},
                    {downRound[0], downRound[1], downRound[2]}
            };

            leftSection = forLeft;

            Point[][] forRight = {
                    {upRound[8], upRound[7], upRound[6]},
                    {frontRound[7], new Point('b'), backRound[1]},
                    {downRound[6], downRound[7], downRound[8]}
            };

            rightSection = forRight;

            Point[][] forFront = {
                    {upRound[11], upRound[10], upRound[9]},
                    {leftRound[7], new Point('r'), rightRound[1]},
                    {downRound[3], downRound[4], downRound[5]}
            };

            frontSection = forFront;

            Point[][] forBack = {
                    {upRound[5], upRound[4], upRound[3]},
                    {rightRound[7], new Point('o'), leftRound[1]},
                    {downRound[9], downRound[10], downRound[11]}
            };

            backSection = forBack;
        }

        void allocate(Point[] layer, char[] colors) {
            for (int i = 0, idx = 0; i < 4; i++) {
                for (int j = 0; j < 3; j++) {
                    layer[idx++] = new Point(colors[i]);
                }
            }
        }

        private void turn(char dir, char subDir) {
            switch (dir) {
                case 'U':
                    rotate(upSection, subDir);
                    turnRound(upRound, subDir);
                    break;
                case 'D':
                    rotate(downSection, subDir);
                    turnRound(downRound, subDir);
                    break;
                case 'L':
                    rotate(leftSection, subDir);
                    turnRound(leftRound, subDir);
                    break;
                case 'R':
                    rotate(rightSection, subDir);
                    turnRound(rightRound, subDir);
                    break;
                case 'F':
                    rotate(frontSection, subDir);
                    turnRound(frontRound, subDir);
                    break;
                case 'B':
                    rotate(backSection, subDir);
                    turnRound(backRound, subDir);
                    break;
            }
        }

        // + 일 때는 --->> ,  - 일 때는 <<--- 방향으로 3칸씩 옮기기
        private void turnRound(Point[] layer, char subDir) {
            for (int k = 0; k < 3; k++) {
                if (subDir == '+') {
                    char temp = layer[0].color;
                    for (int i = 11; i >= 0; i--) {
                        layer[(i + 1) % 12].color = layer[i].color;
                    }
                    layer[1].color = temp;
                } else {
                    char temp = layer[0].color;
                    for (int i = 1; i < 12; i++) {
                        layer[(i - 1) % 12].color = layer[i].color;
                    }
                    layer[11].color = temp;
                }
            }
        }

        private void rotate(Point[][] section, char subDir) {
            if (subDir == '+') {
               rotate90(section);
            }else {
                rotate270(section);
            }
        }

        static void rotate90(Point[][] section) {
            char[][] rotate = new char[3][3];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    rotate[i][j] = section[i][j].color;
                }
            }

            for (int i = 0; i < rotate.length; i++) {
                for (int j = 0; j < rotate[i].length; j++) {
                    section[i][j].color = rotate[3 - 1 - j][i];
                }
            }
        }

        static void rotate270(Point[][] section) {
            char[][] rotate = new char[3][3];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    rotate[i][j] = section[i][j].color;
                }
            }

            for (int i = 0; i < rotate.length; i++) {
                for (int j = 0; j < rotate[i].length; j++) {
                    section[i][j].color = rotate[j][3 - 1 - i];
                }
            }
        }
    }

    static class Point {

        char color;

        public Point(char color) {
            this.color = color;
        }
    }
}


