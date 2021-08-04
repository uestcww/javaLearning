package LeetCode.resolved.graph;

import java.util.*;

// 写的乱七八糟，其实这是一系列问题，求图的最短路径，有迪杰斯特拉算法、弗洛伊德算法等等，系统的学完再回来做题吧
// 好好学习一下这里的四种算法
public class No743 {

    public static void main(String[] args) {
        System.out.println("2 ? " + new No743().networkDelayTime(new int[][]{
                {2, 1, 1},
                {2, 3, 1},
                {3, 4, 1}
        }, 4, 2));
        System.out.println("1 ? " + new No743().networkDelayTime(new int[][]{
                {1, 2, 1}
        }, 2, 1));
        System.out.println("-1 ? " + new No743().networkDelayTime(new int[][]{
                {1, 2, 1}
        }, 2, 2));
        System.out.println("3 ? " + new No743().networkDelayTime(new int[][]{
                {1, 2, 1},
                {2, 3, 2},
                {1, 3, 4}
        }, 3, 1));
        System.out.println("-1 ? " + new No743().networkDelayTime(new int[][]{
                {1, 2, 1},
                {2, 3, 2},
                {1, 3, 1}
        }, 3, 2));
        System.out.println("2 ? " + new No743().networkDelayTime(new int[][]{
                {2, 1, 1},
                {2, 3, 1},
                {3, 4, 1}
        }, 4, 2));
        System.out.println("2 ? " + new No743().networkDelayTime(new int[][]{
                {2, 1, 1},
                {2, 3, 1},
                {3, 4, 1}
        }, 4, 2));
    }

    // 其实还是应该使用boolean数组代替HashSet，我用的没道理
    // 又跑通了，时间85.82% 内存40.74% 这下没问题了
    // 没想到，改着改着，while里的两个循环，还就得是网上代码的先后顺序，我的先后顺序不行，神了
    // 出了一点小bug，居然之前跑通了？？？匪夷所思
    // 成功！用时82.87% 内存37.29% 内存低估计是因为我用了HashSet
    // 尝试使用迪杰斯特拉算法
    public int networkDelayTime(int[][] times, int n, int k) {
        int[][] data = new int[n+1][n+1];
        for(int[] column : data){
            Arrays.fill(column, -1);
        }
        HashSet<Integer> notVisit = new HashSet<>();
        for(int i=1;i<=n;++i){
            data[i][i] = 0;
            notVisit.add(i);
        }
        for(int[] edge : times){
            data[edge[0]][edge[1]] = edge[2];
        }
        int[] distance = new int[n+1];
        for(int i=1;i<=n;++i){
            distance[i] = data[k][i];
        }
        int minDis, minIndex;
        notVisit.remove(k);
        while(!notVisit.isEmpty()){
            minDis = Integer.MAX_VALUE;
            minIndex = -1;
            for(int i : notVisit){
                if(distance[i] == -1){
                    continue;
                }
                if(distance[i] < minDis){
                    minDis = distance[i];
                    minIndex = i;
                }
            }
            if(minIndex == -1){
                return -1;
            }
            for(int i=1;i<=n;++i){
                if(data[minIndex][i] == -1 || i == k || i == minIndex){
                    continue;
                }
                if(distance[i] == -1){
                    distance[i] = distance[minIndex] + data[minIndex][i];
                }else{
                    distance[i] = Math.min(distance[i], distance[minIndex] + data[minIndex][i]);
                }
            }
            notVisit.remove(minIndex);
        }
        int maxDis = -1;
        for(int i=1;i<=n;++i){
            if(distance[i] == -1){
                return -1;
            }
            maxDis = Math.max(maxDis, distance[i]);
        }
        return maxDis;
    }


    private HashMap<Integer, Set<Integer>> neighborMap;
    private int[][] edgeTable;
    private int[][] pathTable;
    public int networkDelayTime0(int[][] times, int n, int k) {
        neighborMap = new HashMap<>();
        edgeTable = new int[n+1][n+1];
        pathTable = new int[n+1][n+1];
        for(int[] arr : edgeTable){
            Arrays.fill(arr, -1);
        }
        for(int[] arr : pathTable){
            Arrays.fill(arr, -1);
        }
        for(int i=1;i<=n;++i){
            edgeTable[i][i] = 0;
            pathTable[i][i] = 0;
        }
        Set<Integer> neighbors;
        for(int[] edge : times){
            neighbors = neighborMap.getOrDefault(edge[0], new HashSet<>());
            neighbors.add(edge[1]);
            neighborMap.put(edge[0], neighbors);
            edgeTable[edge[0]][edge[1]] = edge[2];
        }
        int max = -1;
        int len;
        for(int i=1;i<=n;++i){
            if(i == k){
                continue;
            }
            len = path(k, i);
            if(len > max){
                max = len;
            }
        }
        return max == Integer.MAX_VALUE ? -1 : max;
    }
    public int path(int start, int end){
        if(start == end){
            return 0;
        }
        if(pathTable[start][end] == -2){
            pathTable[start][end] = Integer.MAX_VALUE;
            return Integer.MAX_VALUE;
        }
        if(pathTable[start][end] != -1){
            return pathTable[start][end];
        }
        pathTable[start][end] = -2;
        Set<Integer> neis = neighborMap.getOrDefault(start, null);
        if(neis == null){
            pathTable[start][end] = Integer.MAX_VALUE;
            return Integer.MAX_VALUE;
        }
        int min = Integer.MAX_VALUE;
        int len;
        for(Integer nei : neis){
            path(nei, end);
            if(pathTable[nei][end] != Integer.MAX_VALUE){
                len = edgeTable[start][nei] + pathTable[nei][end];
                if(len < min){
                    min = len;
                }
            }
        }
        pathTable[start][end] = min;
        return min;
    }

