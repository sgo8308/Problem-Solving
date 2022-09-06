package algorythm.Practice.Baekjoon;

public class DecodeWays_1 {

    public static void main(String[] args) {
        System.out.println(numDecodings("12"));
        System.out.println(numDecodings("226"));
        System.out.println(numDecodings("06"));
    }
    public static int numDecodings(String s) {
        char[] chars = s.toCharArray();

        int[] counts = new int[s.length()];

        if(chars[0] == '0') return 0;

        counts[0] = 1;

        int num2 = Character.getNumericValue(chars[1]);


        if (num2 >= 1 && num2 <= 9) {
            counts[1] += counts[0];
        }

        if (counts[0] == 1 || (counts[0] == 2 && num2 >= 0 && num2 <= 6)) {
            counts[1] += 1;
        }

        for (int i = 2; i < s.length(); i++) {
            int num = Character.getNumericValue(chars[i]);
            if (num >= 1 && num <= 9) {
                counts[i] += counts[i - 1];
            }

            int beforeNum = Character.getNumericValue(chars[i - 1]);
            if (beforeNum == 1 || (beforeNum == 2 && num >= 0 && num <= 6)) {
                counts[i] += counts[i - 2];
            }

            if (num == beforeNum) {
                return 0;
            }
        }

        return counts[s.length() - 1];
    }
}
