package LeetCode.resolved.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

// 以下为提示
/*
* 看了题解里的，发现可以使用位运算，其实可以想到的，因为只需要记录是否存在，且是固定长度
* */
public class No36 {

    /*
    * 思路：需要判断行、列、九宫格三种情况
    * 行与列：保存九个数字出现的行与列，每个数字对应两个 HashSet ，分别保存行号和列号，有重复的行号和列号就是重复了，而且可以边添加，边判断
    * 九宫格：保存九个 HashSet 代表九个九宫格，保存数字，判断是否重复
    * */
    // 用时49.18% 内存84.67%
    public boolean isValidSudoku(char[][] board) {
        ArrayList<HashSet<Integer>> XList = new ArrayList<>();
        ArrayList<HashSet<Integer>> YList = new ArrayList<>();
        HashSet<Integer>[][] nine = new HashSet[3][3];
        for(int i=0;i<10;++i){
            XList.add(new HashSet<Integer>());
            YList.add(new HashSet<Integer>());
        }
        for(int i=0;i<3;++i){
            for(int j=0;j<3;++j){
                nine[i][j] = new HashSet<Integer>();
            }
        }
        int temp;
        for(int i=0;i<9;++i){
            for(int j=0;j<9;++j){
                if(board[i][j] != '.'){
                    temp = board[i][j] - '0';
                    if(XList.get(temp).contains(i) || YList.get(temp).contains(j) || nine[i/3][j/3].contains(temp)){
                        return false;
                    }
                    XList.get(temp).add(i);
                    YList.get(temp).add(j);
                    nine[i/3][j/3].add(temp);
                }
            }
        }
        return true;
    }

    // 官方题解，比我的好，不过都是一次遍历，我与官方的时间复杂度都在一个数量级上，可是为何官方的比我好呢
    // 更新，官方的时间是49.18% 内存65.46%，还不如我的哈哈哈哈哈，不过那时间100%的是什么？
    public boolean isValidSudokuLeetCode(char[][] board) {
        // init data
        HashMap<Integer, Integer>[] rows = new HashMap[9];
        HashMap<Integer, Integer> [] columns = new HashMap[9];
        HashMap<Integer, Integer> [] boxes = new HashMap[9];
        for (int i = 0; i < 9; i++) {
            rows[i] = new HashMap<Integer, Integer>();
            columns[i] = new HashMap<Integer, Integer>();
            boxes[i] = new HashMap<Integer, Integer>();
        }
        // validate a board
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char num = board[i][j];
                if (num != '.') {
                    int n = (int)num;
                    int box_index = (i / 3 ) * 3 + j / 3;
                    // keep the current cell value
                    rows[i].put(n, rows[i].getOrDefault(n, 0) + 1);
                    columns[j].put(n, columns[j].getOrDefault(n, 0) + 1);
                    boxes[box_index].put(n, boxes[box_index].getOrDefault(n, 0) + 1);
                    // check if this value has been already seen before
                    if (rows[i].get(n) > 1 || columns[j].get(n) > 1 || boxes[box_index].get(n) > 1)
                        return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        char[][] board = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        System.out.println(new No36().isValidSudoku(board));


        char[][] board2 = {
                {'8', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        System.out.println(new No36().isValidSudoku(board2));


    }


}
