package 二叉树;

public class _235_二叉搜索树的最近公共祖先 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root.val > p.val && root.val > q.val) return lowestCommonAncestor(root.left,q,p);
        if(root.val < p.val && root.val < q.val) return lowestCommonAncestor(root.right,q,p);
        return root;
    }
}
