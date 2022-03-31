package algorythm.Practice.Leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;

public class TimeNeededToBuyTickets {

    public static void main(String[] args) {
        int[] tickets1 = {2, 3, 2};
        int k1 = 2;

        int[] tickets2 = {5, 1, 1, 1};
        int k2 = 0;
        System.out.println(timeRequiredToBuy(tickets1, k1));
        System.out.println(timeRequiredToBuy(tickets2, k2));
    }
    //minheap과 큐를 만든다.
    //최소값 만큼 한번 뺀다. n
    //minheap에서 최소값 뺀다. logn
    //큐 한바퀴돌면서 0인 애들 k인지 검사 n
    //반복 횟수는 최대 ticket[i]의 최대값
    static public int timeRequiredToBuy(int[] tickets, int k) {
        PriorityQueue<Integer> minheap = new PriorityQueue<>();
        Deque<Person> q = new ArrayDeque<>();
        for (int i = 0; i < tickets.length; i++) {
            Person p = new Person(i, tickets[i]);
            q.addLast(p);
            minheap.add(tickets[i]);
        }
        int time = 0;
        while (q.size() > 1) {
            int minValue = minheap.poll();
            time += minValue * q.size();
            int qSize = q.size();
            for (int i = 0; i < qSize; i++) {
                Person p = q.poll();
                p.ticketCount -= minValue;
                if (p.ticketCount == 0) {
                    if (p.index == k) {
                        return time;
                    }
                } else {
                    q.addLast(p);
                }
            }
        }

        time += q.poll().ticketCount;

        return time;
    }
//    static public int timeRequiredToBuy(int[] tickets, int k) {
//        Deque<Person> q = new ArrayDeque<>();
//        for (int i = 0; i < tickets.length; i++) {
//            Person p = new Person(i, tickets[i]);
//            q.addLast(p);
//        }
//
//        int time = 0;
//        while (q.size() > 1) {
//            time++;
//            Person p = q.poll();
//            p.ticketCount--;
//            if (p.ticketCount == 0) {
//                if (p.index == k) {
//                    return time;
//                }
//                continue;
//            }
//
//            q.addLast(p);
//        }
//
//        if (q.size() == 1) {
//            time += q.poll().ticketCount;
//        }
//
//        return time;
//    }


    static class Person {
        int index;
        int ticketCount;

        public Person(int index, int ticketCount) {
            this.index = index;
            this.ticketCount = ticketCount;
        }
    }

}
