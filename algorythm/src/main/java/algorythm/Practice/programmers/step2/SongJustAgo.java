package algorythm.Practice.programmers.step2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SongJustAgo {
    public static void main(String[] args) throws Exception {
        String m = "ABCDEFG";
        String[] ms = {"12:00,12:14,HELLO,CDEFGAB", "13:00,13:05,WORLD,ABCDEF"};
        String m2 = "CC#BCC#BCC#BCC#B";
        String[] ms2 = {"03:00,03:30,FOO,CC#B", "04:00,04:08,BAR,CC#BCC#BCC#B"};
        String m3 = "ABC";
        String[] ms3 = {"12:00,12:14,HELLO,C#DEFGAB", "13:00,13:05,WORLD,ABCDEF"};
        String m4 = "A#";
        String[] ms4 = {"12:00,12:01,HELLO,A#"};
        System.out.println(solution(m4,ms4));


    }

    static public String solution(String m, String[] musicinfos) {
        ArrayList<Music> mList = new ArrayList<>();
        for (int i = 0; i < musicinfos.length; i++) {
            String[] mInfo = musicinfos[i].split(",");
            mList.add(new Music(mInfo[0],mInfo[1], mInfo[2], mInfo[3], i));
        }

        //자정은 00시, 00시 넘기는 일은 없음
        //재생한 시간만큼 악보 만들기
        ArrayList<Music> hoobo = new ArrayList<>();
        //레겍스로 찾아서 배열에 담기. 이때 #조심
        for (int i = 0; i < musicinfos.length; i++) {
            String scores = getScores(mList.get(i));
            scores += "-";
            String regex = m + "[^#]";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(scores);
            if(matcher.find())
                hoobo.add(mList.get(i));
        }

        if(hoobo.size() == 0) return "(None)";

        Collections.sort(hoobo);

        return hoobo.get(0).title;
    }

    static String getScores(Music music){
        music.setPlayTime();
        String scores = "";
        int time = music.playTime;
        for (int i = 0; i < time; i++) {
            char melody = music.score.charAt(i % music.score.length());
            scores += melody;
            if(i < time && music.score.charAt((i + 1) % music.score.length()) == '#')
                time++;
        }
        return scores;
    }
}

class Music implements Comparable<Music>{
    String start;
    String end;
    String title;
    String score;
    int order;
    int playTime;

    public Music(String start, String end, String title, String score, int order) {
        this.start = start;
        this.end = end;
        this.title = title;
        this.score = score;
        this.order = order;
    }

    public void setPlayTime() {
        int t1 = 60 * (Integer.parseInt(end.split(":")[0]) - Integer.parseInt(start.split(":")[0]));
        int t2 = Integer.parseInt(end.split(":")[1]) - Integer.parseInt(start.split(":")[1]);
        playTime = t1 + t2;
    }

    @Override
    public int compareTo(Music o) {
        //제일 긴 시간 , 먼저 입력된 음악 순
        int diff = o.playTime - this.playTime;
        if(diff == 0){
            return this.order - o.order;
        }
        return diff;
    }
}
