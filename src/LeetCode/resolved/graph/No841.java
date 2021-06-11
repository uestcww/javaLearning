package LeetCode.resolved.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// 其实就是图的遍历，深度优先遍历或者广度优先遍历
public class No841 {

    // 通过了，但是时间 10.11%（4ms） 内存 55.25%
    public boolean canVisitAllRooms0(List<List<Integer>> rooms) {
        int n = rooms.size();
        if(n < 1){
            return false;
        }
        if(n == 1){
            return true;
        }
        boolean[] isVisited = new boolean[n];
        int pointer = 0;
        isVisited[pointer] = true;
        List<Integer> currentRoom = rooms.get(pointer);
        if(currentRoom == null || currentRoom.size() < 1){
            return false;
        }
        Queue<Integer> queue = new LinkedList<>();
        for(int key : currentRoom){
            queue.offer(key);
        }
        while(!queue.isEmpty()){
            pointer = queue.poll();
            isVisited[pointer] = true;
            currentRoom = rooms.get(pointer);
            for(int key : currentRoom){
                if(!isVisited[key]){
                    queue.offer(key);
                }
            }
        }
        for(int i=0;i<n;++i){
            if(!isVisited[i]){
                return false;
            }
        }
        return true;
    }

    // 用时50.91&（2ms） 内存38.69%
    public boolean canVisitAllRooms1(List<List<Integer>> rooms) {
        int n = rooms.size();
        if(n < 1){
            return false;
        }
        if(n == 1){
            return true;
        }
        boolean[] canVisit = new boolean[n];
        int pointer, count = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        canVisit[0] = true;
        while(!queue.isEmpty()){
            pointer = queue.poll();
            ++count;
            for(int key : rooms.get(pointer)){
                if(!canVisit[key]){
                    queue.offer(key);
                    canVisit[key] = true;
                }
            }
        }
        return count == n;
    }

    // 用时50.91%（2ms） 内存53.80%
    public boolean canVisitAllRooms2(List<List<Integer>> rooms) {
        int n = rooms.size();
        if(n < 1){
            return false;
        }
        if(n == 1){
            return true;
        }
        boolean[] canVisit = new boolean[n];
        int pointer, count = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        canVisit[0] = true;
        List<Integer> keys;
        while(!queue.isEmpty()){
            pointer = queue.poll();
            ++count;
            keys = rooms.get(pointer);
            for(int key : keys){
                if(!canVisit[key]){
                    queue.offer(key);
                    canVisit[key] = true;
                }
            }
        }
        return count == n;
    }

    // 看了答案，把两个if判断删除了 用时50.91%（2ms） 内存37.54%
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int n = rooms.size(), pointer, count = 0;
        boolean[] canVisit = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        canVisit[0] = true;
        List<Integer> keys;
        while(!queue.isEmpty()){
            pointer = queue.poll();
            ++count;
            keys = rooms.get(pointer);
            for(int key : keys){
                if(!canVisit[key]){
                    queue.offer(key);
                    canVisit[key] = true;
                }
            }
        }
        return count == n;
    }

    public static void main(String[] args) {
        No841 obj = new No841();

        List<List<Integer>> rooms1 = new ArrayList<>();
        rooms1.add(new ArrayList<Integer>(){{add(1);}});
        rooms1.add(new ArrayList<Integer>(){{add(2);}});
        rooms1.add(new ArrayList<Integer>(){{add(3);}});
        rooms1.add(new ArrayList<Integer>());
        System.out.println("true ? " + obj.canVisitAllRooms(rooms1));

        List<List<Integer>> rooms2 = new ArrayList<>();
        rooms2.add(new ArrayList<Integer>(){{add(1);add(3);}});
        rooms2.add(new ArrayList<Integer>(){{add(3);add(0);add(1);}});
        rooms2.add(new ArrayList<Integer>(){{add(2);}});
        rooms2.add(new ArrayList<Integer>(){{add(0);}});
        System.out.println("false ? " + obj.canVisitAllRooms(rooms2));

        List<List<Integer>> rooms3 = new ArrayList<>();
        rooms3.add(new ArrayList<Integer>());
        System.out.println("true ? " + obj.canVisitAllRooms(rooms3));


    }

}
