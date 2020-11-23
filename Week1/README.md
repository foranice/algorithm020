## 心得体会
1.注意中间状态和最终状态的关系，如果能直接到达最终状态显然可以极大的降低算法的成本。比如直接推导出通项公式。
2.对于暴力算法复杂度超过$O(n^2)$的题目，可以考虑是否可以先排序，因为排序的时间复杂度为O$(nlogn)$。
3.动态规划其实是一种高级暴力算法，遇到暴力算法时间复杂度较高时候第一时间想到的就应该是有没有重复计算，能不能记忆化，能不能自底向上迭代。
4.链表题目很多时候添加一个空的头结点会方便很多。
5.双指针甚至多指针也是一种记忆化,很多时候可以对空间复杂度进行进一步优化
##作业部分
### 旋转数组
#### 1.临时数组
使用一个临时数组存储，时间复杂度$O(n)$,空间复杂度$O(n)$
但是这个代码比较清晰，实际工程可以使用这种方式
```java
public class SolutionTempArray {
    public void rotate(int[] nums, int k) {
        if(nums.length == 0) return;
        //这个取余数是一个关键点
        k = k % nums.length;
        int[] temp = new int[k];
        //将前k个元素储存在临时数组
        for(int i = 0;i < k;i++) {
            temp[i] = nums[nums.length-k+i];
        }
        //将最后k个元素移动到前面
        for(int i = nums.length - k - 1;i >= 0 ;i--) {
            nums[i+k] = nums[i];
        }
        //移动临时数组到最后k个
        for(int i = 0;i < k;i++){
            nums[i] = temp[i];
        }
    }
}

```
#### 2.三次反转
标准解法，$O(n)$复杂度，只是不太好想,非常巧妙，是一个非常不错的解法！
```java
public class SolutionReverse {
    public void rotate(int[] nums, int k) {
        k = k % nums.length;
        reverse(nums, 0, nums.length-1);
        reverse(nums, 0, k-1);
        reverse(nums, k, nums.length-1);
    }
    void reverse(int[] arr, int start, int end){
        while(start < end) {
            int temp = arr[start];
            arr[start++] = arr[end];
            arr[end--] = temp;
        }
    }
}

```
#### 3.双端队列
用一个双端队列来进行移动
时间复杂度也是$O(n)$，虽然使用了辅助队列造成了$O(n)$的空间复杂度，但是在工程中这种问题明显**在最开始建模的时候就应该使用双端队列而不是数组**
```java
import java.util.*;

public class SolutionDeque {
    if(nums.length == 0) return ;
        Deque<Integer> queue = new LinkedList<Integer>();
        for(int i:nums){
            queue.addLast(i);
        }
        while(k > 0){
            queue.offerFirst(queue.removeLast());
            k--;
        }
        for(int i =0; i < nums.length;i++){
            nums[i] = queue.pollFirst();
        }
    
}

```
####4.循环置换
另外一种原地算法，作为一种正确性还需要证明一下的算法，可读性实在是堪忧，时间和空间复杂度没有明显的优势，不推荐使用。
```java
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

```
###接雨水
非常不错的题目啊
####1.按行计算
假设height数组的最大值为m，则时间复杂度为$O(m*n)$。
乍看上去和按列计算没多大区别,但
n == height.length
0 <= n <= 3 * 104
0 <= height[i] <= 105
m的值更大。按行计算无法通过最后一个测试用例，超出时间限制
```java
class Solution {
        public int trap(int[] height) {
            int level = 1;
            int waterCount = 0;
            while(true){
                List<Integer> trapIndexs=new ArrayList<>();
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
```
####2.按列计算
时间复杂度为$O(n^2)$,按列计算不仅时间复杂度略低于按行计算。而且可以进行多次优化！
其根本原因在于:**height数组是按列分布的，根据位置找高度是$O(1)$复杂度，而根据高度查位置却是$O(n)$,牛排横切和纵切的难度自然是不同的！**
```java
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
```
####3.动态规划
按列计算的算法中，每次去左边最高和右边最高的都要重新遍历一边数组，
我们将计算结果储存在数组之中，这样就可以避免一些重复遍历。但是本题有一个特殊之处，就是max_right[]需要从后向前计算。用了三次遍历，但是没有嵌套，时间复杂度为$O(n)$，使用了两个辅助数组，空间复杂度为$O(n)$。
```java
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
            //这是一个关键点:
            //1.某处可以接雨水需要左右均有比当前节点高的柱子
            //2.接雨水的量取决于较低的柱子
            int min = Math.min(max_left[i],max_right[i]);
            if(min > height[i]){
                sum += min - height[i];
            }
        }
        return sum;
    }
}
```
####4.双指针
我们发现对于每一个max_left[n]和max_right[n]都是用过两次：
1.求max_left[n+1]或者max_right[n-1]
2.求接雨水的数量
这说明DP算法的空间复杂度还有优化成常数的余地。
这个算法理解的关键就是：
1.maxLeftCurrent是从左到右更新，而maxRightCurrent是从又到左更新
2.未更新部分的区间为(left,right),在这个范围内,因为是求最大值么,maxLeftCurrent和maxRightCurrent的值只可能比当前大，不可能比当前小。也就是如果n在区间(left,right)内，有maxLeftCurrent <= maxLeft[n] 和 maxRight[n]
3.对于left节点maxLeftCurrent是等效与上面的max_left，而maxRightCurrent则不一定。反之亦然。
4.如果maxLeftCurrent <= maxRightCurrent,那么根据 maxRightCurrent <= maxRight[n],根据不等式的传递性 maxLeftCurrent <= maxRight[n];反之maxLeftCurrent > maxRightCurrent则有maxRightCurrent >= maxLeft[n];
5.根据上面算法,我们需要知道的是maxLeft和maxRight的最小值，而不是一定要直接确定这两个值那么根据4的结论,maxLeftCurrent <= maxRightCurrent时，可以根据maxLeftCurrent处理left节点,反之处理right节点。


```java
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

```
####5.栈
反正我是想不出这方法...
这个方法计算的是两根匹配柱子之间的水，一次计算一个矩形。
https://leetcode-cn.com/problems/trapping-rain-water/solution/dan-diao-zhan-jie-jue-jie-yu-shui-wen-ti-by-sweeti/
说不清楚，直接粘贴别人题解

```java
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
```