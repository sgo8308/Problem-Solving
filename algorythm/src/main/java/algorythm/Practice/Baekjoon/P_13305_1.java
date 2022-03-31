package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P_13305_1 {
    static int cityNum;
    static long[] distances;
    static long[] oilPrices;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        cityNum = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        distances = new long[cityNum - 1];
        for (int i = 0; i < cityNum - 1; i++) distances[i] = Long.parseLong(st.nextToken());

        st = new StringTokenizer(br.readLine());
        oilPrices = new long[cityNum];
        for (int i = 0; i < cityNum; i++) oilPrices[i] = Long.parseLong(st.nextToken());

        long totalDistance = Arrays.stream(distances).sum();

        System.out.println("totalDistance = " + totalDistance);
        long distanceLeft = totalDistance;
        long cost = 0;


        for (int i = 0; i < cityNum; i++) {
            System.out.println("distanceLeft = " + distanceLeft);
            long oilPrice = oilPrices[i];

            int index = getPlaceCheaper(i);
            System.out.println("index = " + index);
            //place가 -1이 나온 경우 맨 뒤에까지 더 작은 놈이 없는 경우
            if(index == -1){
                cost += distanceLeft * oilPrice;
                break;
            }

            long dist = getDistToNextPlace(i, index);
            System.out.println("dist = " + dist);
            distanceLeft -= dist;

            cost += dist * oilPrice;
            System.out.println("cost = " + cost);
            i = index - 1;
        }

        System.out.println(cost);
    }

    private static int getPlaceCheaper(int i) {
        long nowOilPrice = oilPrices[i];
        for (int j = i + 1; j < cityNum; j++) {
            long nextPrice = oilPrices[j];
            if(nextPrice < nowOilPrice)
                return j;
        }
        return -1;
    }

    private static long getDistToNextPlace(int i, long index) {
        long dist = 0;
        for (int j = i; j < index; j++) {
            dist += distances[j];
        }
        return dist;
    }
}
