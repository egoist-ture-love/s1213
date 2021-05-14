package 二叉树;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/path-sum/
 */
public class _112_路径总和 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if(root == null) return false;
        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<Integer> queue2 = new LinkedList<>();
        queue1.offer(root);
        queue2.offer(root.val);
        while(!queue1.isEmpty()){
            TreeNode temp =queue1.poll();
            Integer val1 = queue2.poll();
            if(temp.right == null && temp.left == null){
                if(val1 == targetSum) return true;
                continue;
            }
            if(temp.left != null){
                queue1.offer(temp.left);
                Integer t = temp.left.val;
                queue2.offer(t + val1);
            }
            if(temp.right != null){
                queue1.offer(temp.right);
                Integer t = temp.right.val;
                queue2.offer(t + val1);
            }

        }
        return false;
    }
}
