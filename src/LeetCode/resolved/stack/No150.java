package LeetCode.resolved.stack;

import java.util.Deque;
import java.util.LinkedList;

public class No150 {

    // 题目都说了是逆波兰表达式，那就很简单了啊
    // 遇见数字就入栈，遇见运算符就取出栈顶和栈二，用栈二 计算 栈顶（注意顺序不能反）
    // 我看网上的题解，基本都是这个思路，只是部分差异，例如用数组模拟栈啊，用if-else代替switch实现啊，用Stack而不是Deque啊
    public int evalRPN(String[] tokens) {
        // 栈，存放数字
        Deque<Integer> stack = new LinkedList<Integer>();
        // 操作数，永远是 first [运算符] second ，这是因为减法和除法有顺序
        int first, second;
        // 遍历所有的 token
        for(String token : tokens){
            switch(token){
                // 如果是加号，那就取出栈中的两个数字，做运算，然后将结果继续存入栈中
                case "+":
                    second = stack.pop();
                    first = stack.pop();
                    stack.push(first + second);
                    break;
                // 同上
                case "-":
                    second = stack.pop();
                    first = stack.pop();
                    stack.push(first - second);
                    break;
                // 同上
                case "*":
                    second = stack.pop();
                    first = stack.pop();
                    stack.push(first * second);
                    break;
                // 同上
                case "/":
                    second = stack.pop();
                    first = stack.pop();
                    stack.push(first / second);
                    break;
                // 如果是数字的话，直接存进去就好了
                default:
                    stack.push(Integer.parseInt(token));
                    break;
            }
        }
        // 只要是标准的逆波兰表达式，此时栈中有且仅有一个元素，返回它就好，它就是结果
        return stack.pop();
    }

    public static void main(String[] args) {
        No150 obj = new No150();
        System.out.println("9 ? " + obj.evalRPN(new String[]{"2", "1", "+", "3", "*"}));
        System.out.println("6 ? " + obj.evalRPN(new String[]{"4", "13", "5", "/", "+"}));
    }

}
