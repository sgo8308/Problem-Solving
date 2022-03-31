package algorythm.Practice.programmers.step2;

public class NumberExpression {
    public static void main(String[] args) {

        System.out.println(solution(10000));
    }
    static public int solution(int n) {
        int count = 0;

        for (int i = 1; i < n / 2 + 1; i++) {
            int num = i;
            for (int j = i + 1; j < n; j++) {
                num += j;
                if(num >= n)
                    break;
            }
            if(num == n) count++;

        }

        return count + 1;
    }
}
