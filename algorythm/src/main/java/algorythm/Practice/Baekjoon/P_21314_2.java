package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class P_21314_2 {
    //가장 큰 수
    //맨 앞에서 부터 진행하면서
    //5가 맨 앞에 있는 수 중 가장 작은 수를 만들기
    // -> K를 먼저 만나면 바로 5로 확정
    // -> M을 만나면 K를 만날 때까지 넘어가다가 K를 만나면 M~K까지의 값을 확정
    // -> M을 만나서 계속 뒤로가다가 끝을 만나면 M부터 뒤로 간 수만큼 해서 모두 1로 확정
    // 시간복잡도는 O(N)

    //가장 작은 수
    //맨 앞에서부터 진행하면서
    //1이 맨앞에 있는 수 중에서 가장 큰 수 만들기
    // -> M을 만나면 K를 만날 때까지 뒤로가다가 K를 만나면 이전 M들을 모두 1000~로 확정
    // -> k를 만나면 바로 5로 확정
    public static void main(String[] args) throws Exception {
        String minGyum = new BufferedReader(new InputStreamReader(System.in)).readLine();
        StringBuilder max = new StringBuilder();
        StringBuilder min = new StringBuilder();
        int Mcnt = 0;
        for (int i = 0; i < minGyum.length(); i++) {
            String letter = minGyum.substring(i, i + 1);
            if (letter.equals("K")) {
                max.append(5);
                for (int j = 0; j < Mcnt; j++) {
                    max.append(0);
                }

                if (Mcnt != 0) {
                    min.append(1);
                }
                for (int j = 0; j < Mcnt - 1; j++) {
                    min.append(0);
                }
                min.append(5);

                Mcnt = 0;
            } else {
                if (i != minGyum.length() - 1) {
                    Mcnt++;
                } else if (i == minGyum.length() - 1) {
                    for (int j = 0; j < Mcnt + 1; j++) {
                        max.append(1);
                    }

                    min.append(1);
                    for (int j = 0; j < Mcnt; j++) {
                        min.append(0);
                    }
                }
            }

        }

        System.out.println(max);
        System.out.println(min);
    }
}
