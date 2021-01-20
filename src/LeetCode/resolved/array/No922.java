package LeetCode.resolved.array;

import java.util.LinkedList;
import java.util.Queue;

public class No922 {
    // 其实用了双指针，就不需要队列了，可以使用双指针的思想来进一步改进
    public int[] sortArrayByParityII(int[] A) {
        // 存放索引与内容奇偶不同的索引值
        Queue<Integer> queue = new LinkedList<Integer>();
        int pointer = 0, length = A.length;
        // 遍历整个数组
        while(pointer < length){
            // 不同就处理，相同就跳过
            if(A[pointer] % 2 != pointer % 2){
                int temp;
                // 如果队列为空，那么当前就是第一个遇见的错误，直接加入队列中
                // 如果队列不空，两种情况，一种是里面全是与自己相同的错误，此时加入队列
                // 第二种情况是不同错误，那就拿出来一个，互换数据，两个位置就都正确了
                if(queue.isEmpty() || pointer % 2 == queue.peek() % 2){
                    queue.offer(pointer);
                }else{
                    temp = A[pointer];
                    A[pointer] = A[queue.peek()];
                    A[queue.poll()] = temp;
                }
            }
            ++pointer;
        }
        return A;
    }
    public static void main(String[] args) {
        No922 obj = new No922();
        for(int x : obj.sortArrayByParityII(new int[]{ 4, 2, 5, 7})){
            System.out.print( x + ", ");
        }
    }
}
