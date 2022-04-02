package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P_1780_1 {
    public static void main(String[] args) throws Exception{
        sliceAndFind(new int[27][27], new Answer());

        //O(N^2)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] board = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Answer ans = new Answer();

        find(board, ans);

        System.out.println(ans.minus1Count);
        System.out.println(ans.zeroCount);
        System.out.println(ans.plus1Count);
    }

    private static void find(int[][] board, Answer ans) {
        //첫 원소를 기준으로 같은 모두가 같은 값으로 채워졌는지 비교
        //중간에 다른 값이 나오면 9개의 블륵으로 나눈 후 다시 find 진행
        int firstElement = board[0][0];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] != firstElement) {
                    sliceAndFind(board, ans);
                    return;
                }
            }
        }

        ans.addCount(firstElement);
    }

    private static void sliceAndFind(int[][] board, Answer ans) {
        int newBoardN = board.length / 3;

        for (int i = 0; i < newBoardN * 3; i+= newBoardN) {
            for (int j = 0; j < newBoardN * 3; j+= newBoardN) {

                int[][] newBoard = new int[newBoardN][newBoardN];

                for (int k = i; k < i + newBoardN; k++) {
                    for (int l = j; l < j + newBoardN; l++) {
                        newBoard[k - i][l - j] = board[k][l];
                    }
                }

                find(newBoard, ans);
            }
        }
    }

    static class Answer {
        int minus1Count = 0;
        int plus1Count = 0;
        int zeroCount = 0;

        void addCount(int num) {
            switch (num) {
                case -1:
                    minus1Count++;
                    break;
                case 0:
                    zeroCount++;
                    break;
                case 1:
                    plus1Count++;
                    break;
            }
        }
    }
}
