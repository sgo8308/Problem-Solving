package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P_2812_1 {

    public static int N;
    public static int K;
    public static String number;

    public static void main(String[] args) throws Exception {
        while (true) {
            setInput();
            PriorityQueue<Number> queue = new PriorityQueue<>(Collections.reverseOrder());
            for (int i = 0; i < K + 1; i++) {
                queue.add(makeNumber(i));
            }
            StringBuilder ans = new StringBuilder();
            Number nowNumber = queue.poll();
            ans.append(nowNumber.number);

            for (int i = K + 1; i < N; i++) {
                queue.add(makeNumber(i));
                Number cand = queue.poll();
                while (cand.index <= nowNumber.index) {
                    cand = queue.poll();
                }
                ans.append(cand.number);
                nowNumber = cand;
            }
            System.out.println(ans);
        }

    }

    private static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        number = br.readLine();
    }

    private static Number makeNumber(int i) {
        return new Number(Character.getNumericValue(number.charAt(i)), i);
    }
}

class Number implements Comparable<Number> {

    int number;
    int index;

    public Number(int number, int index) {
        this.number = number;
        this.index = index;
    }

    @Override
    public int compareTo(Number o) {
        return Integer.compare(this.number, o.number);
    }
}
