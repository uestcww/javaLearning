package DataStructure.Sort;

import java.util.Arrays;

/*
* 假设 0 到 index-1 都排序好了， index 位置是当前需要排序的元素
* array[index] 的值保存在变量 insertValue 中
* 比较 array[index-1] 和 array[index] 如果 array[index-1] 大，就令 array[index] = array[index-1]
* --index 向前寻找适合的插入位置
* 找到合适位置后，将 insertValue 插入
* */
public class InsertSortTest {

    public static void insertSort(int[] arr){
        int length = arr.length, insertValue, insertIndex;
        for(int i=1;i<length;++i){
            insertIndex = i-1;
            insertValue = arr[i];
            while(insertIndex >= 0 && insertValue < arr[insertIndex]){
                arr[insertIndex+1] = arr[insertIndex];
                --insertIndex;
            }
            arr[insertIndex+1] = insertValue;
        }
    }

    public static void main(String[] args) {
        int[] arr = {101, 34, 119, 1, -1, 89};
        insertSort(arr);
        System.out.println(Arrays.toString(arr));
    }

}
