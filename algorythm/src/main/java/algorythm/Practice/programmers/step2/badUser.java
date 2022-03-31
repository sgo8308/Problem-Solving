package algorythm.Practice.programmers.step2;

import javax.print.attribute.HashAttributeSet;
import java.nio.file.LinkOption;
import java.util.*;

public class badUser {
    public static int count = 0;
    public static boolean[] uidCheck;

    public static void main(String[] args) {
        int test = 3;
        System.out.println(Integer.toBinaryString(-35));
        System.out.println(Long.toBinaryString(-35>>2));
        Set<HashSet<String>> visiteds = new HashSet<>();
        HashSet<String> visited = new HashSet<>();
        HashSet<String> visited2 = new HashSet<>();
        visited.add("test");
        visiteds.add(visited);
        visiteds.add(visited);
        visited.remove("test");
        visited.add("hello");
        visiteds.add(visited);
        visited2.add("test");

        visiteds.add(visited2);

        System.out.println(visiteds.contains(visited2));
    }

    public static int solution(String[] user_id, String[] banned_id) {
        uidCheck = new boolean[user_id.length];
        String[] b_id = new String[banned_id.length];
        for(int i = 0;i < banned_id.length; i++)
            b_id[i] = banned_id[i].replaceAll("\\*",".");

        dfs(user_id, b_id, 1, banned_id.length);

        Set<HashSet<String>> visiteds = new HashSet<>();
        HashSet<String> visited = new HashSet<>();
        visited.add("test");
        HashSet<String> visited2 = (HashSet<String>)visited.clone();
        System.out.println(visited.toString());
        System.out.println(visited2.toString());
        return count;
    }

    static void dfs(String[] user_id, String[] b_id, int depth, int b_id_len){
        if(depth > b_id_len) count++;
        else{
            for(int i = 0; i < user_id.length; i++){
                if(!uidCheck[i] && user_id[i].matches(b_id[depth - 1])){
                    uidCheck[i] = true;
                    dfs(user_id, b_id, depth + 1, b_id_len);
                    uidCheck[i] = false;
                }
            }
        }
    }
}
