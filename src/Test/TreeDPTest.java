package Test;

import java.util.*;

public class TreeDPTest {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] values = new int[n+1];
        for(int i=1;i<=n;++i){
            values[i] = in.nextInt();
        }
        List<List<Integer>> sons = new ArrayList<>();
        for(int i=0;i<=n;++i){
            sons.add(new ArrayList<>());
        }
        int[] sonCount = new int[n+1];
        int[] father = new int[n+1];
        int fa, s;

        int edges = 4;
        while(edges-- > 0){
            fa = in.nextInt();
            s = in.nextInt();
            sons.get(fa).add(s);
            ++sonCount[fa];
            father[s] = fa;
        }


        int[][] data = new int[2][n+1];
        Arrays.fill(data[0], -1);
        Arrays.fill(data[1], -1);
        Queue<Integer> queue = new ArrayDeque<>();
        for(int i=0;i<=n;++i){
            if(sonCount[i] == 0){
                queue.offer(i);
            }
        }
        int currentNode, yesSum, noSum;
        while(!queue.isEmpty()){
            currentNode = queue.poll();
            yesSum = 0;
            noSum = 0;
            for(int son : sons.get(currentNode)){
                yesSum += data[0][son];
                noSum += Math.max(data[0][son], data[1][son]);
            }
            data[1][currentNode] = values[currentNode] + yesSum;
            data[0][currentNode] = noSum;
            fa = father[currentNode];
            --sonCount[fa];
            if(sonCount[fa] == 0){
                queue.offer(fa);
            }
        }
        int root = 1;
        while(father[root] != 0){
            root = father[root];
        }
        System.out.println(Math.max(data[0][root], data[1][root]));
    }










}
