package LeetCode.solving;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class No76 {

    public String minWindow(String s, String t) {
        if(t.length() <= 1){
            if(s.contains(t)){
                return t;
            }else{
                return "";
            }
        }
        char[] target = t.toCharArray();
        List<Character> list = new ArrayList<>();
        for(Character c : target){
            list.add(c);
        }
        int left = 0, right;
        char[] str = s.toCharArray();
        while(!list.contains(str[left])){
            ++left;
        }
        list.remove(str[left]);
        right = left;
        int length = str.length;
        Deque<Integer> deque = new ArrayDeque<>();
        while(!list.isEmpty()){
            ++right;
            while(right < length && !list.contains(str[right])){
                ++right;
            }
            if(right >= length){
                return "";
            }
            deque.offer(right);
            list.remove(str[right]);
        }
        int resLen, resLeft = left, resRight = right;
        char ch;
        resLen = resRight - resLeft + 1;
        while(true){
            ch = str[left];
            left = deque.poll();
            while(right < length && str[right] != ch){
                ++right;
            }
            if(right >= length){
                return s.substring(resLeft, resRight+1);
            }
            deque.offer(right);
            if((right - left + 1) < resLen){
                resLeft = left;
                resRight = right;
                resLen = resRight - resLeft - 1;
            }
        }
    }

    public static void main(String[] args) {
        No76 obj = new No76();
        System.out.println(" BANC ? " + obj.minWindow("ADOBECODEBANC", "ABC"));
        System.out.println(" a ? " + obj.minWindow("a", "a"));
    }

}
