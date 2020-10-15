package LeetCode.resolved.linkedlist;

import LeetCode.utils.ListNode;

import java.util.ArrayList;
import java.util.List;

public class No82 {

    //傻逼，人家题目是排序链表，顺序是排好的
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode left = head, leftParent = dummy;
        ListNode right, rightParent;
        boolean isDelete;
        while(left != null && left.next != null){
            rightParent = left;
            right = left.next;
            isDelete = false;
            while(right != null){
                if(left.val == right.val){
                    rightParent.next = right.next;
                    right = rightParent.next;
                    isDelete = true;
                }else{
                    rightParent = rightParent.next;
                    right = right.next;
                }
            }
            if(isDelete){
                leftParent.next = left.next;
            }else{
                leftParent = leftParent.next;
            }
            left = leftParent.next;
        }
        return dummy.next;
    }

    //网上的递归版，我觉得可以写一个非递归版本的
    public ListNode deleteDuplicatesRecursive(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        if (head.val == head.next.val) {
            while (head != null && head.next != null && head.val == head.next.val) {
                head = head.next;
            }
            return deleteDuplicatesRecursive(head.next);
        } else {
            head.next = deleteDuplicatesRecursive(head.next);
            return head;
        }
    }

    public ListNode deleteDuplicates2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode parent = dummy;
        while(head != null){
            head = parent.next;
            if(head != null && head.next != null && head.val == head.next.val){
                parent.next = head.next;
                while(parent.next != null && head.val == parent.next.val){
                    parent.next = parent.next.next;
                }
            }else{
                parent = parent.next;
            }
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        No82 obj = new No82();

        List<ListNode> list = new ArrayList<ListNode>();
        list.add(new ListNode(1));
        list.add(new ListNode(2));
        list.add(new ListNode(3));
        list.add(new ListNode(3));
        list.add(new ListNode(4));
        list.add(new ListNode(4));
        list.add(new ListNode(5));
        ListNode head = ListNode.asLinkedList(list);

        System.out.println(obj.deleteDuplicates2(head).toString());
    }

}
