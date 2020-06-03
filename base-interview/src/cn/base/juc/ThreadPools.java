package cn.base.juc;

import java.util.concurrent.*;

public class ThreadPools {
    private static volatile ThreadPools threadPools = null;

    //corePoolsSize
    public static final int corePoolSize = 1;

    public static final int maxPoolSize = 4;

    public static final long keepAliveTime = 60L;


    public static final TimeUnit unit = TimeUnit.SECONDS;

    public static final ArrayBlockingQueue<Runnable> arrayBlockingQueue = new ArrayBlockingQueue(3);

    public static ThreadPoolExecutor threadPoolExecutor = null;

    private ThreadPools() {
        Executors.newSingleThreadExecutor();
        threadPoolExecutor = new ThreadPoolExecutor(corePoolSize,maxPoolSize,keepAliveTime,unit,arrayBlockingQueue, Executors.defaultThreadFactory(),new ThreadPoolExecutor.CallerRunsPolicy());
    }

    public static void  executor(int num, Runnable runnable) {
        if (num <= 0) {
            return;
        }
        //DCL双端检锁机制
        if (null == threadPools) {
            synchronized (ThreadPools.class) {
                if (null == threadPools) {
                    threadPools = new ThreadPools();
                }
            }
        }

        for (int i = 0; i < num; i++) {
            threadPoolExecutor.execute(runnable);
        }

//        threadPoolExecutor.shutdown();
    }

    public static void shutdown() {
        if (null != threadPools) {
            threadPoolExecutor.shutdown();
        }
    }
}
