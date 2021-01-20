package LeetCode.resolved.linkedlist;

import LeetCode.utils.ListNode;

public class No328 {

    // 这题就是当链表中节点总数为奇数和偶数时候不太一样，导致有点小问题
    public ListNode oddEvenList(ListNode head) {
        // 只有一个头结点或者头结点都是 null 那就直接返回就好
        if(head == null || head.next == null){
            return head;
        }
        // 使用 node 来遍历链表
        ListNode node = head;
        // 偶数的另外组成一个链表，最后再插入旧链表的最后
        ListNode dummy = new ListNode(-1);
        ListNode dummyTail = dummy;
        // 这里面不用说了吧，经典的链表操作
        // 唯一需要注意的就是，不能轻易 node = node.next
        // 因为这可能使 node = null ，这就使后面不好操作了
        // 所以要判断一下
        while(node.next != null){
            dummyTail.next = node.next;
            node.next = node.next.next;
            dummyTail = dummyTail.next;
            if(node.next != null){
                node = node.next;
            }
        }
        // 这时有可能还指向下一个节点，所以要让他指向 null
        dummyTail.next = null;
        // 将 dummy 中的偶数节点插到最后面去
        node.next = dummy.next;
        return head;
    }

    // 官方的还是比我的更要秒一些
    public ListNode oddEvenListLeetCode(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode evenHead = head.next;
        ListNode odd = head, even = evenHead;
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }

}
