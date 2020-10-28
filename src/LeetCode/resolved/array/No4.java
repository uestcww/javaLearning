package LeetCode.resolved.array;

public class No4 {

    // 官方的二分查找挺秒的
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int left1 = 0, left2 = 0, right1 = nums1.length-1, right2 = nums2.length-1;
        while(left1 <= right1 && left2 <= right2 && !(left1 == right1 && left2 == right2)){
            if(nums1[left1] >= nums2[left2]){
                left2++;
            }else{
                left1++;
            }
            if(nums1[right1] <= nums2[right2]){
                right2--;
            }else{
                right1--;
            }
        }
        // 数组1中已经没有数据了，全在数组2中
        if(left1 > right1){
            int sum2 = left2 + right2;
            return sum2%2 == 0 ? nums2[sum2/2]:(double)(nums2[(sum2-1)/2] + nums2[(sum2+1)/2])/2;
        }
        // 数组2中没有数据了
        if(left2 > right2){
            int sum1 = left1 + right1;
            return sum1%2 == 0 ? nums1[sum1/2]:(double)(nums1[(sum1-1)/2] + nums1[(sum1+1)/2])/2;
        }
        // 最后一种情况就是 left1 == right1 && left2 == right2 这时要结合两个数组中的值
        return (double)(nums1[left1] + nums2[left2])/2;
    }

    public static void main(String[] args) {
        No4 obj = new No4();
        int[] nums1 = {1, 3};
        int[] nums2 = {2};
        System.out.println("2.00000 ? " + obj.findMedianSortedArrays(nums1, nums2));
        int[] nums3 = {1, 2};
        int[] nums4 = {3, 4};
        System.out.println("2.50000 ? " + obj.findMedianSortedArrays(nums3, nums4));
        int[] nums5 = {0, 0};
        int[] nums6 = {0, 0};
        System.out.println("0.00000 ? " + obj.findMedianSortedArrays(nums5, nums6));
        int[] nums7 = {};
        int[] nums8 = {1};
        System.out.println("1.00000 ? " + obj.findMedianSortedArrays(nums7, nums8));
        int[] nums9 = {2};
        int[] nums10 = {};
        System.out.println("2.00000 ? " + obj.findMedianSortedArrays(nums9, nums10));
    }
}
