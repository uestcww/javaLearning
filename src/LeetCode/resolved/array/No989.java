package LeetCode.resolved.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class No989 {

    // 虽然是简单题，虽然思路都是这个思路，但是我和官方比，还是复杂了不少
    public List<Integer> addToArrayForm(int[] A, int K) {
        // 将K存在list中
        List<Integer> KList = new ArrayList<>();
        while(K > 0){
            KList.add(K%10);
            K/=10;
        }
        int size = Math.max(KList.size(), A.length);
        // 如果A的位数多，就给K多填0
        while(KList.size() < size){
            KList.add(0);
        }
        int overflow = 0, temp;
        // 循环，按位进行加法
        for(int i=0;i<size;++i){
            // 如果A已经加完了，就不要加A了
            if(A.length-1-i < 0){
                temp = KList.get(i) + overflow;
            }else{
                temp = KList.get(i) + A[A.length-1-i] + overflow;
            }
            // 看看是否有进位
            if(temp > 9){
                overflow = temp/10;
                temp = temp%10;
            }else{
                overflow = 0;
            }
            // 更新加后的值
            KList.set(i, temp);
        }
        // 如果最高位还有进位，那就加个最高位
        if(overflow != 0){
            KList.add(overflow);
        }
        // 翻转list，使最高位在0位置
        Collections.reverse(KList);
        return KList;
    }

    // 看到这个解法，也是，我为什么先拆了K，然后再做加法呢，可以一起完成的
    public List<Integer> addToArrayFormLeetCode1(int[] A, int K) {
        List<Integer> res = new ArrayList<Integer>();
        int n = A.length;
        for (int i = n - 1; i >= 0; --i) {
            int sum = A[i] + K % 10;
            K /= 10;
            if (sum >= 10) {
                K++;
                sum -= 10;
            }
            res.add(sum);
        }
        for (; K > 0; K /= 10) {
            res.add(K % 10);
        }
        Collections.reverse(res);
        return res;
    }

    // 这种也很好，就是用整个K去与A的一位做加法，加完然后K/=10，再加A的更高一位
    public List<Integer> addToArrayFormLeetCode2(int[] A, int K) {
        List<Integer> res = new ArrayList<Integer>();
        int n = A.length;
        for (int i = n - 1; i >= 0 || K > 0; --i, K /= 10) {
            if (i >= 0) {
                K += A[i];
            }
            res.add(K % 10);
        }
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        No989 obj = new No989();
        System.out.println("[1, 2, 3, 4] ? " + obj.addToArrayForm(new int[]{1, 2, 0, 0}, 34));
        System.out.println("[4, 5, 5] ? " + obj.addToArrayForm(new int[]{2, 7, 4}, 181));
        System.out.println("[1, 0, 2, 1] ? " + obj.addToArrayForm(new int[]{2, 1, 5}, 806));
        System.out.println("[1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0] ? " + obj.addToArrayForm(new int[]{9, 9, 9, 9, 9, 9, 9, 9, 9, 9}, 1));
        System.out.println("[2, 3] ? " + obj.addToArrayForm(new int[]{0}, 23));
        System.out.println("[8, 1, 5] ? " + obj.addToArrayForm(new int[]{6}, 809));
    }

}
