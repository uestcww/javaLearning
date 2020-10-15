package LeetCode.resolved.linkedlist;

import LeetCode.utils.ListNode;

public class No19 {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode pointer = head, node = head, parent = head;
        while(n>0){
            pointer = pointer.next;
            n--;
        }
        if(pointer == null){
            return node.next;
        }
        while(pointer.next != null){
            pointer = pointer.next;
            parent = parent.next;
        }
        node = parent.next;
        parent.next = node.next;
        return head;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        No19 obj = new No19();
        System.out.println(obj.removeNthFromEnd(node1, 2).toString());


    }

}
