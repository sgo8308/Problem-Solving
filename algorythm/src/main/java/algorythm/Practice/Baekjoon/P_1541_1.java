package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class P_1541_1 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String expression = st.nextToken();

        String[] numStrings = expression.split("[-+]");
        int[] nums = new int[numStrings.length];
        for (int i = 0; i < numStrings.length; i++)
            nums[i] = Integer.parseInt(numStrings[i]);

        if (nums.length == 1) {
            System.out.println(nums[0]);
            return;
        }

        String[] ops = expression.split("[0-9]+");
        String[] operators = Arrays.copyOfRange(ops, 1, ops.length);

        int result = nums[0];
        for (int i = 0; i < operators.length; i++) {
            if (operators[i].equals("+")) {
                result += nums[i + 1];
            } else {
                int index = i;
                result -= IntStream
                        .range(0, operators.length + 1)
                        .filter(x -> x >= index + 1)
                        .map(x -> nums[x])
                        .sum();
                break;
            }
        }
        System.out.println(result);
    }
}
