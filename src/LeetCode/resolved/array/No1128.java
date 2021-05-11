package LeetCode.resolved.array;

public class No1128 {

    // easy题就是不一样
    public int numEquivDominoPairs(int[][] dominoes) {
        // 空的就不需要运行后面了
        if(dominoes.length < 1){
            return 0;
        }
        // 存所有样式的牌出现的数量，i j 与 j i 分开存，然后再加起来就好
        int[][] count = new int[10][10];
        for(int i=0;i<dominoes.length;++i){
            ++count[dominoes[i][0]][dominoes[i][1]];
        }
        int sum = 0, temp;
        // 遍历所有种类的牌
        for(int i=1;i<10;++i){
            // 对角线上单独处理
            if(count[i][i] > 1){
                sum += (count[i][i] * (count[i][i]-1))/2;
            }
            for(int j=i+1;j<10;++j){
                // 找到所有的牌，如果总数多于一张，说明会有牌对
                // 这里还要注意排列组合的问题，三张相同的牌，可以组成三组，四张相同，那就是六组
                temp = count[i][j] + count[j][i];
                if(temp > 1){
                    sum += (temp * (temp-1))/2;
                }
            }
        }
        return sum;
    }

    // 一个思路，只不过我是二维数组，它是一维数组，我是最后再算排列组合数，它是边遍历边算
    public int numEquivDominoPairsLeetCode(int[][] dominoes) {
        int[] num = new int[100];
        int ret = 0;
        for (int[] domino : dominoes) {
            int val = domino[0] < domino[1] ? domino[0] * 10 + domino[1] : domino[1] * 10 + domino[0];
            ret += num[val];
            num[val]++;
        }
        return ret;
    }

    public static void main(String[] args) {
        System.out.println("1 ? " + new No1128().numEquivDominoPairs(new int[][]{
                {1, 2}, {2, 1}, {3, 4}, {5, 6}
        }));
        System.out.println("3 ? " + new No1128().numEquivDominoPairs(new int[][]{
                {1, 2}, {1, 2}, {1, 1}, {1, 2}, {2, 2}
        }));
    }
}
