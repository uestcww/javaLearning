package MultiThreading;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Test {

    public static int a;
    public static int b;
    public static int c;

    public static int func(){
        try {
            a = 3;
            b = 5;
            c = 12;
            System.out.println("before return, c = " + c);
            return (c=a+b);
        }finally {
            System.out.println("in finally, c = " + c);
            return 100;
        }
    }

    public static void main(String[] args) {

    }

}
