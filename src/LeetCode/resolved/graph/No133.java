package LeetCode.resolved.graph;

import LeetCode.utils.Node;

import java.util.ArrayList;
import java.util.HashMap;

public class No133 {

    // 重新做这道题
    // 写对了，但是少了一个对null的判断，就是万一 node == null 咋办
    private HashMap<Node, Node> map = new HashMap<Node, Node>();
    public Node cloneGraph(Node node) {
        if(map.containsKey(node)){
            return map.get(node);
        }
        Node newNode = new Node(node.val, new ArrayList<Node>());
        map.put(node, newNode);
        for(Node nei : node.neighbors){
            newNode.neighbors.add(cloneGraph(nei));
        }
        return newNode;
    }

    public static void main(String[] args) {
        No133 obj = new No133();
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        node1.neighbors.add(node2);
        node1.neighbors.add(node4);

        node2.neighbors.add(node1);
        node2.neighbors.add(node3);

        node3.neighbors.add(node2);
        node3.neighbors.add(node4);

        node4.neighbors.add(node1);
        node4.neighbors.add(node3);

        Node head = obj.cloneGraph(node1);
        System.out.println(head);
    }


//    //不会做，这是官方代码，以后可以再做一下
//    private HashMap<Node, Node> hashMap = new HashMap<Node, Node>();
//    public Node cloneGraph(Node node) {
//        if (node == null) {
//            return node;
//        }
//        if (hashMap.containsKey(node)) {
//            return hashMap.get(node);
//        }
//        Node cloneNode = new Node(node.val, new ArrayList());
//        hashMap.put(node, cloneNode);
//        for (Node neighbor: node.neighbors) {
//            cloneNode.neighbors.add(cloneGraph(neighbor));
//        }
//        return cloneNode;
//    }

}
