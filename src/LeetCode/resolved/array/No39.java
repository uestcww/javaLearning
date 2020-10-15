package LeetCode.resolved.array;

import java.util.*;

public class No39 {

    private List<List<Integer>> result = new ArrayList<List<Integer>>();
    private List<Integer> list = new ArrayList<Integer>();
    private int[] candidatesArray;
    private int candidatesLength;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        int[] tempArray = candidates;
        Arrays.sort(tempArray);
        int tempLength = tempArray.length;
        candidatesArray = new int[tempLength];
        for(int i=0;i<tempLength;i++){
            candidatesArray[i] = tempArray[tempLength-1-i];
        }
        candidatesLength = candidatesArray.length;
        backtrack(0,target);
        return result;
    }

    public void backtrack(int pointer, int target){
        if(target == 0){
            result.add(new ArrayList<Integer>(list));
            return;
        }
        int num;
        Integer INum;
        for(int i=pointer;i<candidatesLength;i++){
            num = candidatesArray[i];
            if(num>target){
                continue;
            }
            INum = new Integer(num);
            list.add(INum);
            backtrack(i,target-num);
            list.remove(INum);
        }
    }



    public static void main(String[] args) {

        No39 obj = new No39();
        int[] candidates = {2,3,5};
        int target = 8;
        List<List<Integer>> abc = obj.combinationSum(candidates, target);
        System.out.println(abc);

    }






}
