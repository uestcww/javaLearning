package LeetCode.resolved.graph;

import java.util.*;

public class No1584 {

    // 啪，很快啊，我就做完了
    // 我用了一个迪杰斯特拉算法，结果还不错，用时81.32%，内存89.44%
    // 这经典算法我就不重复了
    public int minCostConnectPoints(int[][] points) {
        // 看看有多少个点
        int length = points.length;
        // 小于等于一个点就不用算了
        if(length <= 1){
            return 0;
        }
        // 保存还没有加入集合的点，一开始除了第一个点，其他都加入
        HashSet<Integer> unselect = new HashSet<Integer>();
        for(int i=1;i<length;++i){
            unselect.add(i);
        }
        // 这里用来保存最短路径，第一个点自己就不需要距离了，就是0，目前别的点都是不可达状态，所以是最大值
        int[] distance = new int[length];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[0] = 0;
        int newPoint = 0, currentDis, minDis, minPoint;
        // 开始循环，剩余length-1个点，所以从i=1开始循环
        for(int i=1;i<length;++i){
            // 初始化，min两个变量是保存一次遍历中的最短的点
            // 一次遍历就遍历所有集合以外的点，计算他们离集合的最短距离，由于集合中的点是一个一个加进去的
            // 所以每次只需要判断与最新的点的距离，然后对比历史最短距离就好了
            minDis = Integer.MAX_VALUE;
            minPoint = -1;
            for(Integer current : unselect){
                // 计算距离
                currentDis = Math.abs(points[newPoint][0] - points[current][0]) + Math.abs(points[newPoint][1] - points[current][1]);
                // 与历史数据对比，看看是否更新数据
                if(currentDis < distance[current]){
                    distance[current] = currentDis;
                }
                if(distance[current] < minDis){
                    minDis = distance[current];
                    minPoint = current;
                }
            }
            // 一趟遍历后，找出了最短距离的点，把它加入集合中，也就是把它从unselect中移除
            unselect.remove(minPoint);
            newPoint = minPoint;
        }
        // 最后把距离和计算出来
        int sum = 0;
        for(int i=0;i<length;++i){
            sum += distance[i];
        }
        return sum;
    }

    public static void main(String[] args) {
        No1584 obj = new No1584();
//        System.out.println("20 ? " + obj.minCostConnectPoints(new int[][]{
//                {0, 0}, {2, 2}, {3, 10}, {5, 2}, {7, 0}
//        }));
        System.out.println("53 ? " + obj.minCostConnectPoints(new int[][]{
                {2, -3}, {-17, -8}, {13, 8}, {-17, -15}
        }));
    }

}
