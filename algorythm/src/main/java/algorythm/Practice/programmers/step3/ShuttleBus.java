package algorythm.Practice.programmers.step3;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShuttleBus {
    public String solution(int n, int t, int m, String[] timetable) {
        Arrays.sort(timetable);
        List<Bus> buses = new ArrayList<>();
        int crewIdx = 0;
        for(int i = 0; i < n; i++){
            Bus bus = new Bus(LocalTime.of(9,0).plusMinutes(i * t), m);
            buses.add(bus);
            for(; crewIdx < timetable.length; crewIdx++){
                LocalTime crew = LocalTime.parse(timetable[crewIdx]);
                if(!bus.isFull() && crew.isBefore(bus.time.plusMinutes(1))){
                    bus.takeCrew(crew);
                }else{
                    break;
                }
            }

        }

        Bus lastBus = buses.get(n - 1);
        if(!lastBus.isFull()){
            return lastBus.time.toString();
        }else{
            if(lastBus.crews.size() == 0){
                return lastBus.time.toString();
            }
            return lastBus.crews.get(lastBus.crews.size() - 1).minusMinutes(1).toString();
        }
    }

    class Bus{
        LocalTime time;
        int afford;
        public List<LocalTime> crews = new ArrayList<>();

        Bus(LocalTime time, int afford){
            this.time = time;
            this.afford = afford;
        }

        boolean isFull(){
            return crews.size() >= afford;
        }
        void takeCrew(LocalTime crew){
            crews.add(crew);
        }

    }

}
