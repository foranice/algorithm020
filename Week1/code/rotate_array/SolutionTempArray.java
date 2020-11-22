package code.rotate_array;
public class SolutionTempArray {
    public void rotate(int[] nums, int k) {
        if(nums.length == 0) return;
        k = k % nums.length;
        int[] temp = new int[k];
        for(int i = 0;i < k;i++) {
            temp[i] = nums[nums.length-k+i];
        }
        for(int i = nums.length-k-1;i >= 0 ;i--) {
            nums[i+k] = nums[i];
        }
        for(int i = 0;i < k;i++){
            nums[i] = temp[i];
        }
    }
}
