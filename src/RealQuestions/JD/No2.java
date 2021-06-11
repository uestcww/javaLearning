package RealQuestions.JD;

//小A做作业
//    时间限制： 5000MS
//    内存限制： 589824KB
//
//    题目描述：
//        即将进入假期的小A打算做很多作业，因为小A每天的心情不同，所以他每天可以做的作业数量可能不同。聪明的小A知道一直做作业是对身体不好的，所以需要他给自己制订一份劳逸结合的假期作业计划，也就是在每次做作业后都需要休息1天或2天（不能不休息，也不能休息大于2天），再继续做作业。
//        爱学习的小A决定在假期的第1天或第2天开始做作业，那么他在假期内最多能做多少作业？
//
//    输入描述
//        第一行有1个正整数n，表示假期的天数；
//        第二行有n个整数ai，表示小A每天能完成的作业数。
//        1 ≤ n ≤ 10000，0 ≤ ai ≤ 10000
//
//    输出描述
//        输出一行一个整数，表示小A在假期内最多能做的作业数。
//
//    样例输入
//        5
//        2 1 2 1 2
//
//    样例输出
//        6
//
//    提示
//        小A选择在第1、3、5天做作业，一共能完成的作业数为6。

import java.util.Scanner;

public class No2 {

    private static int days;
    private static int[] homeworks;
    private static int max = 0;
    private static int sum = 0;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        days = in.nextInt();
        homeworks = new int[days];
        for(int i=0;i<days;++i){
            homeworks[i] = in.nextInt();
        }
        backtrack(0);
        int a = max;
        backtrack(1);
        int b = max;
        System.out.println(Math.max(a, b));
    }
    public static void backtrack(int startDay){
        if(startDay >= days){
            if(sum > max){
                max = sum;
            }
            return;
        }
        sum += homeworks[startDay];
        backtrack(startDay+2);
        backtrack(startDay+3);
        sum -= homeworks[startDay];
    }
}





