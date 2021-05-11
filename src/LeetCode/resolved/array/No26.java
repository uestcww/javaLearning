package LeetCode.resolved.array;

import java.util.HashMap;

public class No26 {

    // 我草我是傻逼，这题中的数组是排序后的数组，我的方法完全没有用到这个条件啊

    // 很简单的一道题，不愧是easy
    public int removeDuplicates(int[] nums) {
        // 老规矩，先判断长度
        int length = nums.length;
        if(length < 1){
            return 0;
        }
        // 声明一个map，存储所有数字在数组中出现的次数，键为数字，值为出现的次数
        HashMap<Integer, Integer> map = new HashMap<>();
        // count位置前的元素全部都是不重复的，count所指向的位置是下一个不重复数字需要占据的位置
        // pointer就是简单的指针，用他来遍历数组
        int count = 0, pointer = 0;
        // 循环，遍历整个数组
        for(;pointer<length;++pointer){
            // 记录出现的数字的次数
            map.put(nums[pointer], map.getOrDefault(nums[pointer], 0) + 1);
            // 如果这个数字的出现次数小于等于1，即第一次出现，那么就需要把它存到count所指向的位置
            if(map.get(nums[pointer]) <= 1){
                nums[count] = nums[pointer];
                ++count;
            }
        }
        return count;
    }

    // 官方题解才是用到了排序这个条件
    public int removeDuplicatesLeetCode(int[] nums) {
        if (nums.length == 0) return 0;
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }

    public static void main(String[] args) {
        No26 obj = new No26();
//        System.out.println("5 ? " + obj.removeDuplicates(new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4}));
//        System.out.println("3 ? " + obj.removeDuplicates(new int[]{-1, 0, 0, 0, 0, 3, 3}));
        System.out.println("3 ? " + obj.removeDuplicates(new int[]{1, 1, 2}));
    }
}
