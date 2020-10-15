package LeetCode.resolved.tree;

import LeetCode.utils.TreeNode;

import java.util.*;

public class No116 {

    public TreeNode connect(TreeNode root) {
        if(root == null){
            return null;
        }
        if(root.right == null || root.left == null){
            root.next = null;
            return root;
        }
        Deque<TreeNode> queue = new LinkedList<TreeNode>();
        TreeNode node;
        root.left.next = root.right;
        queue.add(root.left);
        queue.add(root.right);
        while(!queue.isEmpty()){
            node = queue.remove();
            if(node.left != null && node.right != null){
                queue.add(node.left);
                queue.add(node.right);
                node.left.next = node.right;
                node.right.next = queue.peek().left;
            }
        }
        node = root;
        while(node!=null){
            node.next = null;
            node = node.right;
        }
        return root;
    }

    //层序遍历
    public List<Integer> levelOrder(TreeNode head) {
        List<Integer> res = new ArrayList<Integer>();
        if(head == null){
            return res;
        }
        TreeNode node;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(head);
        while(!queue.isEmpty()) {
            node = queue.remove();
            res.add(node.val);
            if(node.left!=null){
                queue.offer(node.left);
            }
            if(node.right!=null){
                queue.offer(node.right);
            }
        }
        return res;
    }

    /*LeetCode官方第一种方法，层次遍历法*/
    public TreeNode connectLeetCode1(TreeNode root) {
        if (root == null) {
            return root;
        }

        // 初始化队列同时将第一层节点加入队列中，即根节点
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);

        // 外层的 while 循环迭代的是层数
        while (!queue.isEmpty()) {

            // 记录当前队列大小
            int size = queue.size();

            // 遍历这一层的所有节点
            for (int i = 0; i < size; i++) {

                // 从队首取出元素
                TreeNode node = queue.poll();

                // 连接
                if (i < size - 1) {
                    node.next = queue.peek();
                }

                // 拓展下一层节点
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }

        // 返回根节点
        return root;
    }

    /*LeetCode官方第二种方法，next指针法*/
    public TreeNode connectLeetCode2(TreeNode root) {
        if (root == null) {
            return root;
        }

        // 从根节点开始
        TreeNode leftmost = root;

        while (leftmost.left != null) {

            // 遍历这一层节点组织成的链表，为下一层的节点更新 next 指针
            TreeNode head = leftmost;

            while (head != null) {

                // CONNECTION 1
                head.left.next = head.right;

                // CONNECTION 2
                if (head.next != null) {
                    head.right.next = head.next.left;
                }

                // 指针向后移动
                head = head.next;
            }

            // 去下一层的最左的节点
            leftmost = leftmost.left;
        }

        return root;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(8);
        TreeNode node9 = new TreeNode(9);
        TreeNode node10 = new TreeNode(10);
        TreeNode node11 = new TreeNode(11);
        TreeNode node12 = new TreeNode(12);
        TreeNode node13 = new TreeNode(13);
        TreeNode node14 = new TreeNode(14);
        TreeNode node15 = new TreeNode(15);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;

        node4.left = node8;
        node4.right = node9;
        node5.left = node10;
        node5.right = node11;
        node6.left = node12;
        node6.right = node13;
        node7.left = node14;
        node7.right = node15;

        No116 obj = new No116();
        obj.connect(node1);

        node1.printAll();
    }

}
