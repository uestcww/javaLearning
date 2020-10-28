package LeetCode.resolved.linkedlist;

import LeetCode.utils.ListNode;

import java.util.ArrayList;
import java.util.List;

public class No143 {

    private ListNode pointer;

    public void reorderList(ListNode head) {
        this.pointer = head;
        build(head);
        System.out.println(head);
    }

    public ListNode build(ListNode node){
        if(node == null || node.next == null){
            return node;
        }
        ListNode p = build(node.next);
        if(pointer == null || pointer.next == null){
            return node;
        }
        node.next = null;
        p.next = pointer.next;
        pointer.next = p;
        pointer = p.next;
        return node;
    }

    public static void main(String[] args) {
        No143 obj = new No143();
        List<ListNode> lists = new ArrayList<ListNode>();
        lists.add(new ListNode(1));
        lists.add(new ListNode(2));
        lists.add(new ListNode(3));
        lists.add(new ListNode(4));
        lists.add(new ListNode(5));
        lists.add(new ListNode(6));
        obj.reorderList(ListNode.asLinkedList(lists));
    }

}
