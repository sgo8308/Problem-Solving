package algorythm.Practice.Leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

public class SnapshotArray {

    public static void main(String[] args) {
        //["SnapshotArray","snap","get","get","set","get","set","get","set"]
        //[[2],[],[1,0],[0,0],[1,8],[1,0],[0,20],[0,0],[0,7]]
        SnapshotArray s = new SnapshotArray(2);
        System.out.println(s.snap());
        System.out.println(s.get(1, 0));
        System.out.println(s.get(0, 0));
        s.set(1, 8);
        System.out.println(s.get(1, 0));
        s.set(0, 20);
        System.out.println(s.get(0, 0));
        s.set(0, 7);
    }

    Value[] values;
    int snapCnt = 0;
    List<Integer> setIdxes = new ArrayList<>();

    //O(n)
    public SnapshotArray(int length) {
        values = new Value[length];
        for (int i = 0; i < values.length; i++) {
            values[i] = new Value(0);
        }
    }

    //O(1)
    public void set(int index, int val) {
        values[index].value = val;
        setIdxes.add(index);
    }

    //O(setIdxes.size())
    public int snap() {
        snapCnt++;
        for (Integer idx : setIdxes) {
            Value v = values[idx];
            v.snaps.put(snapCnt - 1, v.value);
        }

        setIdxes.clear();
        return snapCnt - 1;
    }

    //O(log(index에 대해서 set이 호출된 횟수))
    public int get(int index, int snap_id) {
        Value v = values[index];
        java.util.Map.Entry<Integer, Integer> entry = v.snaps.floorEntry(snap_id);
        return entry == null ? 0 : entry.getValue();
    }

    class Value {

        int value;
        TreeMap<Integer, Integer> snaps = new TreeMap<>();

        public Value(int value) {
            this.value = value;
        }
    }
}

