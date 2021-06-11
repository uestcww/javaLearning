package JianZhiOffer.resolved;

import java.util.HashSet;

public class Offer03 {

    // 时间 29.74% 内存30.62%
    // 思路很简单，就是一个set就好了，但是效果这么不好，肯定有更好的方法
    public int findRepeatNumber0(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for(int num : nums){
            if(set.contains(num)){
                return num;
            }else{
                set.add(num);
            }
        }
        return -1;
    }

    // 用时84.69% 内存65.16%
    // 用一个数组代替了set，但是其实思路还是一样的
    public int findRepeatNumber(int[] nums) {
        boolean[] data = new boolean[nums.length];
        for (int num : nums){
            if(data[num]){
                return num;
            }
            data[num] = true;
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("2或者3 ? " + new Offer03().findRepeatNumber(new int[]{2, 3, 1, 0, 2, 5, 3}));
    }


}
