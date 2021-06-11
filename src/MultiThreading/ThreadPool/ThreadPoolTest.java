package MultiThreading.ThreadPool;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolTest {

    public static void main(String[] args) {

        AtomicInteger a = new AtomicInteger();

        ExecutorService executorService1 = Executors.newCachedThreadPool();
        ExecutorService executorService2 = Executors.newFixedThreadPool(10);
        ExecutorService executorService3 = Executors.newSingleThreadExecutor();
        ExecutorService executorService4 = Executors.newWorkStealingPool();

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20, 0, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(10));
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
