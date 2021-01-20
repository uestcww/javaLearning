package LeetCode.resolved.tree;

import LeetCode.utils.TreeNode;

import java.util.*;

public class No101 {

    // 结果差的很。。。不过总算做出来了，费了老鼻子劲了
    public boolean isSymmetric(TreeNode root) {
        if(root == null){
            return true;
        }
        // 双端队列保存节点，一行一行的保存
        Deque<TreeNode> queue = new LinkedList<>();
        // 列表保存数据，每遍历一行，就遍历一次列表，看看对称么
        List<Integer> nums = new ArrayList<>();
        // 占位节点，用于知道什么时候换行
        TreeNode dummy = new TreeNode(-1);
        queue.offer(root);
        queue.offer(dummy);
        nums.add(root.val);
        TreeNode node;
        int left, right;
        // 其实这就是个层序遍历
        // 队列中只剩dummy了，说明结束了，退出循环
        while(queue.peek() != dummy){
            // 这是遍历每一行，遍历结束后把dummy从头取出，再放入尾部
            while(queue.peek() != dummy){
                // 把null也存进去，这样才能展示一行中的数据的对称度，要用null去占位
                node = queue.poll();
                if(node == null){
                    nums.add(null);
                    continue;
                }
                nums.add(node.val);
                // 存放节点的孩子，这就是下一行了，不过现在他们的前面还有一个dummy
                queue.offer(node.left);
                queue.offer(node.right);
            }
            // 检查这一行的数据是否对称
            left = 0;
            right = nums.size()-1;
            while(left < right){
                // 如果左右不相等，直接就是一个不对称
                if(nums.get(left++) != nums.get(right--)){
                    return false;
                }
            }
            // 最后剩一个也不行啊，二叉树，都是一对一对的
            if(left == right){
                return false;
            }
            // 清楚数据，为下一行数据的存入做准备
            nums.clear();
            // 将dummy节点取出来，然后存入队尾
            node = queue.poll();
            queue.offer(node);
        }
        return true;
    }

    // 看了官方解答，我傻了，我是傻逼，我是个木头脑袋吗？？？
    // 核心：一个树镜像，等于根节点的两个子树互为镜像；两颗树互为镜像，等于一树的左孩子与二树的右孩子互为镜像 && 一树的右孩子与二树的左孩子互为镜像
    // 然后就可以递归了。。。。众所周知，能递归，那大概率也能迭代了。。。
    // 我是傻逼
    // 这么简单的一道题，我半天没有头绪，吭哧吭哧写出来，结果差的一批，仔细看看官方如何解题，原来如此容易
    // 但是我想破头皮，也没有领会到此题的核心用意，我就是个弟弟，麻痹

    public static void main(String[] args) {
        No101 obj = new No101();
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(2);
        TreeNode node4 = new TreeNode(3);
        TreeNode node5 = new TreeNode(4);
        TreeNode node6 = new TreeNode(4);
        TreeNode node7 = new TreeNode(3);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        System.out.println("true ? " + obj.isSymmetric(node1));
        node2.left = null;
        node2.right = node4;
        node3.left = null;
        node3.right = node7;
        System.out.println("false ? " + obj.isSymmetric(node1));

//        TreeNode node1 = new TreeNode(2);
//        TreeNode node2 = new TreeNode(3);
//        TreeNode node3 = new TreeNode(3);
//        TreeNode node4 = new TreeNode(4);
//        TreeNode node5 = new TreeNode(4);
//        TreeNode node6 = new TreeNode(5);
//        TreeNode node7 = new TreeNode(5);
//        TreeNode node8 = new TreeNode(6);
//        TreeNode node9 = new TreeNode(6);
//        TreeNode node10 = new TreeNode(7);
//        TreeNode node11 = new TreeNode(8);
//        TreeNode node12 = new TreeNode(8);
//        TreeNode node13 = new TreeNode(7);
//        TreeNode node14 = new TreeNode(9);
//        TreeNode node15 = new TreeNode(0);
//        TreeNode node16 = new TreeNode(0);
//        TreeNode node17 = new TreeNode(1);
//        TreeNode node18 = new TreeNode(1);
//        TreeNode node19 = new TreeNode(0);
//        TreeNode node20 = new TreeNode(0);
//        TreeNode node21 = new TreeNode(9);
//        node1.left = node2;
//        node1.right = node3;
//        node2.left = node4;
//        node3.right = node5;
//        node4.right = node6;
//        node5.left = node7;
//        node6.right = node8;
//        node7.left = node9;
//        node8.left = node10;
//        node8.right = node11;
//        node9.left = node12;
//        node9.right = node13;
//        node10.left = node14;
//        node10.right = node15;
//        node11.left = node16;
//        node11.right = node17;
//        node12.left = node18;
//        node12.right = node19;
//        node13.left = node20;
//        node13.right = node21;
//        System.out.println("true ? " + obj.isSymmetric(node1));
    }
}
