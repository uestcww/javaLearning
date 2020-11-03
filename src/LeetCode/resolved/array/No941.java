package LeetCode.resolved.array;

public class No941 {

    // 读清题目，首先长度要大于等于3，然后要有一个山顶，而且不能有平地，即不能出现 A[i] == A[i+1] 这种情况
    // 用时1毫秒击败100%，但是内存消耗39.9，击败13.21%，莫非是因为我保存了数组长度？
    // 事实证明是的，我删除了那个局部变量，瞬间变为了内存消耗39.2，击败97.52%
    public boolean validMountainArray(int[] A) {
        // 如果数组中元素低于三个，直接返回 false
        if(A.length < 3){
            return false;
        }
        // i是指针，遍历数组，length就是数组长度
        int i = 0, length = A.length;
        // 首先往山上走，遇见平地、大于长度、走到最高处，三种情况都会退出循环
        // 当然我们只要走到最高处一种情况，其他的都直接返回 false
        while(i < length-1 && A[i+1] > A[i]){
            ++i;
        }
        // 这里返回 false 有三种情况，除了上面说的遇见平地和大于长度
        // 如果还没开始就停了，那就说明一直在下坡，也要返回 false
        if(i >= length-1 || i <= 0 || A[i+1] == A[i]){
            return false;
        }
        // 走到山顶了，要往山下走了，只要下一步比现在这一步要低，就走
        // 这就意味着，如果出现问题，就走不到最后一个元素那里
        // 那么判断条件就很明显了
        while(i < length-1 && A[i+1] < A[i]){
            ++i;
        }
        // 如果通过上面的循环，还能走到最后一个元素，那么就是 true 反之就是 false
        return i >= length - 1;
    }

}
