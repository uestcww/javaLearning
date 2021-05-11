package LeetCode.unsolved;

import LeetCode.utils.ListNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class No160 {

    // 垃圾方法，搞两个栈，傻子都会做
    // 首先先将两个链表分别进栈，然后开始pop。
    // 如果不同，就返回 node.next
    // 三种情况
    // 1.两个链表完全相同，出循环，return node; 此时是表头
    // 2.中间相同，此时会在刚刚分离的时候进入if语句，返回next，也就是第一个相同的
    // 3.两个链表完全不同，那第一次进入循环就进了if语句，此时是链表尾，node.next的值为null
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null){
            return null;
        }
        Deque<ListNode> aStack = new ArrayDeque<>();
        Deque<ListNode> bStack = new ArrayDeque<>();
        ListNode node;
        node = headA;
        while(node != null){
            aStack.push(node);
            node = node.next;
        }
        node = headB;
        while(node != null){
            bStack.push(node);
            node = node.next;
        }
        ListNode nodeB;
        while(!aStack.isEmpty() && !bStack.isEmpty()){
            node = aStack.pop();
            nodeB = bStack.pop();
            if(node != nodeB){
                return node.next;
            }
        }
        return node;
    }

    // 官方方法二：哈希表法，挺好，就是首先给A搞一个哈希表，然后遍历B，发现在哈希表里时，就成功了，没有就是null
    // 官方方法三：双指针，指针遍历自己的链表，到链表尾，指向对方链表头，继续遍历，此时他们会在第一个相同节点处相遇

    public static void main(String[] args) {

    }

}
