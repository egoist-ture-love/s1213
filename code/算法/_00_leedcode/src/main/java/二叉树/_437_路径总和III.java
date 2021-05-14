package 二叉树;

public class _437_路径总和III {
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
    public int pathSum(TreeNode root, int sum) {
        //1.双循环
        if (root == null) {
            return 0;
        }
        int num1 = count(root, sum);
        int num2 = pathSum(root.left, sum);
        int num3 = pathSum(root.right, sum);
        return num1 + num2 + num3;
    }

    private int count(TreeNode root, int sum) {
        if(root == null) return 0;
        sum = sum - root.val;
        int result = sum == 0 ? 1 : 0;
        return result + count(root.left,sum)+count(root.right,sum);
    }
}
