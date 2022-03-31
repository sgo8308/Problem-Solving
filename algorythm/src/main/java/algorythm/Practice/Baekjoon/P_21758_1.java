package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P_21758_1 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] honeys = new int[N];
        int[] cumulativeSums = new int[N];
        int cumulativeSum = 0;
        for (int i = 0; i < honeys.length; i++) {
            honeys[i] = Integer.parseInt(st.nextToken());
            cumulativeSum += honeys[i];
            cumulativeSums[i] = cumulativeSum;
        }

        int maxHoney = Integer.MIN_VALUE;
        maxHoney = getMaxHoney(honeys, cumulativeSums, maxHoney, new BeeBeeHoneyHoneyCalculator());
        maxHoney = getMaxHoney(honeys, cumulativeSums, maxHoney, new HoneyBeeBeeHoneyCalculator());
        maxHoney = getMaxHoney(honeys, cumulativeSums, maxHoney, new BeeHoneyBeeHoneyCalculator());
        System.out.println(maxHoney);
    }

    private static int getMaxHoney(int[] honeys, int[] cumulativeSums, int max, HoneyCalculator hc) {
        for (int i = 1; i < honeys.length - 1; i++) {
            int firstBeeHoney = hc.calculateFirstBeeHoney(honeys, cumulativeSums, i);
            int secondBeeHoney = hc.calculateSecondBeeHoney(honeys, cumulativeSums, i);
            max = Math.max(max, firstBeeHoney + secondBeeHoney);
        }
        return max;
    }
}

interface HoneyCalculator {

    int calculateFirstBeeHoney(int[] honeys, int[] cumulativeSums, int middlePosition);

    int calculateSecondBeeHoney(int[] honeys, int[] cumulativeSums, int middlePosition);
}

class BeeBeeHoneyHoneyCalculator implements HoneyCalculator {

    @Override
    public int calculateFirstBeeHoney(int[] honeys, int[] cumulativeSums, int middlePosition) {
        return cumulativeSums[honeys.length - 1] - honeys[middlePosition] - honeys[0];
    }

    @Override
    public int calculateSecondBeeHoney(int[] honeys, int[] cumulativeSums, int middlePosition) {
        return cumulativeSums[honeys.length - 1] - cumulativeSums[middlePosition - 1] - honeys[middlePosition];
    }
}

class HoneyBeeBeeHoneyCalculator implements HoneyCalculator {

    @Override
    public int calculateFirstBeeHoney(int[] honeys, int[] cumulativeSums, int middlePosition) {
        return cumulativeSums[honeys.length - 1]
                - honeys[middlePosition] - honeys[honeys.length - 1];
    }

    @Override
    public int calculateSecondBeeHoney(int[] honeys, int[] cumulativeSums, int middlePosition) {
        return cumulativeSums[middlePosition - 1];
    }
}

class BeeHoneyBeeHoneyCalculator implements HoneyCalculator {

    @Override
    public int calculateFirstBeeHoney(int[] honeys, int[] cumulativeSums, int middlePosition) {
        return cumulativeSums[middlePosition] - honeys[0];
    }

    @Override
    public int calculateSecondBeeHoney(int[] honeys, int[] cumulativeSums, int middlePosition) {
        return cumulativeSums[honeys.length - 1] - cumulativeSums[middlePosition - 1]
                - honeys[honeys.length - 1];
    }
}

