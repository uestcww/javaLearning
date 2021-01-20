package LeetCode.resolved.array;

public class No283 {

    // so easy 这就是easy题吗，i了i了
    public void moveZeroes(int[] nums) {
        int length = nums.length;
        // count负责表示前面的位置都被非0数字占好了，pointer是指针，遍历整个数组
        // pointer指向0，就直接后移，指向非0，就把当前元素移动到count的位置上
        // 最后把后面几位全部变为0
        int count = 0, pointer = 0;
        for(;pointer<length;++pointer){
            if(nums[pointer] == 0){
                continue;
            }
            if(count != pointer){
                nums[count] = nums[pointer];
            }
            ++count;
        }
        for(;count<length;++count){
            nums[count] = 0;
        }
    }

    // 看了官方题解，第一次觉得官方是傻逼，还swap个屁啊，都知道是0，最后补0不就好了

    public static void main(String[] args) {
        No283 obj = new No283();
        obj.moveZeroes(new int[]{1});
    }

}
