package CoreJava.Volume1.Chapter6.InnerClass;

import java.util.ArrayList;

/*
* 在编程的过程中，我发现使用 return new ArrayList<Integer>(){{add(12);}}; 很方便
* 但是，根据 core java 一书第六章，255页，这种操作相当于声明了一个 ArrayList 的匿名子类
* 这就使得，如果 equals 方法中对比了两个对象的 class ，那就可能出问题
* */
public class DoubleBraceTest {

    public static ArrayList<Integer> test1(){
        return new ArrayList<Integer>();
    }

    public static ArrayList<Integer> test2(){
        return new ArrayList<Integer>(){{add(1);add(2);add(8);}};
    }

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(10);
        System.out.println("注意以下三个输出的异同");
        System.out.println(list.getClass());
        System.out.println(DoubleBraceTest.test1().getClass());
        System.out.println(DoubleBraceTest.test2().getClass());
    }

}
