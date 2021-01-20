package LeetCode.resolved.array;

import java.util.ArrayList;
import java.util.List;

public class No1018 {

    // 意识到数字太大，首先将int改为long，后来还是太大，说明有别的解法，由%5操作想到了值保存余数
    public List<Boolean> prefixesDivBy5(int[] A) {
        if(A.length < 1){
            return null;
        }
        int mod = 0;
        List<Boolean> res = new ArrayList<>();
        for(int i=0;i<A.length;++i){
            mod = (mod * 2 + A[i]) % 5;
            if(mod == 0){
                res.add(true);
            }else{
                res.add(false);
            }
        }
        return res;
    }

    // 意识到数字太大，首先将int改为long，后来还是太大，说明有别的解法，由%5操作想到了值保存余数
    // 看了答案后发现有的地方可以修改，使结果更好看一些
    public List<Boolean> prefixesDivBy52(int[] A) {
        int mod = 0;
        List<Boolean> res = new ArrayList<>();
        for(int i : A){
            mod = ((mod << 1) + i) % 5;
            res.add(mod == 0);
        }
        return res;
    }

    public static void main(String[] args) {
        No1018 obj = new No1018();
        System.out.println("[false, false, true, false, false, false, false, false, false, false, true, true, true, true, true, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, false, false, false, true, false, false, true, false, false, true, true, true, true, true, true, true, false, false, true, false, false, false, false, true, true]");
        System.out.println(obj.prefixesDivBy5(new int[]{1,0,1,1,1,1,0,0,0,0,1,0,0,0,0,0,1,0,0,1,1,1,1,1,0,0,0,0,1,1,1,0,0,0,0,0,1,0,0,0,1,0,0,1,1,1,1,1,1,0,1,1,0,1,0,0,0,0,0,0,1,0,1,1,1,0,0,1,0}));
    }

}
