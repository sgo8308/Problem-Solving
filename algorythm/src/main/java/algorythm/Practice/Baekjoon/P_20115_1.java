package algorythm.Practice.Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P_20115_1 {
    public static void main(String[] args) throws Exception{
        // 3 5 ,  1 2 3, 3 3 3, 1 1 1, 1000000000, 1000000000 ,1000000000 ,
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] drinkAmounts = new int[N];
        for (int i = 0; i < N; i++)
            drinkAmounts[i] = Integer.parseInt(st.nextToken());

        int maxAmountOfDrink = Arrays.stream(drinkAmounts).max().getAsInt();
        double maxAmountOfAllDrinks = maxAmountOfDrink;

        boolean isMaxValuePassed = false;
        for (int i = 0; i < N; i++){
            System.out.println("maxAmountOfAllDrinks = " + maxAmountOfAllDrinks);
            if (drinkAmounts[i] < maxAmountOfDrink)
                maxAmountOfAllDrinks += (double) drinkAmounts[i] / 2.0;
            else if (drinkAmounts[i] == maxAmountOfDrink && !isMaxValuePassed)
                isMaxValuePassed = true;
            else if (drinkAmounts[i] == maxAmountOfDrink && isMaxValuePassed)
                maxAmountOfAllDrinks += (double) drinkAmounts[i] / 2;
        }
        if((int)maxAmountOfAllDrinks < maxAmountOfAllDrinks)
            System.out.println(maxAmountOfAllDrinks);
        else
            System.out.println((int)maxAmountOfAllDrinks);
    }
}
