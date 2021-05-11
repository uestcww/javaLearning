package LeetCode.resolved.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class No888 {

    // easy，把握住一点，就是两个数组之和的差值的一半，就是两个数的差值
    // 用时93.62%，内存46.29%
    public int[] fairCandySwap(int[] A, int[] B) {
        int aSum = 0, bSum = 0;
        HashSet<Integer> set = new HashSet<>();
        for(int a : A){
            aSum += a;
        }
        for(int b : B){
            set.add(b);
            bSum += b;
        }
        int key = (bSum - aSum) / 2;
        for(int a : A){
            if(set.contains(a + key)){
                return new int[]{a, a+key};
            }
        }
        return null;
    }

    // 跟我一样的思路
    public int[] fairCandySwapLeetCode(int[] A, int[] B) {
        int sumA = Arrays.stream(A).sum();
        int sumB = Arrays.stream(B).sum();
        int delta = (sumA - sumB) / 2;
        Set<Integer> rec = new HashSet<Integer>();
        for (int num : A) {
            rec.add(num);
        }
        int[] ans = new int[2];
        for (int y : B) {
            int x = y + delta;
            if (rec.contains(x)) {
                ans[0] = x;
                ans[1] = y;
                break;
            }
        }
        return ans;
    }

    public static void main(String[] args) {

    }

}
