package algorythm.Practice.Leetcode;

public class P_1004_1 {



    public static void main(String[] args) {
//        int[] nums = {1,1,1,0,0,0,1,1,1,1,0};
//        int k = 2;
        int[] nums = {0,0,1,1,1,0,0};
        int k = 0;
        System.out.println(longestOnes(nums, k));
    }

    /**
     * 필요 시간복잡도 : O(nlogn) 이하
     *
     *  가장 긴거를 찾아야 된다
     *  처음부터 윈도우 크기 늘리면서 0을 만나면 무조건 1로 바꾸고 해서 k만큼 바꿨고 0을 만날 때까지 가서 길이 체크
     *  이 때 옆으로 움직이면서 맨 첫음 꺼를 배줘야 하는데 이게 0이었으면 k회복 해야되고
     *  그럴려면 1과 0을 구분해줘야 함
     *  원본 배열은 그대로 두고 딥카피해서 새로운 배열을 만든다.
     *  근데 이렇게 할 필요가 있을까? 어차피 굳이 1로 변형안시켜도 right만 움직여주고 길이만 보면되는건데
     *  근데 왼쪽에서 땡길 때 0인지 체크해야하나?
     *  잠깐만 갑자기 헷갈린다. left right를 어떤 식으로 움직이지?
     *  어떻게 보면 k는 right가 0을 건너갈 수 있는 티켓이라고 생각하자.
     *  건너갈 수 잇으면 최대한 건너가고 건너가지 못하면 left를 땡기면서 티켓을 획득한다.
     *  left가 다가와서 right와 만났을 때는 어떻게 하지?
     *  이 상황은 사실 k가 1이라도 있다면 계속한칸씩 갈 수 있는데 이상황이 왔다는거는 k가 0이라는 소리
     *  그런 경우는 1을 만날 때까지 left랑 같이 이동 그리고 다시 시작
     *  시간복잡도는 처음부터끝까지 right가 움직이고 left도 움직이면 최대 2n번 걸린다. 101010101010 이런 식이면 left right 둘 다 움직여야함
     *
     *  엣지케이스는 뭐가있을까
     *  nums = any k = 0 , nums = [1] k = 0   nums = any k = nums.length => nums.length
     */

    static public int longestOnes(int[] nums, int k) {
        int left = 0, right = 0;
        int max = Integer.MIN_VALUE;

        while (right < nums.length) {

            if (canMoveRight(nums, right, k)) {
                if(nums[right] == 0) k--;
                right++;
            } else {
                if(nums[left] == 0) k++;
                left++;
            }

            max = Math.max(max, right - left);

            if (left == right && !canMoveRight(nums, right, k)) {
                while (!canMoveRight(nums, right, k) && right < nums.length) {
                    right++;
                    left++;
                }
            }
        }

        return max;
    }

    private static boolean canMoveRight(int[] nums, int right, int k) {
        return nums[right] == 1 || k > 0;
    }
}
