package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class P_9019_1 {
    static int N;
    public static void main(String[] args) throws Exception{
        int a = 1;
        int L = (a % 1000) * 10 + a/1000;
        System.out.println(L);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        List<List<Integer>> testCases = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            testCases.add(Arrays.asList(Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())));
        }

        for (int i = 0; i < N; i++) {
            List<Integer> testCase = testCases.get(i);
            int A = testCase.get(0);
            int B = testCase.get(1);
            Queue<ValOp> q = new LinkedList<>();
            int count = 0;
        }
    }

    class ValOp {
        int value;
        String operation;

        public ValOp(int value, String operation) {
            this.value = value;
            this.operation = operation;
        }
    }
}
