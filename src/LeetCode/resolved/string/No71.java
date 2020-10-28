package LeetCode.resolved.string;

import java.util.Deque;
import java.util.LinkedList;

public class No71 {

    //可以用字符串的split方法
    public String simplifyPath(String path) {
        char[] charArr = path.toCharArray();
        Deque<String> stack = new LinkedList<String>();
        int pointer = 0, length = charArr.length;
        String str;
        while(pointer < length){
            str = "";
            while(pointer < length && charArr[pointer] == '/'){
                ++pointer;
            }
            if(pointer >= length){
                break;
            }
            while(pointer < length && charArr[pointer] != '/'){
                str+=charArr[pointer++];
            }
            if(str.equals("..")){
                if(!stack.isEmpty()){
                    stack.pop();
                }
            }else if(!str.equals(".")){
                stack.push(str);
            }
        }
        if(stack.isEmpty()){
            return "/";
        }
        String result = "";
        while(!stack.isEmpty()){
            result = "/" + stack.pop() + result;
        }
        return result;
    }

    public static void main(String[] args) {
        No71 obj = new No71();
        String str1 = "/home/";
        System.out.println("******************");
        System.out.println("input   =" + str1);
        System.out.println("output  =" + obj.simplifyPath(str1));
        System.out.println("expected=" + "/home");

        String str2 = "/../";
        System.out.println("******************");
        System.out.println("input   =" + str2);
        System.out.println("output  =" + obj.simplifyPath(str2));
        System.out.println("expected=" + "/");

        String str3 = "/home//foo/";
        System.out.println("******************");
        System.out.println("input   =" + str3);
        System.out.println("output  =" + obj.simplifyPath(str3));
        System.out.println("expected=" + "/home/foo");

        String str4 = "/a/./b/../../c/";
        System.out.println("******************");
        System.out.println("input   =" + str4);
        System.out.println("output  =" + obj.simplifyPath(str4));
        System.out.println("expected=" + "/c");

        String str5 = "/a/../../b/../c//.//";
        System.out.println("******************");
        System.out.println("input   =" + str5);
        System.out.println("output  =" + obj.simplifyPath(str5));
        System.out.println("expected=" + "/c");

        String str6 = "/a//b////c/d//././/..";
        System.out.println("******************");
        System.out.println("input   =" + str6);
        System.out.println("output  =" + obj.simplifyPath(str6));
        System.out.println("expected=" + "/a/b/c");

        String str7 = "/...";
        System.out.println("******************");
        System.out.println("input   =" + str7);
        System.out.println("output  =" + obj.simplifyPath(str7));
        System.out.println("expected=" + "/...");
    }

}
