package algorythm.Practice.programmers.step2;

import java.util.ArrayList;
import java.util.HashSet;

public class Friends4Block {
    public static void main(String[] args) {
        int m = 4;
        int n = 5;
        String[] board = {"CCBDE", "AAADE", "AAABF", "CCBBF"};
//        int m = 6;
//        int n = 6;
//        String[] board = {"TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"};
//        System.out.println(solution(m,n,board));

//        int m = 6;
//        int n = 6;
//        String[] board = {"TTTTTT", "TTTTTT", "TTTTTT", "TTTTTT", "TTTTTT", "TTTTTT"};
        System.out.println(solution(m,n,board));
    }

    static String[] s_board;
    static int row;
    static int col;
    static public int solution(int m, int n, String[] board) {
        s_board = board;
        row = m;
        col = n;

        HashSet<Integer[]> eraseSet = new HashSet<>();
        eraseSet.add(new Integer[]{0,0});
        int answer = 0;
        while(eraseSet.size() != 0){

            eraseSet.clear();

            for (int i = 0; i < row - 1; i++) {

                for (int j = 0; j < col - 1; j++) {
                    if(s_board[i].charAt(j) == '0') continue;
                    ArrayList<Integer[]> eraseList = getEraseList(i, j);
                    for (int k = 0; k < eraseList.size(); k++)
                        eraseSet.add(eraseList.get(k));
                }
            }
            answer += erase(eraseSet);
            move();
        }

        return answer;
    }

    static ArrayList<Integer[]> getEraseList(int startRow, int startCol){
        char nowNode = s_board[startRow].charAt(startCol);
        char nodeDown = s_board[startRow + 1].charAt(startCol);
        char nodeDiagonal = s_board[startRow + 1].charAt(startCol + 1);
        char nodeRight = s_board[startRow].charAt(startCol + 1);

        if(nowNode == '0') return new ArrayList<>();

        ArrayList<Integer[]> list = new ArrayList<>();

        if(nowNode == nodeDown && nowNode == nodeDiagonal && nowNode == nodeRight){
            list.add(new Integer[]{startRow, startCol});
            list.add(new Integer[]{startRow + 1, startCol});
            list.add(new Integer[]{startRow + 1, startCol + 1});
            list.add(new Integer[]{startRow, startCol + 1});
            return list;
        }

        return new ArrayList<>();
    }

    static int erase(HashSet<Integer[]> eraseSet){
        int count = 0;
        for (Integer[] eraseRowCol : eraseSet) {
            int row = eraseRowCol[0];
            int col = eraseRowCol[1];
            if(s_board[row].charAt(col) == '0') continue;

            s_board[row] = s_board[row].substring(0, col) + '0' + s_board[row].substring(col + 1);
            count++;
        }
        return count;
    }

    static void move(){
        boolean flag = true;
        while(flag){
            flag = false;
            for (int i = row - 2; i >= 0; i--) {
                for (int j = 0; j < col; j++) {
                    char nowNode = s_board[i].charAt(j);
                    if(nowNode == '0') continue;

                    char nodeBelow = s_board[i + 1].charAt(j);
                    if(nodeBelow == '0'){
                        s_board[i + 1] = s_board[i + 1].substring(0, j) + nowNode + s_board[i + 1].substring(j + 1);
                        s_board[i] = s_board[i].substring(0, j) + '0' + s_board[i].substring(j + 1);
                        flag = true;
                    }
                }
            }
        }
    }
}
