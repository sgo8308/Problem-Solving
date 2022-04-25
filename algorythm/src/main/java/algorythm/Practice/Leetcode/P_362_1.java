package algorythm.Practice.Leetcode;

import java.util.Arrays;
import java.util.stream.IntStream;

public class P_362_1 {

    public static void main(String[] args) {
        P_362_1 hc = new P_362_1();
        hc.hit(1);
        hc.hit(2);
        hc.hit(3);
        System.out.println(hc.getHits(4));
        hc.hit(300);
        System.out.println(hc.getHits(300));
        System.out.println(hc.getHits(301));
    }

    // 1  2  3  4  5
    // 2  2  3  3  5
    // 6  7  8  9  10
    // 3  2  3
    // 11 12 13 14 15
    //          3
    // 16 17 18 19 20
    // 5

    int[] arr = new int[300];
    public P_362_1() {

    }

    int lastTimeStamp = 0;
    public void hit(int timestamp) {
        fillZero(timestamp);
        int idx = (timestamp - 1) % 300;
        arr[idx]++;
    }

    public int getHits(int timestamp) {
        fillZero(timestamp);
        return IntStream.of(arr).sum();
    }

    private void fillZero(int timestamp) {
        int diff = timestamp - lastTimeStamp;
        if (diff >= 300) {
            Arrays.fill(arr, 0);
        } else {
            for (int i = lastTimeStamp + 1; i <= timestamp; i++) {
                int idx = (i - 1) % 300;
                arr[idx] = 0;
            }
        }

        lastTimeStamp = timestamp;
    }
}
