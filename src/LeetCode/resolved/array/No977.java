package LeetCode.resolved.array;

import java.util.Arrays;

public class No977 {

    //双指针也能做，感觉更好一些
    public int[] sortedSquares(int[] A) {
        for(int i=0;i<A.length;i++){
            A[i] = A[i] * A[i];
        }
        Arrays.sort(A);
        return A;
    }

    public static void main(String[] args) {
        No977 obj = new No977();
        int[] a = {-4,-1,0,3,10};
        int[] b = {-7,-3,2,3,11};
        for(int element : obj.sortedSquares(b)){
            System.out.println(element);
        }

    }

}
