package LeetCode.utils;

import java.util.List;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(){

    }
    public ListNode(int val){
        this.val = val;
    }
    public ListNode(int val, ListNode next){
        this.val = val;
        this.next = next;
    }
    public String toString(){
        return this.next == null ? this.val + " -> null":this.val + " -> " + this.next.toString();
    }
//    public String toString(){
//        return "val=" + this.val;
//    }
    public void link(ListNode node){
        this.next = node;
    }
    public void linkAll(List<ListNode> listNodes){
        if(listNodes.size() < 1){
            return;
        }
        this.next = listNodes.get(0);
        for(int i=0;i<listNodes.size()-1;i++){
            listNodes.get(i).next = listNodes.get(i+1);
        }
        listNodes.get(listNodes.size()-1).next = null;
    }
    //写一个参数是List<Integer>就可以构造一个链表的构造函数
//    public void linkAll(List<Integer> listNodes){
//        if(listNodes.size() < 1){
//            return;
//        }
//        ListNode node = new ListNode(listNodes.get(0));
//        this.next = node;
//        for(int i=0;i<listNodes.size()-1;i++){
//            listNodes.get(i).next = listNodes.get(i+1);
//        }
//        listNodes.get(listNodes.size()-1).next = null;
//    }
    public static ListNode asLinkedList(List<ListNode> listNodes){
        if(listNodes.size() < 1){
            return null;
        }
        if(listNodes.size() == 1){
            listNodes.get(0).next = null;
            return listNodes.get(0);
        }
        for(int i=0;i<listNodes.size()-1;i++){
            listNodes.get(i).next = listNodes.get(i+1);
        }
        listNodes.get(listNodes.size()-1).next = null;
        return listNodes.get(0);
    }
}
