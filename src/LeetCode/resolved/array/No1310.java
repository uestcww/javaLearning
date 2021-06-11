package LeetCode.resolved.array;

/*
* 这题关键在于如何使用动态规划，二维数组保存数据，不如一位数组方便
* 设计为 data[x] 表示从 arr[0] 一直异或到 arr[x] 的结果
* 还有就是注意，如果 left == right 那么返回 arr[left] 而不是0
* */
public class No1310 {

    /*
    * 看了官方题解，发现直接把data数组生成好，然后直接使用，效果更好，毕竟不需要各种递归了
    * */

    private int[] data;
    public int func(int[] arr, int left, int right){
        if(left == right){
            return arr[right];
        }
        if(left == 0){
            if(data[right] != 0){
                return data[right];
            }else{
                return (data[right] = func(arr, 0, right-1) ^ arr[right]);
            }
        }else{
            return func(arr, 0, right) ^ func(arr, 0, left-1);
        }
    }
    public int[] xorQueries(int[] arr, int[][] queries) {
        data = new int[arr.length];
        int qLength = queries.length;
        int[] result = new int[qLength];
        for(int i=0;i<qLength;++i){
            result[i] = func(arr, queries[i][0], queries[i][1]);
        }
        return result;
    }

    public static void main(String[] args) {
//        int[] arr = {1, 3, 4, 8};
//        int[][] queries = {
//                {0, 1}, {1, 2}, {0, 3}, {3, 3}
//        };
//        int[] res = new No1310().xorQueries(arr, queries);
//        for(int a : res){
//            System.out.print(a + " ");
//        }

        int[] arr = {4, 8, 2, 10};
        int[][] queries = {
                {2, 3}, {1, 3}, {0, 0}, {0, 3}
        };
        int[] res = new No1310().xorQueries(arr, queries);
        for(int a : res){
            System.out.print(a + " ");
        }
    }

}
