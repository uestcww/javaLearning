package LeetCode.resolved.tree;

import LeetCode.utils.TreeNode;

public class No100 {

    // 没想到一次就过了，还是用时0毫秒击败100%，内存36,击败89%
    // 其实这个题很简单啦，没有任何难点，可能可以尝试一下迭代的做法。
    public boolean isSameTree(TreeNode p, TreeNode q) {
        // 如果p和q有一人是null，那么就只在他们都是null的时候返回true
        if(p == null || q == null){
            return p == null && q == null;
        }else{
            // 二人都不是null，这时首先对比他们的值，不一样就直接false
            // 如果一样，就递归他们的子树，不需要判断孩子节点是否为空，因为在开头有判断了
            if(p.val == q.val){
                return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
            }else{
                return false;
            }
        }
    }

    public static void main(String[] args) {

    }

}
