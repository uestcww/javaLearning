package LeetCode.solving;

import LeetCode.utils.ListNode;

import java.util.ArrayList;
import java.util.List;

public class No86 {

    public ListNode partition(ListNode head, int x) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode dummy = new ListNode(-1, head);
        ListNode parent = dummy;
        while(parent != null && parent.next != null){
            if(head.val < x && parent != dummy){
                parent.next = head.next;
                head.next = dummy.next;
                dummy.next = head;
                head = parent.next;
            }else{
                parent = parent.next;
                head = parent.next;
            }
        }
        return dummy.next;
    }
    public ListNode partition2(ListNode head, int x) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode dummy = new ListNode(-1, head);
        ListNode node;
        while(head != null && head.next != null){
            if(head.next.val < x){
                node = head.next;
                head.next = node.next;
                node.next = dummy.next;
                dummy.next = node;
            }else{
               head = head.next;
            }
        }
        return dummy.next;
    }

    /*这一版也适配不了，我服了，LeetCode能不能把测试用例给我，我自己跑啊*/
    /*为了配合LeetCode所修改的版本*/
    public ListNode partitionX(ListNode head, int x) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode dummy = new ListNode(-1, head);
        ListNode parent;
        if(head.val < x){
            parent = head;
            head = head.next;
        }else{
            parent = dummy;
        }
        ListNode node;
        while(head != null && head.next != null){
            if(head.next.val < x){
                node = head.next;
                head.next = node.next;
                node.next = parent.next;
                parent.next = node;
                parent = parent.next;
            }else{
                head = head.next;
            }
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        List<ListNode> lists = new ArrayList<ListNode>();
        lists.add(new ListNode(1));
        lists.add(new ListNode(4));
        lists.add(new ListNode(3));
        lists.add(new ListNode(2));
        lists.add(new ListNode(5));
        lists.add(new ListNode(2));

        No86 obj = new No86();
        System.out.println(obj.partitionX(ListNode.asLinkedList(lists), 3));
    }

}
