package algorythm.Practice.Leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MeetingRooms2_1 {

    public static void main(String[] args) {
        int[][] i = {{2, 15}, {36, 45}, {9, 29},{16, 23},{4, 9}};
        System.out.println(minMeetingRooms(i));
    }
    //O(nlogn)
    static public int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int[] interval : intervals) {
            if(!pq.isEmpty() && interval[0] >= pq.peek()){
                pq.poll();
            }
            pq.add(interval[1]);
        }
        return pq.size();
    }
}
