package DataStructure.Sort;

import java.util.Arrays;

public class MergeSortTest {

    public static void merge(int[] arr, int left, int middle, int right){
        int length = right - left + 1, index = 0, low = left, high = middle + 1;
        int[] temp = new int[length];
        while(low <= middle && high <= right){
            temp[index++] = arr[low] <= arr[high] ? arr[low++] : arr[high++];
        }
        while(low <= middle){
            temp[index++] = arr[low++];
        }
        while(high <= right){
            temp[index++] = arr[high++];
        }
        for(int i = 0; i < length; ++i){
            arr[left++] = temp[i];
        }
    }

    public static void mergeSortRecursion(int[] arr, int left, int right){
        if(left == right){
            return;
        }
        int middle = (left + right) / 2;
        mergeSortRecursion(arr, left, middle);
        mergeSortRecursion(arr, middle + 1, right);
        merge(arr, left, middle, right);
    }

    public static void MergeSortIteration(int arr[], int len){
        int left, mid, right;
        for(int i = 1; i < len; i *= 2){
            left = 0;
            while(left + i < len){
                mid = left + i - 1;
                right = mid + i < len ? mid + i : len - 1;
                merge(arr, left, mid, right);
                left = right + 1;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};
        int length = arr.length;
        int[] temp = new int[length];
        mergeSortRecursion(arr, 0, length-1);
        System.out.println(Arrays.toString(arr));
    }

}
