package algorythm.Practice.programmers.step2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class StockPrice {
    public static void main(String[] args) {
        int[] p = {1,2,3,2,3};
//        int[] p = {3, 2, 1, 5 ,4 ,5, 7, 7, 6 ,10};
        System.out.println(Arrays.toString(solution(p)));
    }

    static public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        Stack<Integer> timeStack = new Stack<>();
        Stack<Integer> priceStack = new Stack<>();

        timeStack.push(1);
        priceStack.push(prices[0]);
        for (int i = 1; i < prices.length; i++) {
            int nowTime = i + 1;
            if(priceStack.peek() > prices[i]){
                int previousTime = timeStack.pop();
                answer[previousTime - 1] = nowTime - previousTime;
                priceStack.pop();

                while(!priceStack.isEmpty()){
                    if(priceStack.peek() > prices[i]){
                        int previousTime2 = timeStack.pop();
                        answer[previousTime2 - 1] = nowTime - previousTime2;
                        priceStack.pop();
                        continue;
                    }
                    break;
                }
            }
            timeStack.push(nowTime);
            priceStack.push(prices[i]);
        }
        int size = timeStack.size();
        for (int i = 0; i < size; i++) {
            int time = timeStack.pop();
            answer[time - 1] = prices.length - time;
        }

        answer[prices.length - 1] = 0;
        return answer;
    }
}
