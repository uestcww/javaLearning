package RealQuestions.Shopee;

// 时间限制：C/C++ 1秒，其他语言2秒
// 空间限制：C/C++ 256M，其他语言512M
//
// 给定一个字符串式子，返回它的计算结果。算术规则为: k*[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。
// 注意 k 保证为正整数。e.g. s = "3*[a2*[c]]", 返回 “accaccacc”
//
// 输入例子1:
//     "3*[a2*[c]]"
// 输出例子1:
//     "accaccacc"

import java.util.ArrayDeque;
import java.util.Deque;

public class No2 {

    public static void main(String[] args) {
        No2 obj = new No2();
        System.out.println("accaccacc ?");
        System.out.println(obj.computeString("3*[a2*[c]]"));
    }

    public String computeString (String str) {
        char[] input = str.toCharArray();
        StringBuilder result = new StringBuilder();
        String temp;
        int num;
        Deque<String> stack = new ArrayDeque<>();
        Character c;
        for(int i=0;i<input.length;++i){
            c = input[i];
            if(Character.isDigit(c)){
                /*
                拼接数字
                */
//                stack.push(new Character(c));
                ++i;
            }else if(c.equals('[')){
                stack.push(String.valueOf(c));
            }else if(c.equals(']')){
//                while(!(c = stack.pop()).equals('[')){
//                    result.insert(0, c);
//                }
                num = Integer.valueOf(stack.pop());
                temp = result.toString();
                while(num-- > 0 ){
                    result.append(temp);
                }
            }else{
//                stack.push(new Character(c));
            }
        }
        return result.toString();
    }

}
