package algorythm.Practice.programmers.step2;

import java.util.ArrayList;
import java.util.Arrays;

public class LifeBoat {
    public static void main(String[] args) {
        int[] p = {70,50,80,50};
        int[] p1 = {70,50,80};
        int[] p2 = {70,70, 80,80};
        System.out.println(solution(p, 100));
        System.out.println(solution(p1, 100));
        System.out.println(solution(p2, 150));
    }

    static public int solution(int[] people, int limit) {
        Arrays.sort(people);
        int skinny = 0, fat = people.length - 1;
        int count = 0;
        while(skinny <= fat){
            int weight = people[skinny] + people[fat];
            if(weight <= limit)
                skinny++;

            fat--;
            count++;
        }

        return count;
    }
}
