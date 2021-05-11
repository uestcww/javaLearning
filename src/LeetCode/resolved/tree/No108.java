package LeetCode.resolved.tree;

import LeetCode.utils.TreeNode;

// 二叉搜索树的中序遍历是升序序列

public class No108 {

    // 很easy的题，唯一值得讨论的地方就是，如果是偶数个节点，那么是使用(left+right)/2作为根节点
    // 还是(left+right+1)/2作为根节点，还是随机选取(Random)
    public TreeNode sortedArrayToBST(int[] nums) {
        return generate(0, nums.length-1, nums);
    }

    public TreeNode generate(int left, int right, int[] nums){
        if(left == right){
            return new TreeNode(nums[left], null, null);
        }
        int middle = (left + right) / 2;
        TreeNode node = new TreeNode(nums[middle], null, null);
        node.left = left == middle ? null : generate(left, middle-1, nums);
        node.right = generate(middle+1, right, nums);
        return node;
    }

    public static void main(String[] args) {

    }

}
