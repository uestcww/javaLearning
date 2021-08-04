package LeetCode.resolved.graph;

public class No997 {

    public static void main(String[] args) {
        No997 obj = new No997();
        System.out.println("2 ? " + obj.findJudge(2, new int[][]{
                {1, 2}
        }));
        System.out.println("3 ? " + obj.findJudge(3, new int[][]{
                {1, 3}, {2, 3}
        }));
        System.out.println("-1 ? " + obj.findJudge(3, new int[][]{
                {1, 3}, {2, 3}, {3, 1}
        }));
        System.out.println("-1 ? " + obj.findJudge(3, new int[][]{
                {1, 2}, {2, 3}
        }));
        System.out.println("3 ? " + obj.findJudge(4, new int[][]{
                {1, 3}, {1, 4}, {2, 3}, {2, 4}, {4, 3}
        }));
    }

    // 用时 79.03% 内存 33.24%
    public int findJudge(int n, int[][] trust) {
        if(n == 1){
            return 1;
        }
        boolean[][] trustMatrix = new boolean[n+1][n+1];
        for(int[] t : trust){
            trustMatrix[t[0]][t[1]] = true;
        }
        boolean isJudge;
        for(int i=1;i<=n;++i){
            isJudge = true;
            for(int j=1;j<=n;++j){
                if(j == i){
                    continue;
                }
                if(trustMatrix[i][j] || !trustMatrix[j][i]){
                    isJudge = false;
                    break;
                }
            }
            if(isJudge){
                return i;
            }
        }
        return -1;
    }

    // 我脑子真浆糊，就是找到入度为 n-1 出度为 0 的结点嘛
    public int findJudgeLeetCodeCommunity(int N, int[][] trust) {
        // 入度为 N，出度为 0
        int[][] degree = new int[N][2];
        for (int[] item : trust) {
            degree[item[0] - 1][0]++;
            degree[item[1] - 1][1]++;
        }
        for (int i = 0; i < degree.length; ++i)
            if (degree[i][0] == 0 && degree[i][1] == N - 1) return i + 1;
        return -1;
    }

}
