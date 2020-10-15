package LeetCode.resolved.linkedlist;

import LeetCode.utils.ListNode;

public class No2 {

    public void showResult(ListNode result){
        String s = "";
        while (result != null){
            s += result.val;
            result = result.next;
        }
        System.out.println(s);
    }

    public ListNode reverseListLink(ListNode list){
        ListNode temp = list.next, tail = list;
        while (tail.next != null){
            tail.next = temp.next;
            temp.next = list;
            list = temp;
            temp = tail.next;
        }
        return list;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode l1Reverse = this.reverseListLink(l1);
        ListNode l2Reverse = this.reverseListLink(l2);
        l1 = l1Reverse;
        l2 = l2Reverse;
        int num = 0,overflow = 0;
        while (l1Reverse.next != null && l2Reverse.next != null){
            num = l1Reverse.val + l2Reverse.val + overflow;
            if(num > 9){
                l1Reverse.val = num % 10;
                overflow = num / 10;
            }else {
                l1Reverse.val = num;
                overflow = 0;
            }
            l1Reverse = l1Reverse.next;
            l2Reverse = l2Reverse.next;
        }
        num = l1Reverse.val + l2Reverse.val + overflow;
        if(num <= 9){
            l1Reverse.val = num;
            overflow = 0;
        }else {
            l1Reverse.val = num % 10;
            overflow = num / 10;
        }
        if(l2Reverse.next != null){
            l1Reverse.next = l2Reverse.next;
        }
        while (l1Reverse.next != null && overflow != 0){
            l1Reverse = l1Reverse.next;
            num = l1Reverse.val + overflow;
            if(num <= 9){
                l1Reverse.val = num;
                overflow = 0;
            }else {
                l1Reverse.val = num % 10;
                overflow = num / 10;
            }

        }
        if(overflow!=0){
            ListNode tail = new ListNode(overflow);
            l1Reverse.next = tail;
        }
        ListNode result = this.reverseListLink(l1);
        return result;

    }

    public static void main(String[] args) {

        ListNode list1 = new ListNode(2);
        ListNode a1 = new ListNode(4);
        ListNode a2 = new ListNode(3);
        ListNode a3 = new ListNode(5);


        ListNode list2 = new ListNode(5);
        ListNode b1 = new ListNode(6);
        ListNode b2 = new ListNode(4);
        /*
        *
        * 1125 + 365 = 1490
        *
        * */

        list1.next = a1;
        a1.next = a2;
//        a2.next = a3;

        list2.next = b1;
        b1.next = b2;


        /*
        * 原题目就是倒序的数字，我以为正序，其实不需要reverseListLink函数
        * */


        No2 obj = new No2();
        ListNode result = obj.addTwoNumbers(list1, list2);
        obj.showResult(result);



    }

}
