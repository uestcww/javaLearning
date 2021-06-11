package RealQuestions.JD;

//质数串
//    时间限制： 3000MS
//    内存限制： 589824KB
//
//    题目描述：
//        令一个字符串中出现次数最多的字符的出现次数为max，出现次数最少的字符的出现次数（至少一次）为min。一个仅包含小写字母的字符串，且max-min是质数被称为质数串。例如“aaabc”是质数串，因为‘a’的出现次数是3，而‘b’和‘c’的出现次数都是1，3-1=2是一个质数。
//        现在请你判断给出的字符串是否为质数串。但由于这个字符串太长，我们将用一种特殊的方式输入这个字符串，详情请见输入描述。
//
//    输入描述
//        第一行一个仅包含小写字母且长度不超过1000的字符串S
//        第二行一个整数n（1 <=n<=10^12）表示字符串T的长度
//        将S不断重复得到一个无穷长的字符串X，则X的前n个字符组成字符串T
//
//    输出描述
//        第一行，如果T是质数串，则输出“Yes”，否则输出“No”。（不含引号）
//        第二行，一个整数max-min。
//
//    样例输入
//        aaabc
//        9
//    样例输出
//        Yes
//        5
//
//    提示
//        T为aaabcaaab。max为6，min为1，max-min=5是质数。

import java.util.Scanner;

// 测试用例通过率：82%
public class No1 {
    public static boolean isP(long num){
        if(num == 0 || num == 1 || num == 2){
            return true;
        }
        for(int i=2;i<Math.sqrt(num)+1;++i){
            if(num % i == 0){
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String firstStr = in.next();
        long length = in.nextInt();
        int[] word = new int[26];
        char[] charArr = firstStr.toCharArray();
        if(length <= charArr.length){
            for(int i=0;i<length;++i){
                ++word[charArr[i] - 'a'];
            }
        }else{
            long count = length / charArr.length;
            long last = length % charArr.length;
            for(int i=0;i<charArr.length;++i){
                word[charArr[i] - 'a'] += count;
            }
            for(int i=0;i<last;++i){
                ++word[charArr[i] - 'a'];
            }
        }
        long max = 1, min = Integer.MAX_VALUE;
        for(int a : word){
            if(a > 0){
                if(a > max){
                    max = a;
                }
                if(a < min){
                    min = a;
                }
            }
        }
        System.out.println(No1.isP(max - min) ? "Yes" : "No");
        System.out.println(max - min);
    }
}
