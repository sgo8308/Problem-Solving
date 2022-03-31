package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class P_21611_1 {
    static int N,M;
    static int explode1Num = 0, explode2Num = 0, explode3Num = 0;
    static int[][] indexSquare;
    static  ArrayList<Integer> routes;
    public static void main(String[] args) throws Exception {
        //N < 49 , M < 100
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        routes = getRoutes();
        indexSquare = createIndexSquare();

        //0. 격자 세팅
        int[][] square = new int[N][N];
        for (int i = 0; i < square.length; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < square.length; j++) {
                square[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        ArrayList<Integer> list = toList(square);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int direction = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());

            ArrayList<Integer> indexes = getIndexForBlizzard(direction, distance);
            blizzard(list, indexes);
            boolean flag = true;
            while(flag)
                flag = explode(list);

            list = change(list);
        }


        int answer = 1 * explode1Num + 2 * explode2Num + 3 * explode3Num;
        System.out.println(answer);
    }

    //0_1 격자 리스트로 변환
    static ArrayList<Integer> toList(int[][] square){
        int[] LEFT = {0,-1}, RIGHT = {0, 1}, DOWN = {1,0}, UP = {-1,0};
        int[][] directions = {LEFT, DOWN, RIGHT, UP};
        int nowPointRow = (N+1)/2 - 1;
        int nowPointCol = (N+1)/2 - 1;

        ArrayList<Integer> list = new ArrayList<>();

        int directionNum = 0;
        for (Integer route : routes) {
            int[] direction = directions[directionNum % 4];

            for (int i = 0; i < route; i++) {
                nowPointRow += direction[0];
                nowPointCol += direction[1];
                if(nowPointRow >= N || nowPointCol >= N || nowPointRow < 0 || nowPointCol < 0)
                    break;
                list.add(square[nowPointRow][nowPointCol]);
            }
            directionNum ++;
        }

        return list;
    }

    static ArrayList<Integer> getRoutes(){
        ArrayList<Integer> routes = new ArrayList<>();
        int count = 1;
        while(routes.stream().mapToInt(t->t).sum() < N*N){

            for (int i = 0; i < 2; i++) {
                routes.add(count);
            }
            count++;
        }

        return routes;
    }
    //1. 블리자드 (뒤 인덱스부터 없애기)
    static void blizzard(ArrayList<Integer> list, ArrayList<Integer> indexlist){
        for (int i = indexlist.size() - 1; i >= 0; i--) {
            int index = indexlist.get(i);
            if(index < list.size())
                list.remove(index);
        }
    }

    //삭제할 인덱스 구하기
    static ArrayList<Integer> getIndexForBlizzard(int directionIndex, int distance){
        int[] LEFT = {0,-1}, RIGHT = {0, 1}, DOWN = {1,0}, UP = {-1,0};
        int[][] directions = {{0,0}, UP, DOWN, LEFT, RIGHT};
        int[] dir = directions[directionIndex];
        ArrayList<Integer> indexes = new ArrayList<>();
        int nowPointRow = (N+1)/2 - 1;
        int nowPointCol = (N+1)/2 - 1;

        for (int i = 0; i < distance; i++) {
            nowPointRow += dir[0];
            nowPointCol += dir[1];

            indexes.add(indexSquare[nowPointRow][nowPointCol]);
        }
        return indexes;
    }

    //격자의 칸 번호 구하기
    static int[][] createIndexSquare(){
        int[][] indexSquare = new int[N][N];

        int[] LEFT = {0,-1}, RIGHT = {0, 1}, DOWN = {1,0}, UP = {-1,0};
        int[][] directions = {LEFT, DOWN, RIGHT, UP};
        int nowPointRow = (N+1)/2 - 1;
        int nowPointCol = (N+1)/2 - 1;

        ArrayList<Integer> list = new ArrayList<>();

        int directionNum = 0;
        int index = 0;
        main:
        for (Integer route : routes) {
            int[] direction = directions[directionNum % 4];

            for (int i = 0; i < route; i++) {
                nowPointRow += direction[0];
                nowPointCol += direction[1];
                if(nowPointRow >= N || nowPointCol >= N || nowPointRow < 0 || nowPointCol < 0)
                    break main;
                indexSquare[nowPointRow][nowPointCol] = index;
                index++;
            }
            directionNum ++;
        }

        return indexSquare;
    }
    //2. 빈칸으로 구슬 옮기기 - 자동
    //3. 구슬 폭발 ,뒤 인덱스부터 없애기
    static boolean explode(ArrayList<Integer> list){
        boolean flag = false;
        int count = 1;
        int previousBead = -1;
        for (int i = 0; i < list.size(); i++) {
            int nowBead = list.get(i);

            if(nowBead == previousBead)
                count++;
            else{
                if(count >= 4){
                    for (int j = 0; j < count; j++) {
                        list.remove(i - 1 - j);
                    }
                    countingExplode(previousBead, count);

                    i = (i - count >= 0) ? i - count : 0;
                    flag = true;
                }
                count = 1;
            }

            previousBead = nowBead;
            if (nowBead == 0) break;
        }
        return flag;
    }

    static void countingExplode(int bead, int count){
        switch (bead){
            case 1:
                explode1Num += count;
                break;
            case 2:
                explode2Num += count;
                break;
            case 3:
                explode3Num += count;
                break;
        }
    }


    //4. 구슬의 변화 (구슬이 칸의 수보다 많아 칸에 못들어가면 구슬 사라짐)
    static ArrayList<Integer> change(ArrayList<Integer> list){
        ArrayList<Integer> newList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            int bead = list.get(i);
            if(bead == 0 || newList.size() == N*N - 1)
                break;
            int count = getConsecutiveBeadNum(list, bead, i);
            newList.add(count);
            if(newList.size() == N*N - 1)
                break;
            newList.add(bead);
            i += count - 1;
        }

        return newList;
    }

    static int getConsecutiveBeadNum(ArrayList<Integer> list, int bead, int index){
        int count = 0;
        for (int i = index; i < list.size(); i++) {
            if (list.get(i) == bead)
                count++;
            else
                break;
        }
        return count;
    }
}
