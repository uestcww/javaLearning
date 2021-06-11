package JianZhiOffer.resolved;

public class Offer04 {

    // 用时9.76% 内存76.34%
    public boolean findNumberIn2DArray0(int[][] matrix, int target) {
        int row = matrix.length;
        if(row < 1){
            return false;
        }
        int column = matrix[0].length;
        if(column < 1){
            return false;
        }
        int maxRow = row-1, maxColumn = column-1;
        while(maxRow >= 0 && matrix[maxRow][0] > target){
            --maxRow;
        }
        while(maxColumn >= 0 && matrix[0][maxColumn] > target){
            --maxColumn;
        }
        int temp = maxColumn;
        while(temp >= 0){
            if(matrix[maxRow][temp] == target){
                return true;
            }
            --temp;
        }
        temp = maxRow;
        while(temp >= 0){
            if(matrix[temp][maxColumn] == target){
                return true;
            }
            --temp;
        }
        for(int i=maxRow-1;i>=0;--i){
            for(int j=maxColumn-1;j>=0;--j){
                if(matrix[i][j] == target){
                    return true;
                }
            }
        }
        return false;
    }

    // ohhhhhhhhhhhh 用时100.00% 不过内存25.10%
    public boolean findNumberIn2DArray1(int[][] matrix, int target) {
        int row = matrix.length;
        if(row < 1){
            return false;
        }
        int column = matrix[0].length;
        if(column < 1){
            return false;
        }
        int maxRow = row-1, maxColumn = column-1;
        while(maxRow >= 0 && matrix[maxRow][0] > target){
            --maxRow;
        }
        while(maxColumn >= 0 && matrix[0][maxColumn] > target){
            --maxColumn;
        }
        for(int i=maxRow;i>=0;--i){
            int j=maxColumn;
            while(j >= 0){
                if(matrix[i][j] > target){
                    --j;
                }else if(matrix[i][j] == target){
                    return true;
                }else{
                    break;
                }
            }
        }
        return false;
    }

    // 删除了一个i变量，用row代替，怎么内存反而 52.12% 了？
    // 用时 100.00% 内存 68.90%
    // 不看答案的优化，没有优化整体思路，只是局部
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        int row = matrix.length;
        if(row-- < 1){
            return false;
        }
        int column = matrix[0].length;
        if(column-- < 1){
            return false;
        }
        while(row >= 0 && matrix[row][0] > target){
            --row;
        }
        while(column >= 0 && matrix[0][column] > target){
            --column;
        }
        int j;
        while(row >= 0){
            j=column;
            while(j >= 0 && matrix[row][j] > target){
                --j;
            }
            if(j >= 0 && matrix[row][j] == target){
                return true;
            }
            --row;
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };
        System.out.println(new Offer04().findNumberIn2DArray(matrix, 5));
        System.out.println(new Offer04().findNumberIn2DArray(matrix, 20));
    }



}
