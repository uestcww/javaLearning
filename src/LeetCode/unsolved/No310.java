package LeetCode.unsolved;

import java.util.*;

// 看懂了，牛逼，这种思路也要掌握，思路在最下方
public class No310 {

//    public static void main(String[] args) {
//        System.out.println("[1] ? " + new No310().findMinHeightTrees(4, new int[][]{
//                {1, 0}, {1, 2}, {1, 3}
//        }));
//        System.out.println("[3, 4] ? " + new No310().findMinHeightTrees(6, new int[][]{
//                {3, 0}, {3, 1}, {3, 2}, {3, 4}, {5, 4}
//        }));
//        System.out.println("[0] ? " + new No310().findMinHeightTrees(1, new int[][]{
//
//        }));
//        System.out.println("[0, 1] ? " + new No310().findMinHeightTrees(2, new int[][]{
//                {0, 1}
//        }));
//    }

    // 超出内存限制？？？这是咋回事
    public ArrayList<ArrayList<Integer>> neighborList;
    public int[][] data;
    public List<Integer> findMinHeightTrees3(int n, int[][] edges){
        neighborList = new ArrayList<>();
        data = new int[n][n];
        for(int i=0;i<n;++i){
            neighborList.add(new ArrayList<Integer>());
        }
        for(int[] edge : edges){
            neighborList.get(edge[0]).add(edge[1]);
            neighborList.get(edge[1]).add(edge[0]);
        }
        int[] maxHeight = new int[n];
        int minHeight = Integer.MAX_VALUE;
        for(int i=0;i<n;++i){
            maxHeight[i] = heightCalculate3(-1, i);
            if(maxHeight[i] < minHeight){
                minHeight = maxHeight[i];
            }
        }
        List<Integer> result = new ArrayList<>();
        for(int i=0;i<n;++i){
            if(maxHeight[i] == minHeight){
                result.add(i);
            }
        }
        return result;
    }
    public int heightCalculate3(int parentNode, int currentNode){
        if(parentNode != -1 && data[parentNode][currentNode] != 0){
            return data[parentNode][currentNode];
        }
        ArrayList<Integer> neighbors = neighborList.get(currentNode);
        if(parentNode == -1){
            if(neighbors == null || neighbors.size() <= 0){
                return 1;
            }
        }else{
            if(neighbors.size() == 1){
                data[parentNode][currentNode] = 1;
                return 1;
            }
        }
        int max = 0, currentHeight;
        for(int neighbor : neighbors){
            if(neighbor == parentNode){
                continue;
            }
            if(data[currentNode][neighbor] == 0){
                data[currentNode][neighbor] = heightCalculate3(currentNode, neighbor);
            }
            currentHeight = data[currentNode][neighbor] + 1;
            if(currentHeight > max){
                max = currentHeight;
            }
        }
        if(parentNode != -1){
            data[parentNode][currentNode] = max;
        }
        return max;
    }

    // 还是超出时间限制，我服了
    public List<Integer> findMinHeightTrees2(int n, int[][] edges) {
        ArrayList<ArrayList<Integer>> lists = new ArrayList<>();
        for(int i=0;i<n;++i){
            lists.add(new ArrayList<Integer>());
        }
        int[][] data = new int[n][n];
        for(int[] edge : edges){
            lists.get(edge[0]).add(edge[1]);
            lists.get(edge[1]).add(edge[0]);
            data[edge[0]][edge[1]] = 1;
            data[edge[1]][edge[0]] = 1;
        }
        int[] maxDepth = new int[n];
        int depth, min = Integer.MAX_VALUE;
        HashSet<Integer> visitedNode = new HashSet<>();
        HashSet<Integer> neighbors = new HashSet<>();
        HashSet<Integer> temp = new HashSet<>();
        for(int i=0;i<n;++i){
            depth = 1;
            visitedNode.clear();
            visitedNode.add(i);
            neighbors.clear();
            neighbors.addAll(lists.get(i));
            while(visitedNode.size() < n){
                visitedNode.addAll(neighbors);
                ++depth;
                for(int neighbor : neighbors){
                    temp.addAll(lists.get(neighbor));
                }
                temp.removeAll(visitedNode);
                neighbors.clear();
                neighbors.addAll(temp);
            }
            maxDepth[i] = depth;
            if(min > depth){
                min = depth;
            }
        }
        List<Integer> result = new ArrayList<>();
        for(int i=0;i<n;++i){
            if(maxDepth[i] == min){
                result.add(i);
            }
        }
        return result;
    }

    // 还是超出时间限制，问题就出在计算深度的函数上，我在思考如何利用深度优先遍历或者广度优先遍历的思想去计算深度
    public List<Integer> findMinHeightTrees1(int n, int[][] edges) {
        ArrayList<ArrayList<Integer>> lists = new ArrayList<>();
        for(int i=0;i<n;++i){
            lists.add(new ArrayList<Integer>());
        }
        int[][] data = new int[n][n];
        for(int[] edge : edges){
            lists.get(edge[0]).add(edge[1]);
            lists.get(edge[1]).add(edge[0]);
            data[edge[0]][edge[1]] = 1;
            data[edge[1]][edge[0]] = 1;
        }
        int[] maxDepth = new int[n];
        int max, min = Integer.MAX_VALUE;
        for(int i=0;i<n;++i){
            max = 0;
            for(int j=0;j<n;j++){
                heightCalculate1(i, j, data, lists);
                if(data[i][j] > max){
                    max = data[i][j];
                }
            }
            maxDepth[i] = max;
            if(min > max){
                min = max;
            }
        }
        List<Integer> result = new ArrayList<>();
        for(int i=0;i<n;++i){
            if(maxDepth[i] == min){
                result.add(i);
            }
        }
        return result;
    }
    public void heightCalculate1(int left, int right, int[][] data, ArrayList<ArrayList<Integer>> lists){
        if(left == right || data[left][right] != 0){
            return;
        }
        int step = 2;
        HashSet<Integer> temp = new HashSet<>();
        HashSet<Integer> isVisited = new HashSet<>();
        HashSet<Integer> neighbors = new HashSet<>(lists.get(left));
        while(!neighbors.isEmpty()){
            temp.clear();
            for(int nei : neighbors){
                if(lists.get(nei).contains(right)){
                    data[left][right] = step;
                    data[right][left] = step;
                    return;
                }
                temp.addAll(lists.get(nei));
            }
            isVisited.addAll(neighbors);
            temp.removeAll(isVisited);
            neighbors = new HashSet<>(temp);
            ++step;
        }
    }

    // 超出时间限制，有点头疼，看来对是对了，就是算法太垃圾了
    public List<Integer> findMinHeightTrees0(int n, int[][] edges){
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
            height[i] = heightCalculate0(i, edges);
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
    public int heightCalculate0(int num, int[][] edges){
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


}


// 社区的题解的思路，不是既是树又是无向图么，首先用树的角度思考，度为1的叶子节点一定不是，如果把所有叶子节点都去掉，相当于树中去掉了一层，那新的图是不是与老图是一样的答案？
// 是的，因为任意随机选取一个非叶子节点为树根，所形成的的树的叶子节点集合都相同
// 由上面推断，其实最后的结果，不是一个节点就是两个节点，不可能三个及以上，因为又不能成环，三个节点只能连成一条线，那中间的就比两边的要浅一层
// 那也就是说，我们可以一层一层剥洋葱，去掉所有叶子节点形成新图，新图又有了叶子节点，再去掉，以此往复