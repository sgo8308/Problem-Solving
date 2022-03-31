package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class P_1969_1 {

    private static int n;
    private static int m;
    private static List<Set<Integer>> iceCombs;

    public static void main(String[] args) throws Exception {
        getInputAndSetField();
        int numOfAllCases = calcultateAllCases();
        int numOfDontCases = calculateDontCases();
        System.out.println(numOfAllCases - numOfDontCases);
    }

    private static int calcultateAllCases() {
        int nemerator = n;
        for (int i = 1; i < 3; i++) {
            nemerator *= n - i;
        }
        return nemerator / 6;
    }

    private static int calculateDontCases() {
        Set<Set<Integer>> combSet = new HashSet<>();
        for (int i = 0; i < iceCombs.size(); i++) {
            Set<Integer> comb = iceCombs.get(i);
            for (int newIcecream = 1; newIcecream < n + 1; newIcecream++) {
                if (!comb.contains(newIcecream)) {
                    Set<Integer> newComb = new HashSet<>(comb);
                    newComb.add(newIcecream);
                    combSet.add(newComb);
                }
            }
        }
        return combSet.size();
    }

    private static void getInputAndSetField() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        iceCombs = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            Set<Integer> iceCombination = new HashSet<>();
            iceCombination.add(Integer.parseInt(st.nextToken()));
            iceCombination.add(Integer.parseInt(st.nextToken()));
            iceCombs.add(iceCombination);
        }
    }
}

