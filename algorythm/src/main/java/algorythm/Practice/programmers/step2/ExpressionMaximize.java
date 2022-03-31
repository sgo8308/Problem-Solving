package algorythm.Practice.programmers.step2;

import java.util.*;

public class ExpressionMaximize {
    public static void main(String[] args) {
        // 연산자 갯수 찾기
        // 연산자 우선순위 조합 만들기
        // expression 각각 배열로 담기
        // 우선순위에 따라 계산하기
        // 절댓값의 최댓값 찾기
//        String s = "100-200*300-500+20";
        String s2 = "50*6-3*2";
//        System.out.println(solution(s));
        System.out.println(solution(s2));
    }

    static public long solution(String expression) {
        String[] operators = {"*", "+", "-"};
        ArrayList<String> nums = new ArrayList<>(Arrays.asList(expression.split("[^0-9]")));
        ArrayList<String> ops =
                new ArrayList<>(Arrays.asList(Arrays.copyOfRange(expression.split("[0-9]+"),1,nums.size())));

        ArrayList<String> OPCombs =
                getOPCombs(operators, "", new ArrayList<>(), 0);

        Long max = Long.MIN_VALUE;
        for (int i = 0; i < OPCombs.size(); i++) {
            Long a = calculate(OPCombs.get(i), (ArrayList<String>)nums.clone(), (ArrayList<String>)ops.clone());
            max = Math.max(max, a);
        }

        return max;
    }

    static ArrayList<String> getOPCombs(String[] operators, String comb,
                                          ArrayList<String> combs, int depth){
        if(depth >= operators.length){
            combs.add(comb);
        }else{
            for (int i = 0; i < operators.length; i++) {
                if(!operators[i].equals("visited")){
                    String tmp = operators[i];
                    operators[i] = "visited";
                    combs = getOPCombs(operators, comb + tmp, combs, depth + 1);
                    operators[i] = tmp;
                }
            }
        }

        return combs;
    }

    static long calculate(String OPComb, List<String> nums, List<String> ops){
        for (int i = 0; i < OPComb.length(); i++) {
            for (int j = 0; j < ops.size(); j++) {
                if(!ops.get(j).equals("" + OPComb.charAt(i))) continue;

                String opResult = operate(OPComb.charAt(i), nums.get(j), nums.get(j + 1));
                nums.remove(j);
                nums.remove(j);
                nums.add(j, opResult);
                ops.remove(j);
                j--;
            }
        }
        return Math.abs(Long.parseLong(nums.get(0)));
    }

    static String operate(char operator, String a, String b){
        switch (operator){
            case '+':
                return "" + (Long.parseLong(a) + Long.parseLong(b));
            case '-':
                return "" + (Long.parseLong(a) - Long.parseLong(b));
            case '*':
                return "" + (Long.parseLong(a) * Long.parseLong(b));
        }

        return "0";
    }
}
