package LeetCode.resolved;

import LeetCode.utils.ListNode;
import LeetCode.utils.TreeNode;

public class No109 {

    // 哈哈哈哈哈，快慢指针牛逼，写完一遍就过了，用时100%，内存89.57%
    // 思路：递归生成树，利用快慢指针，将链表的中间找到，使中间作为根节点，左子树由前半段链表生成，右子树由后半段生成
    public TreeNode sortedListToBST(ListNode head){
        // 如果当前为null，那就不用判断了，直接就是null
        if(head == null){
            return null;
        }
        // 声明快慢指针，还有一个slow的前置指针pre，用于将pre.next = null，不然前半段链表还连着后面
        ListNode slow = head, fast = head, pre = null;
        // 循环，fast一次移动两格，slow一次移动一格，由此找到中点
        while(fast != null && fast.next != null){
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        // 特殊地在于如果链表长度小于等于二，会出问题，问题就是pre == null，没有进过上面的循环
        TreeNode node;
        // 这里就是大于二的时候生成当前节点，左子树和右子树递归生成
        if(pre != null){
            node = new TreeNode(slow.val);
            // 这里就是断开两节链表
            pre.next = null;
            node.left = sortedListToBST(head);
            node.right = sortedListToBST(slow.next);
        }else if(head.next != null){
            // 这是长度为二时，直接生成一个子树，返回节点，不再递归
            node = new TreeNode(head.next.val, null, null);
            node.left = new TreeNode(head.val, null, null);
        }else{
            // 长度为一就更是这样了，不递归
            node = new TreeNode(head.val, null, null);
        }
        return node;
    }

    // LeetCode官方解法
    // 利用了树的中序遍历的特点进行递归，缩短了找中点的时间
    ListNode globalHead;
    public TreeNode sortedListToBSTLeetCode(ListNode head) {
        globalHead = head;
        int length = getLength(head);
        return buildTree(0, length - 1);
    }
    public int getLength(ListNode head) {
        int ret = 0;
        while (head != null) {
            ++ret;
            head = head.next;
        }
        return ret;
    }
    public TreeNode buildTree(int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = (left + right + 1) / 2;
        TreeNode root = new TreeNode();
        root.left = buildTree(left, mid - 1);
        root.val = globalHead.val;
        globalHead = globalHead.next;
        root.right = buildTree(mid + 1, right);
        return root;
    }

    public static void main(String[] args) {

    }
}
