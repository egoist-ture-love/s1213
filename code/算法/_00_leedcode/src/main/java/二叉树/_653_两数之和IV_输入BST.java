package 二叉树;

import java.util.ArrayList;
import java.util.List;

public class _653_两数之和IV_输入BST {
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
    public boolean findTarget(TreeNode root, int k) {
        inorderTraversal(root);
        return isSame(list,k);
    }
    public void inorderTraversal(TreeNode root){
        if(root == null) return;
        inorderTraversal(root.left);
        list.add(root.val);
        inorderTraversal(root.right);
    }
    public boolean isSame(List<Integer> list,int k){
        int i = 0, j = list.size() - 1;
        while (i < j){
            int sum = list.get(i) + list.get(j);
            if(sum == k){
                return  true;
            }else if(sum < k){
                i++;
            }else {
                j--;
            }
        }
        return false;
    }
}
