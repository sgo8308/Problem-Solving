package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P_11265_1 {
    static int[][] grid, shortestGrid;
    static int N , M;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        grid = new int[N][N];
        shortestGrid = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
                if(i == j)
                    shortestGrid[i][j] = 0;
                else
                    shortestGrid[i][j] = Integer.MAX_VALUE;
            }
        }

        for (int i = 0; i < N; i++) {
            dijkstra(i);
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            int limit = Integer.parseInt(st.nextToken());

            System.out.println(getMessage(from, to, limit));
        }
    }

    static void dijkstra(int start){
        PriorityQueue<Party> heap = new PriorityQueue<>();
        heap.add(new Party(start, 0));

        while(!heap.isEmpty()){
            Party nowParty = heap.poll();

            if(grid[start][nowParty.name] < nowParty.distance) continue;

            for (int i = 0; i < N; i++) {
                int newDistAtoC = nowParty.distance + grid[nowParty.name][i];
                int originalDistAtoC = shortestGrid[start][i];
                if(newDistAtoC < originalDistAtoC){
                    shortestGrid[start][i] = newDistAtoC;
                    heap.add(new Party(i, newDistAtoC));
                }
            }
        }
    }

    static String getMessage(int from, int to, int limit){
        if(shortestGrid[from][to] <= limit)
            return "Enjoy other party";
        return "Stay here";
    }
}

class Party implements Comparable<Party>{
    int name;
    int distance;

    public Party(int name, int distance) {
        this.name = name;
        this.distance = distance;
    }

    @Override
    public int compareTo(Party o) {
        return this.distance - o.distance;
    }
}