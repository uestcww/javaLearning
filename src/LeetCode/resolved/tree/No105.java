package LeetCode.resolved.tree;

import LeetCode.utils.TreeNode;

import java.util.*;

public class No105 {

    // 过是过了，可用时7.41%，内存是5.03%，这结果也太差了
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // 如果是空的，那就返回null
        if(preorder.length < 1 && inorder.length < 1){
            return null;
        }
        // 根节点必定是前序遍历的第一个，毕竟根左右
        TreeNode node = new TreeNode(preorder[0], null, null);
        // 找到根节点在中序遍历中是第几个，前面就是左子树，后面是右子树
        int index = 0;
        while(inorder[index] != preorder[0]){
            ++index;
        }
        // 如果index不为0，即左子树是有节点的
        if(index > 0){
            // 直接复制一下数组，开始递归生成左子树，中序遍历中，根节点前的全是左子树的节点，由于节点数量在两个遍历中相等，所以前序遍历中哪几个节点也就知道了
            node.left = buildTree(
                    Arrays.copyOfRange(preorder, 1, index+1),
                    Arrays.copyOfRange(inorder, 0, index)
            );
        }
        // 如果index不是最后一个元素，也就是右子树是有节点的
        if(index < inorder.length - 1){
            // 这里就跟上面类似
            node.right = buildTree(
                    Arrays.copyOfRange(preorder, index+1, preorder.length),
                    Arrays.copyOfRange(inorder, index+1, inorder.length)
            );
        }
        return node;
    }

    // 区别就在于，官方的方案使用了两个边界，没有动数组本身，其实我是想到了这一层，但是觉得参数太多太麻烦，就没采用
    // 不过给我的启示是，一定要善用哈希表，有的时候是真好用
    private Map<Integer, Integer> indexMap;
    public TreeNode myBuildTree(int[] preorder, int[] inorder, int preorder_left, int preorder_right, int inorder_left, int inorder_right) {
        if (preorder_left > preorder_right) {
            return null;
        }
        int preorder_root = preorder_left;
        int inorder_root = indexMap.get(preorder[preorder_root]);
        TreeNode root = new TreeNode(preorder[preorder_root]);
        int size_left_subtree = inorder_root - inorder_left;
        root.left = myBuildTree(preorder, inorder, preorder_left + 1, preorder_left + size_left_subtree, inorder_left, inorder_root - 1);
        root.right = myBuildTree(preorder, inorder, preorder_left + size_left_subtree + 1, preorder_right, inorder_root + 1, inorder_right);
        return root;
    }
    public TreeNode buildTreeLeetCode(int[] preorder, int[] inorder) {
        int n = preorder.length;
        indexMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < n; i++) {
            indexMap.put(inorder[i], i);
        }
        return myBuildTree(preorder, inorder, 0, n - 1, 0, n - 1);
    }

    public TreeNode buildTreeLeetCode2(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[0]);
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        stack.push(root);
        int inorderIndex = 0;
        for (int i = 1; i < preorder.length; i++) {
            int preorderVal = preorder[i];
            TreeNode node = stack.peek();
            if (node.val != inorder[inorderIndex]) {
                node.left = new TreeNode(preorderVal);
                stack.push(node.left);
            } else {
                while (!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]) {
                    node = stack.pop();
                    inorderIndex++;
                }
                node.right = new TreeNode(preorderVal);
                stack.push(node.right);
            }
        }
        return root;
    }

    public static void main(String[] args) {
        TreeNode head = new No105().buildTree(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7});
//        TreeNode head = new No105().buildTree(new int[]{1, 2, 3}, new int[]{3, 2, 1});
        System.out.println("use debug UI to test");
    }
}
