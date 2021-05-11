package LeetCode.resolved.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class No354 {

    // 超出时间限制，思路：转为有向图，寻找最长的一条路径
    // 优化了一下，采用start数组存储以索引节点为开始的最长路径长度，依然超出时间限制，说明思路不对，还有更高效的方法
    // 再次优化，将hashmap替换为二维数组（邻接矩阵），终于跑通了，时间5.03%，内存5.12%
    private boolean[][] neighborTable;
    private int[] start;
    public int maxEnvelopes(int[][] envelopes) {
        int n = envelopes.length;
        neighborTable = new boolean[n][n];
        for(int i=0;i<n-1;++i){
            for(int j=i+1;j<n;++j){
                if(envelopes[i][0] > envelopes[j][0] && envelopes[i][1] > envelopes[j][1]){
                    neighborTable[i][j] = true;
                }else if(envelopes[i][0] < envelopes[j][0] && envelopes[i][1] < envelopes[j][1]){
                    neighborTable[j][i] = true;
                }
            }
        }
        start = new int[n];
        Arrays.fill(start, -1);
        int max = -1, len;
        for(int i=0;i<n;++i){
            if(start[i] != -1){
                continue;
            }
            len = path(i);
            max = Math.max(max, len);
        }
        return max;
    }
    public int path(int key){
        int max = 1, len;
        for(int i=0;i<neighborTable[key].length;++i){
            if(!neighborTable[key][i]){
                continue;
            }
            len = (start[i] == -1 ? path(i) : start[i]) + 1;
            max = Math.max(max, len);
        }
        start[key] = max;
        return max;
    }

    // 这题还是我没有充分利用题干提供的条件，没有充分使用【只要长或者宽不大于，就套不进去】这个条件
    // 所以相比于官方题解，我的更加的一般化，不够特殊化，从而效率不够高
    public int maxEnvelopesLeetCode(int[][] envelopes) {
        if (envelopes.length == 0) {
            return 0;
        }
        int n = envelopes.length;
        Arrays.sort(envelopes, new Comparator<int[]>() {
            public int compare(int[] e1, int[] e2) {
                if (e1[0] != e2[0]) {
                    return e1[0] - e2[0];
                } else {
                    return e2[1] - e1[1];
                }
            }
        });
        int[] f = new int[n];
        Arrays.fill(f, 1);
        int ans = 1;
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (envelopes[j][1] < envelopes[i][1]) {
                    f[i] = Math.max(f[i], f[j] + 1);
                }
            }
            ans = Math.max(ans, f[i]);
        }
        return ans;
    }

    public int maxEnvelopesLeetCode2(int[][] envelopes) {
        if (envelopes.length == 0) {
            return 0;
        }
        int n = envelopes.length;
        Arrays.sort(envelopes, new Comparator<int[]>() {
            public int compare(int[] e1, int[] e2) {
                if (e1[0] != e2[0]) {
                    return e1[0] - e2[0];
                } else {
                    return e2[1] - e1[1];
                }
            }
        });
        List<Integer> f = new ArrayList<Integer>();
        f.add(envelopes[0][1]);
        for (int i = 1; i < n; ++i) {
            int num = envelopes[i][1];
            if (num > f.get(f.size() - 1)) {
                f.add(num);
            } else {
                int index = binarySearch(f, num);
                f.set(index, num);
            }
        }
        return f.size();
    }
    public int binarySearch(List<Integer> f, int target) {
        int low = 0, high = f.size() - 1;
        while (low < high) {
            int mid = (high - low) / 2 + low;
            if (f.get(mid) < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    public static void main(String[] args) {

        System.out.println("3 ? " + new No354().maxEnvelopes(new int[][]{
                {5, 4}, {6, 4}, {6, 7}, {2, 3}
        }));

    }

}
