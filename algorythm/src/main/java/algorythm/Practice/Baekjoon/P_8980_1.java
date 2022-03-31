package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class P_8980_1 {

    static int townCount;
    static int maxWeight;
    static int boxCount;
    static List<Box> boxes = new ArrayList<>();
    static Integer[] remainingSpaces;

    public static void main(String[] args) throws Exception {
        getInputAndSetVariables();

        int boxWeightTotal = 0;
        for (int i = 0; i < boxes.size(); i++) {
            Box box = boxes.get(i);
            int remainingSpace = getRemainingSpace(box.from, box.to);
            if (box.weight <= remainingSpace) {
                boxWeightTotal += box.weight;
                reduceRemainingSpaces(box.from, box.to, box.weight);
                continue;
            }

            int boxWeight = remainingSpace;
            boxWeightTotal += boxWeight;
            reduceRemainingSpaces(box.from, box.to, boxWeight);
        }

        System.out.println(boxWeightTotal);
    }

    private static int getRemainingSpace(int from, int to) {
        return IntStream.range(from, to).map(townIdx -> remainingSpaces[townIdx]).min().getAsInt();
    }

    private static void reduceRemainingSpaces(int from, int to, int boxWeight) {
        for (int i = from; i < to; i++) {
            remainingSpaces[i] -= boxWeight;
        }
    }

    private static void getInputAndSetVariables() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        townCount = Integer.parseInt(st.nextToken());
        maxWeight = Integer.parseInt(st.nextToken());

        boxCount = Integer.parseInt(br.readLine());

        for (int i = 0; i < boxCount; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            int weight = Integer.parseInt(st.nextToken());
            boxes.add(new Box(from, to, weight));
        }

        remainingSpaces = new Integer[townCount];
        Arrays.fill(remainingSpaces, maxWeight);

        Collections.sort(boxes, (o1, o2) -> {
            if (o1.to == o2.to) {
                return Integer.compare(o1.from, o2.from);
            }
            return Integer.compare(o1.to, o2.to);
        });
    }
}

class Box {

    int from;
    int to;
    int weight;

    public Box(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
}
