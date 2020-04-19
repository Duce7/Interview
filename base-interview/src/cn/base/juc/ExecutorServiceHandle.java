package cn.base.juc;

import java.util.concurrent.*;

/**
 * 自定义线程池
 * @author Duce
 */
public class ExecutorServiceHandle {

    private static ExecutorService executePool;

    public static ExecutorService getExecutePool() {
        return new ThreadPoolExecutor(2, 16,
                2L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(12), Executors.defaultThreadFactory(),new ThreadPoolExecutor.CallerRunsPolicy() );
    }
}
