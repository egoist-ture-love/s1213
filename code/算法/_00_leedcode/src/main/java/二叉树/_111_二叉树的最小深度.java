package 二叉树;

public class _111_二叉树的最小深度 {
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
    public int minDepth(TreeNode root) {
        if(root == null) return 0;
        if(root.right == null && root.left == null) return 1;
        int anx = Integer.MAX_VALUE;
        if(root.left != null){
            anx = Math.min(minDepth(root.left),anx);
        }
        if(root.right != null){
            anx = Math.min(minDepth(root.right),anx);
        }
        return anx + 1;
    }
}
