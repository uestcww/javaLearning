package LeetCode.resolved.tree;

public class No96 {

    //可以用动态规划优化，只要涉及到递归与分治思想的，都要先考虑能不能用动态规划
    public int numTrees(int n) {
        if(n <= 1){
            return 1;
        }
        int leftCount, rightCount, count=0;
        for(int i=1;i<=n;i++){
            leftCount = i-1;
            rightCount = n-i;
            count += numTrees(leftCount) * numTrees(rightCount);
        }
        return count;
    }

    //动态规划版本
    public int numTreesDP(int n) {
        int[] G = new int[n + 1];
        G[0] = 1;
        G[1] = 1;

        for (int i = 2; i <= n; ++i) {
            for (int j = 1; j <= i; ++j) {
                G[i] += G[j - 1] * G[i - j];
            }
        }
        return G[n];
    }

    public static void main(String[] args) {
        No96 obj = new No96();
        System.out.println(obj.numTrees(3));
    }

}
