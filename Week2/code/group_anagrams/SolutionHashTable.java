package code.group_anagrams;
import java.util.*;
public class SolutionHashTable {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String,List<String>> map = new HashMap(strs.length);
        for(String str : strs){
            char[] alphaCount = new char[26];
            for(char c : str.toCharArray()){
                alphaCount[c-'a']++;
            }
            String hashKey = String.valueOf(alphaCount);
            if(!map.containsKey(hashKey)){
                map.put(hashKey,new ArrayList<String>());
            }
            List list = map.get(hashKey);
            list.add(str);
        }
        return new ArrayList(map.values());
    }
}
