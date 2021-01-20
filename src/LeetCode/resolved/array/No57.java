package LeetCode.resolved.array;

import java.util.ArrayList;
import java.util.List;

public class No57 {

    // 这是困难题？？？我一遍就过了，而且过程中没感觉多难，难道是有更妙的解法么
    public int[][] insert(int[][] intervals, int[] newInterval) {
        // 这里面存跟 newInterval 没关系的区间，最后把新的 newInterval 插进去
        List<int[]> list = new ArrayList<>();
        // 遍历 intervals ，找到与 newInterval 相关的区间，然后合成一个更新的区间，没关系就直接存入list中
        for(int[] interval : intervals){
            if(interval[0] <= newInterval[1] && interval[1] >= newInterval[0]){
                newInterval[0] = Math.min(interval[0], newInterval[0]);
                newInterval[1] = Math.max(interval[1], newInterval[1]);
            }else{
                list.add(interval);
            }
        }
        // 找到新新区间要插入的位置，用 i 来标识
        int i=0;
        while(i < list.size() && list.get(i)[0] < newInterval[0]){
            ++i;
        }
        // 插入合成好的新区间
        list.add(i, newInterval);
        // 因为我们用的是list就是为了插入的方便，而且开始无法确定区间的个数，所以不好声明一个具体的数组
        // 所以在最后的时候要将list中的数据导入一个新声明的数组当中去
        int[][] res = new int[list.size()][2];
        for(int j=0;j<res.length;++j){
            res[j] = list.get(j);
        }
        return res;
    }

    // 这是官方的解答，感觉跟我的思路类似
    public int[][] insertLeetCode(int[][] intervals, int[] newInterval) {
        int left = newInterval[0];
        int right = newInterval[1];
        boolean placed = false;
        List<int[]> ansList = new ArrayList<int[]>();
        for (int[] interval : intervals) {
            if (interval[0] > right) {
                // 在插入区间的右侧且无交集
                if (!placed) {
                    ansList.add(new int[]{left, right});
                    placed = true;
                }
                ansList.add(interval);
            } else if (interval[1] < left) {
                // 在插入区间的左侧且无交集
                ansList.add(interval);
            } else {
                // 与插入区间有交集，计算它们的并集
                left = Math.min(left, interval[0]);
                right = Math.max(right, interval[1]);
            }
        }
        if (!placed) {
            ansList.add(new int[]{left, right});
        }
        int[][] ans = new int[ansList.size()][2];
        for (int i = 0; i < ansList.size(); ++i) {
            ans[i] = ansList.get(i);
        }
        return ans;
    }

    public static void main(String[] args) {
        No57 obj = new No57();
        for(int[] inter : obj.insert(new int[][]{{1, 3}, {6, 9}}, new int[]{2, 5})){
            System.out.println("[" + inter[0] + ", " + inter[1] + "]");
        }
        System.out.println("*****************");
        for(int[] inter : obj.insert(new int[][]{{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}}, new int[]{4, 8})){
            System.out.println("[" + inter[0] + ", " + inter[1] + "]");
        }
    }

}
