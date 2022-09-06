package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class P_15683_1 {

    static int n;
    static int m;
    static int[][] board;

    static int min = Integer.MAX_VALUE;

    /**
     * 일단 이 문제는 모든 경우의 수를 다 구한 후 매 경우의 수마다 사각지대를 구해서 그 중 최소를 찾는 문제다. 각 CCTV 마다 경우의 수가 다르다. 총 경우의 수 는
     * 최대 4개씩 8곳에 모두 CCTV가 있다면 4^8 = 65536 DFS 쓰기가 까다로운데.. 틀을 짜보면 일단 각 위치의 CCTV를 만들어서 리스트에 저장 리스트를
     * 모든 경우의 수만큼 순회하면서 CCTV 범위 확인 후 최소값 저장
     * <p>
     * 각 CCTV는 자신이 볼 수 있는 방향을 변수로 갖기 DFS 할 때 리스트랑 , board랑 다음 cctv 번호 1씩 증가하면서 전달 이 때 dFS 안에서는 각
     * CCTV가 자신의 경우의 수만큼 탐색하고 다음으로 넘김 자신의 경우의 수만큼 왔는지 판단하는 법은 맨 처음 탐색 가능한 방향을 저장해놓고 90도 돌렸는데 맨 처음과
     * 같다면 이제 그만. board 리셋하는 거는 어떻게 하지 자기가 # 칠했던거는 지워야 됨. 근데 남이한거는 지우면 안됨. 개복잡;;
     * <p>
     * CCTV는 방향벡터, 내가 볼 수 있는 방향 array, 처음 시작방향을 변수로 갖고 감시하기(이 때 #만든 row와 col 저장 이후 감시 취소 때 사용), 감시
     * 취소, 더 돌릴 수 있는지 메소드 갖음
     * <p>
     * 엣지케이스 n, m 1 1 , 6으로만 가득참
     */
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        List<CCTV> cctvList = new ArrayList<>();

        board = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                int num = Integer.parseInt(st.nextToken());
                board[i][j] = num;

                if (num != 6 && num != 0) {
                    cctvList.add(new CCTV(i, j, num));
                }
            }
        }

        dfs(cctvList, 0, board);
        System.out.println(min);
    }

    private static void dfs(List<CCTV> cctvList, int nowCCTVOrder,int[][] board) {
        if (nowCCTVOrder == cctvList.size()) {
            min = Math.min(min, calculateBindSpots(board));
            return;
        }

        CCTV nowCCTV = cctvList.get(nowCCTVOrder);

        for (int i = 0; i < 4; i++) {
            nowCCTV.monitor(board);
            dfs(cctvList, nowCCTVOrder + 1, board);
            nowCCTV.cancleMonitoring(board);
            nowCCTV.rotate();
        }
    }

    private static int calculateBindSpots(int[][] board) {
        int sum = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 0) {
                    sum += 1;
                }
            }
        }
        return sum;
    }

    static class CCTV {
        // 오, 아, 왼, 위
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        int row;
        int col;
        Integer[] directions;
        List<Integer[]> monitoringPosition = new ArrayList<>();

        public CCTV(int row, int col, int cctvType) {
            this.row = row;
            this.col = col;

            switch (cctvType) {
                case 1:
                    Integer[] dir = {0};
                    directions = dir;
                    break;
                case 2:
                    Integer[] dir2 = {0, 2};
                    directions = dir2;
                    break;
                case 3:
                    Integer[] dir3 = {0, 1};
                    directions = dir3;
                    break;
                case 4:
                    Integer[] dir4 = {3, 0, 1};
                    directions = dir4;
                    break;
                case 5:
                    Integer[] dir5 = {0, 1, 2, 3};
                    directions = dir5;
                    break;
            }

        }

        public void monitor(int[][] board) {
            for (int direction : directions) {
                int nowRow = row;
                int nowCol = col;
                int xx = nowRow + dx[direction];
                int yy = nowCol + dy[direction];

                while (isInRange(xx, yy, board) && board[xx][yy] != 6) {
                    if (board[xx][yy] == 0) {
                        board[xx][yy] = -1;

                        monitoringPosition.add(new Integer[]{xx, yy});
                    }

                    nowRow = xx;
                    nowCol = yy;

                    xx = nowRow + dx[direction];
                    yy = nowCol + dy[direction];
                }
            }
        }
        static public boolean isInRange(int row, int col, int[][] board){
          return row >= 0 && row < board.length && col >= 0 && col < board[0].length;
        }

        public void cancleMonitoring(int[][] board) {
            for (int i = 0; i < monitoringPosition.size(); i++) {
                Integer[] pos = monitoringPosition.get(i);
                board[pos[0]][pos[1]] = 0;
            }
            monitoringPosition.clear();
        }

        public void rotate() {
            for (int i = 0; i < directions.length; i++) {
                directions[i] = (directions[i] + 1) % 4;
            }
        }
    }
}
