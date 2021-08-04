package JianZhiOffer.resolved;

import JianZhiOffer.utils.ListNode;

import java.util.*;

public class Offer06 {

    // 用时38.30% 内存92.92%
    public int[] reversePrint0(ListNode head) {
        if(head == null){
            return new int[]{};
        }
        Stack<Integer> stack = new Stack<>();
        Deque<Integer> deque = new ArrayDeque<>();
        while(head != null){
            deque.push(head.val);
            head = head.next;
        }
        int[] result = new int[deque.size()];
        for(int i=0;i<result.length;++i){
            result[i] = deque.pop();
        }
        return result;
    }

    // 用时73.45% 内存8.08%
    public List<Integer> list = new ArrayList<>();
    public int[] reversePrint(ListNode head) {
        if(head == null){
            return new int[]{};
        }
        func(head);
        int[] result = new int[list.size()];
        for(int i=0;i<result.length;++i){
            result[i] = list.get(i);
        }
        return result;
    }
    public void func(ListNode node){
        if(node.next != null){
            func(node.next);
        }
        list.add(node.val);
    }

    public static void main(String[] args) {


    }

}
