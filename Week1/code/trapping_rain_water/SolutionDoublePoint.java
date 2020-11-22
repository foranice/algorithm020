package code.trapping_rain_water;

public class SolutionDoublePoint {
    class Solution {
        public int trap(int[] height) {
            if(height.length < 2) return 0;
            int sum = 0;
            int max_left = 0;
            int max_right = 0;
            int left = 0;
            int right = height.length - 1;
            while( left <= right){
                if(max_left <= max_right){
                    if(max_left > height[left]){
                        sum += (max_left-height[left]);
                    }
                    max_left = Math.max(max_left,height[left]);
                    left++;
                }
                else{
                    if(max_right > height[right]){
                        sum += (max_right - height[right]) ;
                    }
                    max_right = Math.max(max_right, height[right]);
                    right--;
                }
            }

            return sum;
        }
    }
}
