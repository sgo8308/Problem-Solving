package algorythm.Practice.programmers.step2;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class weekly10 {
    public static void main(String[] args) {
        double x = 4 / 2 ;
        int y = 2;
        System.out.println("ds".repeat(5));
        System.out.println(x);
        System.out.println(y);
        System.out.println(x == y);
    }

    static public String[] solution(int[][] line) {
        //교점 구하기
        int[][] intersectionPoints = getIntersectionPoints(line);
        //가장 위 아 왼 오 에 있는 점들 구하기
        int[] maxCoord = getMaxCoord(intersectionPoints);
        //사각형 판 만들기
        int width = maxCoord[3] - maxCoord[2] + 1;
        int height = maxCoord[0] - maxCoord[1] + 1;
        String[][] square = new String[height][width];
        for (int i = 0; i < square.length; i++) {
            for (int j = 0; j < square[0].length; j++) {
                square[i][j] = ".";
            }
        }
        //가장 위 왼 기준으로 점들의 좌표 새로 구하기
        int[][] newIntersectionPoints = getNewIntersectionPoints(intersectionPoints, maxCoord[0], maxCoord[2]);
        //사각형 판 위에 좌표 찍기
        for (int i = 0; i < newIntersectionPoints.length; i++) {
            int[] point = newIntersectionPoints[i];
            square[point[0]][point[1]] = "*";
        }
        //출력하기
        String[] answer = new String[square.length];
        for (int i = 0; i < square.length; i++) {
            StringBuffer s = new StringBuffer();
            for (int j = 0; j < square[0].length; j++) {
                s.append(square[i][j]);
            }
            answer[i] = s.toString();
        }

        return answer;
    }

    static int[][] getIntersectionPoints(int[][] line){
        ArrayList<Integer[]> points = new ArrayList<>();

        for (int i = 0; i < line.length - 1; i++) {
            for (int j = i + 1; j < line.length; j++) {
                Integer[] p = getIntersectionPoint(line[i], line[j]);
                if(p.length == 2)
                    points.add(p);
            }
        }
        int[][] p = new int[points.size()][points.get(0).length];
        for (int i = 0; i < points.size(); i++) {
            for (int j = 0; j < points.get(0).length; j++) {
                p[i][j] = points.get(i)[j];
            }
        }
        return p;
    }

    static Integer[] getIntersectionPoint(int[] line1, int[] line2){
        Integer[] point = {};
        long a = line1[0], b = line1[1], e = line1[2];
        long c = line2[0], d = line2[1], f = line2[2];

        if(a*d - b*c == 0) return point;

        if((b*f - e*d) % (a*d - b*c) == 0 && (e*c - a*f) % (a*d - b*c) == 0){
            long x = (b*f - e*d) / (a*d - b*c);
            long y = (e*c - a*f) / (a*d - b*c);
            int xx = (int)x;
            int yy = (int)y;
            Integer[] p = {xx,yy};
            return p;
        }
        return point;
    }

    static int[] getMaxCoord(int[][] intersectionPoints){
        int top = Integer.MIN_VALUE;
        int bot = Integer.MAX_VALUE;
        for (int i = 0; i < intersectionPoints.length; i++) {
            top = Math.max(top, intersectionPoints[i][1]);
            bot = Math.min(bot, intersectionPoints[i][1]);
        }

        int left = Integer.MAX_VALUE;
        int right = Integer.MIN_VALUE;
        for (int i = 0; i < intersectionPoints.length; i++) {
            left = Math.min(left, intersectionPoints[i][0]);
            right = Math.max(right, intersectionPoints[i][0]);
        }

        int[] maxs = {top,bot,left,right};
        return maxs;
    }

    static int[][] getNewIntersectionPoints(int[][] intersectionPoints, int top, int left){
        int[][] newIntersectionPoints = new int[intersectionPoints.length][intersectionPoints[0].length];
        for (int i = 0; i < intersectionPoints.length; i++) {
            int[] point = intersectionPoints[i];
            int x = top - point[1];
            int y = point[0] - left;
            int[] newPoint = {x, y};

            newIntersectionPoints[i] = newPoint;
        }
        return newIntersectionPoints;
    }
}
