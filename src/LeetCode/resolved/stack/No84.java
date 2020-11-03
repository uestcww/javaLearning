package LeetCode.resolved.stack;

public class No84 {

    /*
    * 这题很强，这种单调栈的思路，第一次接触
    * 实际上核心就在于，你要找两个边界，但是常规做法是遍历
    * 你想优化，就必须有新的信息才可以，新信息必须由新的数据来提供
    * 也就是说，你在遍历的时候，一定要提取特殊信息并且保存下来
    * 下一次遍历的时候，你就可以利用新信息简化遍历操作
    * 遍历是为了找边界，边界的特点是第一个比当前柱子高度低的柱子
    * 想到这里，我的思路就断了，不知道接下来该怎么办了
    *
    * 官方的方案的核心在于，他找到了一个规律，并使用了栈
    * 这个规律就是，如果 j < i 且 heights[j] >= heights[i]
    * 那么 i 后面的节点找左边界时，一定不会是 j
    * 这个规律就决定了，遍历到 k 这个柱子的时候，我可以将所有栈中比我大的柱子弹出
    * 因为这个规律中说了，左边界一定不会是 j ，既然一定不是 j ，那 j 就没用，就可以将 j 弹出，简化遍历
    * 以后的柱子比 k 高，那 k 可能是左边界，且被 k 弹出的柱子一定不是左边界，因为被 k 拦住了
    * 以后的柱子比 k 矮或者与 k 一样，那 k 一定不是左边界，但是比 k 高的更不可能是了，而 k 只弹出了比 k 高的柱子，所以所有可能的左边界还都在栈里呢
    * 遍历到 k 的时候，弹出了之前的比 k 大的柱子，但是反过来想， k 是不是之前那些弹出来的柱子遇见的，第一个比它们小的柱子？
    * 比如 i 入栈了，如果 i + 1 比 i 小的话，那遍历到 i + 1 时，i 会马上被弹出，在遇见 k 的时候才被弹出，说明 k 是第一个比它们小的柱子
    * 而遍历又是从左到右的，也就是说，在他们右边且比他们小的柱子中， k 是最左边的那一个，那 k 肯定就是他们的右边界了
    * */

    // 最初的版本，虽然通过了，但是结果很差
    public int largestRectangleArea(int[] heights) {
        if(heights.length < 1){
            return 0;
        }
        int max = Integer.MIN_VALUE, length = heights.length;
        int current, left, right;
        // 穷举每一个柱子的高度，得出以每一个柱子高度为高的结果
        for(int i=0;i<length;++i){
            // 如果柱子太矮，就算宽度为length都不能超过当前最大值，那就没必要浪费时间了
            if(heights[i] * length <= max){
                continue;
            }
            // 左边界和右边界都初始化为当前位置
            left = i;
            right = i;
            // 找到左边界
            while(left-1 >= 0 && heights[left-1] >= heights[i]){
                --left;
            }
            // 找到右边界
            while(right+1 <length && heights[right+1] >= heights[i]){
                ++right;
            }
            // 计算当前的值
            current = (right - left + 1) * heights[i];
            // 当前值与当前最大值比较，看看是否更新最大值
            if(current > max){
                max = current;
            }
        }
        return max;
    }

    // 尝试使用栈的思想，失败了，不对
    public int largestRectangleArea2(int[] heights) {
        if(heights.length < 1){
            return 0;
        }
        int max = heights[0], length = heights.length;
        int current = heights[0], count = 1, currentHeight = heights[0];
        for(int i=1;i<length;++i){
            if(heights[i] >= heights[i-1]){
                current += currentHeight;
                if(heights[i] >= current){
                    current = heights[i];
                    count = 1;
                    currentHeight = heights[i];
                }else{
                    ++count;
                }
            }else{
                ++count;
                current = heights[i];
                for(int j=i-count;j>=0;--j){
                    if(heights[j] >= currentHeight){
                        ++count;
                    }
                }
                currentHeight = heights[i];
                current = currentHeight * count;
            }
            if(max < current){
                max = current;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        No84 obj = new No84();
        System.out.println("10 ? " + obj.largestRectangleArea(new int[]{2, 1, 5, 6, 2, 3}));
        System.out.println("9 ? " + obj.largestRectangleArea(new int[]{1, 2, 3, 4, 5}));
    }

}
