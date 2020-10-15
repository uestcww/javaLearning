package LeetCode.resolved.tree;

import LeetCode.utils.TreeNode;

public class No98 {

    //一定注意使用long而不是int，因为测试用例中有一个[2147483647]
    //这个是int类型最大值，2^31-1，使用int类型是一定也过不了的
    public boolean isValidBST(TreeNode root) {
        return isValid(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isValid(TreeNode node, long left, long right){
        if(node == null){
            return true;
        }
        if(node.val <= left || node.val >= right){
            return false;
        }

        return isValid(node.left, left, node.val) && isValid(node.right, node.val, right);
    }

    public static void main(String[] args) {
        No98 obj = new No98();

        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(1);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(7);
        TreeNode node5 = new TreeNode(4);
        TreeNode node6 = new TreeNode(8);
        TreeNode node7 = new TreeNode(3);
        TreeNode node8 = new TreeNode(6);
        TreeNode node9 = new TreeNode(4);
        TreeNode node10 = new TreeNode(7);

    }

}
