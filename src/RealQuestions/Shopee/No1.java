package RealQuestions.Shopee;

// 时间限制：C/C++ 1秒，其他语言2
// 空间限制：C/C++ 32M，其他语言64M
//
// 孔乙己说“回”字有四种写法，编程语言中常见的命名风格有如下四种：
//  - 全部首字母大写
//  - 第一个单词首字母小写，其余单词首字母大写
//  - 单词全部小写，由下划线连接
//  - 单词全部小写，由减号连接
//
// 请设计并实现一个caseTransform函数，使得一个字符串str可以被方便地转成四种形式，并且将四种形式通过空格拼接成一个字符串返回
// 为方便起见，这里假设输入字符串全部符合以上四种形式的英文字母组合
//
// 输入描述:
//     PascalCaseTest
// 输出描述:
//     PascalCaseTest  pascalCaseTest  pascal_case_test pascal-case-test
// 输入例子1:
//     PascalCaseTest
// 输出例子1:
//     PascalCaseTest pascalCaseTest pascal_case_test pascal-case-test

import java.util.ArrayList;
import java.util.List;

public class No1 {

    public static void main(String[] args) {

    }

//    public static String func(String str){
//        String[] result = new String[4];
//        StringBuilder stringBuilder = new StringBuilder(str);
//        List<String> parts = new ArrayList<>();
//        if(str.contains("-")){
//            result[3] = str;
//            result[2] = str.replaceAll("-", "_");
//            int index = stringBuilder.indexOf("-");
//            while(index != -1){
//                parts.add(stringBuilder.substring(0, index));
//                stringBuilder.delete(0, index+1);
//                index = stringBuilder.indexOf("-");
//            }
//            parts.add(stringBuilder.toString());
//            String temp = "";
//            char[] tempArr;
//            for(String part : parts){
//                tempArr = part.toCharArray();
//                tempArr[0] -= 32;
//                temp += String.valueOf(tempArr);
//            }
//            result[0] = temp;
//            tempArr = temp.toCharArray();
//            tempArr[0] += 32;
//            result[1] = String.valueOf(tempArr);
//        }else if(str.contains("_")){
//            int index = stringBuilder.indexOf("_");
//            while(index != -1){
//                parts.add(stringBuilder.substring(0, index));
//                stringBuilder.delete(0, index+1);
//                index = stringBuilder.indexOf("_");
//            }
//            parts.add(stringBuilder.toString());
//        }else if(Character.isUpperCase(str.charAt(0))){
//
//        }else{
//
//        }
//
//
//    }


}
