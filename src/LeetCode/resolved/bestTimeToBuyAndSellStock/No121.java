package LeetCode.resolved.bestTimeToBuyAndSellStock;

public class No121 {

    // 2021.08.03回看代码，写的真好哈哈哈哈哈，这就是动态规划的思路，但是我没有将这个思路沿用到其他股票题目中
    // 太简单，基本等于官方的方法，只有细节不同
    // 关键在于，按照时间流动来看，我们永远是在用历史最低点与当前点进行交易
    // 交易情况比之前收益都高，那就保留，不如历史交易，那就舍弃，如此遍历完数组就得到了答案
    // 所以我们要做的就是随时更新最低点，保留历史交易最好的结果，然后用最低点去和当前点交易，交易结果与历史进行对比
    public int maxProfit(int[] prices) {
        // 时间太短可不行，最起码要两天以上
        if(prices.length <= 1){
            return 0;
        }
        // 记录盈利、最小点和当前遍历点
        // 最小点为截止当前遍历过的元素中的最小点
        int profit = 0, min = prices[0], i = 1;
        // 从第二个位置开始遍历数组，第一个元素为当前最低点
        while(i < prices.length){
            // 当前值比最小点高，那么可以试着交易，如果比历史交易好，那就保留
            if(min < prices[i]){
                profit = Math.max(profit, prices[i] - min);
            }else{
                // 当前值比最小点低，那他就是最新的最小点，更新一下
                min = prices[i];
            }
            ++i;
        }
        return profit;
    }

    public static void main(String[] args) {
        No121 obj = new No121();
        System.out.println("5 ? " + obj.maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
        System.out.println("0 ? " + obj.maxProfit(new int[]{7, 6, 4, 3, 1}));
    }

}
