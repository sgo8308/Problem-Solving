package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class P_2798_1 {
    public static int cardCount;
    public static int M;
    public static List<Integer> cards = new ArrayList<>();
    private static int answer = 0;

    public static void main(String[] args) throws Exception{
        getInputAndSetVar();
        dfs("", 1, 0);
        System.out.println(answer);
    }

    public static void dfs(String comb, int depth, int cardSum) {
        if (depth > 3) {
            if (cardSum <= M) {
                answer = Math.max(answer, cardSum);
            }
            return;
        }

        for (int i = 0; i < cardCount; i++) {
            if (!comb.contains("" + i)) {
                dfs(comb + i, depth + 1, cardSum + cards.get(i));
            }
        }
    }

    private static void getInputAndSetVar() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        cardCount = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < cardCount; i++) {
            cards.add(Integer.valueOf(st.nextToken()));
        }
    }
}
