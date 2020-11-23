## 心得体会
1.注意中间状态和最终状态的关系，如果能直接到达最终状态显然可以极大的降低算法的成本。比如直接推导出通项公式。
2.对于暴力算法复杂度超过$O(n^2)$的题目，可以考虑是否可以先排序，因为排序的时间复杂度为O$(nlogn)$。
3.动态规划其实是一种高级暴力算法，遇到暴力算法时间复杂度较高时候第一时间想到的就应该是有没有重复计算，能不能记忆化，能不能自底向上迭代。
4.链表题目很多时候添加一个空的头结点会方便很多。
5.双指针甚至多指针也是一种记忆化,很多时候可以对空间复杂度进行进一步优化
##作业部分
### 删除排序数组中的重复项
一次遍历，O(n)复杂度,比较简单
```java
public class SolutionDoublePoint {
    public int removeDuplicates(int[] nums) {
        int pos = 0;
        for (int i = 0;i < nums.length ; i++){
            //如果数据元素变化了，将其直接移动到合适的位置
            if(nums[i] > nums[pos]) {
                nums[pos++] = nums[i];
            }
        }
        return pos+1;
    }
}

```
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
