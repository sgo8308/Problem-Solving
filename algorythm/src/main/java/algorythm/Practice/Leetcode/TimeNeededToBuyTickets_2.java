package algorythm.Practice.Leetcode;

public class TimeNeededToBuyTickets_2 {

    public static void main(String[] args) {
        //  3 4 4 2 5 5 5 4 7 8

//        int[] tickets = {2, 3, 2};
//        int k = 2;
        int[] tickets = {5, 1, 1, 1};
        int k = 0;

        System.out.println(timeRequiredToBuy(tickets, k) );
    }

    static public int timeRequiredToBuy(int[] tickets, int k) {
        int time = 0;
        int target = tickets[k];
        for (int i = 0; i < tickets.length; i++) {
            if (tickets[i] >= target - 1) {
                time += target - 1;
            } else {
                time += tickets[i];
            }
        }

        for (int i = 0; i <= k; i++) {
            if (tickets[i] >= target - 1) {
                time++;
            }
        }

        return time;
    }

}
