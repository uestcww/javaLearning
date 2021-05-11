package LeetCode.resolved.linkedlist;

import LeetCode.utils.ListNode;

import java.util.ArrayList;

public class No234 {

    // 这个递归绕死我了，还好写出来了，递归问题复杂的是真复杂，我相信这道题的递归一定不是最复杂的
    // 时间99.97%，内存9.81%
    private ListNode fast;
    private boolean result = true;
    public boolean isPalindrome(ListNode head) {
        if(head == null || head.next == null){
            return true;
        }
        if(head.next.next == null){
            return head.val == head.next.val;
        }
        fast = head.next;
        test(head);
        return result;
    }
    public void test(ListNode node){
        // if1.0 如果还没走到头，就继续往后走
        if(fast.next != null){
            // fast不能上来就走两步，先走一步然后判断，怕遇到null
            fast = fast.next;
            // if2.0 如果没有到头，那就继续移动一格，然后递归
            if(fast.next != null){
                fast = fast.next;
                test(node.next);
            }else{
                // if2.0 此处是奇数个节点结束遍历开始对比的转折点，中间节点是单个的，此时node是中间节点的前一个节点
                //       此时node对应的应该是node.next.next
                fast = node.next.next;
            }
        }else{
            // if1.0 此处是偶数个节点结束遍历开始对比的转折点，将fast指针指向node的对应节点，也就是node.next，准备对比
            fast = node.next;
        }
        // 这是分界线，前面是fast指针往后走，slow去找中心；后面是要进行对比了
        if(!result){
            // 如果result已经是false，就不用对比了。
            // 最深层往回return时，这个if语句是第一个被执行的
            return;
        }
        // 对比一下，如果没有匹配上，就直接置为false，这里返回不返回差不太多
        if(node.val != fast.val){
            result = false;
            return;
        }
        // 没有匹配上的话，fast往后指一个，因为返回到上一层，node会自动往前指一个，正好对上
        fast = fast.next;
    }

    // 这代码再一次让我自愧不如，同样的思路，看看人家这个写的，多漂亮，充分利用了返回值，
    // 用全局变量保存慢指针，快指针用递归层数保留，更加合理
    // 比我的好太多，教科书式的递归（回溯）
    private ListNode frontPointer;
    private boolean recursivelyCheck(ListNode currentNode) {
        if (currentNode != null) {
            if (!recursivelyCheck(currentNode.next)) {
                return false;
            }
            if (currentNode.val != frontPointer.val) {
                return false;
            }
            frontPointer = frontPointer.next;
        }
        return true;
    }
    public boolean isPalindromeLeetCode(ListNode head) {
        frontPointer = head;
        return recursivelyCheck(head);
    }

    public static void main(String[] args) {
        ArrayList<ListNode> list = new ArrayList<>();
        list.add(new ListNode(1));
        list.add(new ListNode(2));
        list.add(new ListNode(3));
        list.add(new ListNode(5));
        list.add(new ListNode(1));
        System.out.println(new No234().isPalindrome(ListNode.asLinkedList(list)));
    }

}
