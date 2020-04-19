package cn.base.juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import static java.util.concurrent.Executors.*;

/**
 * @author duce
 */
public class ExecutorDemo {
    private static ExecutorService executorService = newFixedThreadPool(20);

    public static void main(String[] args) {


        for (int i = 0; i < 500; i++){
//            ExecutorService executorService = new ThreadPoolExecutor(2,3,
//                    5000,TimeUnit.MILLISECONDS,new ArrayBlockingQueue<Runnable>(5));
            executorService.execute(()->{
                try {

//                    System.out.println("#######  "+Thread.currentThread().getName() + "######## parked ,yes");
                    Thread.sleep(3000);
                    System.out.println("#######  "+Thread.currentThread().getName() + "++++++++ start out  "+Thread.activeCount());

                } catch (InterruptedException e) {
                    System.out.println("error");
                }
            });

        }
        executorService.shutdown();
    }
}
