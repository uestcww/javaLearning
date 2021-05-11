package LeetCode.resolved.tree;

import LeetCode.utils.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;


/*
* 很常规的题，遍历，获得叶子节点的列表，然后对比就好了
* */
public class No872 {

    public ArrayList<Integer> leafList(TreeNode root){
        if(root.left == null && root.right == null){
            return new ArrayList<Integer>(){{add(root.val);}};
        }
        ArrayList<Integer> list = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        TreeNode node;
        while(!stack.isEmpty()){
            node = stack.pop();
            if(node.left == null && node.right == null){
                list.add(node.val);
                continue;
            }
            if(node.right != null){
                stack.push(node.right);
            }
            if(node.left != null){
                stack.push(node.left);
            }
        }
        return list;
    }

    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        if(root1 == null || root2 == null){
            return root1 == root2;
        }
        ArrayList<Integer> list1 = leafList(root1);
        ArrayList<Integer> list2 = leafList(root2);
        return list1.equals(list2);
    }

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(3);
        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(1);
        TreeNode node3 = new TreeNode(6);
        TreeNode node4 = new TreeNode(2);
        TreeNode node5 = new TreeNode(9);
        TreeNode node6 = new TreeNode(8);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(4);
        root1.left = node1;
        root1.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.left = node5;
        node2.right = node6;
        node4.left = node7;
        node4.right = node8;

        TreeNode root2 = new TreeNode(3);
        TreeNode node9 = new TreeNode(5);
        TreeNode node10 = new TreeNode(1);
        TreeNode node11 = new TreeNode(6);
        TreeNode node12 = new TreeNode(7);
        TreeNode node13 = new TreeNode(4);
        TreeNode node14 = new TreeNode(2);
        TreeNode node15 = new TreeNode(9);
        TreeNode node16 = new TreeNode(8);
        root2.left = node9;
        root2.right = node10;
        node9.left = node11;
        node9.right = node12;
        node10.left = node13;
        node10.right = node14;
        node14.left = node15;
        node14.right = node16;

        System.out.printf("true ? " + new No872().leafSimilar(root1, root2));





    }

}
