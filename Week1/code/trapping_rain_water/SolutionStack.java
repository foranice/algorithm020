package code.trapping_rain_water;

import java.util.Deque;
import java.util.LinkedList;

public class SolutionStack {
    public int trap(int[] height) {
        if(height.length <= 2) return 0;
        int sum = 0;
        Deque<Integer> stack = new LinkedList<>();
        stack.push(0);
        for( int i = 1;i < height.length; i++){
            if(height[i] < height[stack.peek()]){
                stack.push(i);
            }
            else if(height[i] == height[stack.peek()]){
                stack.pop();
                stack.push(i);
            }
            else{
                while(!stack.isEmpty() && height[i] > height[stack.peek()]){
                    int bottom = stack.pop();
                    if(!stack.isEmpty()){
                        int rainHeight = Math.min(height[i],height[stack.peek()])-height[bottom];
                        int rainWidth = i - stack.peek() - 1;
                        sum += rainHeight*rainWidth;
                    }
                }
                stack.push(i);
            }
        }
        return sum;
    }
}
