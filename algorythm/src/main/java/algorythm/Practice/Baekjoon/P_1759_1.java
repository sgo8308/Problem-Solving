package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class P_1759_1 {

    static int passwordSize;
    static int alphabetCount;
    private static List<String> passwords;
    private static List<String> alphabets;
    private static List<String> vowels = Arrays.asList("a", "e", "i", "o", "u");

    public static void main(String[] args) throws Exception {
        // 암호 사이즈 L, 문자의 종류 C개
        // 최소 한 개의 모음, 최소 두 개의 자음, 오름차순 정렬
        // 엣지 케이스 3 3 a b e -> 0   3 3 a b c -> 1
        // 정렬
        // dfs(중복되지 않게) 돌면서 마지막에 조건 1,2 검사 후 리스트에 추가
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        passwordSize = Integer.parseInt(st.nextToken());
        alphabetCount = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        alphabets = new ArrayList<>();
        for (int i = 0; i < alphabetCount; i++) {
            alphabets.add(st.nextToken());
        }

        Collections.sort(alphabets);

        passwords = new ArrayList<>();
        dfs("", new boolean[alphabetCount], 1, 0);
        List<String> pass = passwords.stream().collect(Collectors.toList());
        Collections.sort(pass);

        for (int i = 0; i < passwords.size(); i++) {
            System.out.println(pass.get(i));
        }
    }

    private static void dfs(String password, boolean[] visit, int depth, int start) {
        if (depth > passwordSize) {
            if (hasEnoughVowel(password) && hasEnoughConsonant(password)) {
                passwords.add(password);
            }
            return;
        }

        for (int i = start; i < alphabetCount; i++) {
            dfs(password + alphabets.get(i), visit, depth + 1, i + 1);
        }
    }

    private static boolean hasEnoughConsonant(String password) {
        int count = 0;
        for (int i = 0; i < password.length(); i++) {
            if (!vowels.contains(password.substring(i, i + 1))) {
                count++;
            }

            if (count >= 2) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasEnoughVowel(String password) {
        for (int i = 0; i < password.length(); i++) {
            if (vowels.contains(password.substring(i, i + 1))) {
                return true;
            }
        }
        return false;
    }
}
