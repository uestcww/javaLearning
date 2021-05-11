package LeetCode.resolved;

public class No29 {

    // 存被除数，主要是后面的递归函数需要，所以要用全局变量的形式存储
    private long Dividend;
    // 这个就是最后的结果，也是由于递归函数的需要
    private long count = 0;
    // 终于做出来了，用时100%，不过内存用的多，18.33%
    // 可能是因为我分了四个的原因吧
    public int divide(int dividend, int divisor) {
        // 首先做一些简单的判断，被除数为0，结果直接为0，除数为1，结果为被除数
        if(dividend == 0){
            return 0;
        }
        if(divisor == 1){
            return dividend;
        }
        // 由于有溢出的问题，所以全部使用long来存储
        long Divisor = (long)divisor;
        // 判断被除数与除数的正负
        boolean isNegative1 = dividend < 0;
        boolean isNegative2 = divisor < 0;
        // 根据不同的正负情况，分四种情况
        if(isNegative1){
            // 被除数为负，转为正
            Dividend = -(long)dividend;
            if(isNegative2){
                // 负数除以负数
                positiveDividePositive(1, -Divisor);
            }else{
                // 负数除以正数
                positiveDividePositive(1, Divisor);
                // 负正得负，所以最终结果为负
                count = -count;
            }
        }else{
            // 被除数为正，不变
            Dividend = (long)dividend;
            if(isNegative2){
                // 正数除以负数
                positiveDividePositive(1, -Divisor);
                // 正负为负，转为负
                count = -count;
            }else{
                // 正数除以正数
                positiveDividePositive(1, Divisor);
            }
        }
        // 如果count大于最大值，说明溢出了，返回最大值
        if(count > Integer.MAX_VALUE){
            return Integer.MAX_VALUE;
        }else{
            return (int)count;
        }
    }
    // 递归，n负责记录当前的倍数，divisor是当前除数
    public void positiveDividePositive(long n, long divisor){
        // 如果被除数大于两倍的除数，那就将除数扩大两倍
        if(Dividend >= (divisor + divisor)){
            positiveDividePositive(n + n, divisor + divisor);
            // 运行到这里时，被除数已经不可能大于两倍的当前除数了，所以最多介于一倍与二倍之间，所以不需要循环
            if(Dividend >= divisor){
                Dividend -= divisor;
                count += n;
            }
        }else if(Dividend >= divisor){
            // 运行到这里的，一般都是最大的那一次，也就是n最大的，最深的那次递归
            Dividend -= divisor;
            count += n;
        }
    }

    public static void main(String[] args) {
        No29 obj = new No29();

//        System.out.println(((1)/(2)) + " ? " + new No29().divide(1, 2));
//        System.out.println(((1)/(-2)) + " ? " + new No29().divide(1, -2));
//        System.out.println(((-1)/(2)) + " ? " + new No29().divide(-1, 2));
//        System.out.println(((-1)/(-2)) + " ? " + new No29().divide(-1, -2));

//        System.out.println(((7)/(2)) + " ? " + new No29().divide(7, 2));
//        System.out.println(((7)/(-2)) + " ? " + new No29().divide(7, -2));
//        System.out.println(((-7)/(2)) + " ? " + new No29().divide(-7, 2));
//        System.out.println(((-7)/(-2)) + " ? " + new No29().divide(-7, -2));
//
//        System.out.println(((7)/(3)) + " ? " + new No29().divide(7, 3));
//        System.out.println(((7)/(-3)) + " ? " + new No29().divide(7, -3));
//        System.out.println(((-7)/(3)) + " ? " + new No29().divide(-7, 3));
//        System.out.println(((-7)/(-3)) + " ? " + new No29().divide(-7, -3));
//
//        System.out.println(((1025)/(2)) + " ? " + new No29().divide(1025, 2));
//        System.out.println(((1025)/(-2)) + " ? " + new No29().divide(1025, -2));
//        System.out.println(((-1025)/(2)) + " ? " + new No29().divide(-1025, 2));
//        System.out.println(((-1025)/(-2)) + " ? " + new No29().divide(-1025, -2));
//
//        System.out.println(((1025)/(3)) + " ? " + new No29().divide(1025, 3));
//        System.out.println(((1025)/(-3)) + " ? " + new No29().divide(1025, -3));
//        System.out.println(((-1025)/(3)) + " ? " + new No29().divide(-1025, 3));
//        System.out.println(((-1025)/(-3)) + " ? " + new No29().divide(-1025, -3));

//        System.out.println("2147483647 ? " + new No29().divide(2147483647, 1));
//        System.out.println("-2147483647 ? " + new No29().divide(2147483647, -1));
//        System.out.println("-2147483648 ? " + new No29().divide(-2147483648, 1));
//        System.out.println("2147483647 ? " + new No29().divide(-2147483648, -1));

        System.out.println(" ? " + new No29().divide(-1010369383, -2147483648));



//        System.out.println("1 ? " + new No29().divide(2147483647, 2147483647));
//        System.out.println("0 ? " + new No29().divide(2147483647, -2147483648));
//        System.out.println("-1 ? " + new No29().divide(-2147483648, 2147483647));
//        System.out.println("1 ? " + new No29().divide(-2147483648, -2147483648));


    }

}
