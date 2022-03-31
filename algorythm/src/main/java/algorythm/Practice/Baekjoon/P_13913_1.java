package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.function.Function;

abstract public class P_13913_1 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int subinPosition = Integer.parseInt(st.nextToken());
        int brotherPosition = Integer.parseInt(st.nextToken());

        if (subinPosition == brotherPosition) {
            System.out.println(0);
            System.out.println(subinPosition);
            return;
        }

        if (subinPosition > brotherPosition) {
            System.out.println(subinPosition - brotherPosition);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < subinPosition - brotherPosition + 1; i++) {
                sb.append(subinPosition - i + " ");
            }
            System.out.println(sb);
            return;
        }

        bfs(subinPosition, brotherPosition);
    }

    private static void bfs(int subinPos, int brother) {
        Function<Integer, Integer>[] operations = new Function[3];
        operations[0] = x -> x * 2;
        operations[1] = x -> x + 1;
        operations[2] = x -> x - 1;

        Queue<Integer> q = new LinkedList<>();

        int[] times = new int[100001];
        int[] parents = new int[100001]; // index에 왔을 때 바로 그 전 위치를 저장
        for (int i = 0; i < operations.length; i++) {
            int nextSubinPos = operations[i].apply(subinPos);
            if (nextSubinPos >= 0 && nextSubinPos <= 100000) {
                times[nextSubinPos] = 1;
                parents[nextSubinPos] = subinPos;
                q.add(nextSubinPos);
            }
        }
        while (!q.isEmpty()) {
            int nowSubinPos = q.poll();
            int nowTime = times[nowSubinPos];

            if (nowSubinPos == brother) {
                System.out.println(nowTime);
                Stack<Integer> stack = new Stack<>();
                stack.push(brother);
                int index = brother;
                for (int i = 0; i < nowTime; i++) {
                    int parent = parents[index];
                    stack.push(parent);
                    index = parent;
                }
                StringBuilder sb = new StringBuilder();
                while (!stack.isEmpty()) {
                    sb.append(stack.pop() + " ");
                }
                System.out.println(sb);
                break;
            }

            for (int i = 0; i < operations.length; i++) {
                int nextSubinPos = operations[i].apply(nowSubinPos);
                if (nextSubinPos >= 0 && nextSubinPos <= 100000 &&
                        (times[nextSubinPos] == nowTime + 1 || times[nextSubinPos] == 0)) {
                    times[nextSubinPos] = nowTime + 1;
                    parents[nextSubinPos] = nowSubinPos;
                    q.add(nextSubinPos);
                }
            }
        }
    }
}