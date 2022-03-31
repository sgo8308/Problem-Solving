package algorythm.Practice.programmers.step2;

import java.util.Arrays;

public class OtherBitUnder2 {
    public static void main(String[] args) {
//        long[] numbers = {562949953421311L, 281474976710655L};
//        for (int i = 0; i < p; i++) {
//            numbers[i] = 1000000000000000L - i;
//        }
//        System.out.println((long)1<<50);
//        long s = 0;
//        for (int i = 0; i < 49; i++) {
//            if(i == 48) continue;
//            s += (long)1<<i;
//        }
//        System.out.println("ss + " + s);

        int z =  10000000 ;
        long[] numbers = new long[z];
        for (int i = 0; i < z; i++) {
            numbers[i] = i + 1000284832000000L;
        }
        long[] s1 = solution(numbers);
        long[] s3 = solution3(numbers);
        for (int i = 0; i < z; i++) {
            if(s1[i] != s3[i])
                System.out.println(s3[i]);
        }
    }

    static public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            int index = getFirstZeroIndex(numbers[i]);
            if(index == 0 || index == 1 )
                answer[i] = numbers[i] + 1;
            else{
                answer[i] = numbers[i] + ((long)1<<index - 1);
            }
        }

        return answer;
    }

    //오른쪽에서부터 가장 처음으로 나오는 0의 위치 구하기
    static int getFirstZeroIndex(long number){
        String bit = Long.toBinaryString(number);

        for (int i = 0; i < bit.length() + 1; i++) {
            if((number & 1<<i) == 0)
                return i;
        }

        return bit.length();
    }

    static public long[] solution2(long[] numbers) {
        long[] answer = new long[numbers.length];

        for(int i=0; i<numbers.length; i++){
            String BinaryString =  Long.toBinaryString(numbers[i]);
            if(numbers[i]%2==0){
                //짝수 가장 낮은 0만 바꾸기
                //사실상 맨 마지막 자리는 0이기에 +1만 해주면 된다.
                answer[i] = numbers[i]+1;
            }else{
                //홀수 가장 낮은 0을 1로 바꾸고 , 방금 바꾼것보다는 나중 위치에서 1을 0으로 바꾸기
                int lastindex =  BinaryString.lastIndexOf("0");
                if(BinaryString.length() - lastindex - 2 == 1){
                    answer[i] = numbers[i] + 1;
                    continue;
                }
                int firstindex = BinaryString.indexOf("1",lastindex);
                if(lastindex==-1){
                    //0이 없는경우 +1을 해주고
                    //앞의 10은 놔두고 나머지는 다 1로 해준다.
                    numbers[i]+=1;
                    BinaryString =  Long.toBinaryString(numbers[i]);
                    BinaryString = BinaryString.substring(0,2)+
                            BinaryString.substring(2,BinaryString.length()).replace("0","1");

                }else{
                    int x = BinaryString.length() - lastindex - 2;
                    long y = 1L<<x;
                    BinaryString = ""+ Long.toBinaryString(numbers[i] + y);

//                    BinaryString = BinaryString.substring(0,lastindex)+"10"+
//                            BinaryString.substring(firstindex+1,BinaryString.length());
                }

                answer[i] = Long.parseLong(BinaryString,2);
            }
        }
        return answer;
    }

    static public long[] solution3(long[] numbers) {
        long[] answer = new long[numbers.length];

        for(int i=0; i<numbers.length; i++){
            String BinaryString =  Long.toBinaryString(numbers[i]);
            if(numbers[i]%2==0){
                //짝수 가장 낮은 0만 바꾸기
                //사실상 맨 마지막 자리는 0이기에 +1만 해주면 된다.
                answer[i] = numbers[i]+1;
            }else{
                //홀수 가장 낮은 0을 1로 바꾸고 , 방금 바꾼것보다는 나중 위치에서 1을 0으로 바꾸기
                int lastindex =  BinaryString.lastIndexOf("0");
                int firstindex = BinaryString.indexOf("1",lastindex);
                if(lastindex==-1){
                    //0이 없는경우 +1을 해주고
                    //앞의 10은 놔두고 나머지는 다 1로 해준다.
                    numbers[i]+=1;
                    BinaryString =  Long.toBinaryString(numbers[i]);
                    BinaryString = BinaryString.substring(0,2)+
                            BinaryString.substring(2,BinaryString.length()).replace("0","1");

                }else{
                    BinaryString = BinaryString.substring(0,lastindex)+"10"+
                            BinaryString.substring(firstindex+1,BinaryString.length());
                }

                answer[i] = Long.parseLong(BinaryString,2);
            }
        }
        return answer;
    }
}
