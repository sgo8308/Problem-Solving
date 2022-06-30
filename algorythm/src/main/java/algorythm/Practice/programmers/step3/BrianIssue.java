package algorythm.Practice.programmers.step3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

public class BrianIssue {

    public static void main(String[] args) {
        System.out.println(solution("abAAab"));

    }


    //다른 규칙 같이 적용 가능, 같은 규칙 적용 x
    //소문자 하나당 규칙 1개
    //틀린 줄 알았는데 다른 방식이 있을 수 있음
    //LinkedHashMap을 이용하여 소문자와 소문자의 첫 인덱스 끝 인덱스를 가진 객체를 모은다.
    //이 해시맵을 돌면서 규칙 적용 후 만들어진 단어를 StringBuilder에 append, 이 때 규칙이 적용안된 대문자들은 바로 append.
    //이 때 만약 소문자끼리 범위가 겹친다면 2중으로 적용된 것이므로 다음으로 넘어가기
    //규칙 1 - 소문자의 개수가 2개가 아닐 때
    //규칙 2-  소문자의 개수가 2개일 때 그리고 bAaBaCb가 아닐 때 (a는 2개이지만 규칙 1임)
    //먼저 각 소문자의 적용된 규칙을 파악한다.
    //규칙 1인 경우 첫 소문자와 마지막 소문자의 앞과 뒤를 포함해서 단어를 가져온 후 소문자를 모두 제거해서 단어 만들기
    //규칙 2인 경우 첫 소문자부터 마지막 소문자까지 단어 가져온 후 소문자 모두 제거해서 단어 만들기
    //모든 규칙에 대해서 규칙 적용 전에 공통 validation 검사를 진행
    //1. 소문자가 연속으로 붙어 있다면 inValid
    //2. 이전에 사용된 소문자가 사용되었다면 inValid(규칙1과 2가 같이 적용된 경우를 대비해서 안에 있는 소문자 파악 먼저 진행)
    //3. 같은 규칙이 2번 적용된 경우 inValid
    //4. 규칙1인 경우 소문자가 한 칸 씩 떨어져 있지 않은 경우 invalid

    //만들어진 단어는 StringBuilder에 append 후 띄어쓰기 한 칸 삽입
    //
    //마지막으로 맨 마지막 띄어쓰기는 삭제 후 리턴

    static public String solution(String sentence) {
        LinkedHashMap<Character, Alphabet> alphas = new LinkedHashMap<>();
        char[] sentenceArr = sentence.toCharArray();

        for (int i = 0; i < sentenceArr.length; i++) {
            if(Character.isUpperCase(sentenceArr[i])) continue;
            alphas.putIfAbsent(sentenceArr[i], new Alphabet(sentenceArr[i]));
            alphas.get(sentenceArr[i]).positions.add(i);
        }

        for (Entry<Character, Alphabet> entry : alphas.entrySet()) {
            System.out.println("key = " + entry.getKey());
            System.out.println("positions = " + entry.getValue().positions);
        }

        for (Alphabet value : alphas.values()) {
            value.doRuleSet(sentenceArr);
        }

        for (Alphabet value : alphas.values()) {
            System.out.println("value.character = " + value.character);
            System.out.println("value.rule = " + value.rule);
        }

        StringBuilder sb = new StringBuilder();
        boolean[] isUsed = new boolean[27];
        int lastIndex = -1;
        for (Alphabet alpha : alphas.values()) {
            try {
                if (lastIndex >= alpha.getFirstIndex() && lastIndex < alpha.getLastIndex(sentence)) {
                    return "invalid";
                }
                System.out.println("lastIndex = " + lastIndex);
                System.out.println("alpha.getFirstIndex() = " + alpha.getFirstIndex());
                int diff = alpha.getFirstIndex() - lastIndex;
                if (diff <= 0) {
                    alpha.applyRule(sentence, alphas, isUsed);
                } else {
                    if (diff > 1) {
                        sb.append(sentence, lastIndex + 1, alpha.getFirstIndex());
                        sb.append(" ");
                    }

                    String word = alpha.applyRule(sentence, alphas, isUsed);
                    sb.append(word);
                    lastIndex = alpha.getLastIndex(sentence);
                    System.out.println("word = " + word);
                }

            } catch (Exception e) {
                e.printStackTrace();
                return "invalid";
            }
        }

        if (lastIndex != sentence.length() - 1) {
            sb.append(sentence, lastIndex + 1, sentence.length());
        }



        if (sb.toString().charAt(sb.length() - 1) == ' ') {
            return sb.substring(0, sb.length() - 1);
        }
        
        return sb.substring(0, sb.length());
    }

    static class Alphabet {
        Character character;
        int rule;
        List<Integer> positions = new ArrayList<>();

        public Alphabet(Character character) {
            this.character = character;
        }

