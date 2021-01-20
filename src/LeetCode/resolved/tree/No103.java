package LeetCode.resolved.tree;

import LeetCode.utils.TreeNode;

import java.util.*;

public class No103 {

    // 虽然结果不太好，但是还是过了，我的解法的思路就是使用层次遍历的解法，然后改进了一下就好了
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        // 保存结果的，如果root为null，就返回一个空的结果
        List<List<Integer>> res = new ArrayList<>();
        if(root == null){
            return res;
        }
        // 两个队列，分着保存，分着用
        Deque<TreeNode> stack1 = new LinkedList<>();
        Deque<TreeNode> stack2 = new LinkedList<>();
        // 临时变量，保存一行的结果
        List<Integer> zigzagLevel = new ArrayList<>();
        TreeNode node;
        // 先用栈1，然后把栈1中遍历完，栈1中的所有孩子节点全部保存到栈2
        // 然后遍历栈2，把栈2的孩子节点又全部保存到栈1，如此反复
        stack1.push(root);
        // 只要两个栈有不空的，就继续循环
        while(!stack1.isEmpty() || !stack2.isEmpty()){
            // 一次循环只遍历一层，所以加一个if语句，判断该遍历哪个栈了
            if(stack1.isEmpty()){
                // 这是栈2，由于是从栈1开始的，所以栈2要反着来，栈1都是正序，栈2都是逆序
                while(!stack2.isEmpty()){
                    node = stack2.pop();
                    zigzagLevel.add(node.val);
                    if(node.right != null){
                        stack1.push(node.right);
                    }
                    if(node.left != null){
                        stack1.push(node.left);
                    }
                }
                // 把这层数据保存好，然后清空，每次都要记得清空
                res.add(new ArrayList<>(zigzagLevel));
                zigzagLevel.clear();
            }else{
                // 这里同上
                while(!stack1.isEmpty()){
                    node = stack1.pop();
                    zigzagLevel.add(node.val);
                    if(node.left != null){
                        stack2.push(node.left);
                    }
                    if(node.right != null){
                        stack2.push(node.right);
                    }
                }
                res.add(new ArrayList<>(zigzagLevel));
                zigzagLevel.clear();
            }
        }
        return res;
    }

    // 感觉我的方法永远有点透露出费劲，有的时候要做好多的无用功，LeetCode官方的就很清爽，直指要害
    public List<List<Integer>> zigzagLevelOrderLeetCode(TreeNode root) {
        List<List<Integer>> ans = new LinkedList<List<Integer>>();
        if (root == null) {
            return ans;
        }
        Queue<TreeNode> nodeQueue = new LinkedList<TreeNode>();
        nodeQueue.offer(root);
        boolean isOrderLeft = true;
        while (!nodeQueue.isEmpty()) {
            Deque<Integer> levelList = new LinkedList<Integer>();
            int size = nodeQueue.size();
            for (int i = 0; i < size; ++i) {
                TreeNode curNode = nodeQueue.poll();
                if (isOrderLeft) {
                    levelList.offerLast(curNode.val);
                } else {
                    levelList.offerFirst(curNode.val);
                }
                if (curNode.left != null) {
                    nodeQueue.offer(curNode.left);
                }
                if (curNode.right != null) {
                    nodeQueue.offer(curNode.right);
                }
            }
            ans.add(new LinkedList<Integer>(levelList));
            isOrderLeft = !isOrderLeft;
        }
        return ans;
    }

    public static void main(String[] args) {
        No103 obj = new No103();
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(9);
        TreeNode node3 = new TreeNode(20);
        TreeNode node4 = new TreeNode(15);
        TreeNode node5 = new TreeNode(7);
        node1.left = node2;
        node1.right = node3;
        node3.left = node4;
        node3.right = node5;
//        System.out.println(obj.levelOrder(node1));
        System.out.println(obj.zigzagLevelOrder(node1));
    }

}
