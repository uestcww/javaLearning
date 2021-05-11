package LeetCode.utils;

import java.util.List;

public class TreeNode {

    public int val = 0;
    public TreeNode left = null;
    public TreeNode right = null;
    public TreeNode next = null;

    public TreeNode(){

    }
    public TreeNode(int val){
        this.val = val;
    }
    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
    public TreeNode(int _val, TreeNode _left, TreeNode _right, TreeNode _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
//    public TreeNode(List<Integer> list){
//        int length;
//        if(list == null || (length = list.size()) < 1){
//            return;
//        }
//
//    }
    public void printAll(){
        System.out.println("********************");
        System.out.println("val   = " + this.val);
        System.out.println("left  = " + (this.left == null ? "null":this.left.val));
        System.out.println("right = " + (this.right == null ? "null":this.right.val));
        System.out.println("next  = " + (this.next == null ? "null":this.next.val));
        if(this.left != null){
            this.left.printAll();
        }
        if(this.right != null){
            this.right.printAll();
        }
    }

}
