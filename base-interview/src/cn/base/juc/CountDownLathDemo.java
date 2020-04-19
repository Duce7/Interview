package cn.base.juc;

import java.util.concurrent.CountDownLatch;

/**
 * @author duce
 */
public class CountDownLathDemo {
    public static void main(String[] args) {

        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i<10; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() +"  ########## get info from che300 star");
                countDownLatch.countDown();
            },"t am thread "+i).start();
        }
        try {
            countDownLatch.await();
            System.out.println(Thread.currentThread().getName() +"  ########## task finished over");
        } catch (Exception e) {
            System.out.println("error message ");
        }
    }
}
