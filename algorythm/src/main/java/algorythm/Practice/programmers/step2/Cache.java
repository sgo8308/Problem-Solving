package algorythm.Practice.programmers.step2;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Cache {
    public static void main(String[] args) {
        String[] s = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"};
        int c = 3;
        String[] s2 = {"Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul"};
        String[] s3 = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"};
        String[] s4 = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"};
        String[] s5 = {"Jeju", "Pangyo", "NewYork", "newyork"};
        String[] s6 = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA"};
        String[] s7 = { "SEOUL", "SEOUL", "SEOUL"};
//        System.out.println(solution(c,s));
//        System.out.println(solution(c,s2));
//        System.out.println(solution(2,s3));
//        System.out.println(solution(5,s4));
//        System.out.println(solution(2,s5));
//        System.out.println(solution(0,s6));

        LinkedHashMap<String, String> o = new LinkedHashMap<>();

        System.out.println(solution(5,s7));
    }
    static Queue<String> cache = new LinkedList<>();
    static public int solution(int cacheSize, String[] cities) {
        cache.clear();
        int time = 0;
        for (int i = 0; i < cities.length; i++) {
            String city = cities[i].toLowerCase();
            //1. 캐쉬사이즈가 0일 때
            if(cacheSize == 0) return 5 * cities.length;
            //2. 캐쉬가 아직 다 안 찼을 때
            if(cache.size() < cacheSize){
                if(isCacheHit(city,cacheSize)){
                    time++;
                    resetOrder(city);
                }
                else{
                    time += 5;
                }
            }else{
                if(isCacheHit(city,cacheSize)){
                    time++;
                    resetOrder(city);
                }
                else{
                    time += 5;
                    cache.poll();
                }
            }
            cache.add(city);

        }

        return time;
    }

    static boolean isCacheHit(String city, int cacheSize){
        for (String cityInCache : cache) {
            if(cityInCache.equals(city)) return true;
        }
        return false;
    }

    static void resetOrder(String city){
        int size = cache.size();
        for (int i = 0; i < size; i++) {
            String cityInCache = cache.poll();
            if(!cityInCache.equals(city)){
                cache.add(cityInCache);
            }
        }
    }
}
