package LeetCode.solving;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class No15 {

    private List<List<Integer>> result = new ArrayList<List<Integer>>();
    private List<Integer> arrayList = new ArrayList<Integer>();
    private HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>();
    private List<Integer> values = new ArrayList<Integer>();

    public void showHashMap(){
        System.out.println(hashMap.toString());
    }

    public List<List<Integer>> threeSum(int[] nums) {
        for (int num : nums) {
            if (!hashMap.containsKey(num)) {
                hashMap.put(num, 1);
            } else {
                hashMap.put(num, hashMap.get(num) + 1);
            }
        }
        values.addAll(hashMap.keySet());
        backtrack(0,0);
        return result;
    }

    public void backtrack(int pointer, int count){
        if(count == 3){
            if(arrayList.get(0) + arrayList.get(1) + arrayList.get(2) == 0){
                result.add(new ArrayList<Integer>(arrayList));
            }
            return;
        }
        if(pointer >= values.size()){
            return;
        }
        int num = values.get(pointer);
        int size = Math.min(3 - count, hashMap.get(num));
        for(int i=0;i<size;i++){
            arrayList.add(num);
            backtrack(pointer+1, count+1);
        }
        for(int i=0;i<size;i++){
            arrayList.remove(num);
        }
        backtrack(pointer+1, count);
    }

    public static void main(String[] args) {
        No15 obj = new No15();
        int[] nums = {-1, 0, 1, 2, -1, -4};
        System.out.println(obj.threeSum(nums));
    }

}
