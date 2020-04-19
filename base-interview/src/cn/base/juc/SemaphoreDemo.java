package cn.base.juc;

import java.util.concurrent.*;

/**
 * @author duce
 */
public class SemaphoreDemo {
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Semaphore semaphore = new Semaphore(2);
        for (int i = 0; i < 20; i++){
//            ExecutorService executorService = new ThreadPoolExecutor(2,3,
//                    5000,TimeUnit.MILLISECONDS,new ArrayBlockingQueue<Runnable>(5));
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    System.out.println("#######  "+Thread.currentThread().getName() + "######## parked ,yes");
                    Thread.sleep(3000);
                    System.out.println("#######  "+Thread.currentThread().getName() + "++++++++ start out  ");

                } catch (InterruptedException e) {
                    System.out.println("error");
                } finally {
                    semaphore.release();
                }
            });

        }
        executorService.shutdown();
    }
}
