package LeetCode.solving;

public class No1035 {

    // 我就知道，肯定超出时间限制
    private int max;
    private int current;
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        max = 0;
        current = 0;
        backtrack(nums1, 0, nums1.length-1, nums2, 0, nums2.length-1);
        return max;
    }

    public void backtrack(int[] nums1, int left1, int right1, int[] nums2, int left2, int right2){
        if(left1 > right1 || left2 > right2){
            return;
        }
        for(int i=left1;i<=right1;++i){
            for(int j=left2;j<=right2;++j){
                if(nums1[i] == nums2[j]){
                    ++current;
                    backtrack(nums1, left1, i-1, nums2, left2, j-1);
                    backtrack(nums1, i+1, right1, nums2, j+1, right2);
                    if(current > max){
                        max = current;
                    }
                    --current;
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(2 == new No1035().maxUncrossedLines(new int[]{1, 4, 2}, new int[]{1, 2, 4}));
        System.out.println(3 == new No1035().maxUncrossedLines(new int[]{2,5,1,2,5}, new int[]{10,5,2,1,5,2}));
        System.out.println(2 == new No1035().maxUncrossedLines(new int[]{1,3,7,1,7,5}, new int[]{1,9,2,5,1}));
    }



}
