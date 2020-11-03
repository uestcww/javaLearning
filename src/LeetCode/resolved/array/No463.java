package LeetCode.resolved.array;

public class No463 {

    public int islandPerimeter(int[][] grid) {
        // 数组为空，就返回0
        if(grid.length < 1){
            return 0;
        }
        int rows = grid.length;
        int count = 0;
        // 两层循环，外循环遍历行，内循环遍历列
        for(int i=0;i<rows;++i){
            for(int j=0;j<grid[i].length;++j){
                // 如果当前方格是陆地，就判断他周围的四个方格
                // 如果周围一个方向是水，那就说明周长加一
                if(grid[i][j] == 1){
                    // 这是方格左边的，越界也算是水，也周长加一
                    if(j-1 < 0 || grid[i][j-1] == 0){
                        ++count;
                    }
                    // 当前方格上方的
                    if(i-1 < 0 || grid[i-1][j] == 0){
                        ++count;
                    }
                    // 当前方格右边的
                    if(j+1 >= grid[i].length || grid[i][j+1] == 0){
                        ++count;
                    }
                    // 当前方格下面的
                    if(i+1 >= rows || grid[i+1][j] == 0){
                        ++count;
                    }
                }
            }
        }
        return count;
    }

    // LeetCode官方方法，深度优先搜索
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    public int islandPerimeterLeetCode(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (grid[i][j] == 1) {
                    ans += dfs(i, j, grid, n, m);
                }
            }
        }
        return ans;
    }
    public int dfs(int x, int y, int[][] grid, int n, int m) {
        if (x < 0 || x >= n || y < 0 || y >= m || grid[x][y] == 0) {
            return 1;
        }
        if (grid[x][y] == 2) {
            return 0;
        }
        grid[x][y] = 2;
        int res = 0;
        for (int i = 0; i < 4; ++i) {
            int tx = x + dx[i];
            int ty = y + dy[i];
            res += dfs(tx, ty, grid, n, m);
        }
        return res;
    }

    public static void main(String[] args) {
        No463 obj = new No463();
        System.out.println("16 ? " + obj.islandPerimeter(new int[][]{{0, 1, 0, 0}, {1, 1, 1, 0}, {0, 1, 0, 0}, {1, 1, 0, 0}}));
    }

}
