package code.trapping_rain_water;

public class SolutionDp {
    public int trap(int[] height) {
        if(height.length == 0) return 0;
        int sum = 0;
        int[] max_left = new int[height.length];
        int[] max_right = new int[height.length];
        max_left[0] = height[0];
        max_right[height.length - 1] = height[height.length - 1];
        for( int i = 1;i < height.length - 1;i++){
            max_left[i] = Math.max(max_left[i-1], height[i]);
        }
        for( int i = height.length - 2;i > 0;i--){
            max_right[i] = Math.max(max_right[i+1], height[i]);
        }
        for(int i = 1;i < height.length - 1;i++){
            int min = Math.min(max_left[i],max_right[i]);
            if(min > height[i]){
                sum += min - height[i];
            }
        }
        return sum;
    }
}
