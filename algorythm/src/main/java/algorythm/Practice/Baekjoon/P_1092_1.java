package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

enum Test {
    HELLO(50);

    private final int value;

    Test(int i) {
        this.value = i;
    }
}
public class P_1092_1 {
    public static List<BoxGroup> boxGroups;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        List<Integer> craneWeightLimits = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            craneWeightLimits.add(Integer.valueOf(st.nextToken()));
        }

        int M = Integer.parseInt(br.readLine());
        List<Integer> boxWeights = new LinkedList<>();
        for (int i = 0; i < M; i++) {
            boxWeights.add(Integer.valueOf(st.nextToken()));
        }
        boxGroups = new ArrayList<>();
        for (int i = 0; i < boxWeights.size(); i++) {

        }
    }


}

class Crane {

    int weightLimit;
    int groupIndex;
    List<BoxGroup> boxGroups;

    public Crane(int weightLimit, int groupIndex, List<BoxGroup> boxGroups) {
        this.weightLimit = weightLimit;
        this.groupIndex = groupIndex;
        this.boxGroups = boxGroups;
    }

    public void moveBox() {
        BoxGroup boxGroup = boxGroups.get(groupIndex);
        if (boxGroup.isEmpty()) {
            moveToNextGroup();

        }
        boxGroup.removeBox();
    }

    private void moveToNextGroup() {
        subtractGroupIndex();
        BoxGroup boxGroup = boxGroups.get(groupIndex);
        while (boxGroup.isEmpty()) {
            subtractGroupIndex();
            boxGroup = boxGroups.get(groupIndex);
        }
    }

    private void subtractGroupIndex() {
        groupIndex--;
    }
}

class BoxGroup {

    int boxCount = 0;

    void addBoxCount() {
        boxCount++;
    }

    public void removeBox() {
        boxCount--;
    }

    boolean isEmpty() {
        return boxCount < 1;
    }
}
