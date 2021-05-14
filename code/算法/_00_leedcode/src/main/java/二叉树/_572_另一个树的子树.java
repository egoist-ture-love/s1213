package 二叉树;

/**
 * https://leetcode-cn.com/problems/subtree-of-another-tree/
 */
public class _572_另一个树的子树 {
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
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if(s == null)  return false;
        if(sameTree(s,t)){
            return true;
        }
       return isSubtree(s.left,t) || isSubtree(s.right,t);
    }
    public boolean sameTree(TreeNode s,TreeNode t){
        if(s == null && t == null) return true;
        if(s == null || t == null) return false;
        return s.val == t.val && sameTree(s.right,t.right) && sameTree(s.left,t.left);
    }
}
