package LeetCode.resolved.linkedlist;

import LeetCode.utils.ListNode;

import java.util.ArrayList;
import java.util.List;

public class No92 {

    // 借用了反转链表1的成果，定位翻转的部分，然后做一定的处理，最后翻转后拼接起来
    public ListNode reverseBetween(ListNode head, int m, int n) {
        // 判断，链表为空或者只有一个节点就不用执行了
        if(head == null || head.next == null){
            return head;
        }
        // 翻转的部分少于1，相当于不用翻转
        if(n-m+1 <= 1){
            return head;
        }
        // 搞一个哑结点当临时的头结点，因为可能会翻转头结点，这个哑结点用于定位
        ListNode dummy = new ListNode(-1, head);
        // head负责定位翻转部分的头结点的上一个节点，不然怎么拼起来啊
        head = dummy;
        // 循环完成，head就定位好了
        while(--m > 0){
            head = head.next;
            // n跟着变化是因为，题目要求只能遍历一趟
            --n;
        }
        // 找完头部，还需要定位尾部，因为借用反转链表1的成果，所以需要在翻转的尾巴指向null
        // 这就使得后面的部分丢失了，所以用temp来保存，tail保存当前的head.next，即翻转后的尾巴
        ListNode tail = head;
        while(--n >= 0){
            tail = tail.next;
        }
        //temp此时为尾巴部分的头
        ListNode temp = tail.next;
        // 要把翻转部分的尾指向null，不然就把后面尾巴部分都翻转了
        tail.next = null;
        // tail保存翻转后的翻转部分的尾，即现在翻转部分的头
        tail = head.next;
        // 开始翻转，并且将翻转部分与前面连起来
        head.next = reverseList(head.next);
        // tail已经是目前的尾部了，还需要把未翻转的尾巴部分与前面连起来，所以tail.next要指向尾巴部分
        tail.next = temp;
        // dummy是永远的头，dummy.next指向永远的头，这是不变的，这也是dummy的作用所在
        return dummy.next;
    }
    // 翻转链表1的答案，没啥好说的，照搬过来的
    public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode p = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }

    // 秒啊，真是秒的，其实还是反转链表1的思路，利用先递归进去，然后再执行后面的代码
    // 前面的代码负责定位，后面的代码负责翻转，真的挺秒的
    private boolean stop;
    private ListNode left;
    public void recurseAndReverse(ListNode right, int m, int n) {
        if (n == 1) {
            return;
        }
        right = right.next;
        if (m > 1) {
            this.left = this.left.next;
        }
        this.recurseAndReverse(right, m - 1, n - 1);
        if (this.left == right || right.next == this.left) {
            this.stop = true;
        }
        if (!this.stop) {
            int t = this.left.val;
            this.left.val = right.val;
            right.val = t;
            this.left = this.left.next;
        }
    }
    public ListNode reverseBetweenLeetCode1(ListNode head, int m, int n) {
        this.left = head;
        this.stop = false;
        this.recurseAndReverse(head, m, n);
        return head;
    }

    // 漂亮，基本上是我第一版的思路，但是比我清楚多了，就是多搞几个指针，固定好特殊位置，然后翻转，最后拼接起来
    public ListNode reverseBetweenLeetCode2(ListNode head, int m, int n) {
        if (head == null) {
            return null;
        }
        ListNode cur = head, prev = null;
        while (m > 1) {
            prev = cur;
            cur = cur.next;
            m--;
            n--;
        }
        ListNode con = prev, tail = cur;
        ListNode third = null;
        while (n > 0) {
            third = cur.next;
            cur.next = prev;
            prev = cur;
            cur = third;
            n--;
        }
        if (con != null) {
            con.next = prev;
        } else {
            head = prev;
        }
        tail.next = cur;
        return head;
    }



















    public static void main(String[] args) {
        No92 obj = new No92();
        List<ListNode> lists = new ArrayList<ListNode>();
//        lists.add(new ListNode(1));
//        lists.add(new ListNode(2));
//        lists.add(new ListNode(3));
//        lists.add(new ListNode(4));
//        lists.add(new ListNode(5));
//        System.out.println(obj.reverseBetween(ListNode.asLinkedList(lists), 2, 4));
        lists.add(new ListNode(3));
        lists.add(new ListNode(5));
        System.out.println(obj.reverseBetween(ListNode.asLinkedList(lists), 1, 2));
    }

}
