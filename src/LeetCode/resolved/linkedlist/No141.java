package LeetCode.resolved.linkedlist;

import LeetCode.utils.ListNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class No141 {

    // 虽然过了，但是结果很垃圾，我实在是想再想想
    // 用时6.89%，内存9.54%
    public boolean hasCycle1(ListNode head) {
        if(head == null || head.next == null){
            return false;
        }
        HashSet<ListNode> set = new HashSet<>();
        set.add(head);
        ListNode node = head.next;
        while(node != null){
            if(set.contains(node)){
                return true;
            }
            set.add(node);
            node = node.next;
        }
        return false;
    }

    // 我草，这就写完了？？？ 用时居然是100%，内存是43.19%
    // 核心思想，让所有遍历过的节点指向头结点
    // 当前节点为node，如果 node.next.next == head 那么就说明有环
    // 需要注意的是，前提是需要提前把先头的两个节点单独讨论完，因为他们不在循环之内
    public boolean hasCycle(ListNode head) {
        if(head == null || head.next == null){
            return false;
        }
        ListNode pre = head.next;
        if(pre.next == null){
            return false;
        }
        ListNode node = pre.next;
        if(node.next == null){
            return false;
        }
        if(pre.next == head || node.next == head || node.next == pre){
            return true;
        }
        head.next = head;
        while(node.next != null){
            pre.next = head;
            if(node.next.next == head){
                return true;
            }
            pre = node;
            node = node.next;
        }
        return false;
    }

    // 快慢指针法，很是好，我觉得这个思路需要学一学
    // 大概意思就是，慢指针一趟向后移一位，快指针一趟向后移两位，然后看他们俩在指向null前会不会相遇
    // 妙，看官方解答我总想说一声妙
    public boolean hasCycleLeetCode(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }

    public static void main(String[] args) {
        List<ListNode> list = new ArrayList<>();
        list.add(new ListNode(0));
        list.add(new ListNode(1));
        list.add(new ListNode(2));
        list.add(new ListNode(3));
        list.add(new ListNode(4));
        list.get(0).next = list.get(1);
        list.get(1).next = list.get(2);
        list.get(2).next = list.get(3);
        list.get(3).next = list.get(4);
        list.get(4).next = null;

        System.out.println("true ? " + new No141().hasCycle(list.get(0)));
    }

}
