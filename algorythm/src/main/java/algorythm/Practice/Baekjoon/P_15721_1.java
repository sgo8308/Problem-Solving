package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class P_15721_1 {
    static int peopleOrder = 0;
    static final int BBUN = 0;
    static final int DEGGI = 1;
    private static long BBUNCount = 0;
    private static int target;
    private static int T;
    private static int peopleCount;
    private static long DEGGICount;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        peopleCount = Integer.parseInt(br.readLine());
        T = Integer.parseInt(br.readLine());
        target = Integer.parseInt(br.readLine());

        int count = 2;
        while (true) {
            checkBBUN(1);
            checkDEGGI(1);
            checkBBUN(1);
            checkDEGGI(1);
            checkBBUN(count);
            checkDEGGI(count);
            count++;
        }
    }

    private static void checkBBUN(int count) {
        for (int i = 0; i < count; i++) {
            checkBBUN();
        }
    }

    private static void checkBBUN() {
        BBUNCount++;
        if (target == BBUN && BBUNCount == T) {
            System.out.println(peopleOrder);
            System.exit(0);
        }
        nextPeopleOrder();
    }

    private static void checkDEGGI(int count) {
        for (int i = 0; i < count; i++) {
            checkDEGGI();
        }
    }

    private static void checkBBUNDEGGI() {
        DEGGICount++;
        if (target == DEGGI && DEGGICount == T) {
            System.out.println(peopleOrder);
            System.exit(0);
        }
        nextPeopleOrder();
    }

    private static void checkDEGGI() {
        DEGGICount++;
        if (target == DEGGI && DEGGICount == T) {
            System.out.println(peopleOrder);
            System.exit(0);
        }
        nextPeopleOrder();
    }

    private static void nextPeopleOrder() {
        peopleOrder = (peopleOrder + 1) % peopleCount;
    }
}
