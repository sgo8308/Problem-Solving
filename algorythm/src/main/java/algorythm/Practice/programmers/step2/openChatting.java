package algorythm.Practice.programmers.step2;
import java.util.*;
class openChatting {
    public static void main(String[] args) {
        String[] strings = {"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"};
        System.out.println(Arrays.toString(solution(strings)));
    }

    public static String[] solution(String[] record) {
        ArrayList<String[]> answer = new ArrayList<>();
        Map<String, Person> persons = new HashMap<>();
        String[][] splitRecords = new String[record.length][3];

        for (int i = 0; i < record.length; i++) {
            splitRecords[i] = record[i].split(" ");
        }

        for (String[] x : splitRecords) {
            Person person = persons.get(x[1]);

            if (x[0].equals("Enter")) {
                if(person == null){
                    persons.put(x[1], new Person(x[1], x[2]));
                    answer.add(enter(x[1]));
                }else{
                    change(person, x[2]);
                    answer.add(enter(x[1]));
                }
            } else if (x[0].equals("Leave")) {
                answer.add(leave(x[1]));
            } else if (x[0].equals("Change")) {
                change(person, x[2]);
            }
        }

        String[] finalAnswer = updateChatting(answer,persons);

        return finalAnswer;
    }

    static String[] enter(String id) {
        String[] ret = new String[2];
        ret[0] = id;
        ret[1] = "Enter";
        return ret;
    }

    static String[] leave(String id) {
        String[] ret = new String[2];
        ret[0] = id;
        ret[1] = "Leave";
        return ret;
    }

    static Person change(Person person, String nickName) {
        person.nickName = nickName;
        return null;
    }

    static Person findPersonById(String id, ArrayList<Person> persons){
        for (int i = 0; i < persons.size(); i++) {
            if(persons.get(i).id.equals(id)){
                return persons.get(i);
            }
        }
        return null;
    }

    static String[] updateChatting(ArrayList<String[]> records, Map<String, Person> persons){
        String[] ret = new String[records.size()];
        for (int i = 0; i < records.size(); i++) {
            Person person = persons.get(records.get(i)[0]);
            switch (records.get(i)[1]){
                case "Enter":
                    ret[i] = person.nickName + "님이 들어왔습니다.";
                    break;
                case "Leave":
                    ret[i] = person.nickName + "님이 나갔습니다.";
                    break;
            }
        }
        return ret;
    }

    }

class Person {
    public String id;
    public String nickName;

    Person(String id, String nickName) {
        this.id = id;
        this.nickName = nickName;
    }
}