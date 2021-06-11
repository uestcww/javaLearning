package LeetCode.solving;

import java.util.*;

// 以下注释为提示
/*
* 这种题其实很明显可以使用优先队列，我该练习一下关于优先队列的题
* */
public class No692 {

    // 哈希表加排序，最普通的解法
    public List<String> topKFrequent0(String[] words, int k){
        HashMap<String, Integer> hashMap = new HashMap<>();
        for(String word : words){
            hashMap.put(word, hashMap.getOrDefault(word, 0) + 1);
        }
        List<String> list = new ArrayList<>();
        for(Map.Entry<String, Integer> entry : hashMap.entrySet()){
            list.add(entry.getKey());
        }
        list.sort((str1, str2) -> hashMap.get(str1).equals(hashMap.get(str2)) ? str1.compareTo(str2) : hashMap.get(str2) - hashMap.get(str1));
        return list.subList(0, k);
    }

    // 不是哈希表加排序的思路，而是？？？
//    public List<String> topKFrequent(String[] words, int k) {
//
//    }

    public static void main(String[] args) {

    }

}
