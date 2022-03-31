package algorythm.Practice.programmers.step2;

import java.util.Arrays;

public class TriangleSnail {
    public static void main(String[] args) {
        int n = 5;
        System.out.println(Arrays.toString(solution(n)));
    }

    static public int[] solution(int n) {
        String[] directions = {"down", "right", "up"};
        int[][] snail = initSnail(n);

        int allCount = n * (n+1) / 2;
        int row = -1;
        int col = 0;
        int directionIdx = 0;
        for (int i = 0; i < allCount; i++) {
            String direction = directions[directionIdx];
            if(direction.equals("down")){
                if(row < n - 1 && snail[row + 1][col] == 0){
                    snail[row + 1][col] = i + 1;
                    row = row + 1;
                    if(i == allCount) break;
                }else{
                    directionIdx = (directionIdx + 1) % 3;
                    i -= 1;
                }
            }else if(direction.equals("right")){
                if(col < n - 1 && snail[row][col + 1] == 0){
                    snail[row][col + 1] = i + 1;
                    col = col + 1;
                    if(i == allCount) break;
                }else{
                    directionIdx = (directionIdx + 1) % 3;
                    i -= 1;
                }
            }else{
                if(snail[row - 1][col - 1] == 0){
                    snail[row - 1][col - 1] = i + 1;
                    row = row - 1;
                    col = col - 1;
                    if(i == allCount) break;
                }else{
                    directionIdx = (directionIdx + 1) % 3;
                    i -= 1;
                }
            }
        }

        int[] answer = new int[allCount];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < snail[i].length; j++) {
                answer[idx] = snail[i][j];
                idx++;
            }
        }
        return answer;
    }



    //1.피라미드 모양으로 2차원 배열 만들기
    static int[][] initSnail(int n){
        int[][] snail = new int[n][];
        for (int i = 0; i < n; i++) {
            snail[i] = new int[i + 1];
        }
        return snail;
    }
    //2.다운 라이트 업 다운 으로 방향정해서 하나씩 채워가기.
    //  다운일 때는 x -1 , 같은 y인덱스, 라이트는 y인덱스 + 1 업일 때는 x인덱스 - 1, y - 1



}
