package algorythm.Practice.programmers.step2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Delivery {
    public static void main(String[] args) {
        int[][] s =  {{1,2,1},{2,3,3},{5,2,2},{1,4,2},{5,3,1},{5,4,2}};
        int k = 3;
//        int[][] s = {{1,2,1},{1,3,2},{2,3,2},{3,4,3},{3,5,2},{3,5,3},{5,6,1}};
//        int k = 4;
        System.out.println(solution(5,s,3));
    }

    static ArrayList<Town> distancesFromFirstTown = new ArrayList<>();
    static HashMap<Integer, ArrayList<Town>> adjacent = new HashMap<>();
    static public int solution(int N, int[][] road, int K) {
        for (int i = 0; i < N; i++)
            distancesFromFirstTown.add(new Town(i,Integer.MAX_VALUE));

        for (int i = 0; i < road.length; i++) {
            int[] r = road[i];
            adjacent.putIfAbsent(r[0] - 1, new ArrayList<>());
            adjacent.get(r[0] - 1).add(new Town(r[1] - 1, r[2]));

            adjacent.putIfAbsent(r[1] - 1, new ArrayList<>());
            adjacent.get(r[1] - 1).add(new Town(r[0] - 1, r[2]));
        }

        dijkstra(0);

        int answer = 0;
        for (int i = 0; i < distancesFromFirstTown.size(); i++)
            if(distancesFromFirstTown.get(i).distance <= K) answer++;

        return answer;
    }

    static void dijkstra(int start){
        PriorityQueue<Town> heap = new PriorityQueue<>();
        distancesFromFirstTown.get(start).distance = 0;
        heap.add(new Town(start,0));

        while (!heap.isEmpty()){
            Town town = heap.poll(); // 현재 온 도시와 , 1부터 이 도시까지의 현재까지의 가장 빠른 거리를 담고 있음

            if(distancesFromFirstTown.get(town.name).distance < town.distance) continue;

            for (int i = 0; i < adjacent.get(town.name).size(); i++) {
                Town nextTown = adjacent.get(town.name).get(i);
                int newDistAtoC = town.distance + nextTown.distance;
                int oriDistAtoC = distancesFromFirstTown.get(nextTown.name).distance;
                if(newDistAtoC < oriDistAtoC){
                    distancesFromFirstTown.get(nextTown.name).distance = newDistAtoC;
                    heap.add(new Town(nextTown.name, newDistAtoC));
                }
            }
        }
    }
}

class Town implements Comparable<Town>{
    int name;
    int distance;

    public Town(int name, int distance) {
        this.name = name;
        this.distance = distance;
    }

    @Override
    public int compareTo(Town o) {
        return this.distance - o.distance;
    }
}
