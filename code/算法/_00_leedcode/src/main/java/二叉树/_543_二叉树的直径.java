package 二叉树;

public class _543_二叉树的直径 {
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
    int maxLength = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        /**
         * 递归求出最长的深度  之后用变量存储起来  每次变量值和其他的根左右和比较
         */
        deep(root);
        return maxLength;

    }
    public int deep(TreeNode root){
        if(root == null) return 0;
        int leftLength = deep(root.left);
        int rightLength = deep(root.right);
        maxLength = Math.max(maxLength,leftLength+rightLength);
        return 1 + Math.max(leftLength,rightLength);
    }
}
