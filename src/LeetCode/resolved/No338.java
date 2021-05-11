package LeetCode.resolved;

public class No338 {

    // 没想到这么简单，其实就是动态规划的思路
    public int[] countBits(int num) {
        int[] res = new int[num + 1];
        res[0] = 0;
        for(int i=1;i<=num;++i){
            if((i%2) != 0){
                res[i] = res[i-1] + 1;
            }else{
                res[i] = res[i >> 1];
            }
        }
        return res;
    }

    // 这样就不需要if判断了
    public int[] countBitsLeetCode(int num) {
        int[] bits = new int[num + 1];
        for (int i = 1; i <= num; i++) {
            bits[i] = bits[i >> 1] + (i & 1);
        }
        return bits;
    }

    public static void main(String[] args) {
        No338 obj = new No338();
        for(int a : obj.countBits(5)){
            System.out.print(a + " ");
        }
    }

}
