package LeetCode.resolved.bestTimeToBuyAndSellStock;

public class No188 {

    public static void main(String[] args) {
        No188 obj = new No188();
        System.out.println("2 ? " + obj.maxProfit(2, new int[]{2, 4, 1}));
        System.out.println("7 ? " + obj.maxProfit(2, new int[]{3, 2, 6, 5, 0, 3}));
    }

    // 加了 k = Math.min(k, n/2); 用时 87.10% 内存 8.81%
    // 通过了，可是效果不太好，为啥？ 用时27.49% 内存19.39%
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        if(n == 0 || k == 0){
            return 0;
        }
        k = Math.min(k, n >>> 1);
        int[][][] data = new int[n][k+1][2];
        for(int i=0;i<n;++i){
            for(int j=0;j<=k;++j){
                if(i == 0){
                    data[0][j][1] = -prices[0];
                }else{
                    data[i][j][0] = j == 0 ? data[i-1][0][0] : Math.max(data[i-1][j][0], data[i-1][j-1][1] + prices[i]);
                    data[i][j][1] = Math.max(data[i-1][j][1], data[i-1][j][0] - prices[i]);
                }
            }
        }
        return data[n-1][k][0];
    }

}
