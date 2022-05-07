package algorythm.Practice.Leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum_2 {

    public static void main(String[] args) {
        int[] nums = {0, 0, 0};
        List<List<Integer>> lists = threeSum(nums);

        for (int i = 0; i < lists.size(); i++) {
            List<Integer> integers = lists.get(i);
            for (int j = 0; j < integers.size(); j++) {
                System.out.print(integers.get(j) + " ");
            }
            System.out.println();
        }

    }

    /*
     * O(N^2)
     * 1. 먼저 정렬을 함
     * 2. 이 후 for문을 돌면서 하나의 타겟을 고정해놓고 타겟보다 크거나 같은 나머지 2개를 더해서 -타겟이 나오도록 로직 구현
     *  - 이 때 타겟이 0보다 커지면 나머지 2개도 양수이기 때문에 위 사항이 성립안되므로 종료
     *  - 이 때 타겟이 이전 타겟과 같으면 다른 타겟이 나올 때까지 이동
     *
     * 2. 2개를 더해서 -타겟이 나오도록 구현하는 방식
     * 2개의 포인터를 이용 하나는 타겟 바로 다음 하나는 nums의 마지막.
     * 만약 두개 더한 값이 -타겟보다 작으면 왼쪽 포인터를 1 높히고 크면 오른쪽 포인터를 1 내림
     * 만약 두개 더한 값이 타겟과 같으면 List에 등록하고 각각의 포인터에 위치한 값이 이전 포인터에 위치한 값과 다를 때까지 땡겨줌
     * 이런 식으로 두 포인터가 서로 만날 때까지 진행
     * */
    static public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> answer = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int target = nums[i];
            if (target > 0) {break;}
            if (i != 0 && target == nums[i - 1]) {continue;}

            int ptr1 = i + 1;
            int ptr2 = nums.length - 1;
            while (ptr1 < ptr2) {
                if (nums[ptr1] + nums[ptr2] == -target) {
                    answer.add(Arrays.asList(target, nums[ptr1], nums[ptr2]));
                    ptr1 = movePtr1(nums, ptr1, ptr2);
                    ptr2 = movePtr2(nums, ptr1, ptr2);
                } else if (nums[ptr1] + nums[ptr2] < -target) {
                    ptr1 = movePtr1(nums, ptr1, ptr2);
                } else {
                    ptr2 = movePtr2(nums, ptr1, ptr2);
                }
            }
        }

        return answer;
    }

    private static int movePtr2(int[] nums, int ptr1, int ptr2) {
        do {
            ptr2--;
        } while (ptr1 < ptr2 && nums[ptr2 + 1] == nums[ptr2]);
        return ptr2;
    }

    private static int movePtr1(int[] nums, int ptr1, int ptr2) {
        do {
            ptr1++;
        } while (ptr1 < ptr2 && nums[ptr1 - 1] == nums[ptr1]);
        return ptr1;
    }
}
