package code.two_sum;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        Map<Integer,Integer> history = new HashMap<>();
        for(int i = 0;i < nums.length;i++) {
            int remain = target - nums[i];
            if(history.containsKey(nums[i])){
                result[1] = i;
                result[0] = history.get(nums[i]);
                return result;
            }
            else {
                history.put(remain,i);
            }
        }
        return result;
    }
}
