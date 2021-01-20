package LeetCode.resolved;

import java.util.*;

public class No130 {

    // 这道题能做出来真的不容易，让我对并查集的认识更加深入了
    // 教训：在union操作完成后，不能直接去读取id[i]然后认为i的老大就是id[i]，而是应该使用find(id,i)来确认i的老大
    // 看了一个题解后发现，我是按照是否相邻来划分为一个集合，其实可以直接把边上的点分为一个集合，然后以是否与边上的点相邻
    // 这样的话，思路就成了用是否需要修改为X来划分集合，这样会简单很多
    public void solve(char[][] board) {
        // 空的就直接返回了
        if(board.length < 1){
            return;
        }
        // 这里好多声明，首先矩阵的长宽要知道，其实长等于宽了
        // 然后max表示矩阵元素总量，后面的操作相当于把二维数组压缩为一位数组，所以需要这个数据
        // id就是保存集合信息的数组，一维，现在想想，好像可以也用二维数组
        int rows = board.length;
        int cols = board[0].length;
        int max = rows * cols;
        int[] id = new int[max];
        Arrays.fill(id, -1);
        int num;
        // edge用来表示在边的O，后面排除包括这些O的集合，剩下的O才变为X
        ArrayList<Integer> edge = new ArrayList<>();
        // 初始化edge和id
        for(int row=0;row<board.length;++row){
            for(int col=0;col<board[row].length;++col){
                if(board[row][col] == 'O'){
                    num = row * cols + col;
                    if(row == 0 || col == 0 || row == board.length-1 || col == board[row].length - 1){
                        edge.add(num);
                    }
                    id[num] = num;
                }
            }
        }
        // 这个循环用来对矩阵中的数据进行合并，每一个元素去合并他下面的元素和右边的元素
        // 为什么只有两个方向，那是因为四个方向就会使每对元素会合并两次
        for(int i=0;i<max;++i){
            if(id[i] == -1){
                continue;
            }
            if((i+1)%cols != 0 && id[i+1] != -1){
                union(id, i, i+1);
            }
            if(i+cols < max && id[i+cols] != -1){
                union(id, i, i+cols);
            }
        }
        // set集合保存所有边上的O所在的集合，到时候便于排除这些集合中的O
        HashSet<Integer> set = new HashSet<>();
        for(int edgePoint : edge){
            set.add(find(id, edgePoint));
        }
        // 将剩下的O变为X
        for(int i=0;i<max;++i){
            if(id[i] != -1 && !set.contains(find(id, i))){
                board[i/cols][i%cols] = 'X';
            }
        }
        // 打印结果
        for(char[] row : board){
            System.out.println(row);
        }
    }
    // 标准find
    public int find(int[] id, int p){
        if(p != id[p]){
            id[p] = find(id, id[p]);
        }
        return id[p];
    }
    // 标准union
    public void union(int[] id, int p, int q){
        id[find(id, q)] = find(id, p);
    }

    // 深度优先搜索，感觉被骗了，不是并查集么。。。
    private int n, m;
    public void solveLeetCode1(char[][] board) {
        n = board.length;
        if (n == 0) {
            return;
        }
        m = board[0].length;
        for (int i = 0; i < n; i++) {
            dfs(board, i, 0);
            dfs(board, i, m - 1);
        }
        for (int i = 1; i < m - 1; i++) {
            dfs(board, 0, i);
            dfs(board, n - 1, i);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'A') {
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }
    public void dfs(char[][] board, int x, int y) {
        if (x < 0 || x >= n || y < 0 || y >= m || board[x][y] != 'O') {
            return;
        }
        board[x][y] = 'A';
        dfs(board, x + 1, y);
        dfs(board, x - 1, y);
        dfs(board, x, y + 1);
        dfs(board, x, y - 1);
    }
    // 广度优先搜索
    private int[] dx = {1, -1, 0, 0};
    private int[] dy = {0, 0, 1, -1};
    public void solveLeetCode2(char[][] board) {
        int n = board.length;
        if (n == 0) {
            return;
        }
        int m = board[0].length;
        Queue<int[]> queue = new LinkedList<int[]>();
        for (int i = 0; i < n; i++) {
            if (board[i][0] == 'O') {
                queue.offer(new int[]{i, 0});
            }
            if (board[i][m - 1] == 'O') {
                queue.offer(new int[]{i, m - 1});
            }
        }
        for (int i = 1; i < m - 1; i++) {
            if (board[0][i] == 'O') {
                queue.offer(new int[]{0, i});
            }
            if (board[n - 1][i] == 'O') {
                queue.offer(new int[]{n - 1, i});
            }
        }
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int x = cell[0], y = cell[1];
            board[x][y] = 'A';
            for (int i = 0; i < 4; i++) {
                int mx = x + dx[i], my = y + dy[i];
                if (mx < 0 || my < 0 || mx >= n || my >= m || board[mx][my] != 'O') {
                    continue;
                }
                queue.offer(new int[]{mx, my});
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'A') {
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }

    public static void main(String[] args) {
        No130 obj = new No130();
        char[][] board = new char[][]{
                {'O','X','O','O','O','O','O','O','O'},
                {'O','O','O','X','O','O','O','O','X'},
                {'O','X','O','X','O','O','O','O','X'},
                {'O','O','O','O','X','O','O','O','O'},
                {'X','O','O','O','O','O','O','O','X'},
                {'X','X','O','O','X','O','X','O','X'},
                {'O','O','O','X','O','O','O','O','O'},
                {'O','O','O','X','O','O','O','O','O'},
                {'O','O','O','O','O','X','X','O','O'}
        };
        obj.solve(board);
    }

}
