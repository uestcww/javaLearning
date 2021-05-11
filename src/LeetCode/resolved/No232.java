package LeetCode.resolved;

import java.util.ArrayDeque;
import java.util.Deque;

// 这题没啥意思，就是两个栈来回倒，我以为有什么高深的办法呢
public class No232 {

    static class MyQueue {
        private Deque<Integer> positiveStack;
        private Deque<Integer> negativeStack;
        private boolean isPositive;
        private int count;

        public MyQueue() {
            positiveStack = new ArrayDeque<>();
            negativeStack = new ArrayDeque<>();
            isPositive = true;
            count = 0;
        }
        public void push(int x) {
            if(!isPositive){
                while(!negativeStack.isEmpty()){
                    positiveStack.push(negativeStack.pop());
                }
                isPositive = true;
            }
            positiveStack.push(x);
            ++count;
        }
        public int pop() {
            if(isPositive){
                while(!positiveStack.isEmpty()){
                    negativeStack.push(positiveStack.pop());
                }
                isPositive = false;
            }
            --count;
            return negativeStack.pop();
        }
        public int peek() {
            if(isPositive){
                while(!positiveStack.isEmpty()){
                    negativeStack.push(positiveStack.pop());
                }
                isPositive = false;
            }
            return negativeStack.peek();
        }
        public boolean empty() {
            if(count <= 0){
                return true;
            }else{
                return false;
            }
        }
    }

    public static void main(String[] args) {
        MyQueue obj = new MyQueue();
        obj.push(1);
        obj.push(2);

        System.out.println("1 ? " + obj.peek());
        System.out.println("1 ? " + obj.pop());
        System.out.println("false ? " + obj.empty());



    }



}
