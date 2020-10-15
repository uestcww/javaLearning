package LeetCode.resolved.array;

import java.util.*;

public class No347 {

    class MapObj{
        int key;
        int value;
        public MapObj(int key, int value){
            this.key = key;
            this.value = value;
        }
        public String toString(){
            return "数字"+this.key+"一共出现了"+this.value+"次";
        }
    }

    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i=0;i<nums.length;i++){
            if (!hashMap.containsKey(nums[i])){
                hashMap.put(nums[i], 0);
            }
            hashMap.put(nums[i], hashMap.get(nums[i])+1);
        }
        System.out.println("{key=value}:" + hashMap.toString());
        Set<Integer> keySet = hashMap.keySet();
        Iterator<Integer> keysIter = keySet.iterator();
        MapObj[] objArray = new MapObj[keySet.size()];
        int i=0;
        while (keysIter.hasNext()){
            Integer key = keysIter.next();
            objArray[i] = new MapObj(key, hashMap.get(key));
            i++;
        }
        for (i=0;i<objArray.length;i++){
            System.out.println(objArray[i].toString());
        }
        Arrays.sort(objArray, new Comparator<MapObj>() {
            @Override
            public int compare(MapObj o1, MapObj o2) {
                return o2.value - o1.value;
            }
        });
        System.out.println("---------------------------");
        for (i=0;i<objArray.length;i++){
            System.out.println(objArray[i].toString());
        }
        int[] result = new int[k];
        for (i=0;i<result.length;i++){
            result[i] = objArray[i].key;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1,1,1,2,2,3,1,2,2,2,2,2};
        int k = 2;
        No347 obj = new No347();
        obj.topKFrequent(nums, k);
    }

}
