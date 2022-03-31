package algorythm.Practice.programmers.step2;

import java.util.LinkedList;
import java.util.Queue;

public class BridgeTruck {
    public static void main(String[] args) {
//    int b = 2;
//    int w = 10;
//    int[] tw = {7,4,5,6};
    int b = 100;
    int w = 100;
    int[] tw = {10,10,10,10,10,10,10,10,10,10};
    System.out.println(solution(b,w,tw));

    }

    static public int solution(int bridge_length, int weightLimit, int[] truck_weights) {
        Queue<Truck> onBridge = new LinkedList<>();
        Queue<Truck> waiting = new LinkedList<>();

        for (int i = 0; i < truck_weights.length; i++)
            waiting.add(new Truck(truck_weights[i], bridge_length));

        onBridge.add(waiting.poll());
        int time = 1;
        while(!onBridge.isEmpty() || !waiting.isEmpty()){
            if(waiting.size() != 0 && weightCheck(weightLimit, onBridge, waiting.peek().weight))
                onBridge.add(waiting.poll());

            for (Truck truck : onBridge)
                truck.distanceLeft -= 1;

            if(onBridge.peek().distanceLeft <= 0)
                onBridge.poll();

            time++;
        }

        return time;
    }

    static boolean weightCheck(int weightLimit, Queue<Truck> onBridge, int nextTruckWeight){
        if(onBridge.stream().mapToInt(t->t.weight).sum() + nextTruckWeight <= weightLimit) return true;
        return false;
    }
}

class Truck {
    int weight;
    int distanceLeft;

    public Truck(int weight, int distanceLeft) {
        this.weight = weight;
        this.distanceLeft = distanceLeft;
    }
}