package LeetCode.solving;

public class No523 {

    /*
    * 真服了我自己了，我怎么这么傻，这都想不到，真他妈废物
    * */

    // 超出时间限制
    public boolean checkSubarraySum0(int[] nums, int k) {
        int length = nums.length;
        if(length <= 1){
            return false;
        }
        int[] arr = new int[length+1];
        arr[0] = 0;
        for(int i=1;i<length+1;++i){
            arr[i] = arr[i-1] + nums[i-1];
        }
        for(int i=0;i<length;++i){
            for(int j=i+2;j<=length;++j){
                if((arr[j]-arr[i]) % k == 0){
                    return true;
                }
            }
        }
        return false;
    }

    // 依然超出时间限制
    public boolean checkSubarraySum(int[] nums, int k) {
        int length = nums.length;
        if(length <= 1){
            return false;
        }
        int sum = 0;
        for(int i=0;i<length;++i){
            sum += nums[i];
        }
        return check(nums, 0, length-1, k, sum);
    }

    public boolean check(int[] nums, int left, int right, int k, int sum){
        if(right - left < 1){
            return false;
        }
        if(sum % k == 0){
            return true;
        }
        return check(nums, left+1, right, k, sum-nums[left]) || check(nums, left, right-1, k, sum-nums[right]);
    }

    public static void main(String[] args) {
        System.out.println("true ? " + new No523().checkSubarraySum(new int[]{23, 2, 4, 6, 7}, 6));
        System.out.println("true ? " + new No523().checkSubarraySum(new int[]{23, 2, 6, 4, 7}, 6));
        System.out.println("false ? " + new No523().checkSubarraySum(new int[]{23, 2, 6, 4, 7}, 13));
        System.out.println("false ? " + new No523().checkSubarraySum(new int[]{0}, 1));
        System.out.println("false ? " + new No523().checkSubarraySum(new int[]{1, 0}, 2));
        System.out.println("true ? " + new No523().checkSubarraySum(new int[]{2, 4, 3}, 6));
    }


}
