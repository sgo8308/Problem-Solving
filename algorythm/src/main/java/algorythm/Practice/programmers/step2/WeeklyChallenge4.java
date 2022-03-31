package algorythm.Practice.programmers.step2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class WeeklyChallenge4 {
    public static void main(String[] args) {



        //예외처리 : 점수가 동점이면 사전순으로 정렬 후 첫번째 원소 리턴

        String[] table = {"SI JAVA JAVASCRIPT SQL PYTHON C#", "CONTENTS JAVASCRIPT JAVA PYTHON SQL C++", "HARDWARE C C++ PYTHON JAVA JAVASCRIPT", "PORTAL JAVA JAVASCRIPT PYTHON KOTLIN PHP", "GAME C++ C# JAVASCRIPT C JAVA"};
        String[] languages = {"PYTHON", "C++", "SQL"};
        String[] languages2 = {"JAVA", "JAVASCRIPT"};
        int[] preference = {7, 5, 5};
        int[] preference2 = {7, 5};

        System.out.println(solution(table, languages, preference));
        System.out.println(solution(table, languages2, preference2));
    }

    public static String solution(String[] table, String[] languages, int[] preference) {
        ArrayList<JobScore> list = new ArrayList<>();

        for (int i = 0; i < table.length; i++) {
            int score = 0;
            for (int j = 0; j < languages.length; j++) {
               score += calculateScore(table[i], languages[j], preference[j]);
            }
            list.add(new JobScore(table[i].split("\\s")[0], score));
        }

        Collections.sort(list);
        return list.get(0).jobName;
    }

    static int calculateScore(String column, String language, int languagePreference){
        String[] t = column.split("\\s");
        for (int i = 1; i < 6; i++) {
            if(t[i].equals(language))
                return (6 - i) * languagePreference;
        }

        return 0;
    }

    static class JobScore implements Comparable<JobScore>{
        public String jobName;
        public int score;

        public JobScore(String jobName, int score) {
            this.jobName = jobName;
            this.score = score;
        }

        @Override
        public int compareTo(JobScore o) {
            if(this.score == o.score)
                return this.jobName.compareTo(o.jobName);

            return o.score - this.score;
        }
    }
}
