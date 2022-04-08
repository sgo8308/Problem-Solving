package algorythm.Tools;

import java.util.ArrayList;
import java.util.LinkedList;

public class PermCombi {

    public static void main(String[] args) {
        String s = "ABC";
        System.out.println(combi(s, new ArrayList<>(), "",2,0).toString()); // 3C2
        System.out.println(perm(s, new ArrayList<>(), "",2,new boolean[s.length()]).toString()); //3P2

    }
    //조합
    static ArrayList<String> combi(String s, ArrayList<String> combs, String comb, int limit, int idx){
        if(comb.length() == limit){
            combs.add(comb);
            return combs;
        }else{
            for (int i = idx; i < s.length(); i++) {
                char c = s.charAt(i);
                combi(s, combs, comb + c, limit, i + 1);
            }
        }

        return combs;
    }

    //중복조합
    static ArrayList<String> reCombi(String s, ArrayList<String> combs, String comb, int limit, int idx){
        if(comb.length() == limit){
            combs.add(comb);
            return combs;
        }else{
            for (int i = idx; i < s.length(); i++) {
                char c = s.charAt(i);
                reCombi(s, combs, comb + c, limit, i);
            }
        }

        return combs;
    }

    //순열
    static ArrayList<String> perm(String s, ArrayList<String> combs, String comb, int limit, boolean[] visited){
        if(comb.length() == limit){
            combs.add(comb);
            return combs;
        }else{
            for (int i = 0; i < s.length(); i++) {
                if(!visited[i]){
                    visited[i] = true;
                    char c = s.charAt(i);
                    perm(s, combs, comb + c, limit, visited);
                    visited[i] = false;
                }
            }
        }

        return combs;
    }

    //중복순열
    static ArrayList<String> rePerm(String s, ArrayList<String> combs, String comb, int limit){
        if(comb.length() == limit){
            combs.add(comb);
            return combs;
        }else{
            for (int i = 0; i < s.length(); i++) {
                    char c = s.charAt(i);
                    rePerm(s, combs, comb + c, limit);
            }
        }

        return combs;
    }
}
