package 二叉树;

import java.util.ArrayList;
import java.util.List;

public class _230_二叉搜索树中第K小的元素 {
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
    static List<Integer> list = new ArrayList<>();
    public int kthSmallest(TreeNode root, int k) {
        inorderTraversal(root);
        return list.get(k - 1);
    }
    public void inorderTraversal(TreeNode root){
        if(root == null) return;
        inorderTraversal(root.left);
        list.add(root.val);
        inorderTraversal(root.right);
    }
}
