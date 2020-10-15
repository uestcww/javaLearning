package LeetCode.resolved.linkedlist;

import LeetCode.utils.ListNode;

public class No61 {

    public ListNode rotateRight(ListNode head, int k) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode tail = head, parent = head;
        int count = 1;
        while(tail.next != null){
            tail = tail.next;
            count++;
        }
        k%=count;
        if(k == 0){
            return head;
        }
        while(count-k-1>0){
            parent = parent.next;
            k++;
        }
        tail.next = head;
        head = parent.next;
        parent.next = null;
        return head;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
//        node2.next = node3;
//        node3.next = node4;
//        node4.next = node5;

        No61 obj = new No61();
//        obj.rotateRight(node1, 2);
        System.out.println(obj.rotateRight(node1, 1).toString());
    }

}
