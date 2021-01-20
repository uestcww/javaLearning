package LeetCode.resolved.graph;

import java.util.*;

public class No547 {

    // 看了答案忽然发现，不用删除啊，只需要记录就好了，就像207 210两题中的visited数组那样

    // 保存所有城市，在这里面删除
    private ArrayList<Integer> list = new ArrayList<Integer>();
    // 这个其实就相当于边的集合
    private int[][] connected;
    // 临时保存一个省的所有城市
    private HashSet<Integer> oneProvince = new HashSet<Integer>();
    // 好久不做题，手生了
    // 实质还是图的遍历问题，就是一回遍历一个省，然后打总删除，如果还有元素，就继续遍历一个省
    public int findCircleNum(int[][] isConnected) {
        // 前置条件，一条边都没有就直接返回吧
        if(isConnected.length < 1){
            return 0;
        }
        // 将参数中的边的集合复制到属性变量中去
        connected = isConnected;
        // 将所有城市添加进去
        for(int i=0;i<isConnected[0].length;++i){
            list.add(i);
        }
        // 统计省的数量
        int res = 0;
        // 循环，一次处理一个省的城市，res在最后自增一
        while(!list.isEmpty()){
            // 执行图的广度优先遍历算法，选择第一个城市，去遍历与他相邻的城市
            bfs(list.get(0));
            // 删除遍历到的所有城市
            for(Integer city : oneProvince){
                list.remove(city);
            }
            // 清空这次的结果
            oneProvince.clear();
            // 统计加一
            ++res;
        }
        return res;
    }
    public void bfs(int num){
        // 先把参数城市加入遍历结果中
        oneProvince.add(num);
        // 用一个队列来遍历，每次遍历一层
        Queue<Integer> queue = new LinkedList<Integer>();
        // 找到参数城市的所有相邻城市，将他们加入队列中
        for(int i=0;i<connected[num].length;++i){
            // 此处注意，如果城市被遍历过，就不加入队列中了，由于此时只遍历了参数城市，所以只需要判断是否为参数城市就好了
            if(connected[num][i] == 1 && i != num) {
                queue.offer(i);
            }
        }
        // 队列不空就继续遍历
        while(!queue.isEmpty()){
            // 获取当前城市，并将其加入遍历结果中
            int city = queue.poll();
            oneProvince.add(city);
            // 找到当前城市的相邻城市，同上，注意去除已经遍历过的，要不然就成死循环了
            for(int i=0;i<connected[city].length;++i){
                if(connected[city][i] == 1 && !oneProvince.contains(i)) {
                    queue.offer(i);
                }
            }
        }
    }

    // 并查集方法，我还是第一次接触这个概念
    public int findCircleNumLeetCode(int[][] isConnected) {
        int provinces = isConnected.length;
        int[] parent = new int[provinces];
        for (int i = 0; i < provinces; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < provinces; i++) {
            for (int j = i + 1; j < provinces; j++) {
                if (isConnected[i][j] == 1) {
                    union(parent, i, j);
                }
            }
        }
        int circles = 0;
        for (int i = 0; i < provinces; i++) {
            if (parent[i] == i) {
                circles++;
            }
        }
        return circles;
    }
    public void union(int[] parent, int index1, int index2) {
        parent[find(parent, index1)] = find(parent, index2);
    }
    public int find(int[] parent, int index) {
        if (parent[index] != index) {
            parent[index] = find(parent, parent[index]);
        }
        return parent[index];
    }

    public static void main(String[] args) {
        No547 obj = new No547();
//        System.out.println("2 ? " + obj.findCircleNum(new int[][]{{1,1,0}, {1,1,0}, {0,0,1}}));
//        System.out.println("3 ? " + obj.findCircleNum(new int[][]{{1,0,0}, {0,1,0}, {0,0,1}}));
        System.out.println("1 ? " + obj.findCircleNum(new int[][]{
                {1,0,0,1}, {0,1,1,0}, {0,1,1,1}, {1,0,1,1}
        }));
    }

}
