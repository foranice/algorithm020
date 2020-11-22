package code.trapping_rain_water;

import java.util.ArrayList;
import java.util.List;

public class SolutionSumColumn {
    public int trap(int[] height) {
        int level = 1;
        int waterCount = 0;
        while(true){
            List<Integer> trapIndexs =new ArrayList<>();
            for (int i = 0;i < height.length;i++){
                if(height[i] >= level){
                    trapIndexs.add(i);
                }
            }
            if (trapIndexs.size() == 1 || trapIndexs.size() == 0){
                break;
            }
            for (int i = 0;i < trapIndexs.size() - 1;i++){
                waterCount += trapIndexs.get(i + 1)-trapIndexs.get(i)-1;
            }
            level++;
        }
        return waterCount;
    }
}
