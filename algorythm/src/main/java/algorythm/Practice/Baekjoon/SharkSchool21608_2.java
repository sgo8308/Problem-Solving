package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class SharkSchool21608_2 {
    static int N;
    static HashMap<Integer, ArrayList<Integer>> studentsLikeMap;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws Exception{
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
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++) seats[i][j] = 0;

        for (int i = 0; i < studentsNum; i++) {
            int student = studentsOrdered.get(i);
            Seat seat = getSeat(student, seats);
            seats[seat.row][seat.column] = student;
        }

        int answer = calSatis(seats);
        System.out.println(answer);
    }

    static Seat getSeat(int student, int[][] seats){
        ArrayList<Seat> ss = new ArrayList<>();

        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats.length; j++) {
                if(seats[i][j] != 0) continue;

                int likeCnt = 0;
                int blankCnt = 0;

                for (int k = 0; k < 4; k++) {
                    int xx = i + dx[k];
                    int yy = j + dy[k];

                    if(!(xx >= 0 && xx < N && yy >= 0 && yy < N))
                        continue;

                    if(studentsLikeMap.get(student).contains(seats[xx][yy]))
                        likeCnt++;
                    if(seats[xx][yy] == 0)
                        blankCnt++;
                }

                Seat seat = new Seat(i ,j ,likeCnt ,blankCnt);
                ss.add(seat);
            }
        }
        Collections.sort(ss);
        return ss.get(0);
    }

    static int calSatis(int[][] seats){
        int allSatis = 0;

        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats.length; j++) {
                int student = seats[i][j];
                int likeCnt = 0;
                for (int k = 0; k < 4; k++) {
                    int xx = i + dx[k];
                    int yy = j + dy[k];

                    if(!(xx >= 0 && xx < N && yy >= 0 && yy < N))
                        continue;

                    if(studentsLikeMap.get(student).contains(seats[xx][yy]))
                        likeCnt++;
                }
                int satis = 0;
                if (likeCnt != 0)
                    satis = (int)Math.pow(10, likeCnt - 1);
                allSatis += satis;
            }
        }
        return allSatis;
    }
}

class Seat implements Comparable<Seat>{
    int row, column, likeCnt, blankCnt;

    public Seat(int row, int column, int likeCnt, int blankCnt) {
        this.row = row;
        this.column = column;
        this.likeCnt = likeCnt;
        this.blankCnt = blankCnt;
    }

    @Override
    public int compareTo(Seat o) {
        if(this.likeCnt != o.likeCnt) return o.likeCnt - this.likeCnt;
        if(this.blankCnt != o.blankCnt) return o.blankCnt - this.blankCnt;
        if(this.row != o.row) return o.row - this.row;
        return this.column = o.column;
    }
}
