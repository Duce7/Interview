package cn.base.juc;

import java.util.concurrent.ExecutorService;

/** 多线程的单例模式实现 DCL + volatile
 * @author Duce
 */
public class SingletonDemo {
    private static volatile SingletonDemo instance = null;

    private SingletonDemo() {
        System.out.println("构造  new instance");
    }

    public static SingletonDemo getInstance() {
        if (null == instance) {
            synchronized (SingletonDemo.class) {
                if (null == instance) {
                    instance = new SingletonDemo();
                }
            }
        }
        return instance;
    }

    @Override
    public String toString() {
        return "SingletonDemo{}";
    }

    public static void main(String[] args) {
        ExecutorService executePool = ExecutorServiceHandle.getExecutePool();
        try {
            int i =1;
            while (i <15) {
                executePool.execute(()->{
                    SingletonDemo instance = SingletonDemo.getInstance();
                    System.out.println("i"+instance);
                });
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executePool.shutdown();
        }
    }
}
