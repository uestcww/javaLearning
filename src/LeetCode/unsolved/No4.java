package LeetCode.unsolved;

public class No4 {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length ==0){
            if(nums2.length == 1){
                return (double)nums2[0];
            }
            return ((double)(nums2[(nums2.length-1)/2]) + (double)(nums2[nums2.length/2]))/2;
        }
        if (nums2 == null || nums2.length == 0){
            if(nums1.length == 1){
                return (double)nums1[0];
            }
            return ((double)(nums1[(nums1.length-1)/2]) + (double)(nums1[nums1.length/2]))/2;
        }
        int left1=0, right1=nums1.length-1, left2=0, right2=nums2.length-1;
        if(right1<=1&&left1<=1){
            int num10=nums1[0],num11,num20=nums2[0],num21;
            if(right1 + right2 == 0){
                return ((double)num10 + (double)num20)/2;
            }
            if(right1 + right2 ==2){
                num11 = nums1[1];
                num21 = nums2[1];
                if(num21>=num11){
                    if(num20<=num10){
                        return ((double)num10 + (double)num11)/2;
                    }else{
                        return ((double)num20 + (double)num11)/2;
                    }
                }else if(num21>=num10){
                    if(num10 >= num20){
                        return ((double)num10 + (double)num21)/2;
                    }else {
                        return ((double)num20 + (double)num21)/2;
                    }
                }else {
                    return ((double)num10 + (double)num21)/2;
                }
            }
            if(right1 == 0){
                num21 = nums2[1];
                if(num10>=num21){
                    return (double)num21;
                }else if(num10>=num20){
                    return (double)num10;
                }else {
                    return (double)num20;
                }
            }
            if(right2 == 0){
                num11 = nums1[1];
                if(num20>=num11){
                    return (double)num11;
                }else if(num20>=num10){
                    return (double)num20;
                }else {
                    return (double)num10;
                }
            }
        }
        while((left1<right1)&&(left2<right2)){
            if(nums1[left1]<=nums2[left2]){
                left1++;
            }else{
                left2++;
            }
            if(nums1[right1]>=nums2[right2]){
                right1--;
            }else{
                right2--;
            }
        }
        System.out.println("****************循环结束****************");
        System.out.println("nums1:" + left1 + "--" + right1);
        System.out.println("nums2:" + left2 + "--" + right2);
        if(left1>=right1){
            if(left1 == right1){
                if(nums1[left1] <= nums2[left2]){
                    right2--;
                }else if(nums1[left1] >= nums2[right2]){
                    left2++;
                }else {
                    System.out.println("S1处有错误");
                }
            }
            while (right2 - left2 > 1){
                right2--;
                left2++;
            }
            System.out.println("****************一短二长****************");
            System.out.println("nums2[" + left2 + "]="+nums2[left2]);
            System.out.println("nums2[" + right2 + "]="+nums2[right2]);
            return ((double)nums2[right2]+(double)nums2[left2])/2;
        }
        if(left2>=right2){
            if(left2 == right2){
                if(nums2[left2] <= nums1[left1]){
                    right1--;
                }else if(nums2[left2] >= nums1[right1]){
                    left1++;
                }else {
                    System.out.println("S2处有错误");
                }
            }
            while (right1 - left1 > 1){
                right1--;
                left1++;
            }
            System.out.println("****************一长二短****************");
            System.out.println("nums1[" + left1 + "]="+nums1[left1]);
            System.out.println("nums1[" + right1 + "]="+nums1[right1]);
            return ((double)nums1[right1]+(double)nums1[left1])/2;
        }
        System.out.println("S3处有错误");
        return -1;
    }

    public static void main(String[] args) {

//        int[] arr1 = new int[]{1,5,6,10,21,22,23,29,30};
//
//        /*/
//        /*12,15*/
//
//        int[] arr2 = new int[]{2,3,5,10,11,12,15,20,21,26,29};
//



        int[] nums1 = {1,2,2};
        int[] nums2 = {1,2,3};



        No4 obj = new No4();
        double result = obj.findMedianSortedArrays(nums1, nums2);
        System.out.println("result:" + result);



    }

}
