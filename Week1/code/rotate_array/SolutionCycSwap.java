package code.rotate_array;
public class SolutionCycSwap {
    public void rotate(int[] nums, int k) {
        if (nums.length == 0) return;
        int count = 0;
        for(int i = 0; count < nums.length; i++){
            int pos = i;
            int temp = nums[i];
            while(true) {
                int target = (pos + k) % nums.length;
                int targetValue = nums [target];
                nums[target] = temp;
                temp = targetValue;
                count++;
                if(target == i) break;
                pos = target;
            }
        }

    }
}
