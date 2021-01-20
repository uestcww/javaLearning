package LeetCode.solving;

import java.util.*;

public class No1356 {
    public int[] sortByBits(int[] arr) {
        quickBitSort(arr, 0, arr.length-1);
        return arr;
    }
    public void quickBitSort(int[] array, int i, int j){
        if(i >= j){
            return;
        }
        int left = i, right = j;
        int temp = array[left];
        while(left < right){
            while(left < right && compare(array[right], temp)){
                --right;
            }
            array[left] = array[right];
            while(left < right && compare(temp, array[left])){
                ++left;
            }
            array[right] = array[left];
        }
        array[left] = temp;
        quickSort(array, i, left-1);
        quickSort(array, right+1, j);
    }
    // 返回 a 是否排在 b 的后面
    public boolean compare(int a, int b){
        int aNum = countOne(a);
        int bNum = countOne(b);
        return aNum > bNum || (aNum == bNum && a >= b);
    }
    public int countOne(int num){
        int count = 0;
        while(num != 0){
            if((num & 1) == 1){
                ++count;
            }
            num = num >> 1;
        }
        return count;
    }
    public static void main(String[] args) {
        No1356 obj = new No1356();
        int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8};
        System.out.println("0, 1, 2, 4, 8, 3, 5, 6, 7, ");
        obj.sortByBits(arr);
        for(int x : arr){
            System.out.print(x + ", ");
        }
    }
    public void quickSort(int[] array, int i, int j){
        if(i >= j){
            return;
        }
        int left = i, right = j;
        int temp = array[left];
        while(left < right){
            while(left < right && array[right] >= temp){
                --right;
            }
            array[left] = array[right];
            while(left < right && array[left] <= temp){
                ++left;
            }
            array[right] = array[left];
        }
        array[left] = temp;
        quickSort(array, i, left-1);
        quickSort(array, right+1, j);
    }
    public int[] sortByBitsLeetCode(int[] arr) {
        int[] map = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            map[i] = Integer.bitCount(arr[i]) * 10000000 + arr[i];
        }
        Arrays.sort(map);
        for (int i = 0; i < map.length; i++) {
            map[i] = map[i] % 10000000;
        }
        return map;
    }
    public int[] sortByBitsLeetCode2(int[] arr) {
        int[] bit = new int[10001];
        List<Integer> list = new ArrayList<Integer>();
        for (int x : arr) {
            list.add(x);
            bit[x] = get(x);
        }
        Collections.sort(list, new Comparator<Integer>() {
            public int compare(Integer x, Integer y) {
                if (bit[x] != bit[y]) {
                    return bit[x] - bit[y];
                } else {
                    return x - y;
                }
            }
        });
        for (int i = 0; i < arr.length; ++i) {
            arr[i] = list.get(i);
        }
        return arr;
    }
    public int get(int x) {
        int res = 0;
        while (x != 0) {
            res += x % 2;
            x /= 2;
        }
        return res;
    }
    public int[] sortByBitsLeetCode3(int[] arr) {
        List<Integer> list = new ArrayList<Integer>();
        for (int x : arr) {
            list.add(x);
        }
        int[] bit = new int[10001];
        for (int i = 1; i <= 10000; ++i) {
            bit[i] = bit[i >> 1] + (i & 1);
        }
        Collections.sort(list, new Comparator<Integer>() {
            public int compare(Integer x, Integer y) {
                if (bit[x] != bit[y]) {
                    return bit[x] - bit[y];
                } else {
                    return x - y;
                }
            }
        });
        for (int i = 0; i < arr.length; ++i) {
            arr[i] = list.get(i);
        }
        return arr;
    }
}
