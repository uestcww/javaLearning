package LeetCode.resolved;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 这种只有两种可能的情况，非常适合位运算
// 一个棋盘中，一行可以直接用一个数来表示
public class No51 {

    private List<List<String>> results = new ArrayList<List<String>>();
    // 棋盘设置为字符二维数组，目的是好修改，等结果出来后再构造字符串列表
    private char[][] board;

    public List<List<String>> solveNQueens(int n) {
        board = new char[n][n];
        // 初始化棋盘，全部用'.'填充，代表空
        for(char[] charArr : board){
            Arrays.fill(charArr, '.');
        }
        // 从n-1开始往下减，n-1代表board数组中最后一个元素
        // 这样是为了简化backtrack中最开始的if判断
        // 这样就只需要在if判断的时候写 k < 0 即可，不然还需要n这个数值
        backtrack(n-1);
        return results;
    }

    public void backtrack(int k){
        // k小于等于0时，就是最后一行的backtrack中又执行了自身
        // 也就意味着现在的board里储存的就是一次正确答案所以这里就构造字符串列表并加入结果中
        if(k < 0){
            List<String> oneResult = new ArrayList<String>();
            for(char[] chars : board){
                oneResult.add(new String(chars));
            }
            results.add(oneResult);
            return;
        }
        // 对当前行的每一位进行判断，看看这些位置能不能放一个皇后
        for(int i=0;i<board[k].length;i++){
            if(bound(k, i)){
                // 如果这个位置跟谁都不冲突，那就放一个皇后，然后看下一行
                // 记得改回空的状态后再去看下一个位置能不能放
                board[k][i] = 'Q';
                backtrack(k-1);
                board[k][i] = '.';
            }
        }
    }

    public boolean bound(int column, int row){
        int n = board.length;
        int left, right, middle;
        // 遍历除了当前行以外的所有行
        for(int i=0;i<n;i++){
            // 如果遍历到当前行，就跳过
            if(i == column){
                continue;
            }
            middle = Math.abs(column - i);
            left = row - middle;
            right = row + middle;
            // left和right就构成了围绕当前位置所形成的的两条斜线
            if(left >= 0 && board[i][left] == 'Q'){
                return false;
            }
            if(right < n && board[i][right] == 'Q'){
                return false;
            }
            // 这是与当前位置同一列的其他位置
            if(board[i][row] == 'Q'){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // 设置棋盘的大小
        int n = 4;
        No51 obj = new No51();
        for(List<String> oneResult : obj.solveNQueens(n)){
            System.out.println("******************");
            for(String column : oneResult){
                System.out.println(column);
            }
        }
    }

}
