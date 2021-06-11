package DataStructure.Sort;

import java.util.Arrays;

/*
* 冒泡排序很容易理解的，比较相邻的两个元素并排序
* 达到的结果就是，每一趟排序，最起码保证最大的数据跑到了最后面去
* 在一趟遍历完全没有变化的时候就可以停止了
* */
public class BubbleSortTest {

    //还能再优化
    public static void bubbleSort(int[] arr){
        int length = arr.length;
        int temp;
        boolean isChanged;
        for(int i=1;i<length;++i){
            isChanged = false;
            for(int j=0;j<length-i;++j){
                if(arr[j] > arr[j+1]){
                    isChanged = true;
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
            if(!isChanged){
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {3, 9, -1, 10, -2};
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
