package LeetCode.resolved.graph;

import java.util.*;

public class No207 {

    public static void main(String[] args) {
        System.out.println("true ? " + new No207().canFinish(2, new int[][]{{1, 0}}));
        System.out.println("false ? " + new No207().canFinish(2, new int[][]{{1, 0},{0, 1}}));
        System.out.println("true ? " + new No207().canFinish(3, new int[][]{{1, 0},{2, 0}}));
        System.out.println("false ? " + new No207().canFinish(3, new int[][]{{1, 0}, {1, 2}, {0, 1}}));
    }

    public boolean canFinish0(int numCourses, int[][] prerequisites) {
        Set<Integer> canFinish = new HashSet<Integer>();
        Map<Integer, List<Integer>> hashmap = new HashMap<>();
        for(int i=0;i<numCourses;++i){
            canFinish.add(i);
        }
        for(int[] pre : prerequisites){
            canFinish.remove(pre[0]);
            if(!hashmap.containsKey(pre[0])){
                hashmap.put(pre[0], new ArrayList<Integer>());
            }
            hashmap.get(pre[0]).add(pre[1]);
        }
        if(canFinish.isEmpty()){
            return false;
        }
        boolean anyChange;
        do{
            anyChange = false;
            for(Iterator<Map.Entry<Integer, List<Integer>>> iterator = hashmap.entrySet().iterator(); iterator.hasNext();){
                Map.Entry<Integer, List<Integer>> item = iterator.next();
                if(!canFinish.contains(item.getKey()) && canFinish.containsAll(item.getValue())){
                    canFinish.add(item.getKey());
                    iterator.remove();
                    anyChange = true;
                }
            }
        }while(anyChange);
        return canFinish.size() >= numCourses;
    }

    // 重新回来做，写了一个深度优先遍历
    // 用时69.77% 内存16.68%
    private HashMap<Integer, List<Integer>> map;
    private int[] flags; // 0为未遍历 1正在遍历 2已遍历
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        map = new HashMap<>();
        for(int[] pre : prerequisites){
            if(map.containsKey(pre[0])){
                map.get(pre[0]).add(pre[1]);
            }else{
                map.put(pre[0], new ArrayList<Integer>(){{add(pre[1]);}});
            }
        }
        flags = new int[numCourses];
        for(int i=0;i<numCourses;++i){
            if(!visit(i)){
                return false;
            }
        }
        return true;
    }
    public boolean visit(int i){
        if(flags[i] == 2){
            return true;
        }
        flags[i] = 1;
        List<Integer> neighbors = map.get(i);
        if(neighbors == null){
            flags[i] = 2;
            return true;
        }
        for(int nei : neighbors){
            if(flags[nei] == 1){
                return false;
            }
            if(!visit(nei)){
                return false;
            }
        }
        flags[i] = 2;
        return true;
    }

    /*
    * 以下是leetcode官方的深度优先遍历和广度优先遍历
    * */

    List<List<Integer>> edges;
    int[] visited;
    boolean valid = true;
    int[] indeg;

    //深度优先遍历
    public boolean canFinishLeetCode1(int numCourses, int[][] prerequisites) {
        edges = new ArrayList<List<Integer>>();
        for (int i = 0; i < numCourses; ++i) {
            edges.add(new ArrayList<Integer>());
        }
        visited = new int[numCourses];
        for (int[] info : prerequisites) {
            edges.get(info[1]).add(info[0]);
        }
        for (int i = 0; i < numCourses && valid; ++i) {
            if (visited[i] == 0) {
                dfs(i);
            }
        }
        return valid;
    }

    public void dfs(int u) {
        visited[u] = 1;
        for (int v: edges.get(u)) {
            if (visited[v] == 0) {
                dfs(v);
                if (!valid) {
                    return;
                }
            } else if (visited[v] == 1) {
                valid = false;
                return;
            }
        }
        visited[u] = 2;
    }

    //广度优先遍历
    public boolean canFinishLeetCode2(int numCourses, int[][] prerequisites) {
        edges = new ArrayList<List<Integer>>();
        for (int i = 0; i < numCourses; ++i) {
            edges.add(new ArrayList<Integer>());
        }
        indeg = new int[numCourses];
        for (int[] info : prerequisites) {
            edges.get(info[1]).add(info[0]);
            ++indeg[info[0]];
        }

        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < numCourses; ++i) {
            if (indeg[i] == 0) {
                queue.offer(i);
            }
        }

        int visited = 0;
        while (!queue.isEmpty()) {
            ++visited;
            int u = queue.poll();
            for (int v: edges.get(u)) {
                --indeg[v];
                if (indeg[v] == 0) {
                    queue.offer(v);
                }
            }
        }

        return visited == numCourses;
    }

}
