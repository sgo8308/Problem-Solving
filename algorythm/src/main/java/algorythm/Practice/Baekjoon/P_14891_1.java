package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class P_14891_1 {

    static int n;
    static int m;
    static int[][] board;

    //일단 큰 그림을 먼저 그려보자. 이 문제는 시뮬레이션이다.
    //문제에서 말하는대로 구현하면 된다.
    //톱니를 만든다. 톱니는 자신의 순서와 각 방향 별 값, 왼쪽 오른쪽 참조 톱니, 방금 돌려졌는지 불린을 변수로 갖는다.

    //1. 방향대로 톱니를 돌린다.
    //2. 이 때 돌린 톱니에 맞춰 다른 톱니도 돌려준다.
    //3. 주어진 만큼 돌린다.
    //4. 최종 점수를 구한다.

    //톱니를 돌릴 때는 한번에 돌려야 한다.
    //선택된 톱니를 돌리면서 자기 왼쪽과 오른쪽 톱니를 돌리도록 한다. 이 때 왼쪽 오른쪽 톱니를 먼저 돌리고 자기 자신을 돌릴 것.
    //한번씩 다돌렸으면 불린 플래그를 초기화시켜준다.
    //각자 점수를 계산하고 더해준다.

    //시간복잡도 계산
    //최대 100번 돌린다.
    //톱니 하나를 돌릴 때 8번 반복 , 4개가 전부 영향 받으면 32번
    //100번 돌리면 3200번 O(32 * N) => O(N)

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        List<Saw> saws = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            saws.add(new Saw(i, st.nextToken()));
        }

        for (int i = 0; i < 4; i++) {
            Saw saw = saws.get(i);
            if (i < 3) {
                saw.right = saws.get(i + 1);
            }

            if (i > 0) {
                saw.left = saws.get(i - 1);
            }
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int count = Integer.parseInt(st.nextToken());

        List<Integer[]> turns = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            st = new StringTokenizer(br.readLine());
            Integer[] turn = {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
            turns.add(turn);
        }

        for (int i = 0; i < count; i++) {
            int selectedSawOrder = turns.get(i)[0];
            int direction = turns.get(i)[1];

            Saw selectedSaw = saws.get(selectedSawOrder - 1);
            selectedSaw.turn(direction);

            saws.forEach((saw) -> saw.resetIsTurned());
        }

        System.out.println(saws.stream().mapToInt((saw) -> saw.calculateScore()).sum());
    }
}

class Saw {

    int order;
    int twelve;
    int one;
    int three;
    int five;
    int six;
    int seven;
    int nine;
    int eleven;
    Saw left;
    Saw right;
    boolean isTurned = false;

    public Saw(int order, String nums) {
        this.order = order;
        char[] chars = nums.toCharArray();
        this.twelve = Character.getNumericValue(chars[0]);
        this.one = Character.getNumericValue(chars[1]);
        this.three = Character.getNumericValue(chars[2]);
        this.five = Character.getNumericValue(chars[3]);
        this.six = Character.getNumericValue(chars[4]);
        this.seven = Character.getNumericValue(chars[5]);
        this.nine = Character.getNumericValue(chars[6]);
        this.eleven = Character.getNumericValue(chars[7]);
    }

    public void turn(int direction) {
        isTurned = true;
        if (left != null) {
            left.turnIfPossible("right", direction);
        }

        if (right != null) {
            right.turnIfPossible("left", direction);
        }

        if (direction == 1) {
            int tmp = eleven;
            eleven = nine;
            nine = seven;
            seven = six;
            six = five;
            five = three;
            three = one;
            one = twelve;
            twelve = tmp;
        } else {
            int tmp = one;
            one = three;
            three = five;
            five = six;
            six = seven;
            seven = nine;
            nine = eleven;
            eleven = twelve;
            twelve = tmp;
        }
    }

    private void turnIfPossible(String from, int direction) {
        if(isTurned) return;

        if (from.equals("left")) {
            if (left.three != nine) {
                turn(-direction);
            }
        } else {
            if (right.nine != three) {
                turn(-direction);
            }
        }
    }

    public void resetIsTurned() {
        isTurned = false;
    }

    public int calculateScore() {
        if(twelve == 0) return 0;

        return (int) Math.pow(2, order);
    }

}
