package 二叉树;

public class _108_将有序数组转换为二叉搜索树 {
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

    public TreeNode sortedArrayToBST(int[] nums) {
        return dfs(nums,0,nums.length - 1);
    }

    public TreeNode dfs(int[] nums, int start, int end) {
        if (start > end) return null;
        int center = (start + end) / 2;
        TreeNode treeNode = new TreeNode(nums[center]);
        treeNode.left = dfs(nums,start,center-1);
        treeNode.right = dfs(nums,center+1,end);
        return treeNode;

    }
}
