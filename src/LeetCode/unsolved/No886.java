package LeetCode.unsolved;

import java.util.*;

// 与785题一样的
public class No886 {

    public static void main(String[] args) {
        System.out.println("true ? " + new No886().possibleBipartition(4, new int[][]{
                {1, 2}, {1, 3}, {2, 4}
        }));
        System.out.println("false ? " + new No886().possibleBipartition(3, new int[][]{
                {1, 2}, {1, 3}, {2, 3}
        }));
        System.out.println("false ? " + new No886().possibleBipartition(5, new int[][]{
                {1, 2}, {2, 3}, {3, 4}, {4, 5}, {1, 5}
        }));
    }

    public static final int UNCOLORED = 0;
    public static final int RED = 1;
    public static final int GREEN = 2;

    public boolean isValid;
    public List<List<Integer>> neighborList;
    public int[] colors;

    //BFS
    public boolean possibleBipartition(int n, int[][] dislikes){
        neighborList = new ArrayList<>();
        for(int i=0;i<=n;++i){
            neighborList.add(new ArrayList<Integer>());
        }
        for(int[] dislike : dislikes){
            neighborList.get(dislike[0]).add(dislike[1]);
            neighborList.get(dislike[1]).add(dislike[0]);
        }
        colors = new int[n+1];
        Arrays.fill(colors, UNCOLORED);
        Queue<Integer> queue = new ArrayDeque<Integer>();
        for(int i=1;i<=n;++i){
            if(colors[i] == UNCOLORED){
                queue.clear();
                colors[i] = RED;
                queue.offer(i);
                while(!queue.isEmpty()){
                    int node = queue.poll();
                    int neighborColor = (colors[node] == RED ? GREEN : RED);
                    for(int neighbor : neighborList.get(node)){
                        if (colors[neighbor] == UNCOLORED) {
                            queue.offer(neighbor);
                            colors[neighbor] = neighborColor;
                        } else if (colors[neighbor] != neighborColor) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    //DFS
    public boolean possibleBipartitionDFS(int n, int[][] dislikes) {
        isValid = true;
        neighborList = new ArrayList<>();
        for(int i=0;i<=n;++i){
            neighborList.add(new ArrayList<Integer>());
        }
        for(int[] dislike : dislikes){
            neighborList.get(dislike[0]).add(dislike[1]);
            neighborList.get(dislike[1]).add(dislike[0]);
        }
        colors = new int[n+1];
        Arrays.fill(colors, UNCOLORED);
        for(int i=1;i<=n&&isValid;++i){
            if(colors[i] == UNCOLORED){
                dfs(i, RED);
            }
        }
        return isValid;
    }
    public void dfs(int node, int color){
        colors[node] = color;
        int neiColor = (color == RED ? GREEN : RED);
        for(int neighbor : neighborList.get(node)){
            if(colors[neighbor] == UNCOLORED){
                dfs(neighbor, neiColor);
                if(!isValid){
                    return;
                }
            }else if(colors[neighbor] != neiColor){
                isValid = false;
                return;
            }
        }
    }

}
