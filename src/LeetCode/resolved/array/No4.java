package LeetCode.resolved.array;

public class No4 {

    // 官方的二分查找挺妙的，这是我自己的版本
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 四个指针，初始化指向两个数组的两端
        int left1 = 0, left2 = 0, right1 = nums1.length-1, right2 = nums2.length-1;
        // left <= right 表示数组中最起码还有一个元素，遍历直到一个数组为空时
        // 特殊情况就是两个数组中各剩一个元素，这种情况是无论如何都要结束循环的
        // 我想了好久，这个循环条件能怎么简化，但是都想不出来，只能直白的这么写了
        while(left1 <= right1 && left2 <= right2 && !(left1 == right1 && left2 == right2)){
            // 这个循环中保证了每次循环都是移动两个指针，且一个左移，一个右移
            // 含义就是每次循环都只淘汰一个最大值和一个最小值，毕竟是求中位数，要注意两边淘汰的数量要相等
            // 毕竟开始就是正序数组，所以方便很多，比较两个数组的最左边元素，谁小谁的指针右移
            if(nums1[left1] >= nums2[left2]){
                left2++;
            }else{
                left1++;
            }
            // 比较最右边，谁大谁左移
            if(nums1[right1] <= nums2[right2]){
                right2--;
            }else{
                right1--;
            }
        }
        // 数组1中已经没有数据了，全在数组2中，这时候只需要单独处理数组2，又是正序的数组，所以就输出中间值就好
        if(left1 > right1){
            int sum2 = left2 + right2;
            // 中间值可能有两个也可能有一个，所以要讨论一下，这里用了一个问号表达式
            return sum2%2 == 0 ? nums2[sum2/2]:(double)(nums2[(sum2-1)/2] + nums2[(sum2+1)/2])/2;
        }
        // 数组2中没有数据了，这里对数组1的操作同上面对数组2的操作
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
