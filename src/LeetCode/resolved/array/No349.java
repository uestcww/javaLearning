package LeetCode.resolved.array;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class No349 {

    // 看了LeetCode的官方解法之后，觉得使用更多的使用set，可以变为一层循环
    // 暴力两层循环，加了set
    public int[] intersection(int[] nums1, int[] nums2) {
        // 数组有一个为空，那么返回的必定是一个空数组
        if(nums1.length < 1 || nums2.length < 1){
            return new int[0];
        }
        // 用一个set来存储结果，避免重复
        Set<Integer> set = new HashSet<Integer>();
        for(int num1 : nums1){
            // 以数组1中数据为准，如果set中已经有了这个数字，那么就跳过
            if(set.contains(num1)){
                continue;
            }
            // 如果在数组2中找到了与数组1中相同的数字
            // 那么就将这个数字加入set，并且跳出当前循环
            for(int num2 : nums2){
                if(num1 == num2){
                    set.add(num1);
                    break;
                }
            }
        }
        // 使用迭代器遍历set，构造结果int数组，返回结果
        Iterator<Integer> it = set.iterator();
        int[] arr = new int[set.size()];
        int k = 0;
        while(it.hasNext()){
            arr[k++] = it.next();
        }
        return arr;
    }

    public static void main(String[] args) {
        No349 obj = new No349();
        int[] arr1 = {1, 2, 2, 1};
        int[] arr2 = {2, 2};
        for(int i : obj.intersection(arr1, arr2)){
            System.out.print(i + ", ");
        }
        System.out.println();
        int[] arr3 = {4, 9, 5};
        int[] arr4 = {9, 4, 9, 8, 4};
        for(int j : obj.intersection(arr3, arr4)){
            System.out.print(j + ", ");
        }
    }

}
