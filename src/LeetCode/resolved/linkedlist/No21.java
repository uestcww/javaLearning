package LeetCode.resolved.linkedlist;

import LeetCode.utils.ListNode;

public class No21 {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null){
            return l2;
        }
        if(l2 == null){
            return l1;
        }
        ListNode head, node, child, pointer;
        if(l1.val >= l2.val){
            head = l2;
            pointer = l2;
            node = l1;
        }else{
            head = l1;
            pointer = l1;
            node = l2;
        }
        while(node != null){
            while(pointer.next != null && pointer.next.val <= node.val){
                pointer = pointer.next;
            }
            if(pointer.next == null){
                pointer.next = node;
                break;
            }
            child = node.next;
            node.next = pointer.next;
            pointer.next = node;
            node = child;
        }
        return head;
    }

    public static void main(String[] args) {
        No21 obj = new No21();

        ListNode list1 = new ListNode(1);
        ListNode list2 = new ListNode(2);
        ListNode list3 = new ListNode(4);
        ListNode list4 = new ListNode(1);
        ListNode list5 = new ListNode(3);
        ListNode list6 = new ListNode(4);

        list1.next = list2;
        list2.next = list3;
        list4.next = list5;
        list5.next = list6;

        System.out.println(obj.mergeTwoLists(list1, list4).toString());
    }


}
