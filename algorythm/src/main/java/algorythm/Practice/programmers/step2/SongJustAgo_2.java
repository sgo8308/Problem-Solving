package algorythm.Practice.programmers.step2;

public class SongJustAgo_2 {
    public static void main(String[] args) {
        String m = "ABCDEFG";
        String[] ms = {"12:00,12:14,HELLO,CDEFGAB", "13:00,13:05,WORLD,ABCDEF"};
        String m2 = "CC#BCC#BCC#BCC#B";
        String[] ms2 = {"03:00,03:30,FOO,CC#B", "04:00,04:08,BAR,CC#BCC#BCC#B"};
        String m3 = "ABC";
        String[] ms3 = {"12:00,12:14,HELLO,C#DEFGAB", "13:00,13:05,WORLD,ABCDEF"};
        String m4 = "A#";
        String[] ms4 = {"12:00,12:01,HELLO,A#"};
        System.out.println(solution(m,ms));
        System.out.println(solution(m2,ms2));
        System.out.println(solution(m3,ms3));
        System.out.println(solution(m4,ms4));

        System.out.println("CDS".contains("c"));
    }

    static public String solution(String m, String[] musicinfos) {
        m = edit(m);
        String answer = "(None)";

        int t = 0;
        for (int i = 0; i < musicinfos.length; i++) {
            String[] info = musicinfos[i].split(",");
            info[3] = edit(info[3]);

            int playTime = getPlayTime(info[0], info[1]);

            String scores = getScores(playTime, info[3]);

            if(playTime > t){
                if(scores.indexOf(m) > 0){
                    answer = info[2];
                    t = playTime;
                }
            }
        }

        return answer;
    }

    static int getPlayTime(String start, String end){
        int t1 = 60 * (Integer.parseInt(end.split(":")[0]) - Integer.parseInt(start.split(":")[0]));
        int t2 = Integer.parseInt(end.split(":")[1]) - Integer.parseInt(start.split(":")[1]);
        return  t1 + t2;
    }

    static String getScores(int time, String score){
        StringBuffer s = new StringBuffer();
        for (int i = 0; i < time; i++) {
            s.append(score.charAt(i % score.length()));
        }
        return s.toString();
    }

    static String edit(String s){
        s = s.replaceAll("C#", "c");
        s = s.replaceAll("D#", "c");
        s = s.replaceAll("F#", "c");
        s = s.replaceAll("G#", "c");
        s = s.replaceAll("A#", "c");
        return s;
    }
}