    // 代码参考：https://leetcode-cn.com/problems/network-delay-time/solution/java-shi-xian-dijkstra-floyd-bellman-ford-spfa-by-/
    // 算法参考：https://www.cnblogs.com/thousfeet/p/9229395.html
    public int networkDelayTimeDijkstra(int[][] times, int N, int K) {
        int[][] graph = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                graph[i][j] = -1;
            }
        }
        for (int[] time : times) {
            graph[time[0]][time[1]] = time[2];
        }
        int[] distance = new int[N + 1];
        Arrays.fill(distance, -1);
        for (int i = 1; i <= N; i++) {
            distance[i] = graph[K][i];
        }
        distance[K] = 0;
        boolean[] visited = new boolean[N + 1];
        visited[K] = true;
        for (int i = 1; i <= N - 1; i++) {
            int minDistance = Integer.MAX_VALUE;
            int minIndex = 1;
            for (int j = 1; j <= N; j++) {
                if (!visited[j] && distance[j] != -1 && distance[j] < minDistance) {
                    minDistance = distance[j];
                    minIndex = j;
                }
            }
            visited[minIndex] = true;
            for (int j = 1; j <= N; j++) {
                if (graph[minIndex][j] != -1) {
                    if (distance[j] != -1) {
                        distance[j] = Math.min(distance[j], distance[minIndex] + graph[minIndex][j]);
                    } else {
                        distance[j] = distance[minIndex] + graph[minIndex][j];
                    }
                }
            }
        }
        int maxDistance = 0;
        for (int i = 1; i <= N; i++) {
            if (distance[i] == -1) {
                return -1;
            }
            maxDistance = Math.max(distance[i], maxDistance);
        }
        return maxDistance;
    }
    public int networkDelayTimeFloyd(int[][] times, int N, int K) {
        int[][] graph = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                graph[i][j] = i == j ? 0 : -1;
            }
        }
        for (int[] time : times) {
            graph[time[0]][time[1]] = time[2];
        }
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (graph[i][k] != -1 && graph[k][j] != -1) {
                        if (graph[i][j] != -1) {
                            graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                        } else {
                            graph[i][j] = graph[i][k] + graph[k][j];
                        }
                    }
                }
            }
        }
        int maxDistance = 0;
        for (int i = 1; i <= N; i++) {
            if (graph[K][i] == -1) {
                return -1;
            }
            maxDistance = Math.max(maxDistance, graph[K][i]);
        }
        return maxDistance;
    }
    public int networkDelayTimeBellmanFord(int[][] times, int N, int K) {
        int[] distance = new int[N + 1];
        Arrays.fill(distance, -1);
        distance[K] = 0;
        for (int i = 1; i <= N - 1; i++) {
            for (int[] time : times) {
                int u = time[0];
                int v = time[1];
                int w = time[2];
                if (distance[u] != -1) {
                    if (distance[v] == -1) {
                        distance[v] = distance[u] + w;
                    } else {
                        distance[v] = Math.min(distance[v], distance[u] + w);
                    }
                }
            }
        }
        int maxDistance = 0;
        for (int i = 1; i <= N; i++) {
            if (distance[i] == -1) {
                return -1;
            }
            maxDistance = Math.max(distance[i], maxDistance);
        }
        return maxDistance;
    }
    public int networkDelayTimeSPFA (int[][] times, int N, int K) {
        int[][] graph = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                graph[i][j] = i == j ? 0 : -1;
            }
        }
        for (int[] time : times) {
            graph[time[0]][time[1]] = time[2];
        }
        int[] distance = new int[N + 1];
        Arrays.fill(distance, -1);
        distance[K] = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(K);
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            for (int i = 1; i <= N; i++) {
                if (graph[curr][i] != -1 && (distance[i] == -1 || distance[i] > distance[curr] + graph[curr][i])) {
                    distance[i] = distance[curr] + graph[curr][i];
                    if (!queue.contains(i)) {
                        queue.add(i);
                    }
                }
            }
        }
        int maxDistance = 0;
        for (int i = 1; i <= N; i++) {
            if (distance[i] == -1) {
                return -1;
            }
            maxDistance = Math.max(distance[i], maxDistance);
        }
        return maxDistance;
    }


}
