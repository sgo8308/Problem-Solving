package algorythm.Practice.inflearn;

import java.util.PriorityQueue;
import java.util.Scanner;

public class wedding {
    public static void main(String[] args) {
        PriorityQueue<Integer> arriveTimes = new PriorityQueue();
        PriorityQueue<Integer> leaveTimes = new PriorityQueue();

        Scanner in=new Scanner(System.in);
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            arriveTimes.add(in.nextInt());
            leaveTimes.add(in.nextInt());
        }

        int time = arriveTimes.peek();
        int count = 0;
        int maxCount = 0;
        while (arriveTimes.size() > 0) {
            //도착한 사람들
            for (int i = 0; i < arriveTimes.size(); i++) {
                if (arriveTimes.peek() == time){
                    arriveTimes.poll();
                    count++;
                    continue;
                }
                break;
            }

            //떠나야 될 사람들은 떠나기
            for (int i = 0; i < leaveTimes.size(); i++) {
                if (leaveTimes.peek() == time){
                    leaveTimes.poll();
                    count--;
                    continue;
                }
                break;
            }

            maxCount = Math.max(maxCount, count);
            time++;
        }

        System.out.println(maxCount);
    }
}
