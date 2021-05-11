package LeetCode.unsolved;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class No310 {

    //看别人的代码，还是强的，最主要的问题就是，一定要想清楚需要什么数据结构，我们有了边和节点，但是需要邻居集合，感觉还是图的基础知识有欠缺，可以回去补一下了

    // 超出时间限制，有点头疼，看来对是对了，就是算法太垃圾了
    public List<Integer> findMinHeightTrees(int n, int[][] edges){
        int[] height = new int[n];
        int[] statistical = new int[n];
        int min = Integer.MAX_VALUE;
        for(int[] edge : edges){
            ++statistical[edge[0]];
            ++statistical[edge[1]];
        }
        for(int i=0;i<n;++i){
            if(n > 10 && statistical[i] <= 1){
                continue;
            }
            height[i] = heightCalculate(i, edges);
            if(height[i] < min){
                min = height[i];
            }
        }
        List<Integer> res = new ArrayList<>();
        for(int i=0;i<height.length;++i){
            if(height[i] == min){
                res.add(i);
            }
        }
        return res;
    }
    public int heightCalculate(int num, int[][] edges){
        int count = edges.length;
        int[] visitedPoint = new int[count + 1];
        boolean[] visitedEdge = new boolean[count];
        visitedPoint[num] = 1;
        int max = 1;
        while(count > 0){
            for(int i=0;i<edges.length;++i){
                if(visitedEdge[i]){
                    continue;
                }
                if(visitedPoint[edges[i][0]] == 0 && visitedPoint[edges[i][1]] != 0){
                    visitedPoint[edges[i][0]] = visitedPoint[edges[i][1]] + 1;
                    if(visitedPoint[edges[i][0]] > max){
                        max = visitedPoint[edges[i][0]];
                    }
                    visitedEdge[i] = true;
                    --count;
                }else if(visitedPoint[edges[i][0]] != 0 && visitedPoint[edges[i][1]] == 0){
                    visitedPoint[edges[i][1]] = visitedPoint[edges[i][0]] + 1;
                    if(visitedPoint[edges[i][1]] > max){
                        max = visitedPoint[edges[i][1]];
                    }
                    visitedEdge[i] = true;
                    --count;
                }
            }
        }
        return max;
    }

    public List<Integer> findMinHeightTreesLeetCodeCommunity(int n, int[][] edges) {
        List<Integer> res = new ArrayList<>();
        /*如果只有一个节点，那么他就是最小高度树*/
        if (n == 1) {
            res.add(0);
            return res;
        }
        /*建立各个节点的出度表*/
        int[] degree = new int[n];
        /*建立图关系，在每个节点的list中存储相连节点*/
        List<List<Integer>> map = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            map.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            degree[edge[0]]++;
            degree[edge[1]]++;/*出度++*/
            map.get(edge[0]).add(edge[1]);/*添加相邻节点*/
            map.get(edge[1]).add(edge[0]);
        }
        /*建立队列*/
        Queue<Integer> queue = new LinkedList<>();
        /*把所有出度为1的节点，也就是叶子节点入队*/
        for (int i = 0; i < n; i++) {
            if (degree[i] == 1) queue.offer(i);
        }
        /*循环条件当然是经典的不空判断*/
        while (!queue.isEmpty()) {
            res = new ArrayList<>();/*这个地方注意，我们每层循环都要new一个新的结果集合，
            这样最后保存的就是最终的最小高度树了*/
            int size = queue.size();/*这是每一层的节点的数量*/
            for (int i = 0; i < size; i++) {
                int cur = queue.poll();
                res.add(cur);/*把当前节点加入结果集，不要有疑问，为什么当前只是叶子节点为什么要加入结果集呢?
                因为我们每次循环都会新建一个list，所以最后保存的就是最后一个状态下的叶子节点，
                这也是很多题解里面所说的剪掉叶子节点的部分，你可以想象一下图，每层遍历完，
                都会把该层（也就是叶子节点层）这一层从队列中移除掉，
                不就相当于把原来的图给剪掉一圈叶子节点，形成一个缩小的新的图吗*/
                List<Integer> neighbors = map.get(cur);
                /*这里就是经典的bfs了，把当前节点的相邻接点都拿出来，
                 * 把它们的出度都减1，因为当前节点已经不存在了，所以，
                 * 它的相邻节点们就有可能变成叶子节点*/
                for (int neighbor : neighbors) {
                    degree[neighbor]--;
                    if (degree[neighbor] == 1) {
                        /*如果是叶子节点我们就入队*/
                        queue.offer(neighbor);
                    }
                }
            }
        }
        return res;/*返回最后一次保存的list*/
    }

    public static void main(String[] args) {
        No310 obj = new No310();
        System.out.println("[1] ? " + obj.findMinHeightTrees(4, new int[][]{
                {1, 0}, {1, 2}, {1, 3}
        }));
        System.out.println("[3, 4] ? " + obj.findMinHeightTrees(6, new int[][]{
                {3, 0}, {3, 1}, {3, 2}, {3, 4}, {5, 4}
        }));
        System.out.println("[0] ? " + obj.findMinHeightTrees(1, new int[][]{

        }));
        System.out.println("[0, 1] ? " + obj.findMinHeightTrees(2, new int[][]{
                {0, 1}
        }));
    }
}
