package LeetCode.resolved.array;

import java.util.HashMap;

public class No477 {

    public int totalHammingDistance0(int[] nums) {
        int length = nums.length, count = 0;
        if(length <= 1){
            return 0;
        }
        for(int i=0;i<length-1;++i){
            for(int j=i+1;j<length;++j){
                count += hammingDistance(nums[i], nums[j]);
            }
        }
        return count;
    }

    public int hammingDistance(int a, int b){
        int num = a ^ b, count = 0;
        while(num != 0){
            if((num & 1) == 1){
                ++count;
            }
            num = num >> 1;
        }
        return count;
    }

    public int totalHammingDistance1(int[] nums) {
        int length = nums.length, count = 0;
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        if(length <= 1){
            return 0;
        }
        for(int i=0;i<length-1;++i){
            for(int j=i+1;j<length;++j){
                int num = nums[i] ^ nums[j];
                if(hashMap.containsKey(num)){
                    count += hashMap.get(num);
                }else{
                    int tempNum = num, temp = 0;
                    while(tempNum != 0){
                        if((tempNum & 1) == 1){
                            ++temp;
                        }
                        tempNum = tempNum >> 1;
                    }
                    hashMap.put(num, temp);
                    count += temp;
                }
            }
        }
        return count;
    }

    // 优化了一下，不用模运算了，使用与运算，用时 40.36% 内存 88.83%
    // 脑子还可以，最后做出来了 用时 18.68% 内存67.45%
    // 核心思路：一次性统计数组中所有数的最低位，然后计算这一位的差的数量，然后所有数右移一位
    public int totalHammingDistance(int[] nums) {
        int length = nums.length, count = 0, countOne;
        for(int i=1;i<=32;++i){
            countOne = 0;
            for(int j=0;j<length;++j){
                countOne += nums[j] & 1;
                nums[j] = nums[j] >> 1;
            }
            count += countOne * (length - countOne);
        }
        return count;
    }

    // 漂亮，这代码写的，真爽
    public int totalHammingDistanceLeetCode(int[] nums) {
        int ans = 0, n = nums.length;
        for (int i = 0; i < 30; ++i) {
            int c = 0;
            for (int val : nums) {
                c += (val >> i) & 1;
            }
            ans += c * (n - c);
        }
        return ans;
    }

    public static void main(String[] args) {

        System.out.println("6 ? " + new No477().totalHammingDistance(new int[]{4, 14, 2}));
        System.out.println("4 ? " + new No477().totalHammingDistance(new int[]{4, 14, 4}));


    }



}
