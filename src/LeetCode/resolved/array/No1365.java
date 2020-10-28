package LeetCode.resolved.array;

public class No1365 {

    public int[] smallerNumbersThanCurrent(int[] nums) {
        int length = nums.length;
        int[] res = new int[length];
        for(int i=0;i<length-1;i++){
            for(int j=i+1;j<length;j++){
                if(nums[i] > nums[j]){
                    res[i]++;
                }else if(nums[i] < nums[j]){
                    res[j]++;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        No1365 obj = new No1365();
        int[] nums1 = {6, 5, 4, 8};
        for(int i : obj.smallerNumbersThanCurrent(nums1)){
            System.out.print(i + " ");
        }
        System.out.println();
        int[] nums2 = {7, 7, 7, 7};
        for(int i : obj.smallerNumbersThanCurrent(nums2)){
            System.out.print(i + " ");
        }
    }

}
