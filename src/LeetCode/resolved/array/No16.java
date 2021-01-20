package LeetCode.resolved.array;

import java.util.Arrays;

public class No16 {

    // 注意吸收双指针的思想，将数组排序后，second 和 third是两个边界，都可以向中心移动
    // 结果又是很不好，用时18%，内存58%，不过总算是通过了
    public int threeSumClosest(int[] nums, int target){
        // nums的长度
        int length = nums.length;
        // 将nums排序，方便后序的操作
        Arrays.sort(nums);
        // 初始化 res 和 dif 一个表示结果，一个表示差值，差值用来衡量新旧结果哪个更好一些
        int res = nums[0] + nums[1] + nums[2];
        int dif = Math.abs(target - res);
        // 一个三层循环
        for(int first = 0;first < length;++first){
            // third从后往前，而 first 和 second 都是从前往后
            int third = length-1;
            for(int second=first+1;second < length-1;++second){
                // 获取当前的 target 和差值，新target是为了在这一层循环时不需要每次都调用 nums[first] 和 nums[second] 的值
                int newTarget = target - nums[first] - nums[second];
                int newDif = Math.abs(newTarget - nums[third]);
                // 第三层循环，如果将 third 向前移动至少不会使结果变得更坏，那就移动
                // 这里一定注意一定要加等号，不然的话会导致third无法向前移动，就会导致前面的值取不到
                while(third-1 > second && Math.abs(newTarget-nums[third-1]) <= newDif){
                    --third;
                    newDif = Math.abs(newTarget - nums[third]);
                }
                // 如果获得了更好的结果，那就更新
                if(newDif < dif){
                    res = nums[first] + nums[second] + nums[third];
                    dif = Math.abs(target - res);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        No16 obj = new No16();
        System.out.println("250 ? " + obj.threeSumClosest(new int[]{1, 1, 48, 50, 99, 100, 103, 333, 333}, 250));
    }

}
