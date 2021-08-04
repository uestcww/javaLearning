package LeetCode.unsolved;

import java.util.*;

public class No787 {

    public static void main(String[] args) {
        No787 obj = new No787();
//        System.out.println("200 ? " + obj.findCheapestPrice(3, new int[][]{
//                {0, 1, 100},
//                {1, 2, 100},
//                {0, 2, 500}
//        }, 0, 2, 1));
//        System.out.println("500 ? " + obj.findCheapestPrice(3, new int[][]{
//                {0, 1, 100},
//                {1, 2, 100},
//                {0, 2, 500}
//        }, 0, 2, 0));
//        System.out.println("5 ? " + obj.findCheapestPrice(5, new int[][]{
//                {1, 2, 10},
//                {2, 0, 7},
//                {1, 3, 8},
//                {4, 0, 10},
//                {3, 4, 2},
//                {4, 2, 10},
//                {0, 3, 3},
//                {3, 1, 6},
//                {2, 4, 5}
//        }, 0, 4, 1));
        System.out.println("6 ? " + obj.findCheapestPrice(4, new int[][]{
                {0, 1, 1},
                {0, 2, 5},
                {1, 2, 1},
                {2, 3, 1}
        }, 0, 3, 1));
    }

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        return 0;
    }

    // 从第四个用例中可以看出来，我这个思路是错误的
    public int findCheapestPrice0(int n, int[][] flights, int src, int dst, int k) {
        int[][] data = new int[n][n];
        int[] distance = new int[n];
        for(int[] column : data){
            Arrays.fill(column, -1);
        }
        for(int[] edge : flights){
            data[edge[0]][edge[1]] = edge[2];
        }
        for(int i=0;i<n;++i){
            data[i][i] = 0;
        }
        for(int i=0;i<n;++i){
            distance[i] = data[src][i];
        }
        if(k == 0){
            return distance[dst];
        }
        boolean[] nodes = new boolean[n];
        nodes[src] = true;
        nodes[dst] = true;
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(-1);
        for(int i=0;i<n;++i){
            if(data[src][i] > 0){
                queue.offer(i);
            }
        }
        int currentNode;
        while(!queue.isEmpty()){
            currentNode = queue.poll();
            if(currentNode == -1){
                if(--k > 0){
                    queue.offer(-1);
                }
                continue;
            }
            nodes[currentNode] = true;
            if(k > 0){
                for(int i=0;i<n;++i){
                    if(data[currentNode][i] <= 0 || nodes[i]){
                        continue;
                    }
                    queue.offer(i);
                }
            }
        }
        boolean[] isVisited = new boolean[n];
        isVisited[src] = true;
        int minDis, minIndex;
        for(int i=1;i<n;++i){
            minDis = Integer.MAX_VALUE;
            minIndex = -1;
            for(int j=0;j<n;++j){
                if(nodes[j] && !isVisited[j] && distance[j] > 0 && distance[j] < minDis){
                    minDis = distance[j];
                    minIndex = j;
                }
            }
            if(minIndex == -1){
                return distance[dst];
            }
            isVisited[minIndex] = true;
            for(int j=0;j<n;++j){
                if(!nodes[j] || data[minIndex][j] <= 0 || j == src){
                    continue;
                }
                if(distance[j] == -1){
                    distance[j] = distance[minIndex] + data[minIndex][j];
                }else{
                    distance[j] = Math.min(distance[j], distance[minIndex] + data[minIndex][j]);
                }
            }
        }
        return distance[dst];
    }

    // 优先级队列永远的神
    public int findCheapestPriceDijkstra(int n, int[][] flights, int src, int dst, int K) {
        // 使用邻接矩阵表示有向图，0 表示不连通
        int[][] graph = new int[n][n];
        for (int[] flight : flights) {
            graph[flight[0]][flight[1]] = flight[2];
        }

        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        // 向集合添加一个记录（起点, 费用, 站数限制）的数组，K + 1 表示可以走过站点的个数
        minHeap.offer(new int[]{src, 0, K + 1});

        while (!minHeap.isEmpty()) {
            int[] front = minHeap.poll();
            int v = front[0];
            int price = front[1];
            int k = front[2];

            if (v == dst) {
                return price;
            }

            // 如果还可以中转一个站
            if (k > 0) {
                for (int i = 0; i < n; i++) {
                    // 并且存在一条有向边
                    if (graph[v][i] > 0 ) {
                        // 优先队列中存入：有向边指向的顶点 i、从起点 src 到 i 的总路径长度、还有多少站可以中转
                        minHeap.offer(new int[]{i, price + graph[v][i]  , k - 1});
                    }
                }
            }
        }
        return -1;
    }

    // 还是要好好看看https://www.cnblogs.com/thousfeet/p/9229395.html这篇文章，不能只会个迪杰斯特拉就行
    public int findCheapestPriceBellmanFord(int n, int[][] flights, int src, int dst, int K) {
        int maxPrice = 1_000_000_000;

        int[][] dp = new int[n][K + 1];
        // 初始化 1：由于找最短路径，因此初始化的时候赋值成为一个不可能的较大的值
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], maxPrice);
        }
        // 自己到自己，不管经过几个顶点，最短路径都是 0
        for (int i = 0; i <= K; i++) {
            dp[src][i] = 0;
        }

        // 第 1 轮松弛操作，只需要对从 src 出发的边进行松弛操作
        for (int[] flight : flights) {
            if (flight[0] == src) {
                dp[flight[1]][0] = flight[2];
            }
        }

        // 第 2 轮到第 K + 1 轮松弛操作，最后一轮松弛操作是为了检测是否可达
        for (int i = 1; i <= K; i++) {
            for (int[] flight : flights) {
                int from = flight[0];
                int to = flight[1];
                // 每一次松弛操作的结果是互相独立的，因此只有在上一轮（第 i - 1 轮）得到单源最短路径的顶点，才需要执行松弛操作
                if (dp[from][i - 1] != maxPrice) {
                    dp[to][i] = Math.min(dp[from][i - 1] + flight[2], dp[to][i]);
                }
            }
        }

        // 如果不可达，返回 -1
        if (dp[dst][K] == maxPrice) {
            return -1;
        }
        return dp[dst][K];
    }

}
