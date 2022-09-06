package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class P_15685_1 {
    static int[][] board;


    /**
     * 일단 큰 그림
     * 사각형 만들기
     * 드래곤 커브 그리면서 꼭짓점 표시하기
     * 처음부터 사각형 돌면서 1 x 1 정사각형의 네 꼭짓점이 표시되어 있으면 답에 한개씩 추가하기
     *
     * 드래곤 커브 객체가 있어야 할테고, 얘네가 어떻게 표시하게 할지? -> 끝점을 기준으로 무언가 식을 세워야 할 듯
     * 끝 점은 선분을 타고 이동. 끝 점 구할라면 결국 선도 다 표시해야 하나. 근데 선이 다른 애들이랑 겹칠 텐데.
     * 끝점은 이제보니 0세대일 때만 빼고 맨 처음 시작점이 90도 회전했을 때구나. 왜그럴까? 회전시키면 회전시킨 점을 기준으로 접합되는데 그렇게 되면 시작점이 끝점이 되는게 당연한 듯
     * 끝점을 기준으로 다른 점들을 어떻게 90도 회전시킬까
     * 끝점을 기준으로 대각선이나 직각에 있는 애들은 간단하다 그렇지 않은 애들은? 끝점을 기준으로 쭈욱 내린 후 같은 행에 있는에 와의 차이만큼을 기억해서 그 노으 ㄹ90도 돌린 후 그 차이만큼?
     *
     * 규칙 찾았다.
     * 끝점을 기준으로  +x +y 였던 애는 90도 돌리면 -y +x로 변함.
     *
     * -- 드래곤 커브 클래스
     * 시작점 위치, 끝 점 위치, 총 세대, 점들 리스트
     * 90도 돌릴 때는 끝점 빼고 모든 점에 대하여 점 - 끝점 해서 값 (x,y)이면 점에다가 (-y, x) 해준 값으로 변경 이 때 격자 벗어나는 것은 주어지지 않음
     * 시작점에다가 연산한 후 끝 점 업데이트
     *
     * -- 사각형 만들기
     * x와 y를 바꿔서 넣어주기
     *
     * -- 총 갯수 구하기
     * board의 0,0 부터 n - 1 m - 1까지 현재, 아래, 오른대각아래, 오른 이렇게 마킹 검사 해서 다 있으면 총 갯수에 추가
     *
     * -- 시간 복잡도
     * 드래곤 커브가 마킹하는 데 걸리는 시간 0세대 1 + 1 1세대 2 + 1 2세대 4 + 1 3세대 8 + 1 총 k세대 이므로 2^k + 1 드래곤 커브 총갯수 N 즉 => N * 2^k + 사각형 찾기 100 x 100 x 4는 상수 총연산 횟수 최대 20000번
     *
     * -- 엣지 케이스
     * x,y 0, 0 방향 0 세대 0 일 때 값 0
     *
     *
     */
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int curveCnt = Integer.parseInt(st.nextToken());

        board = new int[101][101];
        for (int i = 0; i < curveCnt; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            int gen = Integer.parseInt(st.nextToken());
            DragonCurve dc = new DragonCurve(x,y,dir,gen);

            dc.mark();
        }

        int squareCount = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                squareCount += checkSquare(i, j);
            }
        }

        System.out.println(squareCount);
    }

    private static int checkSquare(int i, int j) {
        boolean con1 = board[i + 1][j] == 1;
        boolean con2 = board[i][j] == 1;
        boolean con3 = board[i][j + 1] == 1;
        boolean con4 = board[i + 1][j + 1] == 1;

        if( con1 && con2 && con3 && con4)
            return 1;

        return 0;
    }


    private static class DragonCurve {

        Integer[] start;
        Integer[] end;
        int gen;
        List<Integer[]> points;

        static int[] dx = {1, 0, -1, 0};
        static int[] dy = {0, -1, 0, 1};

        public DragonCurve(int x, int y, int dir, int gen) {
            start = new Integer[]{x, y};
            int endX = start[0] + dx[dir];
            int endY = start[1] + dy[dir];
            end = new Integer[]{endX, endY};
            this.gen = gen;
            points = new ArrayList<>(Arrays.asList(start,end));
        }

        public void mark() {
            board[start[1]][start[0]] = 1;
            board[end[1]][end[0]] = 1;
            for (int i = 0; i < gen; i++) {
                doMark();
            }
        }

        private void doMark() {
            Integer[] standard = end;
            int size = points.size();
            for (int i = 0; i < size; i++) {
                if (Arrays.deepEquals(end, points.get(i))) continue;

                Integer[] point = points.get(i);
                point = rotate(point, standard);
                board[point[1]][point[0]] = 1;

                points.add(point);

                if (Arrays.deepEquals(start, points.get(i))) {
                    end = point;
                }
            }

        }

        private Integer[] rotate(Integer[] point, Integer[] standard) {
            Integer[] newPoint = new Integer[2];

            int diffX = point[0] - standard[0];
            int diffY = point[1] - standard[1];

            newPoint[0] = standard[0] - diffY;
            newPoint[1] = standard[1] + diffX;
            return newPoint;
        }
    }
}
