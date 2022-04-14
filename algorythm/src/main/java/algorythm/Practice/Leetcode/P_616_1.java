package algorythm.Practice.Leetcode;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class P_616_1 {

    public static void main(String[] args) {
        String s = "abcxyz123";
        String[] dict = {"abc", "123"};
        System.out.println(addBoldTag(s, dict));
    }

    static public String addBoldTag(String s, String[] dict) {
        Arrays.sort(dict);
        Stack<BoldTag> stack = new Stack<>();

        for (int start = 0; start < s.length(); start++) {
            BoldTag bt = getBoldTag(s, start, start + 1, Arrays.asList(dict));

            if(bt == null) continue;

            if (stack.size() == 0) {
                stack.push(bt);
                continue;
            }

            BoldTag prebt = stack.peek();
            if (prebt.isOverlappedWith(bt)) {
                prebt.combine(bt);
            } else {
                stack.push(bt);
            }
        }
        while (!stack.isEmpty()) {
            BoldTag bt = stack.pop();
            s = bt.attatch(s);
        }

        return s;
    }

    static private BoldTag getBoldTag(String s, int start, int end, List<String> candidates) {
        if (candidates.size() == 0) return null;

        String str = s.substring(start, end);

        List<String> newCands = new ArrayList<>(candidates.size());

        for (int i = 0; i < candidates.size(); i++) {

            if (candidates.get(i).startsWith(str)) {
                newCands.add(candidates.get(i));
            }
        }

        if (newCands.size() == 1 && newCands.get(0).equals(str)) {
            return new BoldTag(start, end);
        }

        return getBoldTag(s, start, end + 1, newCands);
    }

    static class BoldTag {
        int openIdx;
        int closeIdx;

        public BoldTag(int openIdx, int closeIdx) {
            this.openIdx = openIdx;
            this.closeIdx = closeIdx;
        }

        @Override
        public String toString() {
            return "BoldTag{" +
                    "openIdx=" + openIdx +
                    ", closeIdx=" + closeIdx +
                    '}';
        }

        public boolean isOverlappedWith(BoldTag bt) {
            return this.closeIdx >= bt.openIdx;
        }

        public void combine(BoldTag bt) {
            if(this.closeIdx > bt.closeIdx) return;

            this.closeIdx = bt.closeIdx;
        }

        public String attatch(String s) {
            List<String> li = new ArrayList<>(s.length());
            for (int i = 0; i < s.length(); i++) {
                li.add(s.substring(i, i + 1));
            }

            li.add(openIdx, "<b>");
            li.add(closeIdx + 1, "</b>");
            return li.stream().reduce((a, b) -> a.concat(b)).get();
        }
    }
}
