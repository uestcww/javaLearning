package LeetCode.resolved.array;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class No1207 {

    public boolean uniqueOccurrences(int[] arr) {
        if(arr.length <= 1){
            return true;
        }
        Map<Integer, Integer> hashmap = new HashMap<Integer, Integer>();
        for(int num : arr){
            if(hashmap.containsKey(num)){
                hashmap.put(num, hashmap.get(num) + 1);
            }else{
                hashmap.put(num, 1);
            }
        }
        int before = hashmap.values().size();
        Set<Integer> set = new HashSet<>(hashmap.values());
        int after = set.size();
        return before == after;
    }

    public boolean uniqueOccurrencesLeetCode(int[] arr) {
        Map<Integer, Integer> occur = new HashMap<Integer, Integer>();
        for (int x : arr) {
            occur.put(x, occur.getOrDefault(x, 0) + 1);
        }
        Set<Integer> times = new HashSet<Integer>();
        for (Map.Entry<Integer, Integer> x : occur.entrySet()) {
            times.add(x.getValue());
        }
        return times.size() == occur.size();
    }

    public static void main(String[] args) {
        No1207 obj = new No1207();
        System.out.println("true ? " + obj.uniqueOccurrences(new int[]{1, 2, 2, 1, 1, 3}));
        System.out.println("false ? " + obj.uniqueOccurrences(new int[]{1, 2}));
        System.out.println("true ? " + obj.uniqueOccurrences(new int[]{-3,0,1,-3,1,1,1,-3,10,0}));
    }

}
