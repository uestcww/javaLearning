package LeetCode.resolved.string;

import java.util.Deque;
import java.util.LinkedList;

public class No8 {

    public int myAtoi(String str) {
        if(str == null || str.length() < 1){
            return 0;
        }
        int pointer = 0;
        char temp;
        int isPositive = 1;
        int length = str.length();
        while(pointer<length  &&  str.charAt(pointer) == ' '){
            pointer++;
        }
        if(pointer>=length){
            return 0;
        }
        temp = str.charAt(pointer);
        if((temp<48||temp>57) && temp!=43 && temp!=45){
            return 0;
        }
        switch (temp){
            case 43: isPositive=1;pointer++;break;
            case 45: isPositive=-1;pointer++;break;
        }
        if(pointer>=length){
            return 0;
        }
        Deque<Integer> stack = new LinkedList<Integer>();
        temp = str.charAt(pointer);
        while(pointer<length){
            temp = str.charAt(pointer);
            if(temp<48||temp>57){
                break;
            }
            stack.push(temp-48);
            pointer++;
        }
        int result=0,count=0,middleResult=0;
        while(!stack.isEmpty()){
            middleResult = (int) (stack.pop()*Math.pow(10,count));
            if(result>result+middleResult){
                if(isPositive == 1){
                    return Integer.MAX_VALUE;
                }else {
                    return Integer.MIN_VALUE;
                }
            }
            result+=middleResult;
            count++;
        }
        return result*isPositive;
    }

    public static void main(String[] args) {

        No8 obj = new No8();
        String str = " ";
        int result = obj.myAtoi(str);
        System.out.println(result);


    }


}
