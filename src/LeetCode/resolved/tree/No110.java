package LeetCode.resolved.tree;

import LeetCode.utils.TreeNode;

public class No110 {

    // 不愧是简单题，没啥难度，心里毫无波动，就是递归算树的高度
    // 注意及时中断递归，有助于提高效率
    private boolean res = true;
    public boolean isBalanced(TreeNode root) {
        if(root == null){
            return true;
        }
        func(root);
        return res;
    }
    public int func(TreeNode node){
        if(!res){
            return -1;
        }
        int left, right;
        left = node.left == null ? 0 : func(node.left);
        right = node.right == null ? 0 : func(node.right);
        if(Math.abs(left - right) > 1){
            res = false;
            return -1;
        }
        return Math.max(left, right) + 1;
    }

    public static void main(String[] args) {
//        No110 obj = new No110();
//        TreeNode node1 = new TreeNode(3);
//        TreeNode node2 = new TreeNode(9);
//        TreeNode node3 = new TreeNode(20);
//        TreeNode node4 = new TreeNode(15);
//        TreeNode node5 = new TreeNode(7);
//        node1.left = node2;
//        node1.right = node3;
//        node3.left = node4;
//        node3.right = node5;
//        System.out.println("true ? " + obj.isBalanced(node1));

        No110 obj = new No110();
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(2);
        TreeNode node4 = new TreeNode(3);
        TreeNode node5 = new TreeNode(3);
        TreeNode node6 = new TreeNode(4);
        TreeNode node7 = new TreeNode(4);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node4.left = node6;
        node4.right = node7;
        System.out.println("false ? " + obj.isBalanced(node1));
    }

}
