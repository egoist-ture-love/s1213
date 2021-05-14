package 二叉树;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class _94_二叉树的中序遍历 {
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
    public List<Integer> inorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> list = new ArrayList<>();
        if(root == null) return list;
        TreeNode curt = root;
        while(curt != null || !stack.isEmpty()){
            while (curt!= null){
                stack.push(curt);
                curt = curt.left;
            }
            TreeNode node= stack.pop();
            list.add(node.val);
            curt = node.right;
        }
        return list;
    }
}
