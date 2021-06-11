package LeetCode.resolved.graph;

import java.util.*;

/*
 * 207与210两个题高度相似，其实都是考察图的遍历算法，一般都是深度优先遍历或者广度优先遍历
 * */
public class No210 {

    public static void main(String[] args) {
        int[] output1 = new No210().findOrder(2, new int[][]{{1, 0}});
        System.out.print("[ ");
        for(int e : output1){
            System.out.print(e + ", ");
        }
        System.out.println("]");

        int[] output2 = new No210().findOrder(4, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}});
        System.out.print("[ ");
        for(int e : output2){
            System.out.print(e + ", ");
        }
        System.out.println("]");

        int[] output3 = new No210().findOrder(3, new int[][]{{1, 0}, {2, 0}, {0, 2}});
        System.out.print("[ ");
        for(int e : output3){
            System.out.print(e + ", ");
        }
        System.out.println("]");
    }

    // 做出来了，可是效果不太好，用时 5.06%（95ms） 内存 5.01%
    // 207题用了深度优先遍历，这次我准备尝试使用广度优先遍历
    public int[] findOrder0(int numCourses, int[][] prerequisites) {
        ArrayList<ArrayList<Integer>> lists = new ArrayList<>();
        for(int i=0;i<numCourses;++i){
            lists.add(new ArrayList<Integer>());
        }
        for(int[] pre : prerequisites){
            lists.get(pre[0]).add(pre[1]);
        }
        boolean[] isVisited = new boolean[numCourses];
        int[] neighborCount = new int[numCourses];
        Deque<Integer> queue = new ArrayDeque<>();
        int[] result = new int[numCourses];
        int pointer = 0;
        for(int i=0;i<numCourses;++i){
            neighborCount[i] = lists.get(i).size();
            if(neighborCount[i] == 0){
                queue.offer(i);
            }
        }
        while(!queue.isEmpty()){
            int current = queue.poll();
            if(isVisited[current]){
                continue;
            }
            isVisited[current] = true;
            result[pointer++] = current;
            for(int i=0;i<numCourses;++i){
                if(isVisited[i]){
                    continue;
                }
                lists.get(i).remove(new Integer(current));
                if(lists.get(i).isEmpty()){
                    queue.offer(i);
                }
            }
        }
        return pointer == numCourses ? result : new int[]{};
    }

    // 改了一下队列的实现，从 Deque<Integer> queue = new ArrayDeque<Integer>() 改为了 Queue<Integer> queue = new LinkedList<>()，但是没有任何变化
    // 成功，用时 78.67% 内存52.79%
    // 根据上一版，优化一下，看看能到什么程度
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        ArrayList<ArrayList<Integer>> lists = new ArrayList<>();
        for(int i=0;i<numCourses;++i){
            lists.add(new ArrayList<Integer>());
        }
        int[] neighborCount = new int[numCourses];
        for(int[] pre : prerequisites){
            lists.get(pre[1]).add(pre[0]);
            ++neighborCount[pre[0]];
        }
        Queue<Integer> queue = new LinkedList<>();
        for(int i=0;i<numCourses;++i){
            if(neighborCount[i] == 0){
                queue.offer(i);
            }
        }
        int[] result = new int[numCourses];
        int pointer = 0;
        while(!queue.isEmpty()){
            int current = queue.poll();
            result[pointer++] = current;
            for(int i : lists.get(current)){
                if(--neighborCount[i] == 0){
                    queue.offer(i);
                }
            }
        }
        return pointer == numCourses ? result : new int[]{};
    }

    /*
    * 以下是leetcode官方的深度优先遍历和广度优先遍历
    * */

    //深度优先遍历
    private List<List<Integer>> edges;
    private int[] visited;
    private boolean isPossible = true;
    private Deque<Integer> topSort;
    public int[] findOrderLeetCode1(int numCourses, int[][] prerequisites) {
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
    public int[] findOrderLeetCode2(int numCourses, int[][] prerequisites) {
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

}
