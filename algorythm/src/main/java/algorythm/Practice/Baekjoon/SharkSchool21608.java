package algorythm.Practice.Baekjoon;

import jdk.swing.interop.SwingInterOpUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class SharkSchool21608 {
    // 풀이 1
    static int N;
    static HashMap<Integer,ArrayList<Integer>> studentsLikeMap;
    public static void main(String[] args) throws Exception{
        //가장 왼쪽 윗쪽칸 (1,1) 가장 오른쪽 아래칸 (n,n)

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int studentsNum = N * N;

        studentsLikeMap = new HashMap<>();
        ArrayList<Integer> studentsOrdered = new ArrayList<>();
        for (int i = 0; i < studentsNum; i++) {
            st = new StringTokenizer(br.readLine());

            int student = Integer.parseInt(st.nextToken());
            studentsOrdered.add(student);

            studentsLikeMap.putIfAbsent(student, new ArrayList<>());

            for (int j = 0; j < 4; j++) {
                ArrayList<Integer> likeList = studentsLikeMap.get(student);
                likeList.add(Integer.parseInt(st.nextToken()));
            }
        }

        int[][] seats = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                seats[i][j] = 0;
            }
        }

        for (int i = 0; i < studentsNum; i++) {
            ArrayList<Integer[]> seatCandidates;
            int student = studentsOrdered.get(i);
            seatCandidates = applyRule1(student, seats);

            if(seatCandidates.size() != 1)
                seatCandidates = applyRule2(student, seats, seatCandidates);

            if(seatCandidates.size() != 1)
                seatCandidates = applyRule3_1(student, seats, seatCandidates);

            if(seatCandidates.size() != 1)
                seatCandidates = applyRule3_2(student, seats, seatCandidates);

            int[] seat = {seatCandidates.get(0)[0], seatCandidates.get(0)[1]};

            seats[seat[0]][seat[1]] = student;

            for (int j = 0; j < seats.length; j++) {
                System.out.println(Arrays.toString(seats[j]));
            }

            System.out.println("---------");

        }

        int answer = calculateSatisfaction(seats);
        System.out.println(answer);
    }
    static ArrayList<Integer[]> applyRule1(int student, int[][] seats){
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};

        ArrayList<Integer[]> seatCandidates = new ArrayList<>();
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                Integer[] seat = {i, j};
                if(!checkSeatAvailable(seats, seat))
                    continue;

                int count = 0;
                for (int k = 0; k < 4; k++) {
                    int xx = i + dx[k];
                    int yy = j + dy[k];

                    if(xx >= 0 && yy >= 0 && xx < N && yy < N){
                        if(studentsLikeMap.get(student).contains(seats[xx][yy]))
                            count++;
                    }
                }

                if(count == max)
                    seatCandidates.add(seat);
                else if(count > max){
                    seatCandidates.clear();
                    seatCandidates.add(seat);
                    max = count;
                }
            }
        }

        return seatCandidates;
    }
    static ArrayList<Integer[]> applyRule2(int student, int[][] seats, ArrayList<Integer[]> seatCandidates){
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};

        int max = Integer.MIN_VALUE;
        ArrayList<Integer[]> seatCandidates2 = new ArrayList<>();
        for (int i = 0; i < seatCandidates.size(); i++) {
            Integer[] seatCand = seatCandidates.get(i);
            int count = 0;
            for (int j = 0; j < 4; j++) {
                int xx = seatCand[0] + dx[j];
                int yy = seatCand[1] + dy[j];

                if(xx >= 0 && yy >= 0 && xx < N && yy < N){
                    if(seats[xx][yy] == 0)
                        count++;
                }
            }

            if(count == max)
                seatCandidates2.add(seatCand);
            else if(count > max){
                seatCandidates2.clear();
                seatCandidates2.add(seatCand);
                max = count;
            }
        }
        return seatCandidates2;
    }
    static ArrayList<Integer[]> applyRule3_1(int student, int[][] seats, ArrayList<Integer[]> seatCandidates){
        ArrayList<Integer[]> seatCandidates2 = new ArrayList<>();
        int minRow = Integer.MAX_VALUE;

        for (int i = 0; i < seatCandidates.size(); i++) {
            Integer[] seat = seatCandidates.get(i);

            if(seat[0] == minRow)
                seatCandidates2.add(seat);
            else if(seat[0] < minRow){
                seatCandidates2.clear();
                seatCandidates2.add(seat);
                minRow = seat[0];
            }

        }

        return seatCandidates2;
    }
    static ArrayList<Integer[]> applyRule3_2(int student, int[][] seats, ArrayList<Integer[]> seatCandidates){
        ArrayList<Integer[]> seatCandidates2 = new ArrayList<>();
        int minColumn = Integer.MAX_VALUE;

        for (int i = 0; i < seatCandidates.size(); i++) {
            Integer[] seat = seatCandidates.get(i);

            if(seat[1] == minColumn)
                seatCandidates2.add(seat);
            else if(seat[1] < minColumn){
                seatCandidates2.clear();
                seatCandidates2.add(seat);
                minColumn = seat[1];
            }
        }

        return seatCandidates2;
    }
    static int calculateSatisfaction(int[][] seats){
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};

        int allSatisfaction = 0;

        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats.length; j++) {

                int count = 0;
                int student = seats[i][j];

                for (int k = 0; k < 4; k++) {
                    int xx = i + dx[k];
                    int yy = j + dy[k];

                    if(xx >= 0 && yy >= 0 && xx < N && yy < N){
                        if(studentsLikeMap.get(student).contains(seats[xx][yy]))
                            count++;
                    }
                }
                int satisfaction = 0;
                if(count != 0)
                    satisfaction = (int)Math.pow(10, count - 1);

                System.out.println(satisfaction);
                allSatisfaction += satisfaction;
            }
        }

         return allSatisfaction;
    }
    static boolean checkSeatAvailable(int[][] seats, Integer[] seat){
        if (seats[seat[0]][seat[1]] == 0)
            return true;
        return false;
    }
}
