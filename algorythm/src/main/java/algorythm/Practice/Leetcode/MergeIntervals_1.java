package algorythm.Practice.Leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;

public class MergeIntervals_1 {

    public static void main(String[] args) throws Exception{
        int[][] m = {{1, 4}, {3, 5}};
        int[][] s = merge2(m);
        for (int i = 0; i < s.length; i++) {
            System.out.println(Arrays.toString(s[i]));
        }
    }

    static public int[][] merge(int[][] intervals) {
        int[] mark = new int[10002];
        Arrays.fill(mark, -1);

        Arrays.sort(intervals, Comparator.comparing(x -> x[0]));
        int pos = 0;
        for (int i = 0; i < intervals.length; i++) {
            int[] interval = intervals[i];

            if (mark[interval[0]] != -1) {
                for (int j = pos; j <= interval[1]; j++) {
                    mark[j] = mark[pos];
                }
            } else {
                for (int j = interval[0]; j <= interval[1]; j++) {
                    mark[j] = interval[0];
                }
            }

            pos = Math.max(pos, interval[1]);
        }

        List<Integer[]> list = new ArrayList<>(10002);
        for (int i = 0; i < mark.length; i++) {
            if (mark[i] == -1) {
                continue;
            }

            if (mark[i] != mark[i + 1]) {
                list.add(new Integer[]{mark[i], i});
            }
        }

        int[][] newIntervals = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < 2; j++) {
                newIntervals[i][0] = list.get(i)[0];
                newIntervals[i][1] = list.get(i)[1];
            }
        }

        return newIntervals;
    }
    //2 O(NlogN)
    static public int[][] merge2(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparing(x1 -> x1[0]));
        Deque<int[]> q = new ArrayDeque<>();
        q.addLast(intervals[0]);

        for (int i = 1; i < intervals.length; i++) {
            if (q.peekLast()[1] >= intervals[i][1]) {
                continue;
            } else if (q.peekLast()[1] >= intervals[i][0]) {
                q.peekLast()[1] = intervals[i][1];
            } else {
                q.addLast(intervals[i]);
            }
        }

        return q.toArray(new int[q.size()][]);
    }
}
