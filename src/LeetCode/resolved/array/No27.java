package LeetCode.resolved.array;

public class No27 {

    // 这种题做吐了，没什么意思，就是两个指针就好了
    public int removeElement(int[] nums, int val) {
        // 照常判断一下长度
        int length = nums.length;
        if(length < 1){
            return 0;
        }
        // 两个指针，count前面都是保留的数据，指向的是下一个数据的存放位置
        // pointer就是指针，遍历整个数组
        int count = 0, pointer = 0;
        for(;pointer<length;++pointer){
            // 当前如果就是val，那说实话啥也不用做，count就指向当前位置，pointer当前位置也是可以随意覆盖的，毕竟是val
            // 当前不是val，那这个数据要保留，把它移动到count位置上去，count此时需要后移一位
            if(nums[pointer] != val){
                nums[count] = nums[pointer];
                ++count;
            }
        }
        return count;
    }

    // 官方的另一种思路的解法，可以借鉴
    // 注意，题目要求保留的数据的顺序是可以任意的，所以这里就是判断当前是否为val
    // 如果是，就用最后面的元素覆盖它，然后重新检查当前位置（当前位置已经变为了别的数据）
    public int removeElementLeetCode(int[] nums, int val) {
        int i = 0;
        int n = nums.length;
        while (i < n) {
            if (nums[i] == val) {
                nums[i] = nums[n - 1];
                // reduce array size by one
                n--;
            } else {
                i++;
            }
        }
        return n;
    }

    public static void main(String[] args) {
        No27 obj = new No27();
        System.out.println("2 ? " + obj.removeElement(new int[]{3, 2, 2, 3}, 3));
        System.out.println("5 ? " + obj.removeElement(new int[]{0, 1, 2, 2, 3, 0, 4, 2}, 2));
    }

}
