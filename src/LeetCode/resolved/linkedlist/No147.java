package LeetCode.resolved.linkedlist;

import LeetCode.utils.ListNode;

import java.util.ArrayList;

public class No147 {

    // 写完了，结果一般般，用时43.12%，内存36.01%，本来也不希望好结果，毕竟是用最笨的方法写出来的
    public ListNode insertionSortList(ListNode head) {
        // 常规开局判断
        if(head == null || head.next == null){
            return head;
        }
        // 搞一个哑结点，防止头结点的变化
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        dummy.next = head;
        // tail表示排序完成的部分的尾部，node表示要排序的那个节点
        ListNode tail = head, node = tail.next;
        int temp;
        while(node != null){
            // temp表示当前的值
            temp = node.val;
            // 如果node比当前排序完成的最后的值都要大，说明他是新的尾部
            if(tail.val <= temp){
                tail = node;
                node = tail.next;
                continue;
            }
            // 如果需要插入前面，那要先做准备工作
            // head表示遍历的指针，负责找到插入的位置
            // 改变node之前，要注意先把tail的指针向后指，不能断
            head = dummy.next;
            tail.next = node.next;
            // 如果node比head（此时head依然是指向头结点）还要小，说明node是新的头结点
            if(temp <= head.val){
                node.next = head;
                dummy.next = node;
                node = tail.next;
                continue;
            }
            // 找到插入位置，保证 head.val <= node.val <= head.next.val 成立
            while(head.next.val < temp){
                head = head.next;
            }
            // 插入新位置
            node.next = head.next;
            head.next = node;
            // 插入完成，node指向下一个需要排序的节点 or null
            node = tail.next;
        }
        // dummy.next永远是头结点，而head则不一定
        return dummy.next;
    }

    // 基本差不多，大的区别也就一个
    // 他从dummy节点开始遍历的，于是就免去了考虑头结点变化的判断，其实这个是对的思路，引入哑结点就是为了不单独讨论头结点变化的情况
    public ListNode insertionSortListLeetCode(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode lastSorted = head, curr = head.next;
        while (curr != null) {
            if (lastSorted.val <= curr.val) {
                lastSorted = lastSorted.next;
            } else {
                ListNode prev = dummyHead;
                while (prev.next.val <= curr.val) {
                    prev = prev.next;
                }
                lastSorted.next = curr.next;
                curr.next = prev.next;
                prev.next = curr;
            }
            curr = lastSorted.next;
        }
        return dummyHead.next;
    }

    public static void main(String[] args) {
        ArrayList<ListNode> list = new ArrayList<>();
        list.add(new ListNode(4));
        list.add(new ListNode(2));
        list.add(new ListNode(1));
        list.add(new ListNode(3));
        System.out.println(new No147().insertionSortList(ListNode.asLinkedList(list)));
    }

}
