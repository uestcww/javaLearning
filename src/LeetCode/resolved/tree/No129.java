package LeetCode.resolved.tree;

import LeetCode.utils.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class No129 {

    // 我使用了回溯法，其实也算是深度优先遍历吧
    // 保存当前的数字之和，遍历完成就是所有数字之和了
    private int count = 0;
    public int sumNumbers(TreeNode root) {
        // 空树就直接返回0
        if(root == null){
            return 0;
        }
        // 从根节点开始回溯，num是之前的数值，根节点之前没有节点，所以为0
        backtrack(root, 0);
        return count;
    }
    // node表示当前节点，num是当前节点前的所有父节点积累的值
    // 也可以理解为如果当前节点为null的话，最终结果就要加一个num
    public void backtrack(TreeNode node, int num){
        // 计算加上当前节点的新数值，计算公式就不解释了，读题就能明白
        int current = num * 10 + node.val;
        // 左右孩子都没有，说明到叶子节点了，那么现在的 current 中保存的就是数字之一了，将它加到最终的 count 中去
        if(node.left == null && node.right == null){
            count += current;
            return;
        }
        // 如果当前节点还有左节点，那就继续回溯，传入当前数值，作为孩子节点的父节点积累值
        if(node.left != null){
            backtrack(node.left, current);
        }
        // 如果当前节点还有右节点，那也继续回溯，同上
        if(node.right != null){
            backtrack(node.right, current);
        }
    }

    // 其实这个广度优先遍历也挺好的，不需要递归，一个循环就搞定了，问题是需要两个队列
    public int sumNumbersLeetCode(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int sum = 0;
        Queue<TreeNode> nodeQueue = new LinkedList<TreeNode>();
        Queue<Integer> numQueue = new LinkedList<Integer>();
        nodeQueue.offer(root);
        numQueue.offer(root.val);
        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.poll();
            int num = numQueue.poll();
            TreeNode left = node.left, right = node.right;
            if (left == null && right == null) {
                sum += num;
            } else {
                if (left != null) {
                    nodeQueue.offer(left);
                    numQueue.offer(num * 10 + left.val);
                }
                if (right != null) {
                    nodeQueue.offer(right);
                    numQueue.offer(num * 10 + right.val);
                }
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        No129 obj = new No129();
//        TreeNode node1 = new TreeNode(1);
//        TreeNode node2 = new TreeNode(2);
//        TreeNode node3 = new TreeNode(3);
//        node1.left = node2;
//        node1.right = node3;
//        System.out.println(obj.sumNumbers(node1));
        TreeNode node4 = new TreeNode(4);
        TreeNode node9 = new TreeNode(9);
        TreeNode node0 = new TreeNode(0);
        TreeNode node5 = new TreeNode(5);
        TreeNode node1 = new TreeNode(1);
        node4.left = node9;
        node4.right = node0;
        node9.left = node5;
        node9.right = node1;
        System.out.println(obj.sumNumbers(node4));
    }

}
