package LeetCode.resolved;

import java.util.ArrayList;

public class No738 {

    //初见被杀，一个数一个数递减，超出时间限制，这是预料之内的
    public int monotoneIncreasingDigits1(int N) {
        while(!isMonotoneIncreasing(N)){
            --N;
        }
        return N;
    }
    public boolean isMonotoneIncreasing(int num){
        if(num <= 9){
            return true;
        }
        int bits = 0;
        int temp = num % 10;
        while(num != 0){
            if(num % 10 > temp){
                return false;
            }else{
                temp = num % 10;
            }
            ++bits;
            num /= 10;
        }
        return true;
    }

    // 递减的顺序还是数字从大到小，但是可以跳着判断
    public int monotoneIncreasingDigits(int N){
        // 个位数就直接返回就好了
        if(N <= 9){
            return N;
        }
        // 使用ArrayList保存每一位
        ArrayList<Integer> arr = new ArrayList<Integer>();
        while(N != 0){
            arr.add(N % 10);
            N /= 10;
        }
        // 从个位开始遍历，确保比当前位高的数都不比当前位大
        // 如果高位比当前位大了，就使当前位和更低位都为9，高一位减一
        for(int i=0;i<arr.size()-1;++i){
            for(int j=i+1;j<arr.size();++j){
                if(arr.get(i) < arr.get(j)){
                    int temp = i;
                    while(temp >= 0){
                        arr.set(temp, 9);
                        --temp;
                    }
                    arr.set(i+1, arr.get(i+1) - 1);
                }
            }
        }
        // 使用ArrayList重组数字，返回结果
        for(int i=arr.size()-1;i>=0;--i){
            N *= 10;
            N += arr.get(i);
        }
        return N;
    }

    // 我是从低位到高位，官方解题思路是从高位到低位，感觉还是官方版好一些
    public int monotoneIncreasingDigitsLeetCode(int N) {
        // 这里比我巧妙多了
        char[] strN = Integer.toString(N).toCharArray();
        int i = 1;
        while (i < strN.length && strN[i - 1] <= strN[i]) {
            i += 1;
        }
        if (i < strN.length) {
            while (i > 0 && strN[i - 1] > strN[i]) {
                strN[i - 1] -= 1;
                i -= 1;
            }
            for (i += 1; i < strN.length; ++i) {
                strN[i] = '9';
            }
        }
        return Integer.parseInt(new String(strN));
    }

    public static void main(String[] args){
        No738 obj = new No738();
        System.out.println("9 ? " + obj.monotoneIncreasingDigits(10));
        System.out.println("1234 ? " + obj.monotoneIncreasingDigits(1234));
        System.out.println("299 ? " + obj.monotoneIncreasingDigits(332));

        System.out.println("99 ? " + obj.monotoneIncreasingDigits(101));



    }

}
