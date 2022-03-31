package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class P_2141_1 {
    public static int N;
    public static List<Town> towns;
    public static void main(String[] args) throws Exception {
        getInputAndSetVariable();
        Collections.sort(towns, Comparator.comparingInt(o -> o.position));
        long peopleTotal = towns.stream().mapToLong(x -> x.peopleCount).sum();
        long peopleSum = 0;
        for (int i = 0; i < N; i++) {
            peopleSum += towns.get(i).peopleCount;
            if (peopleSum >= peopleTotal / 2.0d) {
                System.out.println(towns.get(i).position);
                return;
            }
        }
    }

    private static void getInputAndSetVariable() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        towns = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            towns.add(new Town(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
    }
}

class Town {

    int position;
    int peopleCount;

    public Town(int position, int peopleCount) {
        this.position = position;
        this.peopleCount = peopleCount;
    }
}
