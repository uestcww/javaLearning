package LeetCode.resolved.graph;

import java.util.*;

public class No1462 {

    public static void main(String[] args) {
        No1462 obj = new No1462();

        System.out.println("[false, true] ? ");
        System.out.println(obj.checkIfPrerequisite(2, new int[][]{{1, 0}}, new int[][]{
                {0, 1}, {1, 0}
        }));

        System.out.println("[false, false] ? ");
        System.out.println(obj.checkIfPrerequisite(2, new int[][]{}, new int[][]{
                {1, 0}, {0, 1}
        }));

        System.out.println("[true, true] ? ");
        System.out.println(obj.checkIfPrerequisite(3, new int[][]{
                {1, 2}, {1, 0}, {2, 0}
        }, new int[][]{
                {1, 0}, {1, 2}
        }));

        System.out.println("[false, true] ? ");
        System.out.println(obj.checkIfPrerequisite(3, new int[][]{
                {1, 0}, {2, 0}
        }, new int[][]{
                {0, 1}, {2, 0}
        }));

        System.out.println("[true, false, true, false] ? ");
        System.out.println(obj.checkIfPrerequisite(5, new int[][]{
                {0, 1}, {1, 2}, {2, 3}, {3, 4}
        }, new int[][]{
                {0, 4}, {4, 0}, {1, 3}, {3, 0}
        }));
    }

    // 用时 51.37% 内存 85.62% 转了半天，我还以为效果很差呢
    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        List<List<Integer>> neighbors = new ArrayList<>();
        for(int i=0;i<numCourses;++i){
            neighbors.add(new ArrayList<>());
        }
        int[] inDegree = new int[numCourses];
        for(int[] pre : prerequisites){
            neighbors.get(pre[0]).add(pre[1]);
            ++inDegree[pre[1]];
        }
        Queue<Integer> queue = new ArrayDeque<>();
        for(int i=0;i<numCourses;++i){
            if(inDegree[i] == 0){
                queue.offer(i);
            }
        }
        List<Set<Integer>> pres = new ArrayList<>();
        for(int i=0;i<numCourses;++i){
            pres.add(new HashSet<>());
        }
        int currentNode;
        while(!queue.isEmpty()){
            currentNode = queue.poll();
            for(int successor : neighbors.get(currentNode)){
                pres.get(successor).add(currentNode);
                pres.get(successor).addAll(pres.get(currentNode));
                if(--inDegree[successor] == 0){
                    queue.offer(successor);
                }
            }
        }
        List<Boolean> result = new ArrayList<>();
        for(int[] query : queries){
            result.add(pres.get(query[1]).contains(query[0]));
        }
        return result;
    }

}
