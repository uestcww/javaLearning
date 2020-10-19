package LeetCode.resolved.linkedlist;

import LeetCode.utils.ListNode;

import java.util.ArrayList;
import java.util.List;

public class No206 {

    //迭代，使用了哑结点
    public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode dummy = new ListNode(-1, head);
        ListNode node = head.next;
        head.next = null;
        head = node;
        while(head != null){
            head = head.next;
            node.next = dummy.next;
            dummy.next = node;
            node = head;
        }
        return dummy.next;
    }

    //迭代，不使用哑结点
    public ListNode reverseList2(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode tail = head, node = head.next;
        while(node != null){
            tail.next = node.next;
            node.next = head;
            head = node;
            node = tail.next;
        }
        return head;
    }

    /*
    * 递归，没想出来，看的答案，挺强的
    * 通过先调用，使得从最后一个节点开始执行
    * 其实p一直等于最后一个节点
    * */
    public ListNode reverseList3(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode p = reverseList3(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }

    public static void main(String[] args) {
        List<ListNode> lists = new ArrayList<ListNode>();
        lists.add(new ListNode(1));
        lists.add(new ListNode(2));
        lists.add(new ListNode(3));
        lists.add(new ListNode(4));
        lists.add(new ListNode(5));
        No206 obj = new No206();
        System.out.println(obj.reverseList3(ListNode.asLinkedList(lists)));
    }

}
