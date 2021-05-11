package LeetCode.resolved;

public class No209 {

    // 滑动窗口，确定左边界，一点一点向右加，直到加，效果不咋地
    public int minSubArrayLen1(int target, int[] nums) {
        int length = nums.length;
        int sum;
        int res = Integer.MAX_VALUE;
        for(int left=0;left<length;++left){
            sum = nums[left];
            if(sum >= target){
                return 1;
            }
            for(int right=left+1;right<length;++right){
                sum += nums[right];
                if(sum >= target){
                    int len = right - left + 1;
                    if(len < res){
                        res = len;
                    }
                    break;
                }
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }

    // 用时17.60%， 内存82.25%，还是不太好，这个滑动窗口有点难
    public int minSubArrayLen2(int target, int[] nums) {
        int length = nums.length;
        int sum = 0;
        int res = Integer.MAX_VALUE;
        int left=0, right=0;
        while(left < length){
            while(right < length && sum < target){
                sum += nums[right];
                ++right;
            }
            if(sum >= target){
                int len = right - left;
                if(len == 1){
                    return 1;
                }
                if(len < res){
                    res = len;
                }
                sum -= nums[left];
                ++left;
            }else{
                ++left;
                sum = 0;
                right = left;
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }

    // 每次执行的结果都不一样，有1ms的，有2ms的，服了，不过应该是较好的版本了
    public int minSubArrayLen(int target, int[] nums) {
        int length = nums.length, sum = 0, res = Integer.MAX_VALUE, left=0, right=0;
        while(left < length){
            while(right < length && sum < target){
                sum += nums[right++];
            }
            if(sum >= target){
                int len = right - left;
                // nums[right]值如果特别大，会出现一直循环这个if语句
                // 这里加一个if不仅是防止right与left错位，还是因为1是最小的答案，能取到1就不需要再继续了
                if(len == 1){
                    return 1;
                }
                if(len < res){
                    res = len;
                }
                sum -= nums[left++];
            }else{
                // 这里应该是break，因为执行到这里说明，从当前left加到最后都不能大于target，以后也就没有任何机会了
                // 但是break占用内存过多，所以改为这样，使while循环中断
                left = length;
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }

    // 官方的代码写的真好看，我的真垃圾
    public int minSubArrayLenLeetCode(int s, int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        int start = 0, end = 0;
        int sum = 0;
        while (end < n) {
            sum += nums[end];
            while (sum >= s) {
                ans = Math.min(ans, end - start + 1);
                sum -= nums[start];
                start++;
            }
            end++;
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

    public static void main(String[] args) {
        No209 obj = new No209();
        System.out.println("2 ? " + obj.minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3}));
        System.out.println("1 ? " + obj.minSubArrayLen(4, new int[]{1, 4, 4}));
        System.out.println("0 ? " + obj.minSubArrayLen(11, new int[]{1, 1, 1, 1, 1, 1, 1, 1}));
    }

}
