package 二叉树;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/invert-binary-tree/
 */
public class _226_翻转二叉树 {
    //本质就是遍历之后进行左右交换赋值
    //层序遍历  前序遍历 中序遍历 后序遍历
    /**
     * 利用前序遍历
     * class Solution {
     *         // 先序遍历--从顶向下交换
     *         public TreeNode invertTree(TreeNode root) {
     *             if (root == null) return null;
     *             // 保存右子树
     *             TreeNode rightTree = root.right;
     *             // 交换左右子树的位置
     *             root.right = invertTree(root.left);
     *             root.left = invertTree(rightTree);
     *             return root;
     *         }
     *     }
     *
     * 利用中序遍历
     * class Solution {
     *     public TreeNode invertTree(TreeNode root) {
     *             if (root == null) return null;
     *             invertTree(root.left); // 递归找到左节点
     *             TreeNode rightNode= root.right; // 保存右节点
     *             root.right = root.left;
     *             root.left = rightNode;
     *             // 递归找到右节点 继续交换 : 因为此时左右节点已经交换了,所以此时的右节点为root.left
     *             invertTree(root.left);
     *     }
     * }
     *
     * 利用后序遍历
     *  class Solution {
     *         public TreeNode invertTree(TreeNode root) {
     *             // 后序遍历-- 从下向上交换
     *             if (root == null) return null;
     *             TreeNode leftNode = invertTree(root.left);
     *             TreeNode rightNode = invertTree(root.right);
     *             root.right = leftNode;
     *             root.left = rightNode;
     *             return root;
     *         }
     *     }
     *
     * 利用层次遍历
     *    class Solution {
     *         public TreeNode invertTree(TreeNode root) {
     *             // 层次遍历--直接左右交换即可
     *             if (root == null) return null;
     *             Queue<TreeNode> queue = new LinkedList<>();
     *             queue.offer(root);
     *             while (!queue.isEmpty()){
     *                 TreeNode node = queue.poll();
     *                 TreeNode rightTree = node.right;
     *                 node.right = node.left;
     *                 node.left = rightTree;
     *                 if (node.left != null){
     *                     queue.offer(node.left);
     *                 }
     *                 if (node.right != null){
     *                     queue.offer(node.right);
     *                 }
     *             }
     *             return root;
     *         }
     *     }
     */
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
    public TreeNode invertTree(TreeNode root) {
        if(root == null) return null;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            TreeNode nodeRight = node.right;
            node.right = node.left;
            node.left = nodeRight;
            if(node.left != null){
                queue.offer(node.left);
            }
            if(node.right != null){
                queue.offer(node.right);
            }
        }
        return root;
    }

}
