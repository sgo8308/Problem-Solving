package algorythm.Practice.programmers.step2;

public class JumpAndTeleport {
    public static void main(String[] args) {
        System.out.println(solution(5000));
    }

    static public int solution(int n) {
        int batteryUse = 0;
        while (true){
            if(n % 2 != 0){
                batteryUse++;
                n -= 1;
            }
            n /= 2;

            if(n == 0) break;
        }
        return batteryUse;
    }
}
