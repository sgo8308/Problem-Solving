package algorythm.Practice.Baekjoon;

import java.util.*;

public class SetExpression1717 {
    static int[] unf;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        unf = new int[n + 1];
        for (int i = 1; i < unf.length; i++)
            unf[i] = i;

        for (int i = 0; i < m; i++) {
            int operand = sc.nextInt();
            if (operand == 0)
                union(sc.nextInt(), sc.nextInt());
            else
                if (find(sc.nextInt()) == find(sc.nextInt()))
                    System.out.println("YES");
                else
                    System.out.println("NO");
        }
    }

    static int find(int a){
        if(a == unf[a])
            return a;
        else
            return unf[a] = find(unf[a]);
    }

    static void union(int a, int b){
        int fa = find(a);
        int fb = find(b);

        if (fa != fb) unf[fa] = fb;
    }
}
