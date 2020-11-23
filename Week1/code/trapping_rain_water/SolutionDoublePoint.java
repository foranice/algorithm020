package code.trapping_rain_water;

public class SolutionDoublePoint {
    class Solution {
        public int trap(int[] height) {
            if(height.length < 2) return 0;
            int sum = 0;
            int maxLeftCurrent = 0;
            int maxRightCurrent = 0;
            int left = 0;
            int right = height.length - 1;
            while( left <= right){
                if(maxLeftCurrent <= maxRightCurrent){
                    if(maxLeftCurrent > height[left]){
                        sum += (maxLeftCurrent-height[left]);
                    }
                    maxLeftCurrent = Math.max(maxLeftCurrent,height[left]);
                    left++;
                }
                else{
                    if(maxRightCurrent > height[right]){
                        sum += (maxRightCurrent - height[right]) ;
                    }
                    maxRightCurrent = Math.max(maxRightCurrent, height[right]);
                    right--;
                }
            }

            return sum;
        }
    }
}
