package LeetCode.unsolved;

import LeetCode.utils.TreeNode;

import java.util.Stack;

public class No226 {

    //前序遍历，根左右
    public void preOrder(TreeNode root){
        if(root == null){
            System.out.println("空树！");
            return;
        }
        TreeNode node = root;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(node);
        System.out.print("前序遍历：");
        while(!stack.empty()){
            TreeNode p = stack.pop();
            System.out.print(p.val + " ");
            if(p.right != null){
                stack.push(p.right);
            }
            if(p.left != null){
                stack.push(p.left);
            }
        }
    }

    //中序遍历，左根右
    public void inOrder(TreeNode root){
        if(root == null){
            System.out.println("空树！");
            return;
        }
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode node = root;
        System.out.print("中序遍历：");
        while(node != null || !stack.empty()){
            while(node != null){
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            System.out.print(node.val + " ");
            if(node.right != null){
                node = node.right;
            }else{
                node = null;
            }
        }
    }

    //后序遍历，左右根
    public void postOrder(TreeNode root){
        if(root == null){
            System.out.println("空树！");
            return;
        }
        TreeNode node = root;
        TreeNode pre = null;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        System.out.print("后序遍历：");
        while(node != null || !stack.empty()){
            while(node != null){
                stack.push(node);
                node = node.left;
            }
            if(!stack.empty()){
                node = stack.peek();
                if(node.right == null || node.right == pre){
                    node = stack.pop();
                    System.out.print(node.val + " ");
                    pre = node;
                    node = null;
                }else{
                    node = node.right;
                }
            }
        }
    }

    public TreeNode invertTree(TreeNode root) {
        if(root == null){
            return root;
        }
        Stack<TreeNode> stack = new Stack<TreeNode>();
        Stack<Integer> valStack = new Stack<Integer>();
        TreeNode node = root;
        while(node != null || !stack.empty()){
            while(node != null){
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            valStack.push(node.val);
            if(node.right != null){
                node = node.right;
            }else{
                node = null;
            }
        }
        node = root;
        while(node != null || !stack.empty()){
            while(node != null){
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            node.val = valStack.pop();
            if(node.right != null){
                node = node.right;
            }else{
                node = null;
            }
        }
        return root;
    }

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(4);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(7);
        TreeNode n4 = new TreeNode(1);
        TreeNode n5 = new TreeNode(3);
        TreeNode n6 = new TreeNode(6);
        TreeNode n7 = new TreeNode(9);
        n2.left = n4;
        n2.right = n5;
        n3.left = n6;
        n3.right = n7;
        n1.left = n2;
        n1.right = n3;
        No226 obj = new No226();
        obj.preOrder(n1);
        System.out.println();
        obj.inOrder(n1);
        System.out.println();
        obj.postOrder(n1);
        System.out.println();
        System.out.println("********************");
        obj.invertTree(n1);
        obj.preOrder(n1);
        System.out.println();
        obj.inOrder(n1);
        System.out.println();
        obj.postOrder(n1);
    }

}



