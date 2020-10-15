package LeetCode.resolved.array;

import java.util.ArrayList;
import java.util.List;

public class No216 {

    List<List<Integer>> result = new ArrayList<List<Integer>>();
    List<Integer> arrayList = new ArrayList<Integer>();

    public List<List<Integer>> combinationSum3(int k, int n) {
        traceback(9, k, n);
        return result;
    }

    public void traceback(int pointer, int k, int target){
        if(target == 0 && k == 0){
            result.add(new ArrayList<Integer>(arrayList));
            return;
        }
        if(k == 0 || target == 0){
            return;
        }
        while(pointer > 0 && pointer > target){
            pointer--;
        }
        if(pointer <= 0){
            return;
        }
        Integer temp = pointer;
        arrayList.add(temp);
        traceback(pointer-1, k-1, target-pointer);
        arrayList.remove(temp);
        traceback(pointer-1, k, target);
    }

    public static void main(String[] args) {
        No216 obj = new No216();
        int k =3;
        int n = 9;
        System.out.println(obj.combinationSum3(k, n));
    }
}
