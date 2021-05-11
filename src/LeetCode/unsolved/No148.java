package LeetCode.unsolved;

import LeetCode.utils.ListNode;

import java.util.ArrayList;

// 应该使用归并排序
public class No148 {

    // 用递归的方法，有点快排的思路，遗憾的是超出时间限制，并不对
    public ListNode sortList0(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode dummy = new ListNode(0, null);
        ListNode pre = head, node = head.next;
        while(node != null){
            if(node.val < head.val){
                pre.next = node.next;
                node.next = dummy.next;
                dummy.next = node;
                node = pre.next;
            }else{
                pre = node;
                node = node.next;
            }
        }
        head.next = sortList0(head.next);
        pre = sortList0(dummy.next);
        if(pre == null){
            return head;
        }
        node = pre;
        while(node.next != null){
            node = node.next;
        }
        node.next = head;
        return pre;
    }

    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode dummy = new ListNode(Integer.MIN_VALUE, null);
        ListNode pre = head, node = head.next, cur, tail = dummy;
        while(node != null){
            if(node.val < head.val){
                pre.next = node.next;
                cur = dummy;
                while(cur.next != null && cur.next.val <= node.val){
                    cur = cur.next;
                }
                if(cur.next == null){
                    cur.next = node;
                    tail = node;
                }else{
                    node.next = cur.next;
                    cur.next = node;
                }
                node = pre.next;
            }else{
                pre = node;
                node = node.next;
            }
        }
        head.next = sortList(head.next);
        tail.next = head;
        return dummy.next;
    }

    // 自顶向下
    public ListNode sortListLeetCode1(ListNode head) {
        return sortList(head, null);
    }
    public ListNode sortList(ListNode head, ListNode tail) {
        if (head == null) {
            return head;
        }
        if (head.next == tail) {
            head.next = null;
            return head;
        }
        ListNode slow = head, fast = head;
        while (fast != tail) {
            slow = slow.next;
            fast = fast.next;
            if (fast != tail) {
                fast = fast.next;
            }
        }
        ListNode mid = slow;
        ListNode list1 = sortList(head, mid);
        ListNode list2 = sortList(mid, tail);
        ListNode sorted = merge(list1, list2);
        return sorted;
    }
    public ListNode merge(ListNode head1, ListNode head2) {
        ListNode dummyHead = new ListNode(0);
        ListNode temp = dummyHead, temp1 = head1, temp2 = head2;
        while (temp1 != null && temp2 != null) {
            if (temp1.val <= temp2.val) {
                temp.next = temp1;
                temp1 = temp1.next;
            } else {
                temp.next = temp2;
                temp2 = temp2.next;
            }
            temp = temp.next;
        }
        if (temp1 != null) {
            temp.next = temp1;
        } else if (temp2 != null) {
            temp.next = temp2;
        }
        return dummyHead.next;
    }

    // 自底向上
    public ListNode sortListLeetCode2(ListNode head) {
        if (head == null) {
            return head;
        }
        int length = 0;
        ListNode node = head;
        while (node != null) {
            length++;
            node = node.next;
        }
        ListNode dummyHead = new ListNode(0, head);
        for (int subLength = 1; subLength < length; subLength <<= 1) {
            ListNode prev = dummyHead, curr = dummyHead.next;
            while (curr != null) {
                ListNode head1 = curr;
                for (int i = 1; i < subLength && curr.next != null; i++) {
                    curr = curr.next;
                }
                ListNode head2 = curr.next;
                curr.next = null;
                curr = head2;
                for (int i = 1; i < subLength && curr != null && curr.next != null; i++) {
                    curr = curr.next;
                }
                ListNode next = null;
                if (curr != null) {
                    next = curr.next;
                    curr.next = null;
                }
                ListNode merged = merge(head1, head2);
                prev.next = merged;
                while (prev.next != null) {
                    prev = prev.next;
                }
                curr = next;
            }
        }
        return dummyHead.next;
    }

    public static void main(String[] args) {
        ArrayList<ListNode> list = new ArrayList<>();
        list.add(new ListNode(4));
        list.add(new ListNode(2));
        list.add(new ListNode(1));
        list.add(new ListNode(3));
//        list.add(new ListNode(5));
//        list.add(new ListNode(2));
//        list.add(new ListNode(1));
//        list.add(new ListNode(4));
//        list.add(new ListNode(2));
        System.out.println(new No148().sortList(ListNode.asLinkedList(list)));
    }
}
