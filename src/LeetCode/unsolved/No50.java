package LeetCode.unsolved;

public class No50 {

    public double myPow(double x, int n) {
        if(x == 1 || n == 0){
            return 1;
        }
        return 0;
    }

    // 又被LeetCode官方虐了，最近总是被虐，我要疯了
    public double myPowLeetCode(double x, int n) {
        long N = n;
        return N >= 0 ? quickMul(x, N) : 1.0 / quickMul(x, -N);
    }
    double quickMul(double x, long N) {
        double ans = 1.0;
        // 贡献的初始值为 x
        double x_contribute = x;
        // 在对 N 进行二进制拆分的同时计算答案
        while (N > 0) {
            if (N % 2 == 1) {
                // 如果 N 二进制表示的最低位为 1，那么需要计入贡献
                ans *= x_contribute;
            }
            // 将贡献不断地平方
            x_contribute *= x_contribute;
            // 舍弃 N 二进制表示的最低位，这样我们每次只要判断最低位即可
            N /= 2;
        }
        return ans;
    }

    public static void main(String[] args) {
        No50 obj = new No50();
//        System.out.println("1024.00000 ? " + obj.myPow(2.00000, 10));
//        System.out.println("9.26100 ? " + obj.myPow(2.10000, 3));
//        System.out.println("0.25000 ? " + obj.myPow(2.00000, -2));
        System.out.println("0.0 ? " + obj.myPow(2.00000, -2147483648));
    }
}
