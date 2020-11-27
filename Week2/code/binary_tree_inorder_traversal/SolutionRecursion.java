package code.binary_tree_inorder_traversal;

import java.util.ArrayList;
import java.util.List;

public class SolutionRecursion {
    List<Integer> result = new ArrayList();

    public List<Integer> inorderTraversal(TreeNode root) {
        visit(root);
        return result;
    }

    public void visit(TreeNode node) {
        if (node == null) return;
        visit(node.left);
        result.add(node.val);
        visit(node.right);
    }

    private class TreeNode {
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

}
