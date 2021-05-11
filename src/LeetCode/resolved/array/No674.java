package LeetCode.resolved.array;

public class No674 {

    // 这题可能是我做过最简单的一道题了吧
    // 用时99.83%，内存30.30%
    public int findLengthOfLCIS(int[] nums) {
        // 照常判断长度
        int length = nums.length;
        if(length <= 1){
            return length;
        }
        // count存当前长度，max存历史长度
        int count = 1, max = Integer.MIN_VALUE;
        // 遍历，如果当前元素比前一元素大，就加一，反之就判断count与max，并把count置一
        for(int i=1;i<length;++i){
            if(nums[i] > nums[i-1]){
                ++count;
            }else{
                if(count > max){
                    max = count;
                }
                count = 1;
            }
        }
        // 由于有可能一直增大，count比max大，也有可能max比count大，所以最后判断一下
        return Math.max(count, max);
    }

    // 个人感觉不如我的方法好
    public int findLengthOfLCISLeetCode(int[] nums) {
        int ans = 0;
        int n = nums.length;
        int start = 0;
        for (int i = 0; i < n; i++) {
            if (i > 0 && nums[i] <= nums[i - 1]) {
                start = i;
            }
            ans = Math.max(ans, i - start + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        No674 obj = new No674();
        System.out.println("3 ? " + obj.findLengthOfLCIS(new int[]{1, 3, 5, 4, 7}));
        System.out.println("1 ? " + obj.findLengthOfLCIS(new int[]{2, 2, 2, 2, 2}));
    }

}
