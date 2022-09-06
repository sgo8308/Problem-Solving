package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.function.Function;

public class P_14891_2 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        List<Saw2> saws = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            saws.add(new Saw2(i, st.nextToken()));
        }

        for (int i = 0; i < 4; i++) {
            Saw2 saw = saws.get(i);
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

            Saw2 selectedSaw = saws.get(selectedSawOrder - 1);
            selectedSaw.turn(direction);
        }

        System.out.println(saws.stream().mapToInt((saw) -> saw.calculateScore()).sum());
    }
}

class Saw2 {

    int order;
    int[] magnets = new int[8];
    Saw2 left;
    Saw2 right;

    public Saw2(int order, String nums) {
        this.order = order;
        char[] chars = nums.toCharArray();
        for (int i = 0; i < nums.length(); i++) {
            this.magnets[i] = Character.getNumericValue(chars[i]);
        }
    }

    public void turn(int direction) {
        if (left != null) {
            left.turnByRight(direction);
        }

        if (right != null) {
            right.turnByLeft(direction);
        }

        rotate(direction);
    }

    private void rot(int direction, Function<Integer, Integer> next) {
        int tmp = magnets[7];
        for (int i = 7; i >= 1; i--) {
            magnets[i] = magnets[next.apply(i)];
        }
        magnets[0] = tmp;
    }

    private void rotate(int direction) {
        if (direction == 1) {
            int tmp = magnets[7];
            for (int i = 7; i >= 1; i--) {
                magnets[i] = magnets[i - 1];
            }
            magnets[0] = tmp;
        } else {
            int tmp = magnets[7];
            for (int i = 7; i >= 1; i--) {
                magnets[i] = magnets[(i + 1) % 8];
            }
            magnets[6] = tmp;
        }
    }

    private void turnByLeft(int direction) {
        if (left.magnets[2] != magnets[6]) {
            if (right != null) {
                right.turnByLeft(-direction);
            }
            rotate(-direction);
        }
    }

    private void turnByRight(int direction) {
        if (right.magnets[6] != magnets[2]) {
            if (left != null) {
                left.turnByRight(-direction);
            }

            rotate(-direction);
        }
    }

    public int calculateScore() {
        if (magnets[0] == 0) {
            return 0;
        }

        return (int) Math.pow(2, order);
    }

}

