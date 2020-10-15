package LeetCode.resolved.linkedlist;

import LeetCode.utils.ListNode;

import java.util.ArrayList;
import java.util.List;

public class No83 {

    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode dummy = new ListNode(-1, head);
        while(head != null && head.next != null){
            if(head.val == head.next.val){
                head.next = head.next.next;
            }else{
                head = head.next;
            }
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        No83 obj = new No83();
        List<ListNode> lists = new ArrayList<ListNode>();
        lists.add(new ListNode(1));
        lists.add(new ListNode(1));
        lists.add(new ListNode(2));

        System.out.println(obj.deleteDuplicates(ListNode.asLinkedList(lists)));

        lists.add(new ListNode(3));
        lists.add(new ListNode(3));

        System.out.println(obj.deleteDuplicates(ListNode.asLinkedList(lists)));


    }

}
