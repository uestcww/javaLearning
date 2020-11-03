package LeetCode.resolved;

public class No42 {

    /*
    * 初见思路，保存最左边界，遇见大于等于左边界的就计算，遇不着就往后遍历
    * 遍历到底后，将最左边界+1，接着找
    * 问题在于，在 height = [ 4, 2, 3 ] 中，应该左边界不动，使用右边界计算
    *
    * 这个没走通
    *
    * */
    public int trap(int[] height) {
        int length = height.length;
        if(length <= 1){
            return 0;
        }
        int pointer = 0, middleCount, minHeight, minPointer, resultCount = 0, width;
        while(height[pointer] <= 0){
            pointer++;
        }
        minHeight = height[pointer];
        minPointer = pointer;
        pointer++;
        while(pointer < length){
            width = 0;
            middleCount = 0;
            while(pointer < length && height[pointer] < minHeight){
                middleCount+=height[pointer];
                width++;
                pointer++;
            }
            if(pointer >= length){
                pointer = ++minPointer;
                minHeight = height[pointer];
                continue;
            }
            resultCount += width * minHeight - middleCount;
            minHeight = height[pointer];
            minPointer = pointer;
            pointer++;
        }
        return resultCount;
    }

    /*
    * 二次思路
    * 先做筛选，保证最左边最右边都是极高点，令最左为left，最右为right
    * 使 pointer = left + 1
    *
    * 与官方解答第四种方法相同，双指针
    *
    * */
    public int trap2(int[] height){
        int length = height.length;
        //数组最少也得有三个元素，不然没办法接水
        if(length <= 2){
            return 0;
        }
        int left = 0, right = length-1;
        //找到左边的极高点，如果是阶梯型或者平面也没办法接水
        while(left+1 < length && height[left] <= height[left+1]){
            left++;
        }
        //找右边的极高点
        while(right-1 >= 0 && height[right] <= height[right-1]){
            right--;
        }
        int waterCount = 0;
        int width, middleCount, minHeight;
        //如果两个极高点都挨着了，或者都是平地，left > right 了，那就不能接水，接水量为0
        while(right - left > 1){
            //初始化变量
            width = 0;
            middleCount = 0;
            //每次选较低的开始，left和right在中间汇合
            if(height[left] <= height[right]){
                minHeight = height[left];
                left++;
                //找到第一个比它高的，中间肯定能蓄水
                while(left <= right && height[left] < minHeight){
                    middleCount+=height[left];
                    width++;
                    left++;
                }
                //肯定能找到了一个比它高的，至少是一样高的，因为它比右边低
            }else{
                minHeight = height[right];
                right--;
                //同右边的情况，找到第一个比它高的，中间肯定能蓄水
                while(left <= right && height[right] < minHeight){
                    middleCount+=height[right];
                    width++;
                    right--;
                }
                //肯定能找到了一个比它高的，至少是一样高的，因为它比右边低
            }
            waterCount += width * minHeight - middleCount;
        }
        return waterCount;
    }

    public static void main(String[] args) {
        No42 obj = new No42();
        int[] height = {4, 2, 3};
        System.out.println(obj.trap2(height));
    }


}
