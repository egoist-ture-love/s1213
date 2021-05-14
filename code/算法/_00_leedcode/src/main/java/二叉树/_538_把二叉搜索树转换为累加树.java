package 二叉树;

public class _538_把二叉搜索树转换为累加树 {
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
    public TreeNode convertBST(TreeNode root) {
        postorderTraversal(root);
        return root;
    }
    int sum = 0;
    public void postorderTraversal(TreeNode root){
        if(root == null)return;
        postorderTraversal(root.right);
        sum = sum + root.val;
        root.val = sum;
        postorderTraversal(root.left);
    }
}
