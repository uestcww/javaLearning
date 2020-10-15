package LeetCode.resolved.tree;

import LeetCode.utils.TreeNode;

import java.util.*;

public class No94 {

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if(root == null){
            return res;
        }
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        TreeNode node = root;
        while(node != null || !stack.isEmpty()){
            while(node != null){
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            res.add(node.val);
            if(node.right != null){
                node = node.right;
            }else{
                node = null;
            }
        }
        return res;
    }

}
