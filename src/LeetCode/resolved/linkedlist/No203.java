package LeetCode.resolved.linkedlist;

import LeetCode.utils.ListNode;

public class No203 {

    // 这题确实是简单的过分了
    public ListNode removeElements(ListNode head, int val) {
        if(head == null){
            return null;
        }
        // 搞一个哑结点
        ListNode dummy = new ListNode(-val, head);
        // node从head开始，那么pre就从dummy开始
        ListNode pre = dummy, node = dummy.next;
        // 遍历链表
        while(node != null){
            // 如果相等，就直接将pre指向node的下一个节点，跳过node，相当于删除了node
            if(node.val == val){
                pre.next = node.next;
            }else{
                // 如果没有，就向后移动一位
                pre = pre.next;
            }
            node = pre.next;
        }
        // 为防止head被删除，返回时这样返回，保证绝对是链表头
        return dummy.next;
    }

    public static void main(String[] args) {

    }

}
