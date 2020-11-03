package LeetCode.resolved.stack;

import java.util.Arrays;

public class No155 {

    // 看了题解，怎么感觉不太对，我自己用数组实现的栈，怎么结果大家都直接用什么Deque ArrayList什么的
    // 我蒙了
    static class MinStack {

        // 存储数据的数组
        private int[] elementData;
        // 初始数组长度，以后东西多了要扩张的
        private static final int INITIAL_CAPACITY = 10;
        // 栈顶指针，一直指着栈顶元素，而不是栈顶元素的后一个位置
        private int pointer;
        // 保存最小值的栈，这样可以保证出栈操作后，最小值不会丢失
        private int[] mins;

        public MinStack() {
            // 初始化，两个数组都先申请初始长度，指针指向不存在的位置，因为是栈是空的
            elementData = new int[INITIAL_CAPACITY];
            mins = new int[INITIAL_CAPACITY];
            pointer = -1;
        }

        public void push(int x) {
            // 如果数组满了，就需要扩充了
            if(++pointer >= elementData.length){
                grow(pointer);
            }
            // 将新元素存入栈内
            elementData[pointer] = x;
            // 更新最小值，第一个最小值是直接写进去，后面的都要与前一个最小值比较
            if(pointer == 0){
                mins[pointer] = x;
            }else{
                mins[pointer] = Math.min(mins[pointer - 1], x);
            }
        }

        public void grow(int minCapacity){
            // 就的数组长度
            int oldCapacity = elementData.length;
            // 扩充到之前的1.5倍
            int newCapacity = oldCapacity + (oldCapacity >> 1);
            // 如果需要的最小长度更大，就扩充到这个值
            // 这里是ArrayList的实现逻辑，其实这里并不需要这么写，我就图省事就这么写了
            if (newCapacity - minCapacity < 0)
                newCapacity = minCapacity;
            // 使用这个方法，复制数组
            elementData = Arrays.copyOf(elementData, newCapacity);
            mins = Arrays.copyOf(mins, newCapacity);
        }
        // pop就直接将指针移动就好了
        public void pop() {
            --pointer;
        }
        // 指针会一直指向栈顶元素，直接返回就好
        public int top() {
            return elementData[pointer];
        }

        public int getMin() {
            return mins[pointer];
        }
    }

    public static void main(String[] args) {
        MinStack obj = new MinStack();
        obj.push(-2);
        obj.push(0);
        obj.push(-3);
        System.out.println(obj.getMin());
        obj.pop();
        System.out.println(obj.top());
        System.out.println(obj.getMin());
    }

}
