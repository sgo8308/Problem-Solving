package algorythm.Practice.programmers.step2;

public class CompressString {
    public static void main(String[] args) {
        System.out.println(solution("ababcdcdababcdcd"));
    }

    public static int solution(String s) {
        if(s.length() < 2){
            return 1;
        }

        int minLength = 10000;

        for(int i = 1; i < s.length() / 2 + 1; i++){//1개 단위로 잘랐을 때부터 마지막 단위로 잘랐을 때까지
            String target = s.substring(0, i); // 비교 대상
            String subject = ""; // 피실험체
            StringBuffer newString = new StringBuffer(); //압축된 문자열
            int count = 0; //동일한 문자 갯수

            for(int j = 0; j < s.length(); j+=i){ //단위는 i니까 i만큼 증가
                if(j + i <= s.length()) {
                    subject = s.substring(j, j + i);
                }else{
                    subject = s.substring(j);
                }

                if (target.equals(subject)) {
                    count += 1;
                }else{
                    if(count > 1){newString.append(count);}
                    newString.append(target);
                    if(j + i <= s.length()) {
                        target = s.substring(j, j + i);//더 이상 동일한 문자 없을면 타겟 변경
                    }
                    count = 1;
                }

                if(j + i >= s.length()){
                    if(count > 1){newString.append(count);}
                    newString.append(s.substring(j));
                }
            }

            minLength = Math.min(minLength, newString.length());
        }
        return minLength;
    }
}
