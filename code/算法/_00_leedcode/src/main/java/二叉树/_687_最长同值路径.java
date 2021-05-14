package 二叉树;

import java.util.Map;

/**
 * https://leetcode-cn.com/problems/longest-univalue-path/
 */
public class _687_最长同值路径 {
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
    int ans = 0;
    public int longestUnivaluePath(TreeNode root) {
        if(root == null) return 0;
        help(root);
        return ans;
    }
    public int help(TreeNode root){
        if(root == null) return 0;
        int left = help(root.left);
        int right = help(root.right);
        if(root.left != null && root.left.val == root.val){
            left++;
        }else {
            left = 0;
        }
        if(root.right != null && root.right.val == root.val){
            right++;
        }else {
            right = 0;
        }
        ans = Math.max(ans,left+right);
        return Math.max(left,right);
    }
}
