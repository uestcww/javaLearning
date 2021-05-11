package LeetCode.solving;

import java.util.ArrayList;

public class No123 {

    public int maxProfit(int[] prices) {
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

    public static void main(String[] args) {

    }

}
