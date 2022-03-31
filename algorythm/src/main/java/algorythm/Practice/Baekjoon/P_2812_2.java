package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class P_2812_2 {
    static int N;
    static int K;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        String number = br.readLine();

        Deque<Integer> nums = new ArrayDeque<>();
        int eraseCount = 0;
        for (int i = 0; i < N; i++) {
            eraseCount = pushNumAndGetEraseCount(eraseCount, nums, number.substring(i, i + 1));
        }

        System.out.println(dequeToWholeNumber(nums));
    }

    private static int pushNumAndGetEraseCount(int eraseCount, Deque<Integer> nums, String num) {
        while (eraseCount < K && !nums.isEmpty() &&  nums.getLast() < Integer.parseInt(num)) {
            nums.removeLast();
            eraseCount++;
        }
        nums.offerLast(Integer.parseInt(num));

        return eraseCount;
    }

    private static String dequeToWholeNumber(Deque<Integer> nums) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N - K; i++) {
            sb.append(nums.removeFirst());
        }
        return sb.toString();
    }
}
