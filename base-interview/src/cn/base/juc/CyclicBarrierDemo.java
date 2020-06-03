package cn.base.juc;

import java.util.concurrent.CyclicBarrier;

/**
 * @author duce
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(2, () -> {
            System.out.println("===================================== 获取经销商列表 ");
            System.out.println("===================================== 获取服务商列表 ");
        });

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "  ########## 车架号查车型信息");
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "  ########## 开始估值信息");

            },"33333").start();
            try {
                cyclicBarrier.await();
            } catch (Exception e) {
                System.out.println("error message ");
            }
        }, "t am thread " + 1).start();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "  ########## 车价格评估");
            try {
                cyclicBarrier.await();
            } catch (Exception e) {
                System.out.println("error message ");
            }
        }, "t am thread " + 2).start();
    }
}
