package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P_2422_3 {

    int N;
    int M;
    int[][] hateCombination;
    int answer = 0;

    public static void main(String[] args) throws Exception {
        P_2422_3 p_2422_3 = new P_2422_3();
        p_2422_3.getInputAndSetHadteCombination();
        p_2422_3.dfs(new Integer[p_2422_3.N + 1], 1, 0);
        System.out.println(p_2422_3.answer);
    }

    private void getInputAndSetHadteCombination() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        hateCombination = new int[N + 1][N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int ice1 = Integer.parseInt(st.nextToken());
            int ice2 = Integer.parseInt(st.nextToken());
            hateCombination[ice1][ice2] = 1;
            hateCombination[ice2][ice1] = 1;
        }
    }

    private void dfs(Integer[] combinations, int start, int index) {
        if (index == 3) {
            if (hateCombination[combinations[0]][combinations[1]] != 1 &&
                    hateCombination[combinations[1]][combinations[2]] != 1 &&
                    hateCombination[combinations[0]][combinations[2]] != 1) {

                answer++;
            }
            // 여기에 return을 안써서 메모리 초과가 뜨는데 왜 메모리 초과가 뜰까?
            // 아무리 계쏙 호출한다고 해도 결국 combinations 사이즈는 정해져 있고 그렇다면 dfs재귀 깊이가 깊어져서인데 아무리 깊어져도 200개인데..(N최대갑 200)
        }

        for (int i = start; i < N + 1; i++) {
            combinations[index] = i;
            dfs(combinations, i + 1, index + 1);
        }
    }
}
