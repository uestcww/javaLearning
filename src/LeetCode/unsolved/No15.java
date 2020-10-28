package LeetCode.unsolved;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class No15 {

    private List<List<Integer>> result = new ArrayList<List<Integer>>();
    private List<Integer> arrayList = new ArrayList<Integer>();
    private HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>();
    private List<Integer> values = new ArrayList<Integer>();

    public List<List<Integer>> threeSum(int[] nums) {
        for (int num : nums) {
            if (!hashMap.containsKey(num)) {
                hashMap.put(num, 1);
            } else {
                hashMap.put(num, hashMap.get(num) + 1);
            }
        }
        values.addAll(hashMap.keySet());
        backtrack(0);
        return result;
    }

    public void backtrack(int pointer){
        if(arrayList.size() == 3){
            if(arrayList.get(0) + arrayList.get(1) + arrayList.get(2) == 0){
                result.add(new ArrayList<Integer>(arrayList));
            }
            return;
        }
        if(pointer >= values.size()){
            return;
        }
        int num = values.get(pointer);
        int size = Math.min(3 - arrayList.size(), hashMap.get(num));
        for(int i=0;i<size;i++){
            arrayList.add(new Integer(num));
            backtrack(pointer+1);
        }
        for(int i=0;i<size;i++){
            arrayList.remove(new Integer(num));
        }
        backtrack(pointer+1);
    }

    //LeetCode官方解法
    public List<List<Integer>> threeSumLeetCode(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        // 枚举 a
        for (int first = 0; first < n; ++first) {
            // 需要和上一次枚举的数不相同
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            // c 对应的指针初始指向数组的最右端
            int third = n - 1;
            int target = -nums[first];
            // 枚举 b
            for (int second = first + 1; second < n; ++second) {
                // 需要和上一次枚举的数不相同
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                // 需要保证 b 的指针在 c 的指针的左侧
                while (second < third && nums[second] + nums[third] > target) {
                    --third;
                }
                // 如果指针重合，随着 b 后续的增加
                // 就不会有满足 a+b+c=0 并且 b<c 的 c 了，可以退出循环
                if (second == third) {
                    break;
                }
                if (nums[second] + nums[third] == target) {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(nums[first]);
                    list.add(nums[second]);
                    list.add(nums[third]);
                    ans.add(list);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        No15 obj = new No15();
        int[] nums = {-1, 0, 1, 2, -1, -4};
        System.out.println(obj.threeSum(nums));
    }

}
