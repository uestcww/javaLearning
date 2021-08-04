package LeetCode.resolved.bestTimeToBuyAndSellStock;

import java.util.ArrayList;

public class No123 {

    public static void main(String[] args) {
        No123 obj = new No123();
        System.out.println("6 ? " + obj.maxProfit(new int[]{3, 3, 5, 0, 0, 3, 1, 4}));
        System.out.println("4 ? " + obj.maxProfit(new int[]{1, 2, 3, 4, 5}));
        System.out.println("0 ? " + obj.maxProfit(new int[]{7, 6, 4, 3, 1}));
        System.out.println("0 ? " + obj.maxProfit(new int[]{1}));
    }

    // 用时 26.97% 内存 5.00% 为什么？？？
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][][] data = new int[n][3][2];
        for(int k=0;k<=2;++k){
            data[0][k][1] = -prices[0];
        }
        for(int i=1;i<n;++i){
            for(int k=0;k<=2;++k){
                data[i][k][0] = k == 0 ? data[i-1][k][0] : Math.max(data[i-1][k][0], data[i-1][k-1][1] + prices[i]);
                data[i][k][1] = Math.max(data[i-1][k][1], data[i-1][k][0] - prices[i]);
            }
        }
        return data[n-1][2][0];
    }

    public int maxProfit0(int[] prices) {
        if(prices.length <= 1){
            return 0;
        }
        ArrayList<Integer> list = new ArrayList<Integer>();
        int currentDay = 1, length = prices.length;
        if(prices[0] < prices[1]){
            list.add(prices[0]);
        }
        while(currentDay < length - 1){
            if((prices[currentDay] <= prices[currentDay - 1] && prices[currentDay] <= prices[currentDay + 1])
                    ||(prices[currentDay] >= prices[currentDay - 1] && prices[currentDay] >= prices[currentDay + 1])){
                list.add(prices[currentDay]);
            }
            ++currentDay;
        }
        if(length > 2 && prices[length-1] > prices[length-2]){
            list.add(prices[length-1]);
        }

        return 0;
    }

}
