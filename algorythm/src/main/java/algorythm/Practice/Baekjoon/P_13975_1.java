package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P_13975_1 {

    public static int T;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        ArrayList<Novel> novels = new ArrayList<>();
        for (int i = 0; i < T; i++) {
            int K = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            ArrayList<Long> fileSizes = new ArrayList<>();
            for (int j = 0; j < K; j++) {
                fileSizes.add(Long.valueOf((st.nextToken())));
            }
            novels.add(new Novel(fileSizes));
        }

        List<Long> fileSizeTotals = new ArrayList<>();
        for (int i = 0; i < novels.size(); i++) {
            Novel novel = novels.get(i);
            PriorityQueue<Long> q = new PriorityQueue<>();
            long expense = 0;
            for (int j = 0; j < novel.fileSizes.size(); j++) {
                q.add(novel.fileSizes.get(j));
            }
            while (q.size() != 1) {
                long fileSize = q.poll() + q.poll();
                expense += fileSize;
                q.add(fileSize);
            }
            fileSizeTotals.add(expense);
        }

        for (int i = 0; i < fileSizeTotals.size(); i++) {
            System.out.println(fileSizeTotals.get(i));
        }
    }
}

class Novel {
    List<Long> fileSizes;

    public Novel(List<Long> fileSizes) {
        this.fileSizes = fileSizes;
    }
}