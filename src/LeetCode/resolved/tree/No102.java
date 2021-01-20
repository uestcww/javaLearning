package LeetCode.resolved.tree;

import LeetCode.utils.TreeNode;

import java.util.*;

public class No102 {

    // 不告诉我 root == null 的时候怎么处理，害我错了一次
    // 我的解法的关键就在于队列加一个标志节点
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null){
            return res;
        }
        // 这个list存储每一层的数据，加入res后就clear
        List<Integer> list = new ArrayList<>();
        // 双端队列，用于按行存储节点，用一个dummy节点来表示一行的结束
        Deque<TreeNode> queue = new LinkedList<TreeNode>();
        // 标志节点，存放于末尾，dummy前面的都是同一行的节点，dummy后面就是下一行的节点了
        TreeNode dummy = new TreeNode(-1);
        TreeNode node;
        // 把第一行的root与dummy放进去，然后就正式开始了
        queue.offer(root);
        queue.offer(dummy);
        // 当这里的队头都是dummy节点了，说明队列里只剩dummy了，所以遍历结束了
        while(queue.peek() != dummy){
            // 当这里队头是dummy，说明一行的遍历结束了，该处理这一行的数据了
            while(queue.peek() != dummy){
                // 取出一个节点，将它的数据存入列表中
                node = queue.poll();
                list.add(node.val);
                // 如果他的孩子节点不为空，就放入队列中，先左孩子，后右孩子
                if(node.left != null){
                    queue.offer(node.left);
                }
                if(node.right != null){
                    queue.offer(node.right);
                }
            }
            // 将当前行的数据存入结果中
            res.add(new ArrayList<>(list));
            // 清除掉当前行的数据，准备存储下一行的数据
            list.clear();
            // 把dummy从队头取出来，放到队尾去
            node = queue.poll();
            queue.offer(node);
        }
        return res;
    }

    // 这是官方的解法之一，感觉比较标准
    public List<List<Integer>> levelOrderLeetCode(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        if (root == null) {
            return ret;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<Integer>();
            int currentLevelSize = queue.size();
            for (int i = 1; i <= currentLevelSize; ++i) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            ret.add(level);
        }
        return ret;
    }



}
