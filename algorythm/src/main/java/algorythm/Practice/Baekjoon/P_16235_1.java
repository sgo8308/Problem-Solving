package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.function.Consumer;

public class P_16235_1 {
    static int n;
    static int m;
    static int k;
    static Block[][] board;

    /****
     * N * N 땅 M개의 나무 K년 후 r과 c는 1부터 시작, n,m 바뀌지 않음
     *
     * 봄에는 나무가 자신의 나이만큼 양분을 먹고 나이가 1 증가
     * 나무가 양분을 먹을 수 없으면 즉시 죽는다.
     * 여름에는 봄에 죽은 나무가 양분으로 변하며, 죽은 나무의 나이를 2로 나눈 값이 해당 칸에 양분으로 추가된다.
     * 가을에는 나무가 번식하는데, 나이가 5의 배수인 나무는 인접한 8개의 칸에 나이가 1인 나무가 생긴다.
     * 겨울에는 S2D2가 땅을 돌아다니면서 땅에 양분을 추가한다.
     * 각 칸에 추가되는 양분의 양은 A[r][c]이고, 입력으로 주어진다.
     *
     * arraylist 로 하고 뒤에다가 새로운 나무 추가 내림차순
     */

    public static void main(String[] args) throws Exception{
        // 초기 세팅
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        board = new Block[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = new Block(i, j, Integer.parseInt(st.nextToken()));
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int age = Integer.parseInt(st.nextToken());
            board[x][y].addTree(age);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j].sortTree();
            }
        }
        // 로직
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < n; j++) {
                for (int l = 0; l < n; l++) {
                    board[j][l].spring();
                }
            }

            for (int j = 0; j < n; j++) {
                for (int l = 0; l < n; l++) {
                    board[j][l].summer();
                }
            }

            for (int j = 0; j < n; j++) {
                for (int l = 0; l < n; l++) {
                    board[j][l].fall(board);
                }
            }

            for (int j = 0; j < n; j++) {
                for (int l = 0; l < n; l++) {
                    board[j][l].winter();
                }
            }
        }

        //출력
        int sum = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sum += board[i][j].getTreeCount();
            }
        }

        System.out.println(sum);
    }

    static class Block{
        int x,y;
        List<Integer> trees;
        int nutrientAmount;
        int addNutrient;
        int firstDeadTreeIdx;

        public Block(int x, int y, int addNutrient) {
            this.x = x;
            this.y = y;
            this.addNutrient = addNutrient;
            this.nutrientAmount = 5;
            trees = new ArrayList<>();
            firstDeadTreeIdx = -1;
        }

        void spring() {
            for (int i = trees.size() - 1; i >= 0; i--) {
                Integer treeAge = trees.get(i);
                if (treeAge <= nutrientAmount) {
                    trees.set(i, treeAge + 1);
                    nutrientAmount -= treeAge;
                }else {
                    firstDeadTreeIdx = i;
                    break;
                }

                firstDeadTreeIdx = -1;
            }
        }

        void summer() {
            for (int i = 0; i <= firstDeadTreeIdx; i++) {
                if (trees.size() >= 1) {
                    nutrientAmount += trees.get(0) / 2;
                    trees.remove(0);
                }
            }
        }

        void fall(Block[][] board) {
            int[] dx = {1, 1, 1, 0, 0, -1, -1, -1};
            int[] dy = {-1, 0, 1,-1, 1, -1, 0, 1};

            for (int i = 0; i < trees.size(); i++) {
                if (trees.get(i) != 0 && trees.get(i) % 5 == 0) {
                    for (int j = 0; j < 8; j++) {
                        int xx = x + dx[j];
                        int yy = y + dy[j];

                        if (isInRange(xx, yy)) {
                            board[xx][yy].addTree(1);
                        }
                    }
                }
            }
        }

        void winter() {
            nutrientAmount += addNutrient;
        }

        boolean isInRange(int row, int col){
          return row >= 0 && row < n && col >= 0 && col < n;
        }

        void addTree(Integer treeAge) {
           trees.add(treeAge);
        }

        public void sortTree() {
            Collections.sort(trees, Collections.reverseOrder());
        }

        public int getTreeCount() {
            return trees.size();
        }
    }
}
