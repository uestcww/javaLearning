package JianZhiOffer.solving;

import JianZhiOffer.utils.TreeNode;

// 这题可以递归也可以迭代，都可以试试
// 此题与LeetCode105题是相同的
public class Offer07 {

    // 递归
//    public TreeNode buildTree(int[] preorder, int[] inorder) {
//        int length = preorder.length;
//        if(length == 0){
//            return null;
//        }
//        int prePointer = 0, inPointer = 0, value;
//        value = preorder[prePointer];
//        TreeNode head = new TreeNode(value);
//        while(inorder[inPointer] != value){
//            ++inPointer;
//        }
//
//        return null;
//    }
//    public TreeNode generate(int[] preorder, int preLeft, int preRight, int[] inorder, int inLeft, int inRight){
//
//    }


    // 迭代
    public TreeNode buildTree0(int[] preorder, int[] inorder) {
        int length = preorder.length;
        if(length == 0){
            return null;
        }




        return null;
    }

    public static void main(String[] args) {

    }

}
