package code.binary_tree_preorder_traversal;
import java.util.*;

public class SolutionRecursion {
    List<Integer> result = new ArrayList();
    public List<Integer> preorderTraversal(TreeNode root) {
        visit(root);
        return result;
    }
    public void visit(TreeNode node){
        if(node == null) return;
        result.add(node.val);
        visit(node.left);
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
