package LeetCode.resolved.linkedlist;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;

public class No138 {

    private class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }

        public Node(int _val,Node _next,Node _random) {
            val = _val;
            next = _next;
            random = _random;
        }
    }

    // 我这个不行，因为改了原链表，按理说应该是对的，但是leetcode不允许这么做
    public Node copyRandomList0(Node head) {
        if(head == null){
            return null;
        }
        Node newHead = new Node(head.val);
        newHead.random = head;
        Node pre = head, node = head.next, newPre = newHead;
        while(node != null){
            Node newNode = new Node(node.val);
            newNode.random = node;
            newPre.next = newNode;
            pre.next = newPre;
            pre = node;
            node = node.next;
            newPre = newNode;
        }
        pre.next = newPre;
        node = newHead;
        while(node != null){
            pre = node.random;
            node.random = pre.random == null ? null : pre.random.next;
            node = node.next;
        }
        return newHead;
    }

    // 又是通过了但是效果非常不好的一天
    // 两个arraylist，存新旧两个链表，然后根据所在的位置索引，来决定random指针的指向
    public Node copyRandomList(Node head) {
        if(head == null){
            return null;
        }
        ArrayList<Node> list = new ArrayList<>();
        ArrayList<Node> newList = new ArrayList<>();
        Node node = head;
        while(node != null){
            Node newNode = new Node(node.val);
            list.add(node);
            newList.add(newNode);
            node = node.next;
        }
        int i = 0, size = list.size();
        newList.add(null);
        while(i < size){
            newList.get(i).next = newList.get(i+1);
            newList.get(i).random = list.get(i).random == null ? null : newList.get(list.indexOf(list.get(i).random));
            ++i;
        }
        return newList.get(0);
    }

    // 这代码居然过了，只不过效果依然不太好就是了，用时17.27%，内存99.55%
    // 让新链表的random指针先指向旧链表的对应节点，然后在遍历后用pre指针将旧链表中的节点的next指针指向新链表的对应节点
    // 然后在生成完后，oldNode = newNode.random; newNode.random = oldNode.random.next or null
    // 最后还原旧链表，还原使用了一个双端队列
    public Node copyRandomList2(Node head) {
        if(head == null){
            return null;
        }
        Node newHead = new Node(head.val);
        newHead.random = head;
        Node pre = head, node = head.next, newPre = newHead;
        while(node != null){
            Node newNode = new Node(node.val);
            newNode.random = node;
            newPre.next = newNode;
            pre.next = newPre;
            pre = node;
            node = node.next;
            newPre = newNode;
        }
        pre.next = newPre;
        node = newHead;
        Deque<Node> deque = new ArrayDeque<>();
        while(node != null){
            pre = node.random;
            deque.offer(pre);
            node.random = pre.random == null ? null : pre.random.next;
            node = node.next;
        }
        node = deque.poll();
        while(!deque.isEmpty()){
            node.next = deque.peek();
            node = deque.pop();
        }
        node.next = null;
        return newHead;
    }

    // 递归，将链表看作图，深度优先遍历图，我为什么就想不出来
    private HashMap<Node, Node> visitedHash = new HashMap<Node, Node>();
    public Node copyRandomListLeetCode1(Node head) {
        if (head == null) {
            return null;
        }
        if (this.visitedHash.containsKey(head)) {
            return this.visitedHash.get(head);
        }
        Node node = new Node(head.val, null, null);
        this.visitedHash.put(head, node);
        node.next = this.copyRandomListLeetCode1(head.next);
        node.random = this.copyRandomListLeetCode1(head.random);
        return node;
    }

    // 我怎么这么笨，这代码愣是完全写不出来，这写的多漂亮，官方代码真的大多数都写的贼漂亮
    // 这个解法就是单纯的使用了字典，没别的技巧
    HashMap<Node, Node> visited = new HashMap<Node, Node>();
    public Node getClonedNode(Node node) {
        if(node != null){
            if(this.visited.containsKey(node)){
                return this.visited.get(node);
            }else{
                this.visited.put(node, new Node(node.val, null, null));
                return this.visited.get(node);
            }
        }
        return null;
    }
    public Node copyRandomListLeetCode2(Node head) {
        if(head == null){
            return null;
        }
        Node oldNode = head;
        Node newNode = new Node(oldNode.val);
        this.visited.put(oldNode, newNode);
        while(oldNode != null){
            newNode.random = this.getClonedNode(oldNode.random);
            newNode.next = this.getClonedNode(oldNode.next);
            oldNode = oldNode.next;
            newNode = newNode.next;
        }
        return this.visited.get(head);
    }

    // 将新节点放在老链表对应节点的后面，呈交替穿插的形式，通过 oldNode.next.random = oldNode.random or null 来解决random指针问题
    public Node copyRandomListLeetCode3(Node head) {
        if(head == null){
            return null;
        }
        Node ptr = head;
        while (ptr != null) {
            Node newNode = new Node(ptr.val);
            newNode.next = ptr.next;
            ptr.next = newNode;
            ptr = newNode.next;
        }
        ptr = head;
        while (ptr != null) {
            ptr.next.random = (ptr.random != null) ? ptr.random.next : null;
            ptr = ptr.next.next;
        }
        Node ptr_old_list = head;
        Node ptr_new_list = head.next;
        Node head_old = head.next;
        while (ptr_old_list != null) {
            ptr_old_list.next = ptr_old_list.next.next;
            ptr_new_list.next = (ptr_new_list.next != null) ? ptr_new_list.next.next : null;
            ptr_old_list = ptr_old_list.next;
            ptr_new_list = ptr_new_list.next;
        }
        return head_old;
    }

    public void test(){
//        Node node1 = new Node(7);
//        Node node2 = new Node(13);
//        Node node3 = new Node(11);
//        Node node4 = new Node(10);
//        Node node5 = new Node(1);
//        node1.next = node2;
//        node2.next = node3;
//        node3.next = node4;
//        node4.next = node5;
//        node5.next = null;
//
//        node1.random = null;
//        node2.random = node1;
//        node3.random = node5;
//        node4.random = node3;
//        node5.random = node1;
//
//        Node head = copyRandomList(node1);
//        while(head != null){
//            if(head.random == null){
//                System.out.println("val=" + head.val + "|" + "random=null");
//            }else{
//                System.out.println("val=" + head.val + "|" + "random=" + head.random.val);
//            }
//            head = head.next;
//        }
    }

    public static void main(String[] args) {
        new No138().test();
    }
}
