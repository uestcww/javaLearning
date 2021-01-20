package LeetCode.resolved.graph;

public class No684 {

    // 我就知道这道题是用并查集，这个并查集是真的好用，强无敌
    // 存储所有的点，这里注意，由于edges中是从1开始的，所以其实id[0]是没用的
    private int[] id;
    // 思路：一条边一条边的遍历，去检查一条边所连接的两个点在不在一个集合中
    // 如果在，说明是回路，答案就是这个边，如果不在，就union两个点，然后继续遍历
    public int[] findRedundantConnection(int[][] edges) {
        // 这里+1就是为了从1开始存储所有的点
        int N = edges.length + 1;
        id = new int[N];
        // 初始化
        for(int i=0;i<N;++i){
            id[i] = i;
        }
        // 遍历所有的边
        for(int[] edge : edges){
            // 检查两个点是否在同一个集合中
            if(find(edge[0]) == find(edge[1])){
                return edge;
            }
            // 不在就union一下，继续遍历
            union(edge[0], edge[1]);
        }
        // 这里我感觉并不会被执行到
        return edges[0];
    }
    // find没啥好说的，标准的并查集find
    public int find(int num){
        if(num != id[num]){
            id[num] = find(id[num]);
        }
        return id[num];
    }
    // union也没啥好说的，标准并查集union
    public void union(int p, int q){
        id[find(p)] = find(q);
    }

    public static void main(String[] args) {
        No684 obj = new No684();

        int[] res1 = obj.findRedundantConnection(new int[][]{{1, 2}, {1, 3}, {2, 3}});
        System.out.println("[2,3] ? [" + res1[0] + "," + res1[1] + "]");

        int[] res2 = obj.findRedundantConnection(new int[][]{{1, 2}, {2, 3}, {3, 4}, {1, 4}, {1, 5}});
        System.out.println("[1,4] ? [" + res2[0] + "," + res2[1] + "]");

    }

}
