package MultiThreading.unsynch;

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

        AtomicInteger a = new AtomicInteger();

        ExecutorService executorService1 = Executors.newCachedThreadPool();
        ExecutorService executorService2 = Executors.newFixedThreadPool(10);
        ExecutorService executorService3 = Executors.newSingleThreadExecutor();
//
//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20, 0, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(10));
        System.out.println("before retry");
        retry:
        for(;;){
            System.out.println("just enter for");
            int i=0;
            for(;i<10;++i){
                if(i == 5){
                    break retry;
                }
            }
        }


    }

}
