package LeetCode.resolved.linkedlist;

import LeetCode.utils.ListNode;

public class No24 {

    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode parent = head;
        ListNode node = head.next;
        parent.next = node.next;
        node.next = parent;
        head = node;
        node = parent.next;
        while(node != null && node.next != null){
            parent.next = node.next;
            node.next = node.next.next;
            parent.next.next = node;
            parent = node;
            node = node.next;
        }
        return head;
    }

    public static void main(String[] args) {
        No24 obj = new No24();
        ListNode node4 = new ListNode(4);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(1, node2);
        System.out.println(node1.toString());
        System.out.println(obj.swapPairs(node1).toString());
    }

}
