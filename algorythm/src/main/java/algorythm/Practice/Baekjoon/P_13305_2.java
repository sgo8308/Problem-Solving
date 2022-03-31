package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class P_13305_2 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int cityNum = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        long[] distances = new long[cityNum - 1];
        for (int i = 0; i < cityNum - 1; i++) distances[i] = Long.parseLong(st.nextToken());

        st = new StringTokenizer(br.readLine());
        long[] oilPrices = new long[cityNum];
        for (int i = 0; i < cityNum; i++) oilPrices[i] = Long.parseLong(st.nextToken());

        long cost = 0;
        long minOilPrice = oilPrices[0];
        for (int i = 0; i < cityNum - 1; i++) {
            long oilPrice = oilPrices[i];
            minOilPrice = Math.min(minOilPrice, oilPrice);
            cost += minOilPrice * distances[i];
        }

        System.out.println(cost);
    }
}
