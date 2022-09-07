package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class P_5373_2 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int tc = Integer.parseInt(st.nextToken());

        for (int i = 0; i < tc; i++) {
            Cube cube = new Cube();
            st = new StringTokenizer(br.readLine());
            int turnCnt = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < turnCnt; j++) {
                String line = st.nextToken();
                char dir = line.charAt(0);
                char subDir = line.charAt(1);

                cube.turn(dir, subDir);
            }

            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    sb.append(cube.upSection[j][k].color);
                }
                sb.append("\n");
            }
            sb.deleteCharAt(sb.length() - 1);
            System.out.println(sb);
        }
    }

    static class Cube {

        Point[] upRound = new Point[12],
                downRound = new Point[12],
                leftRound = new Point[12],
                rightRound = new Point[12],
                frontRound = new Point[12],
                backRound = new Point[12];

        Point[][] upSection = new Point[3][3],
                downSection = new Point[3][3],
                leftSection = new Point[3][3],
                rightSection = new Point[3][3],
                frontSection = new Point[3][3],
                backSection = new Point[3][3];

        List<Point[][]> sections = Arrays.asList(upSection, downSection, leftSection, rightSection,
                frontSection, backSection);
        char[] colors = {'w', 'y', 'g', 'b', 'r', 'o'};

        Cube() {
            initSection();
            initRound();
        }

        private void initSection() {
            for (int i = 0; i < sections.size(); i++) {
                Point[][] section = sections.get(i);
                for (int j = 0; j < 3; j++) {
                    for (int k = 0; k < 3; k++) {
                        section[j][k] = new Point(colors[i]);
                    }
                }
            }
        }

        private void initRound() {
            Point[] forUp = {
                    leftSection[0][2], leftSection[0][1], leftSection[0][0],
                    backSection[0][2], backSection[0][1], backSection[0][0],
                    rightSection[0][2], rightSection[0][1], rightSection[0][0],
                    frontSection[0][2], frontSection[0][1], frontSection[0][0],
            };

            upRound = forUp;

            Point[] forDown = {
                    leftSection[2][0], leftSection[2][1], leftSection[2][2],
                    frontSection[2][0], frontSection[2][1], frontSection[2][2],
                    rightSection[2][0], rightSection[2][1], rightSection[2][2],
                    backSection[2][0], backSection[2][1], backSection[2][2]
            };

            downRound = forDown;

            Point[] forLeft = {
                    backSection[2][2], backSection[1][2], backSection[0][2],
                    upSection[0][0], upSection[1][0], upSection[2][0],
                    frontSection[0][0], frontSection[1][0], frontSection[2][0],
                    downSection[0][0], downSection[1][0], downSection[2][0]
            };

            leftRound = forLeft;

            Point[] forRight = {
                    frontSection[2][2], frontSection[1][2], frontSection[0][2],
                    upSection[2][2], upSection[1][2], upSection[0][2],
                    backSection[0][0], backSection[1][0], backSection[2][0],
                    downSection[2][2], downSection[1][2], downSection[0][2]
            };

            rightRound = forRight;

            Point[] forFront = {
                    leftSection[2][2], leftSection[1][2], leftSection[0][2],
                    upSection[2][0], upSection[2][1], upSection[2][2],
                    rightSection[0][0], rightSection[1][0], rightSection[2][0],
                    downSection[0][2], downSection[0][1], downSection[0][0]
            };

            frontRound = forFront;

            Point[] forBack = {
                    rightSection[2][2], rightSection[1][2], rightSection[0][2],
                    upSection[0][2], upSection[0][1], upSection[0][0],
                    leftSection[0][0], leftSection[1][0], leftSection[2][0],
                    downSection[2][0], downSection[2][1], downSection[2][2]
            };

            backRound = forBack;
        }

        private void turn(char dir, char subDir) {
            switch (dir) {
                case 'U':
                    rotateSection(upSection, subDir);
                    turnRound(upRound, subDir);
                    break;
                case 'D':
                    rotateSection(downSection, subDir);
                    turnRound(downRound, subDir);
                    break;
                case 'L':
                    rotateSection(leftSection, subDir);
                    turnRound(leftRound, subDir);
                    break;
                case 'R':
                    rotateSection(rightSection, subDir);
                    turnRound(rightRound, subDir);
                    break;
                case 'F':
                    rotateSection(frontSection, subDir);
                    turnRound(frontRound, subDir);
                    break;
                case 'B':
                    rotateSection(backSection, subDir);
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

        private void rotateSection(Point[][] section, char subDir) {
            if (subDir == '+') {
                rotate90(section);
            } else {
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


