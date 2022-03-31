package algorythm.Practice.programmers.step2;

import java.util.ArrayList;
import java.util.Stack;

public class BracketChange {
    public static void main(String[] args) {
        //예외처리
        //입력이 비면 빈 문자열 출력
        String[] ss = {"(()())()", ")(", "()))((()"};
        for (String s : ss) {
            System.out.println(solution(s));
        }
    }

    public static String solution(String p) {
        if(p.equals("")) return "";
        if(checkRightString(p)) return p;

        String[] uv;
        uv = seperate(p);
        if(checkRightString(uv[0]))
            return uv[0] + solution(uv[1]);
        else{
            String answer = "";
            answer += "(" + solution(uv[1]) + ")";
            answer += reverse(uv[0].substring(1,uv[0].length() - 1));
            return answer;
        }
    }

    static String reverse(String s){
        String ns = "";
        for (int i = 0; i < s.length(); i++) {
            ns += (s.substring(i,i+1).equals("(")) ? ")" : "(";
        }
        return ns;
    }

    static String[] seperate(String p){
        String u = "";
        String[] ret = new String[2];

        ArrayList lList = new ArrayList();
        ArrayList rList = new ArrayList();
        for (int i = 0; i < p.length(); i++) {
           String s = p.substring(i, i+1);
           u += s;
           if(s.equals("(")) lList.add(s);
           else rList.add(s);

           if(lList.size() == rList.size()){
               ret[0] = u;
               ret[1] = p.substring(u.length());
               return ret;
           }
        }

        return ret;
    }

    static boolean checkRightString(String p){
        //스택에 ( 이거면 넣고 ) 이거면 빼서 없애주고 ) 이건데 스택에 아무것도 없으면 옳바르지 않음
        Stack<String> stack = new Stack<>();

        for (int i = 0; i < p.length(); i++) {
            if(p.substring(i,i+1).equals("("))
                stack.push("(");
            else{
                if (stack.size() == 0) return false;
                stack.pop();
            }
        }
        return true;
    }
}
