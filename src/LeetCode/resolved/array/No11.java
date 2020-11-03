package LeetCode.resolved.array;

public class No11 {

    // 最优的方法是双指针，先指向两头，然后向中间移动，我这个还不是最优的方法
    public int maxArea(int[] height) {
        int length = height.length;
        int max=0;
        int temp,tempLeft,tempRight;
        for(int i=0;i<length;i++){
            for(int j=i+1;j<length;j++){
                tempLeft = height[i];
                tempRight = height[j];
                temp = (j-i) * Math.min(tempLeft, tempRight);
                if(temp > max){
                    max = temp;
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        No11 obj = new No11();
        int[] height = {1,8,6,2,5,4,8,3,7};
        System.out.println(obj.maxArea(height));
    }

}
