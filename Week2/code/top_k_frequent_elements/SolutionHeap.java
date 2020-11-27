package code.top_k_frequent_elements;
import java.util.*;
public class SolutionHeap {
    class Solution {
        public int[] topKFrequent(int[] nums, int k) {
            Map<Integer,Freq> map = new HashMap<>(nums.length);
            for(int num : nums){
                if(map.containsKey(num)){
                    Freq currentFreq = map.get(num);
                    currentFreq.inc();
                }
                else{
                    Freq currentFreq = new Freq(num);
                    map.put(num,currentFreq);
                }
            }
            PriorityQueue<Freq> freqQueue = new PriorityQueue<>(k);
            for (Map.Entry<Integer,Freq> entry : map.entrySet()){
                if(freqQueue.size()<k){
                    freqQueue.add(entry.getValue());
                }
                else if(entry.getValue().freq > freqQueue.peek().freq){
                    freqQueue.poll();
                    freqQueue.add(entry.getValue());
                }
            }
            int[] result = new int[k];
            for(int i = 0;i < k;i++){
                result [i] = freqQueue.poll().key;
            }
            return result;
        }
        public class Freq implements Comparable<Freq>{
            int freq;
            int key;
            Freq(int key){
                this.key = key;
                this.freq = 1;
            }
            public void inc(){
                this.freq++;
            }
            @Override
            public int compareTo(Freq o) {
                return this.freq - o.freq;
            }
        }
    }
}
