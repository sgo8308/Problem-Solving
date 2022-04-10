package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P_14890_1 {

    /**
     * n x n 지도
     * 길을 지나가려면 칸의 높이 같아야 함
     * 경사로는 무제한, 높이 1 길이 L, 낮은 칸에서 높은 칸 높이 1만 가능
     * 경사로 놓은 곳 또 x, 낮은 지점 칸 높이 모두 같아야, L개 연속되야, 경사로 범위 넣어가면 안댐
     *
     * 배열 하나를 체크하는 로직만 작성
     *
     * 배열 첫 원소부터 시작해서 오른쪽으로 가면서 더 1만큼 큰 애를 만나면 l만큼 뒤로 체크, 1만큼 더 작은 애 만나면 l만큼 앞으로 체크
     * 만약 2이상 차이나면 바로 리턴
     * 이 때 길이가 충분하고, 경사로를 이미 깔아놓은 칸이 없으면 경사로를 깔아주고 경사로 깔았다고 체크. 그게 아니라면 바로 리턴;
     * 모든 행 열에 대해서 조사
     * 결과 리턴
     */
    static int n;
    static int l;
    static int[][] board;
    static int validRoadCnt = 0;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());

        board = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            int[] horiRoad = board[i];
            checkRoad(horiRoad);
        }

        for (int i = 0; i < n; i++) {
            int[] verRoad = new int[n];
            for (int j = 0; j < n; j++) {
                verRoad[j] = board[j][i];
            }
            checkRoad(verRoad);
        }

        System.out.println(validRoadCnt);
    }

    private static void checkRoad(int[] road) {
        int pre = road[0];
        boolean[] isSlopes = new boolean[n];

        for (int i = 1; i < n; i++) {
            int now = road[i];

            if(Math.abs(pre - now) > 1) return;

            if (pre == now - 1) {
                if (!checkBackSlope(road, i, isSlopes)) {
                    return;
                }
            }


            if (pre == now + 1) {
                if (!checkFrontSlope(road, i, isSlopes)) {
                    return;
                }
            }

            pre = now;
        }

        validRoadCnt++;
    }

    private static boolean checkBackSlope(int[] road, int now, boolean[] isSlopes) {
        int cnt = l;
        int height = road[now - 1];
        for (int i = now - 1; ; i--) {
            if (i < 0) return false;

            if (isSlopes[i]) return false;

            if(road[i] != height) return false;

            isSlopes[i] = true;

            cnt--;
            if(cnt == 0) break;
        }
        return true;
    }

    private static boolean checkFrontSlope(int[] road, int now, boolean[] isSlopes) {
        int cnt = l;
        int height = road[now];
        for (int i = now; ; i++) {
            if (i == road.length) return false;

            if (isSlopes[i]) return false;

            if(road[i] != height) return false;

            isSlopes[i] = true;

            cnt--;
            if(cnt == 0) break;
        }
        return true;
    }
}
