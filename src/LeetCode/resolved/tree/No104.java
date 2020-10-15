package LeetCode.resolved.tree;

import LeetCode.utils.TreeNode;

public class No104 {

    public int maxDepth(TreeNode root) {
        if(root == null){
            return 0;
        }
        return computeDepth(root, 1);
    }

    public int computeDepth(TreeNode node, int depth){
        if(node.left == null && node.right == null){
            return depth;
        }
        int left = 0,right = 0;
        if(node.left != null){
            left = computeDepth(node.left, depth+1);
        }
        if(node.right != null){
            right = computeDepth(node.right, depth+1);
        }
        return Math.max(left, right);
    }

    public static void main(String[] args) {
        No104 obj = new No104();

        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(9);
        TreeNode node3 = new TreeNode(20);
        TreeNode node4 = new TreeNode(15);
        TreeNode node5 = new TreeNode(7);
        node3.left = node4;
        node3.right = node5;
        node1.left = node2;
        node1.right = node3;

        System.out.println(obj.maxDepth(node1));

    }



}
