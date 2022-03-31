package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P_1343_1 {
    //조건 1. 폴리오미노로 다 덮어야 한다.
    //조건 2. 사전순으로 가장 앞서는 답 출력

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String board = st.nextToken();

        for (int i = 0; i < board.length(); i++) {
            if(board.charAt(i) == '.') continue;

            if (board.substring(i).length() >= 4 && board.substring(i, i + 4).equals("XXXX")) {
                board = board.substring(0, i) + "AAAA" + board.substring(i + 4);
                i += 3;
            } else if (board.substring(i).length() >= 2 && board.substring(i, i + 2).equals("XX")) {
                board = board.substring(0, i) + "BB" + board.substring(i + 2);
                i += 1;
            } else {
                System.out.println(-1);
                return;
            }
        }

        System.out.println(board);
    }
}
