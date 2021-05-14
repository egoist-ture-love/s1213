package 二叉树;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/average-of-levels-in-binary-tree/submissions/
 */
public class _637_二叉树的层平均值 {
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

        public List<Double> averageOfLevels(TreeNode root) {
            List<Double> list = new ArrayList<>();
            if (root == null) return list;
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            int levelSize = 1;
            int i = 0;
            double sum = 0;

            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                levelSize--;
                i++;
                sum = sum + node.val;
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                if (levelSize == 0) {
                    levelSize = queue.size();
                    Double j = sum / i;
                    list.add(j);
                    i = 0;
                    sum = 0;
                }
            }
            return list;
        }

    }
}
