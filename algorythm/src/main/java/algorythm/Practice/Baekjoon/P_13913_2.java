package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.function.Function;

public class P_13913_2 {
    /**
     * 시간복잡도 = 1~10만까지 모든 숫자에 대해서 탐색한다고 했을 때 각 숫자마다 -1 +1 *2 이렇게 3개의 방향을 탐색하므로
     * 시간복잡도는 3 * 10만 -> 3 * n 즉 o(N)
     * 대신에 한 번 도착했던 숫자에서는 더 이상 진행하지 않아야 함
     */

    //질문 남기기 10만 넘어가면 ?
    static int N;
    static int K;
    public static void main(String[] args) throws Exception{
        HashMap
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        if (N == K) {
            System.out.println(0);
            System.out.println(N);
            return;
        }

        StringBuilder s = new StringBuilder();
        if (K < N) {
            System.out.println(N - K);
            for (int i = 0; i <= N - K; i++) {
                s.append(N - i + " ");
            }
            System.out.println(s);
            return;
        }

        Integer[] parentNodes = new Integer[200000]; // 현재 노드는 인덱스, 이 노드 전에 노드는 이 인덱스에 위치한 값

        Deque<Integer> q = new ArrayDeque<>();
        q.addLast(N);
        parentNodes[N] = -1;

        List<Function<Integer, Integer>> operations = new ArrayList<>();
        operations.add(x -> x + 1);
        operations.add(x -> x - 1);
        operations.add(x -> x * 2);

        while (!q.isEmpty()) {
            Integer nowNode = q.removeFirst();

            if (nowNode == K) break;

            for (int i = 0; i < 3; i++) {
                int newNode = operations.get(i).apply(nowNode);
                if (newNode >= 0 && newNode < 200000 && parentNodes[newNode] == null) {
                    parentNodes[newNode] = nowNode;
                    q.addLast(newNode);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        int time = 0;
        int nextNode = K;
        sb.append(nextNode);
        while (true) {
            nextNode = parentNodes[nextNode];
            if (nextNode == -1) {
                break;
            }
            sb.insert(0,nextNode + " ");
            time++;
        }
        System.out.println(time);
        System.out.println(sb);
    }
}
