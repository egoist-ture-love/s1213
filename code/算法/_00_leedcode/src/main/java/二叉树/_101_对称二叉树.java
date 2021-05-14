package 二叉树;

import java.lang.annotation.Target;

public class _101_对称二叉树 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public boolean isSymmetric(TreeNode root) {
        if(root == null) return true;
        return childTree(root.left,root.right);
    }
    public boolean childTree(TreeNode l, TreeNode r){
        if(l == null && r == null) return true;
        if(l == null || r == null) return false;
        if(l.val == r.val && childTree(l.left,r.right) && childTree(l.right,r.left)) return true;
        return false;
    }
}
