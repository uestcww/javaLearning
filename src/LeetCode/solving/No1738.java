package LeetCode.solving;


/*
* 又是异或运算的题目，官方的题解就是使用了积分面积的思路，然后加一个排序
* */
public class No1738 {

//    // 超出时间限制
//    private int[][] dataTable;
//    public int kthLargestValue(int[][] matrix, int k) {
//        int row = matrix.length, column = matrix[0].length;
//        dataTable = new int[row][column];
//        int[] topK = new int[k];
//        int temp;
//        for(int i=0;i<row;++i){
//            for(int j=0;j<column;++j){
//                if(j == 0){
//                    temp = i == 0 ? matrix[0][0] : dataTable[i-1][0] ^ matrix[i][0];
//                }else{
//                    temp = dataTable[i][j-1];
//                    for(int t=0;t<=i;++t){
//                        temp ^= matrix[t][j];
//                    }
//                }
//                testK(topK, temp);
//                dataTable[i][j] = temp;
//            }
//        }
//        return topK[0];
//    }
//    public void testK(int[] topK, int num){
//        if(topK[0] >= num){
//            return;
//        }
//        int pointer = 1;
//        while(pointer < topK.length && topK[pointer] < num){
//            topK[pointer-1] = topK[pointer];
//            ++pointer;
//        }
//        topK[pointer-1] = num;
//    }

//    public static void main(String[] args) {
//        System.out.println(7 == new No1738().kthLargestValue(new int[][]{{5, 2}, {1, 6}}, 1));
//        System.out.println(5 == new No1738().kthLargestValue(new int[][]{{5, 2}, {1, 6}}, 2));
//        System.out.println(4 == new No1738().kthLargestValue(new int[][]{{5, 2}, {1, 6}}, 3));
//        System.out.println(0 == new No1738().kthLargestValue(new int[][]{{5, 2}, {1, 6}}, 4));
//    }

}
