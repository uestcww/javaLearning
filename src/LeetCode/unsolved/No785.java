package LeetCode.unsolved;

import java.util.*;

// 跟886题一样的
public class No785 {

    public static void main(String[] args) {
        No785 obj = new No785();
        int[][] graph1 = {
                {1, 2, 3},
                {0, 2},
                {0, 1, 3},
                {0, 2}
        };
        System.out.println("false ? " + obj.isBipartite(graph1));
        int[][] graph2 = {
                {1, 3},
                {0, 2},
                {1, 3},
                {0, 2}
        };
        System.out.println("true ? " + obj.isBipartite(graph2));
    }

    // 看了官方的思路，觉得自己就是个傻逼，这么简单的思路怎么就想不到呢我，我真是服了




    // 测试用例：[[],[2],[1],[],[],[7,8],[7,8,9],[5,6],[5,6],[6],[12,13,14],[12],[10,11],[10],[10],[18],[17,18],[16],[15,16],[],[22,23,24],[22,23,24],[20,21],[20,21],[20,21],[27,28,29],[27,28,29],[25,26],[25,26],[25,26],[32,33,34],[33],[30],[30,31],[30],[37,39],[38],[35],[36],[35],[44],[43,44],[],[41],[40,41],[47,48,49],[47,48,49],[45,46],[45,46],[45,46]]
    // 输出：false
    // 预期输出：true
    // 有一个很复杂的测试用例无法通过，80个用例通过了68个，第69个卡住了，不过反正我这个写的估计也是不对的，直接看答案吧
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        boolean[] isVisited = new boolean[n];
        int currentNode;
        Queue<Integer> queue = new ArrayDeque<>();
        HashSet<Integer> leftSet = new HashSet<>();
        HashSet<Integer> rightSet = new HashSet<>();
        HashSet<Integer> currentSet = leftSet;
        HashSet<Integer> neighborSet = rightSet;
        HashSet<Integer> temp;
        for(int i=0;i<n;++i){
            if(isVisited[i]){
                continue;
            }
            currentSet.add(i);
            queue.offer(-1);
            for(int neighbor : graph[i]){
                neighborSet.add(neighbor);
                queue.offer(neighbor);
            }
            queue.offer(-1);
            isVisited[i] = true;
            while(!queue.isEmpty()){
                currentNode = queue.poll();
                if(currentNode == -1){
                    temp = currentSet;
                    currentSet = neighborSet;
                    neighborSet = temp;
                    continue;
                }
                for(int neighbor : graph[currentNode]){
                    if(isVisited[neighbor]){
                        continue;
                    }
                    if(currentSet.contains(neighbor)){
                        return false;
                    }
                    neighborSet.add(neighbor);
                    queue.offer(neighbor);
                }
                queue.offer(-1);
                isVisited[currentNode] = true;
            }
        }
        return true;
    }






    private static final int UNCOLORED = 0;
    private static final int RED = 1;
    private static final int GREEN = 2;
    private int[] color;
    private boolean valid;

    public boolean isBipartiteLeetCodeDFS(int[][] graph) {
        int n = graph.length;
        valid = true;
        color = new int[n];
        Arrays.fill(color, UNCOLORED);
        for (int i = 0; i < n && valid; ++i) {
            if (color[i] == UNCOLORED) {
                dfs(i, RED, graph);
            }
        }
        return valid;
    }
    public void dfs(int node, int c, int[][] graph) {
        color[node] = c;
        int cNei = c == RED ? GREEN : RED;
        for (int neighbor : graph[node]) {
            if (color[neighbor] == UNCOLORED) {
                dfs(neighbor, cNei, graph);
                if (!valid) {
                    return;
                }
            } else if (color[neighbor] != cNei) {
                valid = false;
                return;
            }
        }
    }

    public boolean isBipartiteLeetCodeBFS(int[][] graph) {
        int n = graph.length;
        color = new int[n];
        Arrays.fill(color, UNCOLORED);
        for (int i = 0; i < n; ++i) {
            if (color[i] == UNCOLORED) {
                Queue<Integer> queue = new LinkedList<Integer>();
                queue.offer(i);
                color[i] = RED;
                while (!queue.isEmpty()) {
                    int node = queue.poll();
                    int cNei = color[node] == RED ? GREEN : RED;
                    for (int neighbor : graph[node]) {
                        if (color[neighbor] == UNCOLORED) {
                            queue.offer(neighbor);
                            color[neighbor] = cNei;
                        } else if (color[neighbor] != cNei) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

}
