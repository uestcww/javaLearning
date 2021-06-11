package LeetCode.resolved.array;

/*
* 以后再遇到这种异或运算加递推的题，一定记住不能用二维数组存储，一定用一维的，因为好操作，而且只需要多加一步操作就可以等同于二维数组
* 还有就是，这道题其实就是让 [i, k] 间的数异或为0，然后 j 取之间的任何数字都可以
* 最后还可以再利用哈希表再进行优化，其实这道题考的是数学，如果用纸币写一写，会更容易
* */
public class No1442 {

    // 用时5.19% 内存12.26%
    private int[][] dataTable;
    private  boolean[][] flagTable;
    public int countTriplets(int[] arr) {
        int length = arr.length;
        dataTable = new int[length][length];
        flagTable = new boolean[length][length];
        for(int i=0;i<length;++i){
            dataTable[i][i] = arr[i];
            flagTable[i][i] = true;
        }
        int sum = 0;
        for(int i=0;i<length-1;++i){
            for(int j=i+1;j<length;++j){
                int temp = generate(arr, i, j-1);
                for(int k=j;k<length;++k){
                    if(temp == generate(arr, j, k)){
                        ++sum;
                    }
                }
            }
        }
        return sum;
    }
    public int generate(int[] arr, int left, int right){
        if(flagTable[left][right]){
            return dataTable[left][right];
        }
        flagTable[left][right] = true;
        if(flagTable[left][right-1]){
            return (dataTable[left][right] = dataTable[left][right-1] ^ arr[right]);
        }else{
            int res = arr[left];
            for(int i=left+1;i<=right;++i){
                res ^= arr[i];
            }
            return res;
        }
    }

    public static void main(String[] args) {
        System.out.println(4 == new No1442().countTriplets(new int[]{2, 3, 1, 6, 7}));
        System.out.println(10 == new No1442().countTriplets(new int[]{1, 1, 1, 1, 1}));
        System.out.println(0 == new No1442().countTriplets(new int[]{2, 3}));
        System.out.println(3 == new No1442().countTriplets(new int[]{1, 3, 5, 7, 9}));
        System.out.println(8 == new No1442().countTriplets(new int[]{7, 11, 12, 9, 5, 2, 7, 17, 22}));
    }



}
