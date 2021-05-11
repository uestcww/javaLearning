package DataStructure;

public class Sort {

    public static void quickSort(int[] array) {
        int len;
        if(array == null || (len = array.length) == 0 || len == 1) {
            return;
        }
        sort2(array, 0, len - 1);
    }

    public static void sort(int[] array, int left, int right) {
        if(left > right) {
            return;
        }
        int base = array[left];
        int i = left, j = right;
        while(i != j) {
            while(array[j] >= base && i < j) {
                j--;
            }
            while(array[i] <= base && i < j) {
                i++;
            }
            if(i < j) {
                int tmp = array[i];
                array[i] = array[j];
                array[j] = tmp;
            }
        }
        array[left] = array[i];
        array[i] = base;
        sort(array, left, i - 1);
        sort(array, i + 1, right);
    }

    public static void sort2(int[] array, int low, int high){
        if(low >= high){
            return;
        }
        int left = low, right = high;
        int key = array[left];
        while(left < right){
            while(left < right && array[right] >= key){
                --right;
            }
            array[left] = array[right];
            while(left < right && array[left] <= key){
                ++left;
            }
            array[right] = array[left];
        }
        array[left] = key;
        sort2(array, low, left-1);
        sort2(array, left+1, high);
    }


    public static void main(String[] args) {
        int[] testArr = {2, 5, 45, 18, 37, 76, 44, 13, 44, 45, 12, 64, 53, 43, 16, 24, 36, 22, 83};
        Sort.quickSort(testArr);
        for(int a : testArr){
            System.out.print(a + " ");
        }
    }
}
