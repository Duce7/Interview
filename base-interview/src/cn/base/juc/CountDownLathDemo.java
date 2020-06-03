package cn.base.juc;

import java.util.concurrent.CountDownLatch;

/**
 * 1.创建CountDownLatch（） 初始化count
 * 2.完成相应任务时 调用countDown()方法，减去数量
 * 3.使用await（）方法，并在方法和定义所以任务完成后的 事件
 */
public class CountDownLathDemo {
    private static volatile int num = 0;

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(3*7*7);

        ThreadPools.executor(3,() ->{
            System.out.println(Thread.currentThread().getName() +"  ########## 获取品牌信息");
            ThreadPools.executor(7,() -> {
                System.out.println(Thread.currentThread().getName() +"  ########## 获取车系信息");
                ThreadPools.executor(7,() -> {
                    System.out.println(Thread.currentThread().getName() +"  ########## "+ num++ +"获取车型信息");
                    countDownLatch.countDown();
                });
//                countDownLatch.countDown();
            });
//            countDownLatch.countDown();
        });
        try {
            countDownLatch.await();
            System.out.println("=========================" + 3*7*7 +"====" + num);
            ThreadPools.shutdown();
            System.out.println(Thread.currentThread().getName() +"  ########## task finished over");
        } catch (Exception e) {
            System.out.println("error message ");
        }
    }
}
