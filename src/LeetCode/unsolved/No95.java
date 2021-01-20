package LeetCode.unsolved;

import LeetCode.utils.TreeNode;

import java.util.LinkedList;
import java.util.List;

public class No95 {

    // 这题实在不会做，只好先看看答案了，不然死盯着也看不出花来
    public List<TreeNode> generateTrees(int n) {


        return null;
    }

    // 官网题解
    // 个人感觉需要学习的地方就是，对于allTrees这个局部变量的使用，注意这个变量是在递归体里声明的
    // 也就是说，每一层都有一个自己的allTrees，他们可以负责收集自己所属的这颗子树的所有可能，然后返回给上一层
    // 如果让我写，我可能会写两个全局变量，一个保存最终结果，一个保存每一次生成的一棵树的结果
    // 但是我觉得他的方法更好，又容易理解，又好控制
    public List<TreeNode> generateTreesLeetCode(int n) {
        if (n == 0) {
            return new LinkedList<TreeNode>();
        }
        return generateTrees(1, n);
    }
    public List<TreeNode> generateTrees(int start, int end) {
        List<TreeNode> allTrees = new LinkedList<TreeNode>();
        if (start > end) {
            allTrees.add(null);
            return allTrees;
        }
        for (int i = start; i <= end; i++) {
            List<TreeNode> leftTrees = generateTrees(start, i - 1);
            List<TreeNode> rightTrees = generateTrees(i + 1, end);
            for (TreeNode left : leftTrees) {
                for (TreeNode right : rightTrees) {
                    TreeNode currTree = new TreeNode(i);
                    currTree.left = left;
                    currTree.right = right;
                    allTrees.add(currTree);
                }
            }
        }
        return allTrees;
    }

    public static void main(String[] args) {

    }

}
