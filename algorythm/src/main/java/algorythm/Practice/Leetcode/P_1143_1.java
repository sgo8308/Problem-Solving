package algorythm.Practice.Leetcode;

public class P_1143_1 {

    public static void main(String[] args) {
        longestCommonSubsequence("abc", "adbdc");
    }
    static public int longestCommonSubsequence(String text1, String text2) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < text1.length(); i++) {
            int len = 0;
            for (int j = i, k = 0; j < text1.length(); j++) {
                System.out.println(i + " " + j + " " + k);
                while (k < text2.length()) {
                    if (text1.charAt(j) == text2.charAt(k)) {
                        len++;
                        break;
                    }
                    k++;

                }
            }
        }

        return  0;
    }
}
