package algorythm.Practice.programmers.step2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileNameSort {
    public static void main(String[] args) {
        String[] s = {"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"};
        String[] s2 = {"F-5 Freedom Fighter", "B-50 Superfortress", "A-10 Thunderbolt II", "F-14 Tomcat"};
        System.out.println(Arrays.toString(solution(s)));
        System.out.println(Arrays.toString(solution(s2)));

    }

    static public String[] solution(String[] files) {
        ArrayList<File> fileList = new ArrayList<>();

        for (int i = 0; i < files.length; i++) {
            File file = new File(files[i], i);
            fileList.add(file);
        }

        Collections.sort(fileList);
        String[] answer = new String[files.length];
        for (int i = 0; i < files.length; i++) {
            answer[i] = fileList.get(i).fullName;
        }
        return answer;
    }
}

class File implements Comparable<File>{
    String fullName;
    String head;
    int number;
    int order;

    public File(String fullName, int order) {
        this.fullName = fullName;
        this.order = order;
        setHead();
        setNumber();
    }

    void setHead(){
        head = fullName.split("[0-9]")[0].toLowerCase();
    }

    void setNumber(){
        Pattern pattern = Pattern.compile("[0-9]+");
        Matcher matcher = pattern.matcher(fullName);
        matcher.find();
        number = Integer.parseInt(matcher.group());
    }

    @Override
    public int compareTo(File o) {
        if(this.head.compareTo(o.head) == 0){
            if(this.number == o.number){
                return this.order - o.order;
            }

            return this.number - o.number;
        }

        return this.head.compareTo(o.head);
    }
}
