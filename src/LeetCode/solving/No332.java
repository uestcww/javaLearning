package LeetCode.solving;

import java.util.*;

// 题眼：欧拉回路/欧拉通路 类似的题目还有753题
public class No332 {

    // 有关图的题，一般都会给节点和边，这时候多数情况都需要根据所有边，求得所有节点的邻居节点，存在一个数据结构中，比如一个HashMap<Integer, List<Integer>>

    // 又超出时间限制，又超出时间限制，我服了，爆炸
    // 应该是按照图的思想去做，找回路啊，什么的
    private boolean[] visited;
    private List<String> res;
    private List<List<String>> results;
    private int count = 0;
    public List<String> findItinerary(List<List<String>> tickets) {
        visited = new boolean[tickets.size()];
        res = new ArrayList<>();
        results = new ArrayList<>();
        for(int i=0;i<tickets.size();++i){
            if(visited[i]){
                continue;
            }
            if(tickets.get(i).get(0).equals("JFK")){
                ++count;
                visited[i] = true;
                res.add("JFK");
                res.add(tickets.get(i).get(1));
                search(tickets.get(i).get(1), tickets);
                --count;
                visited[i] = false;
                res.clear();
            }
        }
        if(results.size() == 1){
            return results.get(0);
        }else if(results.size() > 1){
            res.clear();
            res = results.get(0);
            for(int i=1;i<results.size();++i){
                if(AIsBiggerThanB(results.get(i), res)){
                    res = results.get(i);
                }
            }
            return res;
        }else{
            return new ArrayList<String>();
        }
    }
    public void search(String target, List<List<String>> tickets){
        if(count >= visited.length){
            results.add(new ArrayList<>(res));
            return;
        }
        for(int i=0;i<tickets.size();++i){
            if(visited[i]){
                continue;
            }
            if(tickets.get(i).get(0).equals(target)){
                ++count;
                visited[i] = true;
                res.add(tickets.get(i).get(1));
                search(tickets.get(i).get(1), tickets);
                --count;
                visited[i] = false;
                res.remove(res.size() - 1);
            }
        }
    }
    public boolean AIsBiggerThanB(List<String> A, List<String> B){
        int length = A.size();
        for(int i=0;i<length;++i){
            if(A.get(i).equals(B.get(i))){
                continue;
            }
            String a = A.get(i);
            String b = B.get(i);
            if(a.charAt(0) < b.charAt(0)){
                return true;
            }else if(a.charAt(0) > b.charAt(0)){
                return false;
            }else if(a.charAt(1) < b.charAt(1)){
                return true;
            }else if(a.charAt(1) > b.charAt(1)){
                return false;
            }else if(a.charAt(2) < b.charAt(2)){
                return true;
            }else if(a.charAt(2) > b.charAt(2)){
                return false;
            }
        }
        return true;
    }

    // 一般我自己写了上面那么多代码，基本不是效果很差，就是超时，要不就是写错了
    Map<String, PriorityQueue<String>> map = new HashMap<String, PriorityQueue<String>>();
    List<String> itinerary = new LinkedList<String>();
    public List<String> findItineraryLeetCode(List<List<String>> tickets) {
        for (List<String> ticket : tickets) {
            String src = ticket.get(0), dst = ticket.get(1);
            if (!map.containsKey(src)) {
                map.put(src, new PriorityQueue<String>());
            }
            map.get(src).offer(dst);
        }
        dfs("JFK");
        Collections.reverse(itinerary);
        return itinerary;
    }
    public void dfs(String curr) {
        while (map.containsKey(curr) && map.get(curr).size() > 0) {
            String tmp = map.get(curr).poll();
            dfs(tmp);
        }
        itinerary.add(curr);
    }

    public static void main(String[] args) {
        No332 obj = new No332();
        List<List<String>> tickets = new ArrayList<>();

//        tickets.add(new ArrayList<String>(){{add("MUC");add("LHR");}});
//        tickets.add(new ArrayList<String>(){{add("JFK");add("MUC");}});
//        tickets.add(new ArrayList<String>(){{add("SFO");add("SJC");}});
//        tickets.add(new ArrayList<String>(){{add("LHR");add("SFO");}});
//        System.out.println(obj.findItinerary(tickets));

//        tickets.add(new ArrayList<String>(){{add("JFK");add("SFO");}});
//        tickets.add(new ArrayList<String>(){{add("JFK");add("ATL");}});
//        tickets.add(new ArrayList<String>(){{add("SFO");add("ATL");}});
//        tickets.add(new ArrayList<String>(){{add("ATL");add("JFK");}});
//        tickets.add(new ArrayList<String>(){{add("ATL");add("SFO");}});
//        System.out.println(obj.findItinerary(tickets));

//        tickets.add(new ArrayList<String>(){{add("EZE");add("AXA");}});
//        tickets.add(new ArrayList<String>(){{add("TIA");add("ANU");}});
//        tickets.add(new ArrayList<String>(){{add("ANU");add("JFK");}});
//        tickets.add(new ArrayList<String>(){{add("JFK");add("ANU");}});
//        tickets.add(new ArrayList<String>(){{add("ANU");add("EZE");}});
//        tickets.add(new ArrayList<String>(){{add("TIA");add("ANU");}});
//        tickets.add(new ArrayList<String>(){{add("AXA");add("TIA");}});
//        tickets.add(new ArrayList<String>(){{add("TIA");add("JFK");}});
//        tickets.add(new ArrayList<String>(){{add("ANU");add("TIA");}});
//        tickets.add(new ArrayList<String>(){{add("JFK");add("TIA");}});
//        System.out.println(obj.findItinerary(tickets));
    }

}
