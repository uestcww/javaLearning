package LeetCode.resolved.array;

import java.util.Arrays;

// 也可以不排序，直接线性扫描，找到最小的两个数和最大的三个数就好了
public class No628 {

    // 就这。。。。也太简单了
    // 先排序一下，然后由于有可能有负数，所以要考虑两个负数和一个正数的乘积，还有三个正数乘积这两种情况
    // 所以最后需要判断一下，就这
    public int maximumProduct(int[] nums) {
        int length = nums.length;
        Arrays.sort(nums);
        int a = nums[length-1] * nums[length-2] * nums[length-3];
        int b = nums[0] * nums[1] * nums[length-1];
        return Math.max(a, b);
    }

    public int maximumProduct2(int[] nums) {
        int length = nums.length;
        Arrays.sort(nums);
        return Math.max(nums[length-1] * nums[length-2] * nums[length-3], nums[0] * nums[1] * nums[length-1]);
    }

    public static void main(String[] args) {
        No628 obj = new No628();
        obj.maximumProduct(new int[]{1, 2, -3});
    }

}
