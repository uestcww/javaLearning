package LeetCode.solving;

import LeetCode.utils.TreeNode;

import java.util.*;

//已解决，只是有一种莫里斯后序遍历方法没看懂
public class No145 {

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if(root == null){
            return res;
        }
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        TreeNode node;
        stack.push(root);
        while(!stack.isEmpty()){
            node = stack.pop();
            res.add(node.val);
            if(node.right != null){
                stack.push(node.right);
            }
            if(node.left != null){
                stack.push(node.left);
            }
        }
        return res;
    }

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

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if(root == null){
            return res;
        }
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        TreeNode node = root;
        TreeNode pre = null;
        while(node != null || !stack.isEmpty()){
            while(node != null){
                stack.push(node);
                node = node.left;
            }
            if(!stack.isEmpty()){
                node = stack.pop();
                //后序遍历是左右根，走到这里肯定是没有左孩子了
                //这时候，如果有右孩子，就不能访问当前节点
                //换句话说，只有当前节点没有右孩子，或者当前节点的右孩子被访问过了
                //才可以访问当前节点
                if(node.right == null || node.right == pre){
                    res.add(node.val);
                    pre = node;
                    node = null;
                }else{
                    stack.push(node);
                    node = node.right;
                }
            }
        }
        return res;
    }

    //思想：后序就是前序的倒过来，可以用链表保存数据，从头结点插入新数据
    public List<Integer> postorderTraversal2(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<>();
        LinkedList<Integer> ans = new LinkedList<>();
        if (null == root) return ans;
        stack.addFirst(root);
        while(!stack.isEmpty()) {
            TreeNode node = stack.removeFirst();
            ans.addFirst(node.val);
            if (null != node.left) {
                stack.addFirst(node.left);
            }
            if (null != node.right) {
                stack.addFirst(node.right);
            }
        }
        return ans;
    }

    /*Morris前序遍历(暂时搞不懂，以后再看)
    * 算法的思路是从当前节点向下访问先序遍历的前驱节点，每个前驱节点都恰好被访问两次。
    * 首先从当前节点开始，向左孩子走一步然后沿着右孩子一直向下访问，直到到达一个叶子节点（当前节点的中序遍历前驱节点）
    * 所以我们更新输出并建立一条伪边 predecessor.right = root 更新这个前驱的下一个点。
    * 如果我们第二次访问到前驱节点，由于已经指向了当前节点，我们移除伪边并移动到下一个顶点。
    * 如果第一步向左的移动不存在，就直接更新输出并向右移动。
    * */
    public List<Integer> preorderTraversalMorris(TreeNode root) {
        LinkedList<Integer> output = new LinkedList<>();

        TreeNode node = root;
        while (node != null) {
            if (node.left == null) {
                output.add(node.val);
                node = node.right;
            }
            else {
                TreeNode predecessor = node.left;
                while ((predecessor.right != null) && (predecessor.right != node)) {
                    predecessor = predecessor.right;
                }

                if (predecessor.right == null) {
                    output.add(node.val);
                    predecessor.right = node;
                    node = node.left;
                }
                else{
                    predecessor.right = null;
                    node = node.right;
                }
            }
        }
        return output;
    }

    /*Morris中序遍历(暂时搞不懂，以后再看)
     * Morris中序遍历算法整体步骤如下（假设当前遍历到的节点为x）：
     * 1.如果x无左孩子，先将x的值加入答案数组，再访问x的右孩子，即 x = x.right
     * 2.如果x有左孩子，则找到x左子树上最右的节点（即左子树中序遍历的最后一个节点，x在中序遍历中的前驱节点），我们记为predecessor。
     *   根据predecessor的右孩子是否为空，进行如下操作。
     *   2.1如果predecessor的右孩子为空，则将其右孩子指向x，然后访问x的左孩子，即 x = x.left。
     *   2.2如果predecessor的右孩子不为空，则此时其右孩子指向x，说明我们已经遍历完x的左子树，我们将predecessor的右孩子置空，将x的值加入答案数组，然后访问x的右孩子，即 x = x.right。
     * 3.重复上述操作，直至访问完整棵树。
     * 其实整个过程我们就多做一步：假设当前遍历到的节点为x，将x的左子树中最右边的节点的右孩子指向x
     * 这样在左子树遍历完成后我们通过这个指向走回了x，且能通过这个指向知晓我们已经遍历完成了左子树，而不用再通过栈来维护，省去了栈的空间复杂度
     * */
    public List<Integer> inorderTraversalMorris(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        TreeNode predecessor = null;

        while (root != null) {
            if (root.left != null) {
                // predecessor 节点就是当前 root 节点向左走一步，然后一直向右走至无法走为止
                predecessor = root.left;
                while (predecessor.right != null && predecessor.right != root) {
                    predecessor = predecessor.right;
                }

                // 让 predecessor 的右指针指向 root，继续遍历左子树
                if (predecessor.right == null) {
                    predecessor.right = root;
                    root = root.left;
                }
                // 说明左子树已经访问完了，我们需要断开链接
                else {
                    res.add(root.val);
                    predecessor.right = null;
                    root = root.right;
                }
            }
            // 如果没有左孩子，则直接访问右孩子
            else {
                res.add(root.val);
                root = root.right;
            }
        }
        return res;
    }

    /*Morris后序遍历(暂时搞不懂，以后再看)
    * Morris遍历的核心思想是利用树的大量空闲指针，实现空间开销的极限缩减。其后序遍历规则总结如下：
    * 1.新建临时节点，令该节点为 root；
    * 2.如果当前节点的左子节点为空，则遍历当前节点的右子节点；
    * 3.如果当前节点的左子节点不为空，在当前节点的左子树中找到当前节点在中序遍历下的前驱节点；
    *   3.1如果前驱节点的右子节点为空，将前驱节点的右子节点设置为当前节点，当前节点更新为当前节点的左子节点。
    *   3.2如果前驱节点的右子节点为当前节点，将它的右子节点重新设为空。倒序输出从当前节点的左子节点到该前驱节点这条路径上的所有节点。当前节点更新为当前节点的右子节点。
    * 4.重复步骤 2 和步骤 3，直到遍历结束。
    * */
    public List<Integer> postorderTraversalMorris(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if(root == null){
            return res;
        }
        TreeNode p1=root, p2=null;
        while(p1 != null){
            p2 = p1.left;
            if(p2 != null){
                while(p2.right != null && p2.right != p1){
                    p2 = p2.right;
                }
                if(p2.right == null){
                    p2.right = p1;
                    p1 = p1.left;
                    continue;
                }else{
                    p2.right = null;
                    addPath(res, p1.left);
                }
            }
            p1 = p1.right;
        }
        addPath(res, root);
        return res;
    }

    public void addPath(List<Integer> res, TreeNode node) {
        List<Integer> tmp = new ArrayList<Integer>();
        while(node != null){
            tmp.add(node.val);
            node = node.right;
        }
        for(int i = tmp.size() - 1; i >= 0; i--){
            res.add(tmp.get(i));
        }
    }

    public static void main(String[] args) {
        TreeNode n5 = new TreeNode(5);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n7 = new TreeNode(7);
        TreeNode n1 = new TreeNode(1);
        TreeNode n9 = new TreeNode(9);
        TreeNode n4 = new TreeNode(4);
        TreeNode n6 = new TreeNode(6);
        TreeNode n8 = new TreeNode(8);
        n1.left = n4;
        n9.left = n6;
        n9.right = n8;
        n2.left = n7;
        n2.right = n1;
        n3.right = n9;
        n5.left = n2;
        n5.right = n3;
        No145 obj = new No145();
        List<Integer> preres = obj.preorderTraversal(n5);
        List<Integer> inres = obj.inorderTraversal(n5);
        List<Integer> postres = obj.postorderTraversal(n5);
        List<Integer> postres2 = obj.postorderTraversal2(n5);
        System.out.println(" 前序遍历=" + preres);
        System.out.println(" 中序遍历=" + inres);
        System.out.println(" 后序遍历=" + postres);
        System.out.println("后序遍历2=" + postres2);
    }
}
