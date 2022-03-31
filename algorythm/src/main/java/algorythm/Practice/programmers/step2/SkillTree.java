package algorythm.Practice.programmers.step2;

import java.util.HashMap;

public class SkillTree {
    public static void main(String[] args) {
        String skill = "CBD";
        String[] ss = {"AEF", "ZJW"};

//        String skill = "CBD" ["CAD"] 0
//        "CBD" ["AEF", "ZJW"] 2
//        "REA" ["REA", "POA"] 1
//        "CBDK" ["CB", "CXYB", "BD", "AECD", "ABC", "AEX", "CDB", "CBKD", "IJCB", "LMDK"] 4
//        "BDC" ["AAAABACA"] 0
//        "CBD" ["C", "D", "CB", "BDA"] 2
        System.out.println(solution(skill,ss));
    }

    static public int solution(String skill, String[] skill_trees) {
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 1; i < skill.length() + 1; i++) {
            map.put(skill.substring(0,i), 1);
        }

        int count = 0;
        for (int i = 0; i < skill_trees.length; i++) {

            String sk = skill_trees[i].replaceAll("[^" + skill + "]", "");
            if(map.containsKey(sk)) count++;
        }
        return count;
    }
}