        //규칙 1 - 소문자의 개수가 2개가 아닐 때
        //규칙 2-  소문자의 개수가 2개일 때 그리고 bAaBaCb가 아닐 때 (a는 2개이지만 규칙 1임)
        public void doRuleSet(char[] sentence) {
            if (positions.size() != 2) {
                rule = 1;
            } else if (positions.size() == 2 && isInsideRule2(sentence)) {
                rule = 1;
            } else {
               rule = 2;
            }
        }

        private boolean isInsideRule2(char[] sentence) {
            if (positions.get(0) - 2  < 0 || positions.get(positions.size() - 1) + 2 > sentence.length - 1) {
               return false;
            }
            boolean cond1 = Character.isLowerCase(sentence[positions.get(0) - 2]);
            boolean cond2 = Character.isLowerCase(sentence[positions.get(positions.size() - 1) + 2]);
            boolean cond3 = sentence[positions.get(0) - 2] == sentence[positions.get(0) + 2];
            return cond1 && cond2 && cond3;
        }

        public String applyRule(String sentence, LinkedHashMap<Character, Alphabet> alphas, boolean[] isUsed) throws Exception{
            String word = sentence.substring(getFirstIndex(), getLastIndex(sentence) +1);

            System.out.println("파싱한 word = " +word);

            validationCheck(word, alphas, isUsed);
            isUsed[character - 97] = true;
            word = word.replaceAll("[a-z]", "");
            word += " ";
            return word;
        }

        private int getFirstIndex() throws Exception{
            if (rule == 1) {
                if (positions.get(0) - 1 < 0) {
                    throw new Exception();
                }
                System.out.println(character +" getFirstIndex() 통과");
                return positions.get(0) - 1;
            } else {
                System.out.println(character +" getFirstIndex() 통과");
                return positions.get(0);
            }

        }

        private int getLastIndex(String sentence) throws Exception{
            if (rule == 1) {
                if (positions.get(positions.size() - 1) + 1 > sentence.length() - 1){
                    throw new Exception();
                }
                System.out.println(character +" getLastIndex() 통과");
                return positions.get(positions.size() - 1) + 1;
            } else {
                System.out.println(character +" getLastIndex() 통과");
                return positions.get(positions.size() - 1);
            }
        }

        //1. 소문자가 연속으로 붙어 있다면 inValid
        //2. 이전에 사용된 소문자가 사용되었다면 inValid(규칙1과 2가 같이 적용된 경우를 대비해서 안에 있는 소문자 파악 먼저 진행)
        //3. 같은 규칙이 2번 적용된 경우 inValid
        //4. 규칙1인 경우 소문자가 한 칸 씩 떨어져 있지 않은 경우 invalid
        private void validationCheck(String word, LinkedHashMap<Character, Alphabet> alphas, boolean[] isUsed) throws Exception {
            //1. 소문자가 연속으로 붙어 있는지
            for (int i = 0; i < positions.size() - 1; i++) {
                int diff = positions.get(i + 1) - positions.get(i);
                if (diff == 1) {
                    throw new Exception();
                }
            }

            System.out.println(character + "1통과");
            //이전에 사용된 소문자가 다시 사용되는지
            for (int i = 0; i < word.length(); i++) {
                if (Character.isLowerCase(word.charAt(i)) && isUsed[word.charAt(i) - 97]) {
                    throw new Exception();
                }
            }

            System.out.println(character + "2통과");
            //같은 규칙이 2번 적용됐는지 -> 이거는 연속된 소문자 체크로 걸러짐짐

            //규칙 1인 경우 소문자가 갖고온 문자열에 소문자가 2개 이상이면 invalid
            if (rule == 1) {
                Set<Character> set = new HashSet<>();
                for (int i = 0; i < word.length(); i++) {
                    if (Character.isLowerCase(word.charAt(i))) {
                        set.add(word.charAt(i));
                    }

                    if (set.size() >= 2) {
                        throw new Exception();
                    }
                }
            }

            System.out.println(character + "3통과");
            //규칙 2인 경우 갖고온 문자열에 소문자가 3개 이상이면 invalid
            if (rule == 2) {
                Set<Character> set = new HashSet<>();
                for (int i = 0; i < word.length(); i++) {
                    if (Character.isLowerCase(word.charAt(i))) {
                        set.add(word.charAt(i));
                    }

                    if (set.size() >= 3) {
                        throw new Exception();
                    }
                }
            }

            System.out.println(character + "4통과");
            //규칙 1인 경우 소문자가 한 칸씩 떨어져 있지 않은 경우 invalid
            if (rule == 1) {
                for (int i = 0; i < positions.size() - 1; i++) {
                    int diff = positions.get(i + 1) - positions.get(i);
                    if (diff != 2) {
                        throw new Exception();
                    }
                }
            }

            System.out.println(character + "5통과");
        }
    }
}

