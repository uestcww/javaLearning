package DataStructure.Sort;

import java.util.Arrays;

/*
* 希尔排序也是一种插入排序，它是简单插入排序经过改进之后的一个更高效的版本，也称为缩小增量排序
* */
public class ShellSortTest {

//    [8, 9, 1, 7, 2, 3, 5, 4, 6, 0]
//    [3, 5, 1, 6, 0, 8, 9, 4, 7, 2]
//    [0, 2, 1, 4, 3, 5, 7, 6, 9, 8]
//    [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]

    // 交换法
    public static void shellSort0(int[] arr){
        int temp = 0;
        for(int gap = arr.length / 2; gap > 0; gap /= 2){
            for(int i = gap; i < arr.length; ++i){
                for(int j = i - gap; j >= 0; j -= gap){
                    if(arr[j] > arr[j + gap]){
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
        }
    }

    // 移位法
    public static void shellSort(int[] arr){
        for(int gap = arr.length / 2; gap > 0; gap /= 2){
            for(int i = gap; i < arr.length; ++i){
                int j = i;
                int temp = arr[j];
                if(arr[j] < arr[j-gap]){
                    while(j - gap >= 0 && temp < arr[j-gap]){
                        arr[j] = arr[j-gap];
                        j-=gap;
                    }
                    arr[j] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        shellSort(arr);
        System.out.println(Arrays.toString(arr));
    }

}
