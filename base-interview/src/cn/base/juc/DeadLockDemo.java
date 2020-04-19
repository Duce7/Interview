package cn.base.juc;

import java.util.concurrent.TimeUnit;

class LockThread extends Thread{
    String lock1;
    String lock2;

    public LockThread(String lock1, String lock2) {
        this.lock1 = lock1;
        this.lock2 = lock2;
    }
    @Override
    public void run(){
        synchronized (lock1) {
            System.out.println(Thread.currentThread().getName()+" 持有"+ lock1);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock2) {
                System.out.println(Thread.currentThread().getName()+" 持有"+ lock2);
            }
        }
    }

}

public class DeadLockDemo {
    public static void main(String[] args) {
        LockThread lockThread1 = new LockThread("lock1", "lock2");
        LockThread lockThread2 = new LockThread("lock2", "lock1");
        lockThread1.start();
        lockThread2.start();
    }
}
