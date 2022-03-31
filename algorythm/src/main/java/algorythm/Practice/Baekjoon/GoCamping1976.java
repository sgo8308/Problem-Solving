package algorythm.Practice.Baekjoon;

import java.util.*;

public class GoCamping1976 {
    static int[] setArray;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        setArray = new int[n + 1];
        for (int i = 1; i < setArray.length; i++) {
            setArray[i] = i;
        }

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                int isConnected = sc.nextInt();
                if (isConnected == 1)
                    union(i, j);
            }
        }

        int group = find(sc.nextInt());
        for (int i = 0; i < m - 1; i++) {
            if (find(sc.nextInt()) != group){
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }

    static int find(int a){
        if(setArray[a] == a)
            return a;
        else
            return setArray[a] = find(setArray[a]);
    }

    static void union(int a, int b){
        int fa = find(a);
        int fb = find(b);

        if (fa != fb)
            setArray[fa] = fb;
    }

}
