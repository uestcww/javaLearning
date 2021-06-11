package LeetCode.solving;

import java.util.HashMap;
import java.util.Map;

public class No525 {

    // 这都超出时间限制我是没想到的
    public int findMaxLength0(int[] nums) {
        int length = nums.length;
        if(length < 2){
            return 0;
        }
        int[] preData = new int[length+1];
        for(int i=1;i<length+1;++i){
            preData[i] = preData[i-1] + (nums[i-1] == 1 ? 1 : 0);
        }
        int max = 0;
        for(int size = 2;size<=length;size+=2){
            for(int i=0;i<length-size+1;++i){
                if(max >= size){
                    break;
                }
                if(preData[i+size] - preData[i] == (size >> 1)){
                    max = size;
                }
            }
        }
        return max;
    }

    // 效果贼差，不过还是过了 用时 5.45%（指1279ms） 内存66.43%
    public int findMaxLength(int[] nums) {
        int length = nums.length;
        if(length < 2){
            return 0;
        }
        int[] preData = new int[length+1];
        for(int i=1;i<length+1;++i){
            preData[i] = preData[i-1] + (nums[i-1] == 1 ? 1 : 0);
        }
        int size = length % 2 == 0 ? length : length-1;
        for(;size>=2;size-=2){
            for(int i=0;i<length-size+1;++i){
                if(preData[i+size] - preData[i] == (size >> 1)){
                    return size;
                }
            }
        }
        return 0;
    }

    // 秒，我只能说一句太秒了，
    // 将0看作-1，然后计算前缀和，将前缀和作为key，index作为value存哈希表
    // 如果两个不同index有相同前缀和，不就是中间0和1数量相等吗，而且，相同的前缀和中，保证存的是最左边的index，所以保证是最大值
    public int findMaxLengthLeetCode(int[] nums) {
        int maxLength = 0;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int counter = 0;
        map.put(counter, -1);
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            if (num == 1) {
                counter++;
            } else {
                counter--;
            }
            if (map.containsKey(counter)) {
                int prevIndex = map.get(counter);
                maxLength = Math.max(maxLength, i - prevIndex);
            } else {
                map.put(counter, i);
            }
        }
        return maxLength;
    }



    public static void main(String[] args) {
//        System.out.println("2 ? " + new No525().findMaxLength(new int[]{0, 1}));
//        System.out.println("2 ? " + new No525().findMaxLength(new int[]{0, 1, 0}));
//        System.out.println("6 ? " + new No525().findMaxLength(new int[]{0,0,1,0,0,0,1,1}));


        System.out.println("68 ? " + new No525().findMaxLength(new int[]{
                0,1,0,1,1,1,0,0,1,1,0,1,1,1,1,1,1,0,1,1,0,1,1,0,0,0,1,0,1,0,0,1,0,1,1,1,1,1,1,0,0,0,0,1,0,0,0,1,1,1,0,1,0,0,1,1,1,1,1,0,0,1,1,1,1,0,0,1,0,1,1,0,0,0,0,0,0,1,0,1,0,1,1,0,0,1,1,0,1,1,1,1,0,1,1,0,0,0,1,1
        }));


    }


}
