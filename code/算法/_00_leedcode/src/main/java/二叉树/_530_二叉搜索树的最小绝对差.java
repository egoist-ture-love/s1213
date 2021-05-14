package 二叉树;

public class _530_二叉搜索树的最小绝对差 {
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
    private int max = Integer.MAX_VALUE;
    private TreeNode pre = null;
    public int getMinimumDifference(TreeNode root) {
        inOrder(root);
        return  max;
    }
    private void inOrder(TreeNode root){
        if(root == null) return;
        inOrder(root.left);
        if(pre != null) max = Math.min(max,root.val - pre.val);
        pre = root;
        inOrder(root.right);
    }
}
