package code.n_ary_tree_level_order_traversal;
import java.util.*;
public class SolutionQueue {
    class Solution {
        public List<List<Integer>> levelOrder(Node root) {
            Queue<Node> queue = new LinkedList();
            int levelCount = 1;
            int nextLevelCount = 0;
            queue.offer(root);
            List<List<Integer>> result = new ArrayList();
            if(root == null) return result;
            List<Integer> level = new ArrayList(levelCount);
            while(!queue.isEmpty()){
                Node current = queue.poll();
                level.add(current.val);
                if(current.children != null){
                    for(Node n : current.children){
                        queue.offer(n);
                    }
                    nextLevelCount += current.children.size();
                }
                levelCount--;
                if(levelCount == 0) {
                    result.add(level);
                    levelCount = nextLevelCount;
                    level = new ArrayList(levelCount);
                    nextLevelCount = 0;
                }
            }
            return result;
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
