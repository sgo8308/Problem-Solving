package algorythm.Practice.Leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class OceanView {

    int value  = 3;

    public static void main(String[] args) {
        OceanView ov = new OceanView();
        int[] heigths1 = {4, 2, 3, 1};
        int[] heigths2 = {4, 3, 2, 1};
        int[] heigths3 = {1, 3, 2, 4};
        int[] heigths4 = {2, 2, 2, 2};
        System.out.println(Arrays.toString(ov.getOceavViewIndex(heigths1)));
        System.out.println(Arrays.toString(ov.getOceavViewIndex(heigths2)));
        System.out.println(Arrays.toString(ov.getOceavViewIndex(heigths3)));
        System.out.println(Arrays.toString(ov.getOceavViewIndex(heigths4)));
    }

    public int[] getOceavViewIndex(int[] heights) {
        Deque<Building> OVBuildings = new ArrayDeque<>();

        for (int i = 0; i < heights.length; i++) {

            Building nowBuilding = new Building(i, heights[i]);
            while (!OVBuildings.isEmpty() && OVBuildings.getFirst().height <= nowBuilding.height) {
                OVBuildings.poll();
            }

            OVBuildings.addFirst(nowBuilding);
        }

        return getSortedIndexes(OVBuildings);
    }

    private int[] getSortedIndexes(Deque<Building> OVBuildings) {
        Override o = getClass().getAnnotation(Override.class);

        int[] answer = new int[OVBuildings.size()];
        for (int i = answer.length - 1; i >= 0; i--) {
            answer[i] = OVBuildings.removeFirst().index;
        }
        return answer;
    }

    class Building {
        int index;
        int height;

        @Override
        public int hashCode() {
            return super.hashCode();
        }

        public Building(int index, int height) {
            this.index = index;
            this.height = height;
        }
    }
}
