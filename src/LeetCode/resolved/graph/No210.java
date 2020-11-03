package LeetCode.resolved.graph;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/*
 * 207与210两个题高度相似，其实都是考察图的遍历算法，一般都是深度优先遍历或者广度优先遍历
 * */
public class No210 {

    //深度优先遍历
    private List<List<Integer>> edges;
    private int[] visited;
    private boolean isPossible = true;
    private Deque<Integer> topSort;
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        edges = new ArrayList<List<Integer>>();
        for(int i=0;i<numCourses;++i){
            edges.add(new ArrayList<Integer>());
        }
        //0表示未访问，1表示正在访问，2表示已经访问过
        visited = new int[numCourses];
        for(int[] pre : prerequisites){
            edges.get(pre[1]).add(pre[0]);
        }
        topSort = new LinkedList<Integer>();
        for(int i=0;i<numCourses && isPossible;++i){
            if(visited[i] == 0){
                visitNode(i);
            }
        }
        if(!isPossible){
            return new int[]{};
        }
        int[] result = new int[topSort.size()];
        for(int i=0;i<result.length;++i){
            result[i] = topSort.pop();
        }
        return result;
    }

    public void visitNode(int pointer){
        visited[pointer] = 1;
        for(int next : edges.get(pointer)){
            if(visited[next] == 0){
                visitNode(next);
                if(!isPossible){
                    return;
                }
            }else if(visited[next] == 1){
                isPossible = false;
                return;
            }
        }
        topSort.push(pointer);
        visited[pointer] = 2;
    }

    //广度优先
    public int[] findOrder2(int numCourses, int[][] prerequisites) {
        List<List<Integer>> edges = new ArrayList<List<Integer>>();
        for(int i=0;i<numCourses;++i){
            edges.add(new ArrayList<Integer>());
        }
        int[] preCount = new int[numCourses];
        for(int[] pre : prerequisites){
            edges.get(pre[1]).add(pre[0]);
            ++preCount[pre[0]];
        }
        Deque<Integer> queue = new LinkedList<Integer>();
        for(int i=0;i<numCourses;++i){
            if(preCount[i] == 0){
                queue.offer(i);
            }
        }
        int current;
        List<Integer> topSort = new ArrayList<Integer>();
        while(!queue.isEmpty()){
            current = queue.poll();
            topSort.add(current);
            for(int post : edges.get(current)){
                if(--preCount[post] == 0){
                    queue.offer(post);
                }
            }
        }
        if(topSort.size() < numCourses){
            return new int[]{};
        }
        int[] result = new int[numCourses];
        for(int i=0;i<numCourses;++i){
            result[i] = topSort.get(i);
        }
        return result;
    }

    public static void main(String[] args) {
        No210 obj = new No210();
        int[] output1 = obj.findOrder2(2, new int[][]{{1, 0}});
        System.out.print("[ ");
        for(int e : output1){
            System.out.print(e + ", ");
        }
        System.out.println("]");
        int[] output2 = obj.findOrder2(4, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}});
        System.out.print("[ ");
        for(int e : output2){
            System.out.print(e + ", ");
        }
        System.out.println("]");
        int[] output3 = obj.findOrder2(3, new int[][]{{1, 0}, {2, 0}, {0, 2}});
        System.out.print("[ ");
        for(int e : output3){
            System.out.print(e + ", ");
        }
        System.out.println("]");
    }

}
