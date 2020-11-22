package code.remove_duplicates_from_sorted_array;
public class SolutionDoublePoint {
    public int removeDuplicates(int[] nums) {
        int pos = 0;
        for (int i = 0;i < nums.length ; i++){
            if(nums[i] > nums[pos]) {
                nums[pos++] = nums[i];
            }
        }
        return pos+1;
    }
}
