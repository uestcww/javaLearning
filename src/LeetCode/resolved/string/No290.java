package LeetCode.resolved.string;

import java.util.HashMap;
import java.util.Map;

public class No290 {

    // 初见过，其实是很粗暴的解法，用时很长，但是内存消耗比较好
    public boolean wordPattern(String pattern, String s) {
        char[] patternChars = pattern.toCharArray();
        String[] strs = s.split(" ");
        if(pattern.length() != strs.length){
            return false;
        }
        int length = pattern.length();
        for(int i=0;i<length-1;++i){
            for(int j=i+1;j<length;++j){
                if(patternChars[i] == patternChars[j]){
                    if(!strs[i].equals(strs[j])){
                        return false;
                    }
                }else{
                    if(strs[i].equals(strs[j])){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean wordPatternLeetCode(String pattern, String str) {
        Map<String, Character> str2ch = new HashMap<String, Character>();
        Map<Character, String> ch2str = new HashMap<Character, String>();
        int m = str.length();
        int i = 0;
        for (int p = 0; p < pattern.length(); ++p) {
            char ch = pattern.charAt(p);
            if (i >= m) {
                return false;
            }
            int j = i;
            while (j < m && str.charAt(j) != ' ') {
                j++;
            }
            String tmp = str.substring(i, j);
            if (str2ch.containsKey(tmp) && str2ch.get(tmp) != ch) {
                return false;
            }
            if (ch2str.containsKey(ch) && !tmp.equals(ch2str.get(ch))) {
                return false;
            }
            str2ch.put(tmp, ch);
            ch2str.put(ch, tmp);
            i = j + 1;
        }
        return i >= m;
    }

    public static void main(String[] args) {
        No290 obj = new No290();
        System.out.println("true ? " + obj.wordPattern("abba", "dog cat cat dog"));
        System.out.println("false ? " + obj.wordPattern("abba", "dog cat cat fish"));
        System.out.println("false ? " + obj.wordPattern("aaaa", "dog cat cat dog"));
        System.out.println("false ? " + obj.wordPattern("abba", "dog dog dog dog"));


    }

}
