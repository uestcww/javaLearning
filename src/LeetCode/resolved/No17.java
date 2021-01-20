package LeetCode.resolved;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class No17 {

    // 修改了一下，用 Map<Character, String> 代替了 Map<Character, List<Character>>
    // 这个修改就让结果好了很多
    // 保存最后结果
    private List<String> res;
    // 保存每一次的结果
    private StringBuilder stringBuilder;
    // 保存输入的数据，方便顺序提取其中的字符
    private char[] inputs;
    // 保存电话上数字与其对应的字母
    private Map<Character, String> map;
    public List<String> letterCombinations(String digits) {
        res = new ArrayList<String>();
        // 如果输入的是空字符串，自然就返回空list
        if(digits.length() < 1){
            return res;
        }
        // 设置StringBuilder，注意设置好它的长度，避免越界问题
        stringBuilder = new StringBuilder();
        stringBuilder.setLength(digits.length());
        // 将输入转为字符数组，存入field中
        inputs = digits.toCharArray();
        // 把这个map生成好
        map = new HashMap<>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");
        // 开始回溯，从0位置开始
        backtrack(0);
        return res;
    }
    public void backtrack(int pointer){
        // pointer大于等于长度，这时已经生成了一个结果，该把这个结果存好
        if(pointer >= inputs.length){
            res.add(stringBuilder.toString());
        }else{
            //遍历map中对应的列表，每次都是改变固定的位置上的字符，位置由pointer规定
            for(char c : map.get(inputs[pointer]).toCharArray()){
                stringBuilder.setCharAt(pointer, c);
                backtrack(pointer + 1);
            }
        }
    }

    public static void main(String[] args) {
        No17 obj = new No17();
        System.out.println(obj.letterCombinations("23"));
    }

}
