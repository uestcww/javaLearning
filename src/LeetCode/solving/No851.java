package LeetCode.solving;

import java.util.*;

public class No851 {

    public static void main(String[] args) {
        No851 obj = new No851();

        System.out.println("[5, 5, 2, 5, 4, 5, 6, 7] ? ");
        System.out.println(Arrays.toString(obj.loudAndRich(new int[][]{
                {1, 0}, {2, 1}, {3, 1}, {3, 7},
                {4, 3}, {5, 3}, {6, 3}
        }, new int[]{3, 2, 5, 4, 6, 1, 7, 0})));

    }

    // 超出内存限制，服了
    public int[] loudAndRich(int[][] richer, int[] quiet) {
        int n = quiet.length;
        List<List<Integer>> neighborList = new ArrayList<>();
        for(int i=0;i<n;++i){
            neighborList.add(new ArrayList<Integer>());
        }
        for(int[] edge : richer){
            neighborList.get(edge[0]).add(edge[1]);
        }
        int[] getNodes = new int[n];
        for(int i=0;i<n;++i){
            getNodes[quiet[i]] = i;
        }
        int[] result = new int[n];
        Arrays.fill(result, -1);
        Queue<Integer> queue = new ArrayDeque<>();
        int currentNode, color, count = 0;
        for(int i=0;i<n;++i){
            color = getNodes[i];
            queue.add(color);
            while(!queue.isEmpty()){
                currentNode = queue.poll();
                if(result[currentNode] == -1){
                    result[currentNode] = color;
                    ++count;
                }
                for(int neighbor : neighborList.get(currentNode)){
                    queue.offer(neighbor);
                }
            }
            if(count == n){
                break;
            }
        }
        return result;
    }






}
