package LeetCode.resolved.unionfind;

import java.util.HashSet;

public class No200 {

    // 经典并查集，总觉得之前做过这道题
    public int numIslands(char[][] grid) {
        int row = grid.length;
        int column = grid[0].length;
        int n = row * column;
        int[] id = new int[n];
        for(int i=0;i<n;++i){
            id[i] = i;
        }
        for(int i=0;i<row;++i){
            for(int j=0;j<column;++j){
                if(grid[i][j] == 48){
                    continue;
                }
                if(j + 1 < column && grid[i][j+1] == 49){
                    union(i * column + j, i * column + j + 1, id);
                }
                if(i + 1 < row && grid[i+1][j] == 49){
                    union(i * column + j, (i + 1) * column + j, id);
                }
            }
        }
        HashSet<Integer> set = new HashSet<>();
        for(int i=0;i<n;++i){
            if(grid[i/column][i%column] == 49){
                set.add(find(i, id));
            }
        }
        return set.size();
    }
    public int find(int p, int[] id){
        if(p != id[p]){
            id[p] = find(id[p], id);
        }
        return id[p];
    }
    public void union(int p, int q, int[] id){
        id[find(p, id)] = find(q, id);
    }


    public static void main(String[] args) {
        char[][] arr = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'},
        };
        System.out.println("1 ? " + new No200().numIslands(arr));



    }
}
