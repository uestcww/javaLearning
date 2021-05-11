package LeetCode.resolved.unionfind;

import java.util.HashSet;

public class No959 {

    // 官方就是这个思路，只不过官方搞了一个专门的类来解决并查集的问题，代码我已经放在LeetCode.utils包里了
    public int regionsBySlashes(String[] grid) {
        // 首先获取边长，然后把1*1方格的情况直接在这里解决了算了
        int n = grid.length;
        if(n == 1){
            if(grid[0].equals(" ")){
                return 1;
            }else{
                return 2;
            }
        }
        // 把一个方格分为四个，上下左右，然后以细分的小三角为单位进行分类
        int count = 4 * n * n;
        int[] id = new int[count];
        // 并查集准备工作
        for(int i=0;i<count;++i){
            id[i] = i;
        }
        char[] charArr;
        int current;
        // 遍历，不用多说
        for(int i=0;i<n;++i){
            charArr = grid[i].toCharArray();
            for(int j=0;j<n;++j){
                // 获取当前方格的编号
                current = i * n + j;
                // 临近的方格之间的小三角需要合并，只需要向下与向右，因为上左与下右是重复的
                if(j + 1 < n){
                    union(current * 4 + 1, (current + 1) * 4 + 3, id);
                }
                if(i + 1 < n){
                    union(current * 4 + 2, (current + n) * 4, id);
                }
                //分三种情况，把方格中的四个三角合并
                if(charArr[j] == '\\'){
                    union(current * 4, current * 4 + 1, id);
                    union(current * 4 + 2, current * 4 + 3, id);
                }else if(charArr[j] == '/'){
                    union(current * 4, current * 4 + 3, id);
                    union(current * 4 + 1, current * 4 + 2, id);
                }else{
                    union(current * 4, current * 4 + 1, id);
                    union(current * 4 + 1, current * 4 + 2, id);
                    union(current * 4 + 2, current * 4 + 3, id);
                }
            }
        }
        // 借用set找到一共有多少个集合
        HashSet<Integer> set = new HashSet<>();
        for(int i=0;i<count;++i){
            set.add(find(i, id));
        }
        return set.size();
    }
    // 并查集标准操作
    public int find(int p, int[] id){
        if(p != id[p]){
            id[p] = find(id[p], id);
        }
        return id[p];
    }
    // 并查集标准操作
    public void union(int p, int q, int[] id){
        id[find(p, id)] = find(q, id);
    }

    public static void main(String[] args) {
        System.out.println("2 ? " + new No959().regionsBySlashes(new String[]{" /", "/ "}));
        System.out.println("1 ? " + new No959().regionsBySlashes(new String[]{" /", "  "}));
        System.out.println("4 ? " + new No959().regionsBySlashes(new String[]{"\\/", "/\\"}));
        System.out.println("5 ? " + new No959().regionsBySlashes(new String[]{"/\\", "\\/"}));
        System.out.println("3 ? " + new No959().regionsBySlashes(new String[]{"//", "/ "}));
    }

}
