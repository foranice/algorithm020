package code.move_zeroes;

public class Solution {
    public void moveZeroes(int[] nums) {
        int countZero = 0;
        for(int i = 0;i < nums.length;i++) {
            if(nums[i] == 0){
                countZero++;
            }
            else if(countZero > 0){
                nums[i-countZero] = nums[i];
                nums[i] = 0;
            }
        }
    }
}
