package 二叉树;

import java.util.ArrayList;

public class _501_二叉搜索树中的众数 {
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
    int tempNum = 0, maxNum = 0;
    ArrayList<Integer> list = new ArrayList<>();
    TreeNode pre = null;
    public int[] findMode(TreeNode root) {
        inorder(root);
        int[] arr = new int[list.size()];
        for(int i = 0; i < list.size(); i++){
            arr[i] = list.get(i);
        }
        return arr;
    }
    private void inorder(TreeNode root){
        if(root == null)return;
        inorder(root.left);
        if(pre != null && pre.val == root.val){
            tempNum++;
        }else {
            tempNum = 0;
        }
        if(maxNum == tempNum){
            list.add(root.val);
        }else if(tempNum > maxNum){
            maxNum = tempNum;
            list.clear();
            list.add(root.val);
        }
        pre = root;
        inorder(root.right);
    }
}
