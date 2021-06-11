package LeetCode.resolved.string;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

public class No1309 {

    // 用时5.07%，内存5.07%，23333
    public String freqAlphabets1(String s) {
        Deque<String> deque = new ArrayDeque<>();
        char[] charArr = s.toCharArray();
        int pointer = charArr.length-1;
        while(pointer >= 0){
            if(charArr[pointer] == '#'){
                deque.push("" + charArr[pointer-2] + charArr[pointer-1]);
                pointer-=3;
            }else{
                deque.push("" + charArr[pointer--]);
            }
        }
        String result = "";
        while(!deque.isEmpty()){
            result += (char)('a' + (Integer.parseInt(deque.poll()) - 1));
        }
        return result;
    }

    // 用时5.07%，内存37.52%，难道我思路不对？
    public String freqAlphabets2(String s) {
        StringBuilder stringBuilder = new StringBuilder();
        String temp;
        char[] charArr = s.toCharArray();
        int pointer = charArr.length-1;
        while(pointer >= 0){
            if(charArr[pointer] == '#'){
                temp = "" + charArr[pointer-2] + charArr[pointer-1];
                pointer-=3;
            }else{
                temp = "" + charArr[pointer--];
            }
            stringBuilder.insert(0, (char)('a' + (Integer.parseInt(temp) - 1)));
        }
        return stringBuilder.toString();
    }

    // 用时16.89%，内存22.52%，感觉还不是对的思路，还能怎么优化啊
    public String freqAlphabets3(String s) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("1", "a");
        hashMap.put("2", "b");
        hashMap.put("3", "c");
        hashMap.put("4", "d");
        hashMap.put("5", "e");
        hashMap.put("6", "f");
        hashMap.put("7", "g");
        hashMap.put("8", "h");
        hashMap.put("9", "i");
        hashMap.put("10", "j");
        hashMap.put("11", "k");
        hashMap.put("12", "l");
        hashMap.put("13", "m");
        hashMap.put("14", "n");
        hashMap.put("15", "o");
        hashMap.put("16", "p");
        hashMap.put("17", "q");
        hashMap.put("18", "r");
        hashMap.put("19", "s");
        hashMap.put("20", "t");
        hashMap.put("21", "u");
        hashMap.put("22", "v");
        hashMap.put("23", "w");
        hashMap.put("24", "x");
        hashMap.put("25", "y");
        hashMap.put("26", "z");
        StringBuilder stringBuilder = new StringBuilder();
        String temp;
        char[] charArr = s.toCharArray();
        int pointer = charArr.length-1;
        while(pointer >= 0){
            if(charArr[pointer] == '#'){
                temp = "" + charArr[pointer-2] + charArr[pointer-1];
                pointer-=3;
            }else{
                temp = "" + charArr[pointer--];
            }
            stringBuilder.insert(0, hashMap.get(temp));
        }
        return stringBuilder.toString();
    }

    public String freqAlphabets4(String s) {
        HashMap<String, String> hashMap = new HashMap<>();
        for(int i=1;i<=26;++i){
            hashMap.put("" + i, "" +(char)(96+i));
        }
        StringBuilder stringBuilder = new StringBuilder();
        String temp;
        char[] charArr = s.toCharArray();
        int pointer = charArr.length-1;
        while(pointer >= 0){
            if(charArr[pointer] == '#'){
                temp = "" + charArr[pointer-2] + charArr[pointer-1];
                pointer-=3;
            }else{
                temp = "" + charArr[pointer--];
            }
            stringBuilder.insert(0, hashMap.get(temp));
        }
        return stringBuilder.toString();
    }

    public String freqAlphabets(String s) {
        char[] alphabetChars = new char[27];
        for (int i = 1; i < alphabetChars.length; i++) {
            alphabetChars[i] = (char) (96 + i);
        }
        StringBuilder builder = new StringBuilder();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (i < chars.length - 2 && chars[i + 2] == '#') {
                builder.append(alphabetChars[(chars[i] - '0') * 10 + (chars[i + 1] - '0')]);
                i += 2;
            } else {
                builder.append(alphabetChars[chars[i] - '0']);
            }
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        No1309 obj = new No1309();
        String str1 = "10#11#12";
        System.out.println("jkab".equals(obj.freqAlphabets(str1)));
        String str2 = "1326#";
        System.out.println("acz".equals(obj.freqAlphabets(str2)));
        String str3 = "25#";
        System.out.println("y".equals(obj.freqAlphabets(str3)));
        String str4 = "12345678910#11#12#13#14#15#16#17#18#19#20#21#22#23#24#25#26#";
        System.out.println("abcdefghijklmnopqrstuvwxyz".equals(obj.freqAlphabets(str4)));
    }

}
