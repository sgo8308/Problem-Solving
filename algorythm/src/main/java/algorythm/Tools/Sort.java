package algorythm.Tools;

import java.util.ArrayList;
import java.util.Collections;

public class Sort {
    public static void main(String[] args) {
        ArrayList<Person> persons = new ArrayList<>();
        persons.add(new Person(1,"지우"));
        persons.add(new Person(2,"우"));
        persons.add(new Person(3,"사랑"));
        /////////////////////////
        Collections.sort(persons);
        /////////////////////////
        System.out.println("persons = " + persons.get(0).id);
        System.out.println("persons = " + persons.get(1).id);
        System.out.println("persons = " + persons.get(2).id);

    }
}


//특정한 조건으로 정렬
class Person implements Comparable<Person>{
    int id;
    String name;

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    //id로 오름차순 정렬
//    @Override
//    public int compareTo(Person o) {
//        return this.id - o.id;
//    }

    //id로 내림차순 정렬
    @Override
    public int compareTo(Person o) {
        return o.id - this.id;
    }
}