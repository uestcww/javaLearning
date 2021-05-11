package LeetCode.resolved.string;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class No22 {

    // 没想到啊，我居然一次就写对了
    // 用set来保证里面存的字符串都是不同的，避免重复的情况
    private HashSet<String> res = new HashSet<>();
    public List<String> generateParenthesis(int n) {
        // 首先把n=1的情况写进来，要不然set为空，不好操作
        res.add("()");
        // 进行递归操作
        generate(n-1);
        //经过递归，已经构造好了，把set转为list就好了
        List<String> result = new ArrayList<String>(res);
        return result;
    }
    public void generate(int n){
        // n在每回递归后自减一，小于等于零时停止
        if(n <= 0){
            return;
        }
        // 这里创建一个临时set，用来倒斗，把原始set清空，方便操作
        HashSet<String> temp = new HashSet<String>(res);
        res.clear();
        // 循环，把所有的字符串的所有位置插入一个新的括号，然后add进set中，由于是set，所以避免了重复
        for(String str : temp){
            for(int i=0;i<str.length();++i){
                res.add(new StringBuilder(str).insert(i, "()").toString());
            }
        }
        // 进行下一层递归操作
        generate(--n);
    }

    // 这个方法挺好的
    ArrayList[] cache = new ArrayList[100];
    public List<String> generateLeetCode(int n) {
        if (cache[n] != null) {
            return cache[n];
        }
        ArrayList<String> ans = new ArrayList<String>();
        if (n == 0) {
            ans.add("");
        } else {
            for (int c = 0; c < n; ++c) {
                for (String left: generateLeetCode(c)) {
                    for (String right: generateLeetCode(n - 1 - c)) {
                        ans.add("(" + left + ")" + right);
                    }
                }
            }
        }
        cache[n] = ans;
        return ans;
    }
    public List<String> generateParenthesisLeetCode(int n) {
        return generateLeetCode(n);
    }


    public static void main(String[] args) {
        No22 obj = new No22();
        System.out.println(obj.generateParenthesis(3));
    }

}
