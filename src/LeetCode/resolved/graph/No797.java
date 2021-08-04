package LeetCode.resolved.graph;

import java.util.ArrayList;
import java.util.List;

public class No797 {

    public static void main(String[] args) {
        System.out.println("[[0, 1, 3], [0, 2, 3]] ? ");
        System.out.println(new No797().allPathsSourceTarget(new int[][]{
                {1, 2},
                {3},
                {3},
                {}
        }));
        System.out.println("****************************************************");
        System.out.println("[[0, 4], [0, 3, 4], [0, 1, 3, 4], [0, 1, 2, 3, 4], [0, 1, 4]] ? ");
        System.out.println(new No797().allPathsSourceTarget(new int[][]{
                {4, 3, 1},
                {3, 2, 4},
                {3},
                {4},
                {}
        }));
        System.out.println("****************************************************");
        System.out.println("[[0, 1]] ? ");
        System.out.println(new No797().allPathsSourceTarget(new int[][]{
                {1},
                {}
        }));
        System.out.println("****************************************************");
        System.out.println("[[0, 1, 2, 3], [0, 2, 3], [0, 3]] ? ");
        System.out.println(new No797().allPathsSourceTarget(new int[][]{
                {1, 2, 3},
                {2},
                {3},
                {}
        }));
        System.out.println("****************************************************");
        System.out.println("[[0, 1, 2, 3], [0, 3]] ? ");
        System.out.println(new No797().allPathsSourceTarget(new int[][]{
                {1, 3},
                {2},
                {3},
                {}
        }));
        System.out.println("****************************************************");



        System.out.println("[[0, 4], [0, 3, 4], [0, 1, 3, 4], [0, 1, 4]] ? ");
        System.out.println(new No797().allPathsSourceTarget(new int[][]{
                {4, 3, 1},
                {3, 2, 4},
                {},
                {4},
                {}
        }));
        System.out.println("****************************************************");
    }

    // 这题有点简单啊，用时99.76% 内存67.69% 这题没啥，就是一个回溯就好了
    // 唯独就是注意一下停止条件，因为题目要求是“找到所有从0到n-1的路径并输出”也就源是0，目的是n-1
    public List<List<Integer>> results;
    public List<Integer> result;
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        results = new ArrayList<>();
        result = new ArrayList<>();
        result.add(0);
        backtrack(graph, 0);
        return results;
    }
    public void backtrack(int[][] graph, int nextNode){
        if(nextNode == graph.length-1){
            results.add(new ArrayList<>(result));
            return;
        }
        for(int neighbor : graph[nextNode]){
            result.add(neighbor);
            backtrack(graph, neighbor);
            result.remove(new Integer(neighbor));
        }
    }



}
