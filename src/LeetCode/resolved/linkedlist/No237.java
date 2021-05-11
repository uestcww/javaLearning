package LeetCode.resolved.linkedlist;

import LeetCode.utils.ListNode;

// 简单题里面真是藏龙卧虎
public class No237 {

    public void deleteNode0(ListNode node) {
        ListNode cur = node.next;
        while(cur.next != null){
            node.val = cur.val;
            node = node.next;
            cur = node.next;
        }
        node.val = cur.val;
        node.next = null;
    }

    public void deleteNode(ListNode node) {
        while(node.next.next != null){
            node.val = node.next.val;
            node = node.next;
        }
        node.val = node.next.val;
        node.next = null;
    }

    // 删除的节点就是当前节点，关后面的兄弟们什么事呢？
    // 小丑竟是我自己
    public void deleteNodeLeetCode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

}
