package algorythm.Practice.programmers.step3;

import java.util.*;

class TrainerIssue {

    public static void main(String[] args) {
        int n = 3;
        int m = 2;

        int[][] tt = {{1170, 1210}, {1200, 1260}};

        TrainerIssue ti = new TrainerIssue();

        ti.solution(n, m, tt);
    }


    public int solution(int n, int m, int[][] timetable) {
        N = n;
        int max = calMax(timetable);

        if(max == 1) return 0;

        int maxDist = 0;
        for(int i = 4; i <= (2 * n) - 2; i++){
            if(isPossible(n, i, max)){// i거리에 대해 max명 가능한지
                maxDist = i;
                continue;
            }

            break;
        }

        return maxDist;
    }

    void pln(int s){
        System.out.println(s);
    }

    int calMax(int[][] tt){
        int[] arr = new int[1330];

        for(int[] t : tt){
            arr[t[0]] += 1;
            arr[t[1] + 1] -= 1;
        }

        int val = 0;
        int max = 1;
        for(int i = 600; i < arr.length; i++){
            val += arr[i];
            max = Math.max(max, val);
        }

        return max;
    }

    boolean isPossible(int n, int dist, int manCnt){
        Map<Integer, Set<Integer>> graph = getGraph(n, dist);

        suc = false;
        for(int i = 1; i <= n * n; i++){
            Set<Integer> visited = new HashSet<>();
            visited.add(i);
            dfs(i, graph, visited, manCnt);
        }

        return suc;
    }

    boolean suc = false;

    void dfs(int node, Map<Integer, Set<Integer>> graph, Set<Integer> visited, int manCnt){
        if(visited.size() == manCnt){
            suc = true;
            return;
        }

        if(!check(graph, visited)){
            return;
        }

        Set<Integer> neighbors = graph.get(node);

        for(Integer no : neighbors){
            if(visited.contains(no)) continue;
            visited.add(no);
            dfs(no, graph, visited, manCnt);
            visited.remove(no);
        }
    }

    boolean check(Map<Integer, Set<Integer>> graph, Set<Integer> visited){
        for(Integer node : visited){
            Set<Integer> neighbors = graph.get(node);

            for(Integer no : visited){
                if(no == node) continue;

                if(!neighbors.contains(no))
                    return false;
            }
        }

        return true;
    }


    Map<Integer, Set<Integer>> getGraph(int n, int dist){
        Map<Integer, Set<Integer>> map = new HashMap<>();

        for(int i = 1; i <= n * n; i++){
            for(int j = 1; j <= n * n; j++){
                map.putIfAbsent(i, new HashSet());
                if(dist <= calDist(i, j)){
                    map.get(i).add(j);
                };
            }
        }

        return map;
    }

    int calDist(int i, int j){
        int[] xy1 = getXY(i);
        int[] xy2 = getXY(j);

        int a = Math.abs(xy1[0] - xy2[0]);
        int b = Math.abs(xy1[1] - xy2[1]);

        return a + b;
    }

    int N;
    int[] getXY(int i){
        int[] a = new int[2];
        if(i > 0) i -= 1;

        a[0] = (i / N) + 1;
        a[1] = (i % N) + 1;

        return a;
    }
}
