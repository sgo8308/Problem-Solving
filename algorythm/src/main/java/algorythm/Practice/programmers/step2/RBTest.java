package algorythm.Practice.programmers.step2;

import java.util.Stack;

public class RBTest {
    boolean solution(String s) {
        Stack<Character> stk=new Stack<>();
        for(int i=0; i<s.length(); i++){
            char c=s.charAt(i);
            if(c==')'){
                if(stk.isEmpty()){
                    return false;
                }
                stk.pop();
            }else{
                stk.push('(');
            }
        }
        if(stk.isEmpty()){
            return true;
        }
        return false;
    }


}


