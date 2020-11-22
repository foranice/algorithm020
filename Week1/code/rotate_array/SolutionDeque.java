package code.rotate_array;

import java.util.*;

public class SolutionDeque {
    public void rotate(int[] nums, int k) {
        if(nums.length == 0) return ;
        Deque<Integer> queue = new ArrayDeque<Integer>();
        for(int i:nums){
            queue.addLast(i);
        }
        while(k > 0){
            queue.offerFirst(queue.removeLast());
        }
        nums = queue.stream().mapToInt(Integer::valueOf).toArray();
    }
}
