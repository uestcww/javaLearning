package LeetCode.unsolved;

import java.util.*;

public class No89 {

    private Set<Integer> numsSet;
    private int[] numsArr;
    public List<Integer> grayCode(int n) {
        if(n == 0){
            List<Integer> grayArr = new ArrayList<>();
            grayArr.add(0);
            return grayArr;
        }
        numsSet = new HashSet<Integer>();
        for(int i=0;i<n;++i){
            numsSet.add(i);
        }
        numsArr = new int[n];
        backtrack(1);
        List<Integer> list = new ArrayList<>();
        for(int e : numsArr){
            list.add(e);
        }
        return list;
    }
    public void backtrack(int pointer){

    }

    // 镜像反转法，流下了菜鸡的泪水，我满脑子都是回溯和分支界限
//    n=0 n=1  n=2  n=3
//     0   0   00   000
//         1   01   001
//             11   011
//             10   010
//                  110
//                  111
//                  101
//                  100
    public List<Integer> grayCodeMirrorReflection(int n){
        List<Integer> res = new ArrayList<Integer>() {{ add(0); }};
        int head = 1;
        for (int i = 0; i < n; i++) {
            for (int j = res.size() - 1; j >= 0; j--)
                res.add(head + res.get(j));
            head <<= 1;
        }
        return res;
    }

    // 格雷编码是有公式的，所以可以直接套公式计算
    List<Integer> ans = new ArrayList<>();
    public List<Integer> grayCode2(int n) {
        for(int i=0;i<(1<<n);i++){
            ans.add(i^(i>>1));
        }
        return ans;
    }

//    public void backtrack(int pointer, int n){
//        if(pointer )
//        int origin = grayArr.get(pointer-1);
//
//    }

    public static void main(String[] args) {

    }

}
