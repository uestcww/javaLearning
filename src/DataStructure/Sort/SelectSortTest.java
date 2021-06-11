package DataStructure.Sort;

import java.util.Arrays;
/*
* 遍历数组，找到最小的，放在最前面，再找第二小的，放第二个位置，以此类推
* */
public class SelectSortTest {

    public static void selectSort(int[] arr){
        int length = arr.length, minIndex, min;
        for(int i=0;i<length-1;++i){
            minIndex = i;
            min = arr[minIndex];
            for(int j=minIndex+1;j<length;++j){
                if(arr[j] < min){
                    min = arr[j];
                    minIndex = j;
                }
            }
            arr[minIndex] = arr[i];
            arr[i] = min;
        }
    }

    public static void main(String[] args) {
        int[] arr = {101, 34, 119, 1};
        selectSort(arr);
        System.out.println(Arrays.toString(arr));
    }

}
