package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class P_2141_2 {


    static int N;
    static List<Town> towns = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());



       for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            towns.add(new Town(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        Collections.sort(towns);

        long peopleSum = towns.stream().mapToLong(town -> town.peopleCount).sum();
        int optimalPOPosition = towns.stream()
                .reduce((town, town2) ->
                     (town.peopleCount >= peopleSum / 2.0d) ?
                             town :
                             new Town(town2.position, town.peopleCount + town2.peopleCount)
                ).get().position;

        System.out.println(optimalPOPosition);
    }

    static class Town implements Comparable<Town> {

        int position;
        long peopleCount;

        public Town(int position, long peopleCount) {
            this.position = position;
            this.peopleCount = peopleCount;
        }

        @Override
        public int compareTo(Town o) {
            return Integer.compare(this.position, o.position);
        }
    }
}
