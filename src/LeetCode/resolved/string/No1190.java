package LeetCode.resolved.string;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public class No1190 {

    // 用时 50.84% 内存 39.55%
    public String reverseParentheses(String s) {
        StringBuilder stringBuilder = new StringBuilder(s);
        Deque<Integer> stack = new ArrayDeque<>();
        int length = stringBuilder.length(), i=0, count = 0;
        while(i < length - count){
            char ch = stringBuilder.charAt(i);
            if(ch == '('){
                stack.push(i);
                stringBuilder.delete(i, i+1);
                ++count;
            }else if(ch == ')'){
                int left = stack.pop();
                StringBuilder subStr = new StringBuilder(stringBuilder.substring(left, i));
                stringBuilder.delete(left, i+1);
                stringBuilder.insert(left, subStr.reverse());
                ++count;
            }else{
                ++i;
            }
        }
        return stringBuilder.toString();
    }

    // 有意思，直接在原字符串上进行操作
    public String reverseParenthesesLeetCode1(String s) {
        Deque<String> stack = new LinkedList<String>();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                stack.push(stringBuffer.toString());
                stringBuffer.setLength(0);
            } else if (ch == ')') {
                stringBuffer.reverse();
                stringBuffer.insert(0, stack.pop());
            } else {
                stringBuffer.append(ch);
            }
        }
        return stringBuffer.toString();
    }

    // 牛逼，先把所有的括号对用 pair 数组的形式储存起来
    // 然后遍历字符串，碰到 ( 就跳到配对的 ) 位置，然后反向走，这样就倒着遍历了括号里的内容
    // 然后往回走走到 ( 的时候，又跳到配对的 ) 位置，再反向就成正着走了，这样就逆序遍历了括号里的内容
    public String reverseParenthesesLeetCode2(String s) {
        int n = s.length();
        int[] pair = new int[n];
        Deque<Integer> stack = new LinkedList<Integer>();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else if (s.charAt(i) == ')') {
                int j = stack.pop();
                pair[i] = j;
                pair[j] = i;
            }
        }

        StringBuffer sb = new StringBuffer();
        int index = 0, step = 1;
        while (index < n) {
            if (s.charAt(index) == '(' || s.charAt(index) == ')') {
                index = pair[index];
                step = -step;
            } else {
                sb.append(s.charAt(index));
            }
            index += step;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        No1190 obj = new No1190();
        System.out.println("dcba".equals(obj.reverseParentheses("(abcd)")));
        System.out.println("iloveu".equals(obj.reverseParentheses("(u(love)i)")));
        System.out.println("leetcode".equals(obj.reverseParentheses("(ed(et(oc))el)")));
        System.out.println("apmnolkjihgfedcbq".equals(obj.reverseParentheses("a(bcdefghijkl(mno)p)q")));
        System.out.println("vdgzyj".equals(obj.reverseParentheses("vdgzyj()")));
        System.out.println("tauswa".equals(obj.reverseParentheses("ta()usw((((a))))")));
    }


}
