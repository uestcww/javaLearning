package LeetCode.solving;

import LeetCode.utils.ListNode;

import java.util.ArrayList;
import java.util.List;

public class No92 {

    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(head == null || head.next == null){
            return head;
        }
        int count = n-m+1;
        if(count <= 1){
            return head;
        }
        ListNode dummy = new ListNode(-1, head);
        head = dummy;
        ListNode tail, node;
        while(m > 1){
            head = head.next;
            m--;
            n--;
        }
        node = head.next;
        tail = head.next;
        while(n-- > 0){
            tail = tail.next;
        }
        while(count-- > 0){
            head.next = node.next;
            node.next = tail;
            tail = node;
            node = head.next;
        }
        head.next = tail;
        node.next = null;
        return head;
    }

    public static void main(String[] args) {
        No92 obj = new No92();
        List<ListNode> lists = new ArrayList<ListNode>();
//        lists.add(new ListNode(1));
//        lists.add(new ListNode(2));
//        lists.add(new ListNode(3));
//        lists.add(new ListNode(4));
//        lists.add(new ListNode(5));
//        System.out.println(obj.reverseBetween(ListNode.asLinkedList(lists), 2, 4));
        lists.add(new ListNode(3));
        lists.add(new ListNode(5));
        System.out.println(obj.reverseBetween(ListNode.asLinkedList(lists), 1, 2));
    }

}
