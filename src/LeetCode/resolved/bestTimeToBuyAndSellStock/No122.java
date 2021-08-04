package LeetCode.resolved.bestTimeToBuyAndSellStock;

public class No122 {

    public static void main(String[] args) {
        No122 obj = new No122();
        System.out.println("7 ? " + obj.maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
        System.out.println("4 ? " + obj.maxProfit(new int[]{1, 2, 3, 4, 5}));
        System.out.println("0 ? " + obj.maxProfit(new int[]{7, 6, 4, 3, 1}));
    }

    public int maxProfitGreedy(int[] prices){
        int sum = 0;
        for(int i=1;i<prices.length;++i){
            if(prices[i] > prices[i-1]){
                sum += prices[i] - prices[i-1];
            }
        }
        return sum;
    }

    // 内存消耗65.35%，执行用时99.32%？？？离谱，大家都是怎么写的
    // 看完官方的方法，我发现应该这么想，既然是最佳买股票方式，而且买卖次数无限制
    // 也就是说我们要找的答案，是在数学上的最大值，那么就要利用到所有的股票升降差
    // 只不过我们只需要先低后高的差，而不需要先高后低的差，那么官方那个贪心算法就呼之欲出了
    public int maxProfit(int[] prices) {
        if(prices.length <= 1){
            return 0;
        }
        int currentDay = 0;
        // min存最低股票价格
        int min = Integer.MAX_VALUE, profit = 0;
        // 遍历整个数组，原理在于在局部最低点买入，在局部最高点卖出
        while(currentDay < prices.length){
            // 股票持续走低，那么就更新最低价格
            if(prices[currentDay] <= min){
                min = prices[currentDay];
            }else{
                // 股票持续走高，找到最高点，卖出，更新最低点
                while(currentDay + 1 < prices.length && prices[currentDay+1] >= prices[currentDay]){
                    ++currentDay;
                }
                profit += prices[currentDay] - min;
                min = Integer.MAX_VALUE;
            }
            ++currentDay;
        }
        return profit;
    }

    public int maxProfitLeetCodeDP(int[] prices) {
        int n = prices.length;
        int dp0 = 0, dp1 = -prices[0];
        for (int i = 1; i < n; ++i) {
            int newDp0 = Math.max(dp0, dp1 + prices[i]);
            int newDp1 = Math.max(dp1, dp0 - prices[i]);
            dp0 = newDp0;
            dp1 = newDp1;
        }
        return dp0;
    }

    public int maxProfitLeetCodeGreedy(int[] prices) {
        int ans = 0;
        int n = prices.length;
        for (int i = 1; i < n; ++i) {
            ans += Math.max(0, prices[i] - prices[i - 1]);
        }
        return ans;
    }

}
