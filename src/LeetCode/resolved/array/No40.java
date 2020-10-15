package LeetCode.resolved.array;

import java.util.*;

public class No40 {

    private List<List<Integer>> result = new ArrayList<List<Integer>>();
    private List<Integer> arrayList = new ArrayList<Integer>();
    private HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>();
    private ArrayList<Integer> candidatesList = new ArrayList<Integer>();
    private int candidatesListLength;

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        for (int candidate : candidates) {
            if (!hashMap.containsKey(candidate)) {
                hashMap.put(candidate, 1);
            } else {
                hashMap.put(candidate, hashMap.get(candidate) + 1);
            }
        }
        candidatesList.addAll(hashMap.keySet());
        candidatesList.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        candidatesListLength = candidatesList.size();
        backtrack(0, target);
        return result;
    }

    public void backtrack(int pointer, int target){
        if(target == 0){
            result.add(new ArrayList<Integer>(arrayList));
            return;
        }
        while(pointer < candidatesListLength && candidatesList.get(pointer) > target){
            pointer++;
        }
        if(pointer >= candidatesListLength){
            return;
        }
        int temp = candidatesList.get(pointer);
        Integer num = temp;
        int count = hashMap.get(num);
        for(int i=1;i<=count;i++){
            if(temp * i > target){
                break;
            }
            arrayList.add(num);
            backtrack(pointer+1, target - temp * i);
        }
        for(int i=0;i<hashMap.get(num);i++){
            arrayList.remove(num);
        }
        backtrack(pointer+1, target);
    }

    public static void main(String[] args) {
        No40 obj = new No40();
        int[] candidates = {2,5,2,1,2};
        int target = 5;
        System.out.println(obj.combinationSum2(candidates, target));


//        Integer[] test = {10,1,2,7,6,1,5};
//        ArrayList<Integer> arrayList = new ArrayList<Integer>(Arrays.asList(test));
//        System.out.println(arrayList);
//        System.out.println("*****************************");
//        arrayList.sort(new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return o2-o1;
//            }
//        });
//        System.out.println(arrayList);
    }
}
