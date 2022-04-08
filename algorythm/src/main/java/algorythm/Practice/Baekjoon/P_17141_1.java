package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import javax.management.monitor.Monitor;

public class P_17141_1 {
    static volatile int s = 5;
    public static void main(String[] args) throws Exception{
        //바이러스 위치 조합 구하기
        //위치에 따라 바이러스 놓고 퍼트리기
        //최소 시간 업데이트 하기
        //시간복잡도는 N^2 * m의 최대 조합의 개수 현재는 252 * 10000
        //바이러스는 동시에 복제되며 1초가 걸린다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Integer[]> virusCandidates = new ArrayList<>();
        int[][] board = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());

                if (board[i][j] == 2) {
                    virusCandidates.add(new Integer[]{i, j});
                }
            }
        }

        int minTime = Integer.MAX_VALUE;
        boolean flag = false;
        mark(N, board);
        List<List<Integer[]>> virusCombis = getVirusCombis(new ArrayList<>(), virusCandidates, new ArrayList<>(), M,0);
        for (int i = 0; i < virusCombis.size(); i++) {
            int[][] newBoard = newBoard(N, board);
            List<Integer[]> virusCombi = virusCombis.get(i);
            for (Integer[] virus : virusCombi) {
                newBoard[virus[0]][virus[1]] = 0;
            }
            spreadVirus(newBoard);

            int time = calculateTime(newBoard);
            if (time != -1) {
                flag = true;
                minTime = Math.min(minTime, time);
            }
        }

        System.out.println(flag ? minTime : -1);
    }

    private static int[][] newBoard(int N, int[][] board) {
        int[][] newBoard = new int[N][N];
        for (int j = 0; j < board.length; j++) {
            newBoard[j] = board[j].clone();
        }
        return newBoard;
    }

    private static List<List<Integer[]>> getVirusCombis(List<List<Integer[]>> virusCombis,
            List<Integer[]> virusCandidates, List<Integer[]> virusCombi, int virusCnt, int start) {

        if (virusCombi.size() == virusCnt) {
            virusCombis.add(virusCombi.stream().collect(Collectors.toList()));
            return virusCombis;
        }

        for (int i = start; i < virusCandidates.size(); i++) {
            virusCombi.add(virusCandidates.get(i));
            getVirusCombis(virusCombis, virusCandidates, virusCombi, virusCnt, i + 1);
            virusCombi.remove(virusCombi.size() - 1);
        }

        return virusCombis;
    }

    private static void mark(int N, int[][] newBoard) {
        for (int j = 0; j < N; j++){
            for (int k = 0; k < N; k++) {
                if (newBoard[j][k] == 0 || newBoard[j][k] == 2 ) {
                    newBoard[j][k] = -1; // 빈칸, 바이러스가능 영역 -1로 새로 표시
                } else {
                    newBoard[j][k] = -2; // 벽은 -2로 새로 표시
                }
            }
        }
    }

    private static int calculateTime(int[][] newBoard) {
        int time = 0;
        for (int i = 0; i < newBoard.length; i++) {
            for (int j = 0; j < newBoard.length; j++) {
                time = Math.max(time, newBoard[i][j]);

                if (newBoard[i][j] == -1) {
                    return -1;
                }
            }
        }
        return time;
    }

    private static void spreadVirus(int[][] newBoard) {
        for (int i = 0; i < newBoard.length; i++) {
            for (int j = 0; j < newBoard.length; j++) {
                if (newBoard[i][j] == 0) {
                    dfs(i, j, newBoard);
                }
            }
        }
    }

    private static void dfs(int i, int j, int[][] newBoard) {
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        for (int k = 0; k < 4; k++) {
            int xx = i + dx[k];
            int yy = j + dy[k];

            if (xx >= 0 && xx < newBoard.length && yy >= 0 && yy < newBoard.length
                    && (newBoard[xx][yy] > newBoard[i][j] || newBoard[xx][yy] == -1)) {
                newBoard[xx][yy] = newBoard[i][j] + 1;
                dfs(xx, yy, newBoard);
            }
        }
    }
}
