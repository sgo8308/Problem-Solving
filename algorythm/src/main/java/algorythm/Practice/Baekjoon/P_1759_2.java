package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
public class P_1759_2 {

    static int passwordSize;
    static int alphabetCount;
    private static Set<String> passwords;
    private static List<String> alphabets;
    private static List<String> vowels = Arrays.asList("a", "e", "i", "o", "u");
    public static void main(String[] args) throws Exception {
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

        passwords = new HashSet<String>();
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
                String[] array = password.split("");
                Arrays.sort(array);
                passwords.add(String.join("", array));
            }
            return;
        }

        for (int i = 0; i < alphabetCount; i++) {
            if (!visit[i]) {
                visit[i] = true;
                dfs(password + alphabets.get(i), visit, depth + 1, start +1);
                visit[i] = false;
            }
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
