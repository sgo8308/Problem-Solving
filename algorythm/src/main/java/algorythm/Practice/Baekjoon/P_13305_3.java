package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P_13305_3 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        ArrayList<Integer> oilAmounts = new ArrayList<>();
        for (int i = 0; i < N - 1; i++) {
            oilAmounts.add(Integer.valueOf(st.nextToken()));
        }

        st = new StringTokenizer(br.readLine());
        ArrayList<Integer> oilPrices = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            oilPrices.add(Integer.valueOf(st.nextToken()));
        }

        long oilPriceTotal = 0;
        int nowOilPrice = Integer.MAX_VALUE;
        for (int i = 0; i < N - 1; i++) {
            if (oilPrices.get(i) <= nowOilPrice) {
                oilPriceTotal += oilPrices.get(i) * oilAmounts.get(i);
                nowOilPrice = oilPrices.get(i);
                continue;
            }
            oilPriceTotal += nowOilPrice * oilAmounts.get(i);
        }
        System.out.println(oilPriceTotal);
    }
}
