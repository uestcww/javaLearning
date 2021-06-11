package LeetCode.solving;

import java.util.Arrays;

public class No37 {

    // 大体思路：顺着人解数独的思路去做，记录所有位置的所有可能，然后把唯一可能的更新了，如果没有唯一可能就开分支
    public void solveSudoku(char[][] board) {
        short[][] records = new short[9][9];
        boolean[][] confirm = new boolean[9][9];
        Arrays.fill(records, 511); // 数字511的二进制形式为 0001 1111 1111
        int temp;
        for(int i=0;i<9;++i){
            for(int j=0;j<9;++j){
                if(board[i][j] != '.'){
                    records[i][j] = 0;
                }else{

                }





            }
        }
    }

    public static void main(String[] args) {

    }



}
