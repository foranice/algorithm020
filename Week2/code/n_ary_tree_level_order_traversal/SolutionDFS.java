package code.n_ary_tree_level_order_traversal;
import java.util.*;
public class SolutionDFS {
    private List<List<Integer>> result;
    public List<List<Integer>> levelOrder(Node root) {
        result = new ArrayList();
        dfs(root,0);
        return result;
    }
    private void dfs(Node node,int depth){
        if(node == null) return;
        if(result.size() < depth+1){
            result.add(new ArrayList<Integer>());
        }
        List<Integer> level = result.get(depth);
        level.add(node.val);
        for (Node child : node.children) {
            dfs(child,depth+1);
        }
    }
    private class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    };
}
