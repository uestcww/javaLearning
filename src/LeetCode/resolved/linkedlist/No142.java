package LeetCode.resolved.linkedlist;

import LeetCode.utils.ListNode;

import java.util.ArrayList;
import java.util.List;

public class No142 {

    // 用时太离谱了，174ms减到43ms又减到21ms，我太励志了，但用时依然很高
    // 核心思路，快慢指针
    public ListNode detectCycle(ListNode head) {
        if(head == null || head.next == null){
            return null;
        }
        ListNode node = head, slow = head, fast = head.next;
        // 这个循环是为了判断是否有环，且如果有环，循环结束时必有 slow = fast 且两个指针在环里
        while(slow != fast){
            if(fast == null || fast.next == null){
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        // 这个循环是为了找到进入循环的第一个节点
        // node依次向后遍历，slow不动，fast依次向后遍历，如果node与fast或者slow相等，说明此时node就是第一个
        while(true){
            //为避免上来赶巧了，slow就是第一个节点，所以这里判断一下
            if(node == slow){
                return node;
            }
            // 将fast置为slow的next，准备开始转圈
            fast = slow.next;
            while(fast != slow){
                // 由于前面判断了slow与node，所以按照顺序，应先判断fast与node，再让fast向后移
                if(node == fast){
                    return node;
                }
                fast = fast.next;
            }
            // 转了一圈，都不等于node，说明当前节点还在环外，node向后移动一位
            node = node.next;
        }
    }

    // 官方题解与141题相似，依然是哈希表与快慢指针两种，我没有选择使用哈希表是因为题干要求o(1)的空间
    // 官方的这个快慢指针的变种，是有公式推导的，它推导出，在快慢指针相遇时，一个新指针从head出发，此时slow也向前一步一步的走，最终新指针与slow会在入环点相遇
    public ListNode detectCycleLeetCode(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head, fast = head;
        while (fast != null) {
            slow = slow.next;
            if (fast.next != null) {
                fast = fast.next.next;
            } else {
                return null;
            }
            if (fast == slow) {
                ListNode ptr = head;
                while (ptr != slow) {
                    ptr = ptr.next;
                    slow = slow.next;
                }
                return ptr;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        List<ListNode> list = new ArrayList<>();
        list.add(new ListNode(3));
        list.add(new ListNode(2));
        list.add(new ListNode(0));
        list.add(new ListNode(-4));
        list.get(0).next = list.get(1);
        list.get(1).next = list.get(2);
        list.get(2).next = list.get(3);
        list.get(3).next = list.get(1);

        System.out.println("2 ? " + new No142().detectCycle(list.get(0)).val);
    }

}


